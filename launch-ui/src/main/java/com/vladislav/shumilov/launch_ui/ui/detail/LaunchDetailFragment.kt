package com.vladislav.shumilov.launch_ui.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.launch_ui.R
import com.example.launch_ui.databinding.LaunchesDetailBinding
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_ui.app
import javax.inject.Inject

@FragmentScope
class LaunchDetailFragment : Fragment() {

    companion object {
        private const val LAUNCH_ID_KEY = "launchId"

        fun getBundle(launchId: String): Bundle {
            val bundle = Bundle()
            bundle.putString(LAUNCH_ID_KEY, launchId)

            return bundle
        }
    }

    @Inject
    lateinit var viewModelFactory: LaunchDetailViewModelFactory

    private lateinit var binding: LaunchesDetailBinding

    private val viewModel: LaunchDetailViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(LaunchDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app()?.createLaunchComponent()?.inject(this)

        val launchId = arguments?.getString(LAUNCH_ID_KEY)

        checkNotNull(launchId) {
            "launchId is required"
        }

        viewModel.getLaunchForDetail(launchId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        DataBindingUtil.inflate<LaunchesDetailBinding>(
            inflater,
            R.layout.launches_detail,
            container,
            false
        ).apply {
            binding = this
            viewModel = this@LaunchDetailFragment.viewModel
        }.root

    override fun onDestroyView() {
        super.onDestroyView()

        app()?.clearLaunchComponent()
    }
}