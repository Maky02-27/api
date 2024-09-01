package com.example.mealrecipes.Meal_Module

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import android.content.Intent
import android.net.Uri
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.text.AnnotatedString

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter

import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController


@OptIn(UnstableApi::class) @kotlin.OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealDetailScreen(mealName: String, navController: NavController, viewModel: MealViewModel = viewModel()) {
    val mealState = remember { mutableStateOf<Meal?>(null) }
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    LaunchedEffect(mealName) {
        mealState.value = viewModel.getCachedMealByName(mealName)
        if (mealState.value == null) {
            Log.d("MealDetailScreen", "Meal not found in cache, fetching from API...")
            val meal = viewModel.fetchMealByName(mealName)
            if (meal != null) {
                Log.d("MealDetailScreen", "Meal fetched from API: ${meal.strMeal}")
                mealState.value = meal
            } else {
                Log.d("MealDetailScreen", "Failed to fetch meal from API")
            }
        } else {
            Log.d("MealDetailScreen", "Meal found in cache: ${mealState.value?.strMeal}")
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = mealState.value?.strMeal ?: "Meal Details",
                        color = Color.Black,
                        style = TextStyle(
                            fontFamily = ThirdFont,
                            fontSize = 24.sp
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            if (mealState.value == null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(10.dp)
                ) {
                    mealState.value?.let { selectedMeal ->
                        AsyncImage(
                            model = selectedMeal.strMealThumb,
                            contentDescription = selectedMeal.strMeal,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .padding(0.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = selectedMeal.strMeal,
                            style = TextStyle(
                                fontFamily = ThirdFont,
                                fontSize = 42.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Area: ${selectedMeal.strArea}",
                            style = TextStyle(
                                fontFamily = FourthFont,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Category: ${selectedMeal.strCategory}",
                            style = TextStyle(
                                fontFamily = FourthFont,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Price:",
                            style = TextStyle(
                                fontFamily = MainFont,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = selectedMeal.strPrice ?: "Price not available",
                            style = TextStyle(
                                fontFamily = FourthFont,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        selectedMeal.strYoutube?.let { youtube ->
                            if (youtube.isNotEmpty()) {
                                Text(
                                    text = "Add to Cart",
                                    style = TextStyle(
                                        fontFamily = FourthFont,
                                        fontSize = 16.sp,
                                        color = Color.Black,
                                        textDecoration = TextDecoration.Underline
                                    ),
                                    modifier = Modifier
                                        .clickable {
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtube))
                                            context.startActivity(intent)
                                        }
                                        .padding(vertical = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
