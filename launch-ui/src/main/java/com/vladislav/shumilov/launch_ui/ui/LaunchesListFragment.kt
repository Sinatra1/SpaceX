package com.vladislav.shumilov.launch_ui.ui

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.launch_ui.R
import com.example.launch_ui.databinding.LaunchesListBinding
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_domain.model.local.LaunchWithMissions
import com.vladislav.shumilov.launch_ui.app
import javax.inject.Inject


private const val LAUNCHES_LIST_POOL_SIZE = 10

@FragmentScope
class LaunchesListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: LaunchesListViewModelFactory
    private lateinit var binding: LaunchesListBinding
    private val layoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }
    private val viewModel: LaunchesListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(LaunchesListViewModel::class.java)
    }

    private lateinit var launchesListAdapter: LaunchesListAdapter
    private var inProcess = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app()?.createLaunchComponent()?.inject(this)

        setListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.launches_list, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        launchesListAdapter = LaunchesListAdapter(requireContext())
        binding.launchesList.layoutManager = layoutManager
        binding.launchesList.adapter = launchesListAdapter
        binding.launchesList.addOnScrollListener(recyclerViewOnScrollListener)
        binding.launchesList.recycledViewPool.setMaxRecycledViews(
            LAUNCHES_LIST_VIEW_HOLDER_TYPE,
            LAUNCHES_LIST_POOL_SIZE
        )

        viewModel.getListWithMissions()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        app()?.clearLaunchComponent()
    }

    private fun setListeners() {
        viewModel.launchesWithMissionsLiveData.observe(this, Observer { launches ->
            showLaunches(launches)
        })

        viewModel.inProcessLiveData.observe(this, Observer { inProcess ->
            this.inProcess = inProcess
        })

        viewModel.isLastPageLiveData.observe(this, Observer { isLastPage ->
            this.isLastPage = isLastPage
        })
    }

    private fun showLaunches(launches: List<LaunchWithMissions>) {
        launchesListAdapter.addItems(launches)
    }

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if (!inProcess && !isLastPage) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE
                ) {
                    viewModel.getListWithMissions()
                }
            }
        }
    }
}