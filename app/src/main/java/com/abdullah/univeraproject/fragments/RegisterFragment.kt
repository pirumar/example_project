package com.abdullah.univeraproject.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.abdullah.univeraproject.R
import com.abdullah.univeraproject.databinding.FragmentRegisterBinding
import com.abdullah.univeraproject.di.retrofit.RetrofitServiceInstance
import com.abdullah.univeraproject.viewmodels.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {


    @set:Inject
    internal var mSharedPreferences: SharedPreferences? = null

    @set:Inject
    internal var retrofitServiceInstance: RetrofitServiceInstance? = null

    val viewModel by viewModels<RegisterViewModel>()

    lateinit var binding: FragmentRegisterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.username.addTextChangedListener {
            it.let {
                viewModel.setUserName(it.toString())

            }
        }
        binding.password.addTextChangedListener {
            it.let {
                viewModel.setPassword(it.toString())

            }
        }
        binding.email.addTextChangedListener {
            it.let {
                viewModel.setEmail(it.toString())

            }
        }
        binding.registerbutton.setOnClickListener {
            viewModel.submitRegister()
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                if (it.success) {

                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RegisterFragment()
    }
}