package com.vladislav.shumilov.launch_ui.ui.list

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.launch_ui.R
import com.example.launch_ui.databinding.LaunchesListBinding
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.launch_ui.app
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


private const val LAUNCHES_LIST_POOL_SIZE = 15

@FragmentScope
class LaunchesListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: LaunchesListViewModelFactory

    private lateinit var binding: LaunchesListBinding

    private val viewModel: LaunchesListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(LaunchesListViewModel::class.java)
    }

    private lateinit var launchesListAdapter: LaunchesListAdapter
    private var inProcess = false
    private var isLastPage = false
    private var compositeDisposable: CompositeDisposable? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app()?.createLaunchComponent()?.inject(this)

        viewModel.getLaunchesForList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
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

        setLiveDataListeners()
    }

    override fun onResume() {
        super.onResume()

        compositeDisposable = CompositeDisposable()

        compositeDisposable?.add(launchesListAdapter.onClickViewHolderCallback.subscribe {
            viewModel.showLaunchDetail(it.launch.id)
        })
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

    private fun setListAdapter() {
        launchesListAdapter =
            LaunchesListAdapter(requireContext())
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
        launchesListAdapter.addItems(launches)
    }

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