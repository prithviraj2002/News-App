package com.example.newsapp.screens.screens.homeScreen.splashscreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SignUpSplashScreen(navController: NavController){

    var startAnimation by remember{
        mutableStateOf(false)
    }

    val animationState = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 4000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screens.HomeScreen.name) }

    Surface(modifier = Modifier
        .fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Image(painterResource(id = R.drawable.singin), contentDescription = "Splash Screen Image")
            Text(text = "SUCCESS!", color = Color.Black, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)

        }
    }
}