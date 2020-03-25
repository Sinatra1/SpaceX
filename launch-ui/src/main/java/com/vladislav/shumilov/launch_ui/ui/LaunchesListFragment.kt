package com.vladislav.shumilov.launch_ui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.auth_ui.R
import com.example.auth_ui.databinding.AuthFragmentBinding
import com.vladislav.shumilov.launch_data.LaunchScope
import com.vladislav.shumilov.launch_ui.app
import javax.inject.Inject

@LaunchScope
class LaunchesListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: LaunchesListViewModelFactory
    private lateinit var binding: AuthFragmentBinding
    private val viewModel: LaunchesListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(LaunchesListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app()?.createLaunchComponent()?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.auth_fragment, container, false)

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.getList()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        app()?.clearLaunchComponent()
    }
}