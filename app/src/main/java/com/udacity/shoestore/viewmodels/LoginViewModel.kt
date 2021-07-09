package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _hasAuthenticated = MutableLiveData<Boolean>()
    val hasAuthenticated: LiveData<Boolean> = _hasAuthenticated

    init {
        _hasAuthenticated.value = false
    }

    fun isUserInfoAvailable(usrnm: String, pswrd: String) = !(usrnm.isEmpty() || pswrd.isEmpty())

    fun onUserAuthenticated(){
        _hasAuthenticated.value = true
    }

    fun afterUserLoggedIn(){
        _hasAuthenticated.value = false
    }
}