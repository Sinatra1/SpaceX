package com.vladislav.shumilov.launch_ui.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.ui.NavigationUI
import androidx.transition.TransitionInflater
import com.example.launch_ui.R
import com.example.launch_ui.databinding.LaunchDetailBinding
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_ui.ui.activity.SingleActivity
import com.vladislav.shumilov.core_ui.ui.list_with_detail.BaseDetailFragment
import com.vladislav.shumilov.core_ui.utils.isLandscape
import com.vladislav.shumilov.launch_ui.app
import kotlinx.android.synthetic.main.launch_detail.*
import javax.inject.Inject

@FragmentScope
class LaunchDetailFragment : BaseDetailFragment() {

    companion object {
        private const val LAUNCH_ID_KEY = "launchId"
        internal const val ANIMATION_DURATION = 400L

        fun newInstance(launchId: String): LaunchDetailFragment =
            LaunchDetailFragment().apply {
                arguments = getBundle(launchId)
            }

        fun getBundle(launchId: String): Bundle =
            Bundle().apply {
                putString(LAUNCH_ID_KEY, launchId)
            }
    }

    @Inject
    lateinit var viewModelFactory: LaunchDetailViewModelFactory

    private lateinit var binding: LaunchDetailBinding

    private val viewModel: LaunchDetailViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(LaunchDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAnimation()

        app()?.createLaunchComponent()?.inject(this)

        val launchId = arguments?.getString(LAUNCH_ID_KEY)

        checkNotNull(launchId) {
            "launchId is required"
        }

        lifecycle.addObserver(viewModel)

        viewModel.getLaunchForDetail(launchId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        DataBindingUtil.inflate<LaunchDetailBinding>(
            inflater,
            R.layout.launch_detail,
            container,
            false
        ).apply {
            binding = this
            viewModel = this@LaunchDetailFragment.viewModel
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let { context ->
            if (!isLandscape(context) && !isShownInListWithDetail()) {
                (activity as? AppCompatActivity)?.supportActionBar?.hide()

                (activity as? SingleActivity)?.getNavigationController()?.let {
                    NavigationUI.setupWithNavController(launchDetailToolbarLayout, launchDetailToolbar, it)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        setLiveDataListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        app()?.clearLaunchComponent()
    }

    private fun setLiveDataListeners() {
        viewModel.getTitle().observe(viewLifecycleOwner, Observer {
            launchDetailToolbar.contentDescription = it
        })
    }

    private fun setAnimation() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.fade).apply {
                duration = ANIMATION_DURATION
            }
        enterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.fade).apply {
                duration = ANIMATION_DURATION
            }
    }
}