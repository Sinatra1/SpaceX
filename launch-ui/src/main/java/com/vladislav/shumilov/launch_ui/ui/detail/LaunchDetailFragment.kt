package com.vladislav.shumilov.launch_ui.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.launch_ui.R
import com.example.launch_ui.databinding.LaunchDetailBinding
import com.vladislav.shumilov.launch_ui.app
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

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

    private lateinit var binding: LaunchDetailBinding

    private val viewModel: LaunchDetailViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(LaunchDetailViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(viewModel)
    }

    fun getLaunchDetailId() =
        arguments?.getString(LAUNCH_ID_KEY) ?: IllegalArgumentException("launchId is required")

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

    override fun onDestroyView() {
        super.onDestroyView()

    }
}