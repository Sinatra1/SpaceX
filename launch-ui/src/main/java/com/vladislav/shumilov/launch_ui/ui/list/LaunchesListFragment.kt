package com.vladislav.shumilov.launch_ui.ui.list

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.requestAppUpdateInfo
import com.vladislav.shumilov.core_ui.ui.list_with_detail.BaseListFragment
import com.vladislav.shumilov.core_ui.ui.list_with_detail.BaseListWithDetail
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_ui.di.modules.CoreViewModelFactory
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.launch_ui.R
import com.vladislav.shumilov.launch_ui.app
import com.vladislav.shumilov.launch_ui.databinding.LaunchesListBinding
import com.vladislav.shumilov.launch_ui.ui.detail.LaunchDetailFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val LAUNCHES_LIST_POOL_SIZE = 15

@FragmentScope
class LaunchesListFragment : Fragment(), BaseListFragment {

    companion object {
        fun newInstance() = LaunchesListFragment()
    }

    @Inject
    lateinit var viewModelFactory: CoreViewModelFactory

    private lateinit var binding: LaunchesListBinding

    private val viewModel: LaunchesListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(LaunchesListViewModel::class.java)
    }

    private var launchesListAdapter: LaunchesListAdapter? = null
    private var inProcess = false
    private var isLastPage = false
    private var compositeDisposable: CompositeDisposable? = null
    private val handler = Handler()
    private lateinit var navController: NavController

    private val appUpdateManager by lazy { AppUpdateManagerFactory.create(requireActivity()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAnimation()

        app()?.createLaunchComponent()?.inject(this)

        viewModel.getLaunchesForList()

        CoroutineScope(IO).launch {
            runCatching {
                appUpdateManager.requestAppUpdateInfo().updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
            }.onSuccess { isUpdateAvailable ->
                CoroutineScope(Main.immediate).launch {
                    if (isUpdateAvailable) {
                        Toast.makeText(requireContext(), R.string.launches_app_new_version, Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(requireContext(), R.string.launches_app_up_to_date, Toast.LENGTH_LONG).show()
                    }
                }
            }.onFailure { error ->
                    CoroutineScope(Main.immediate).launch {
                        Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        DataBindingUtil.inflate<LaunchesListBinding>(
            inflater,
            R.layout.launches_list,
            container,
            false
        ).apply {
            binding = this
            viewModel = this@LaunchesListFragment.viewModel
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        viewModel.setNavController(navController)

        setListAdapter()
    }

    override fun onStart() {
        super.onStart()

        setLiveDataListeners()
    }

    override fun onResume() {
        super.onResume()

        compositeDisposable = CompositeDisposable()

        launchesListAdapter?.let {
            compositeDisposable?.add(it.onClickViewHolderCallback.subscribe { (view, launchList) ->
                transmitSelectedItemId(launchList.launch.id, view)
            })
        }
    }

    override fun onPause() {
        super.onPause()

        compositeDisposable?.dispose()
        compositeDisposable = null
    }

    override fun onDestroyView() {
        super.onDestroyView()

        app()?.clearLaunchComponent()
    }

    override fun showDetailFragment(itemId: String, transitionView: View?) {
        handler.post {
            viewModel.showLaunchDetailFragment(itemId, transitionView)
        }
    }

    override fun selectListRow(itemId: String) {
        launchesListAdapter?.selectItem(itemId)
    }

    private fun setAnimation() {
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.slide_bottom)
                .apply {
                    duration = LaunchDetailFragment.ANIMATION_DURATION
                }
        exitTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.fade).apply {
                duration = LaunchDetailFragment.ANIMATION_DURATION
            }
    }

    private fun setListAdapter() {
        launchesListAdapter = LaunchesListAdapter(requireContext())
        binding.launchesList.layoutManager = LinearLayoutManager(activity)
        binding.launchesList.adapter = launchesListAdapter
        binding.launchesList.addItemDecoration(
            LaunchesListDecoration(
                requireActivity().applicationContext
            )
        )
        binding.launchesList.addOnScrollListener(recyclerViewOnScrollListener)
        binding.launchesList.recycledViewPool.setMaxRecycledViews(
            LAUNCHES_LIST_VIEW_HOLDER_TYPE,
            LAUNCHES_LIST_POOL_SIZE
        )
    }

    private fun setLiveDataListeners() {
        viewModel.getLaunches().observe(viewLifecycleOwner, Observer { launches ->
            showLaunches(launches)
        })

        viewModel.getInProcess().observe(viewLifecycleOwner, Observer { inProcess ->
            this.inProcess = inProcess
        })

        viewModel.getIsLastPage().observe(viewLifecycleOwner, Observer { isLastPage ->
            this.isLastPage = isLastPage
        })
    }

    private fun showLaunches(launches: List<LaunchForList>) {
        if (getSelectedLaunchId() == null && launches.isNotEmpty()) {
            initializeSelectedItemId(launches.first().launch.id)
        }

        launchesListAdapter?.setItems(launches)

        getSelectedLaunchId()?.let {
            launchesListAdapter?.selectItem(it)
        }
    }

    private fun transmitSelectedItemId(launchId: String, transitionView: View? = null) {
        (parentFragment as? BaseListWithDetail)?.transmitSelectedItemId(
            launchId,
            transitionView
        )
    }

    private fun initializeSelectedItemId(launchId: String, transitionView: View? = null) {
        (parentFragment as? BaseListWithDetail)?.initializeSelectedItemId(
            launchId,
            transitionView
        )
    }

    private fun getSelectedLaunchId(): String? =
        (parentFragment as? BaseListWithDetail)?.getSelectedItemId()

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = binding.launchesList.layoutManager as LinearLayoutManager

            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if (!inProcess && !isLastPage) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE
                ) {
                    viewModel.getLaunchesForList()
                }
            }
        }
    }
}