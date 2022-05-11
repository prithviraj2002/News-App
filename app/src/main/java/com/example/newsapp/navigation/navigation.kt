package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.screens.screens.homeScreen.LoginScreen.LoginScreen
import com.example.newsapp.screens.screens.homeScreen.SignIn.SignIn
import com.example.newsapp.screens.screens.homeScreen.homeScreen.HomeScreen
import com.example.newsapp.screens.screens.homeScreen.homeScreen.HomeScreenViewModel
import com.example.newsapp.screens.screens.homeScreen.splashscreen.SignUpSplashScreen
import com.example.newsapp.screens.screens.homeScreen.splashscreen.SplashScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.name) {
        composable(Screens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(Screens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
        composable(Screens.SigninScreen.name) {
            SignIn(navController)
        }
        composable(Screens.SignInSuccess.name) {
            SignUpSplashScreen(navController = navController)
        }
        composable(Screens.HomeScreen.name) {
            val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
            HomeScreen(homeScreenViewModel, navController)
        }
    }
}
