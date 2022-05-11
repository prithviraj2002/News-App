package com.example.newsapp.widgets

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun NewsAppBar(
    title: String = "Search",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp =  0.dp,
    onButtonClicked: () -> Unit = {}){
    TopAppBar(title = {
                      Text(text = title)
    }, actions = {},
    elevation = elevation, backgroundColor = Color.Transparent)
}