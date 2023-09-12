package com.vladislav.shumilov.launch_ui.ui.list

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_ui.di.modules.CoreViewModelFactory
import com.vladislav.shumilov.core_ui.ui.list_with_detail.BaseListFragment
import com.vladislav.shumilov.core_ui.ui.list_with_detail.BaseListWithDetail
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.launch_ui.R
import com.vladislav.shumilov.launch_ui.app
import com.vladislav.shumilov.launch_ui.databinding.LaunchesListBinding
import com.vladislav.shumilov.launch_ui.ui.detail.LaunchDetailFragment
import kotlinx.coroutines.flow.collect
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

    private val handler = Handler()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAnimation()

        app()?.createLaunchComponent()?.inject(this)

        viewModel.getLaunchesForList()
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

        setListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        app()?.clearLaunchComponent()
    }

    override fun showDetailFragment(itemId: String) {
        handler.post {
            viewModel.showLaunchDetailFragment(itemId)
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
        launchesListAdapter = LaunchesListAdapter { launchList ->
            transmitSelectedItemId(launchList.launch.id)
        }

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

    private fun setListeners() {
        lifecycleScope.launch {
            viewModel.stateFlow.collect {
                showLaunches(it.launches)
            }
        }
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

    private fun transmitSelectedItemId(launchId: String) {
        (parentFragment as? BaseListWithDetail)?.transmitSelectedItemId(launchId)
    }

    private fun initializeSelectedItemId(launchId: String) {
        (parentFragment as? BaseListWithDetail)?.initializeSelectedItemId(launchId)
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

            if (!viewModel.state.isInProgress && !viewModel.state.isLastPage) {
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