package com.example.newsapp.screens.screens.homeScreen.LoginScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class AuthenticationViewModel: ViewModel() {

//    var loading = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun signup(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch{
            if (_loading.value == false) {
                _loading.value = true
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            home()
                        }
                        else{
                            Log.d("FB", "signup: ${task.result.toString()}")
                        }
                    }
            }
            _loading.value = false
    }

    fun login(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try{
                 auth.signInWithEmailAndPassword(email, password)
                     .addOnCompleteListener{    task ->
                         if(task.isSuccessful){
                             home()
                         }
                         else{
                             Log.d("FB Task", task.result.toString())
                         }
                     }
            }
            catch (ex: Exception){
                Log.d("FB", "${ex.message}")
            }
        }
    }
