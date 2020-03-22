package com.vladislav.shumilov.auth_ui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.auth_ui.R
import com.example.auth_ui.databinding.AuthFragmentBinding
import com.vladislav.shumilov.auth_ui.app
import javax.inject.Inject

class AuthFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: AuthViewModelFactory
    private lateinit var binding: AuthFragmentBinding
    private val viewModel: AuthViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(AuthViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app()?.createAuthComponent()?.inject(this)
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

    override fun onDestroyView() {
        super.onDestroyView()

        app()?.clearAuthComponent()
    }
}