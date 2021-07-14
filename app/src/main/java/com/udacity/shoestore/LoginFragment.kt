package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.viewmodels.LoginViewModel

class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            loginFragment = this@LoginFragment
        }

        loginViewModel.hasAuthenticated.observe(viewLifecycleOwner, Observer { isUserAuthenticated ->
            if (isUserAuthenticated)
            {
                loginViewModel.afterUserLoggedIn()
                resetUserInfoFields()
            }
        })
        return binding.root
    }

    fun tryToLogin() {
        val username = binding.emailEdit.text.toString()
        val password = binding.passwordEdit.text.toString()

        if (loginViewModel.isUserInfoAvailable(username, password)) {
            loginViewModel.onUserAuthenticated()
            goToWelcomePage()
        }
        else {
            setErrorTextField(true)
        }
    }

    fun goToWelcomePage() = findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())

    private fun setErrorTextField(error: Boolean)
    {
        if (error){
            binding.validationErrorText.text = getString(R.string.validation_error_msg)
            binding.validationErrorText.visibility = View.VISIBLE
        }
        else {
            binding.validationErrorText.text = null
            binding.validationErrorText.visibility = View.INVISIBLE
        }
    }

    private fun resetUserInfoFields(){
        binding.emailEdit.text = null
        binding.passwordEdit.text = null
    }
}