package com.vladislav.shumilov.launch_ui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.launch_ui.R
import com.example.launch_ui.databinding.LaunchesListBinding
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.model.local.LaunchWithMissionsImpl
import com.vladislav.shumilov.launch_ui.app
import javax.inject.Inject

private const val LAUNCHES_LIST_POOL_SIZE = 10

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

    override fun onResume() {
        super.onResume()

        viewModel.getListWithMissions()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        launchesListAdapter = LaunchesListAdapter(requireContext())
        binding.launchesList.layoutManager = LinearLayoutManager(activity)
        binding.launchesList.adapter = launchesListAdapter
        binding.launchesList.recycledViewPool.setMaxRecycledViews(
            LAUNCHES_LIST_VIEW_HOLDER_TYPE,
            LAUNCHES_LIST_POOL_SIZE
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()

        app()?.clearLaunchComponent()
    }

    private fun setListeners() {
        viewModel.getLaunchesWithMissions().observe(this, Observer { launches ->
            showLaunches(launches)
        })
    }

    private fun showLaunches(launches: List<LaunchWithMissionsImpl>) {
        launchesListAdapter.addItems(launches)
    }
}