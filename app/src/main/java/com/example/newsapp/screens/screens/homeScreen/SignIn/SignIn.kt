package com.example.newsapp.screens.screens.homeScreen.SignIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.components.EmailInput
import com.example.newsapp.components.PasswordInput
import com.example.newsapp.navigation.Screens
import com.example.newsapp.screens.screens.homeScreen.LoginScreen.AuthenticationViewModel


@Composable
fun SignIn(navController: NavController, viewModel: AuthenticationViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){

    val email = rememberSaveable() {
        mutableStateOf("")
    }

    val password = rememberSaveable() {
        mutableStateOf("")
    }

    val passwordVisibility = rememberSaveable() {
        mutableStateOf(false)
    }

    val passwordFocusRequest = FocusRequester.Default

    Box{
        Surface(color = Color.Black, modifier = Modifier.fillMaxSize()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom, modifier = Modifier.offset(y = (-100).dp)) {
                Text(text = "Already Existing User?", color = Color.White, fontSize = 20.sp)
                Text(text = "Log-in", color = Color(0xff4cc9f0), fontSize = 20.sp,
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                        navController.navigate(Screens.LoginScreen.name)
                    })
            }
        }
        Surface(color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(575.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(68.dp)
                .copy(topEnd = ZeroCornerSize, topStart = ZeroCornerSize)) {
            Column(modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(id = R.drawable.signin_image),
                    contentDescription = "Login image",
                    modifier = Modifier.height(250.dp))
                Text(text = "WELCOME", fontSize = 30.sp)
                Spacer(modifier = Modifier.height(26.dp))
                EmailInput(emailState = email, enabled = true, onAction = KeyboardActions {
                    passwordFocusRequest.requestFocus()
                })
                PasswordInput(modifier = Modifier,
                    passwordState = password,
                    labelId = "Password",
                    enabled = true,
                    passwordVisibility = passwordVisibility)
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    navController.popBackStack()
                        viewModel.signup(email.value, password.value){
                            navController.navigate(Screens.SignInSuccess.name)
                        }
                }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White)) {
                    Text(text = "SIGN IN", fontWeight = FontWeight.ExtraBold)
                }
            }
        }
    }
}