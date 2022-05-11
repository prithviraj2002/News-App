package com.example.newsapp.screens.screens.homeScreen.homeScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.newsapp.data.DataOrException
import com.example.newsapp.model.Article
import com.example.newsapp.model.News
import com.example.newsapp.navigation.Screens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel, navController: NavController){
    val country by remember{
        mutableStateOf("in")
    }
    val selectedCategory = rememberSaveable {
            mutableStateOf("")
    }
    val show = rememberSaveable {
        mutableStateOf(false)
    }

    val categories: List<String> = listOf("business", "sports", "entertainment", "technology","science","health")
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp), horizontalArrangement = Arrangement.Center) {
                Text(text = "NEWS APP", color = Color.Black, fontWeight = FontWeight.ExtraBold, fontSize = 30.sp,
                modifier = Modifier.padding(end = 20.dp))
                Spacer(modifier = Modifier.width(50.dp))
            }
            Divider(modifier = Modifier.fillMaxWidth(), color = Color.Black)
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp), horizontalArrangement = Arrangement.Center) {
                LazyRow {
                    items(categories) { category ->
                        Button(onClick = { selectedCategory.value = category;
                                         show.value = true
                                         },
                            shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            disabledBackgroundColor = Color.White,
                            disabledContentColor = Color.Black
                        )) {
                            Text(text = category)
                        }
                    }
                }
            }
            Divider(modifier = Modifier.fillMaxWidth(), color = Color.Black)
            if (!show.value && selectedCategory.value.isEmpty()){
                SportsContent(viewModel = viewModel, navController = navController, country = country)
            }
            if(show.value && selectedCategory.value.isNotEmpty() && selectedCategory.value == "entertainment"){
                EntertainmentContent(viewModel = viewModel, navController = navController, country = country)
            }
            if(show.value && selectedCategory.value.isNotEmpty() && selectedCategory.value == "sports"){
                SportsContent(viewModel = viewModel, navController = navController, country = country)
            }
            if(show.value && selectedCategory.value.isNotEmpty() && selectedCategory.value == "technology"){
                TechContent(viewModel = viewModel, navController = navController, country = country)
            }
            if(show.value && selectedCategory.value.isNotEmpty() && selectedCategory.value == "health"){
                HealthContent(viewModel = viewModel, navController = navController, country = country)
            }
            if(show.value && selectedCategory.value.isNotEmpty() && selectedCategory.value == "science"){
                ScienceContent(viewModel = viewModel, navController = navController, country = country)
            }
            if(show.value && selectedCategory.value.isNotEmpty() && selectedCategory.value == "business"){
                BusinessContent(viewModel = viewModel, navController = navController, country = country)
            }
            show.value != show.value
        }
    }
}

@Composable
fun EntertainmentContent(viewModel: HomeScreenViewModel, navController: NavController, country: String = "in") {
    val newsData = produceState<DataOrException<News, Boolean, Exception>>(initialValue = DataOrException(loading = true)){
        value = viewModel.getNews(country = country, category = "entertainment")
    }.value

    if(newsData.loading == true){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Color.Black)
        }
    }
    else if(newsData.data != null){
        LazyColumn{
            items(newsData.data!!.articles){ article->
                NewsCard(article = article, navController = navController)
            }
        }
    }
}

@Composable
fun SportsContent(viewModel: HomeScreenViewModel, navController: NavController, country: String = "in") {
    val newsData = produceState<DataOrException<News, Boolean, Exception>>(initialValue = DataOrException(loading = true)){
        value = viewModel.getNews(country = country, category = "sports")
    }.value

    if(newsData.loading == true){
        Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Color.Black)
        }
    }
    else if(newsData.data != null){
        LazyColumn{
            items(newsData.data!!.articles){ article->
                NewsCard(article = article, navController = navController)
            }
        }
    }
}

@Composable
fun HealthContent(viewModel: HomeScreenViewModel, navController: NavController, country: String = "in") {
    val newsData = produceState<DataOrException<News, Boolean, Exception>>(initialValue = DataOrException(loading = true)){
        value = viewModel.getNews(country = country, category = "health")
    }.value

    if(newsData.loading == true){
        Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Color.Black)
        }
    }
    else if(newsData.data != null){
        LazyColumn{
            items(newsData.data!!.articles){ article->
                NewsCard(article = article, navController = navController)
            }
        }
    }
}

@Composable
fun ScienceContent(viewModel: HomeScreenViewModel, navController: NavController, country: String = "in") {
    val newsData = produceState<DataOrException<News, Boolean, Exception>>(initialValue = DataOrException(loading = true)){
        value = viewModel.getNews(country = country, category = "science")
    }.value

    if(newsData.loading == true){
        Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Color.Black)
        }
    }
    else if(newsData.data != null){
        LazyColumn{
            items(newsData.data!!.articles){ article->
                NewsCard(article = article, navController = navController)
            }
        }
    }
}

@Composable
fun BusinessContent(viewModel: HomeScreenViewModel, navController: NavController, country: String = "in") {
    val newsData = produceState<DataOrException<News, Boolean, Exception>>(initialValue = DataOrException(loading = true)){
        value = viewModel.getNews(country = country, category = "business")
    }.value

    if(newsData.loading == true){
        Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Color.Black)
        }
    }
    else if(newsData.data != null){
        LazyColumn{
            items(newsData.data!!.articles){ article->
                NewsCard(article = article, navController = navController)
            }
        }
    }
}

@Composable
fun TechContent(viewModel: HomeScreenViewModel, navController: NavController, country: String = "in") {
    val newsData = produceState<DataOrException<News, Boolean, Exception>>(initialValue = DataOrException(loading = true)){
        value = viewModel.getNews(country = country, category = "technology")
    }.value

    if(newsData.loading == true){
        Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Color.Black)
        }
    }
    else if(newsData.data != null){
        LazyColumn{
            items(newsData.data!!.articles){ article->
                NewsCard(article = article, navController = navController)
            }
        }
    }
}

@Composable
fun NewsCard(article: Article, navController: NavController) {

    val expanded = rememberSaveable{
        mutableStateOf(false)
    }

    val context = LocalUriHandler.current

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .height(100.dp)
        .clickable {
            expanded.value = true
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Surface(shape = RectangleShape) {
                Image(painter = rememberImagePainter(data = article.urlToImage),
                    contentDescription = "News Image", modifier = Modifier
                        .padding(12.dp)
                        .size(100.dp))
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = article.title.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    overflow = TextOverflow.Ellipsis)
            }
        }
    }
        if (expanded.value && article.toString().isNotEmpty()) {
            Dialog(onDismissRequest = { }) {
                Surface(modifier = Modifier
                    .height(400.dp)
                    .width(300.dp),
                    shape = RoundedCornerShape(15.dp),
                    color = Color.White) {
                    Column(modifier = Modifier
                        .padding(5.dp)
                        .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End) {
                            Icon(imageVector = Icons.Default.Close,
                                contentDescription = "close icon",
                                tint = Color.Gray,
                                modifier = Modifier.clickable { expanded.value = false })
                        }
                        Image(painter = rememberImagePainter(data = article.urlToImage),
                            contentDescription = "News Image",
                            modifier = Modifier
                                .height(200.dp)
                                .width(300.dp)
                                .padding(bottom = 5.dp))
                        Column(modifier = Modifier
                            .fillMaxSize()) {
                            Text(text = article.title.toString(),
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
                            Text(text = article.description.toString(),
                                color = Color.Black,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
                            Text(text = article.author.toString(),
                                color = Color.Black,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                                textAlign = TextAlign.Right)
                            Text(text = article.url.toString(),
                                fontStyle = FontStyle.Italic,
                                color = Color.Blue,
                                fontSize = 12.sp,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                                    .clickable {
                                        context.openUri(article.url.toString())
                                    })
                            Log.d("Dialog", "${article.title}")
                        }
                    }
                }
            }
        }
}

