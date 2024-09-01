package com.example.mealrecipes.Meal_Module


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.mealrecipes.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen() {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Brand Logo",
                            modifier = Modifier.size(130.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,             // Set TopAppBar background to white
                    titleContentColor = Color.Black           // Set title text color to black
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),                     // Set background color to white
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = rememberImagePainter("https://media.disneylandparis.com/d4th/en-usd/images/3164084_2050jan01_the-steakhouse_16-9_tcm1861-157231.jpg"),
                contentDescription = "About Us Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(255.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Welcome to LongHorn Streak House",
                style = TextStyle(
                    fontFamily = MainFont,
                    fontSize = 32.sp,
                    color = Color.Black                         // Set text color to black
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Welcome to Streak House Restaurant! Indulge in our premium steaks, fresh salads, and delectable sides. Whether you're a steak lover or just craving a delicious meal, our app makes it easy to browse our menu and make reservations. Enjoy exceptional service and top-quality dining. Thank you for choosing Streak House!",
                    style = TextStyle(
                        fontFamily = FourthFont,
                        fontSize = 14.sp,
                        color = Color.Black                        // Set text color to black
                    ),
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Welcome to Streak House Restaurant! Indulge in our premium steaks, fresh salads, and delectable sides. Whether you're a steak lover or just craving a delicious meal, our app makes it easy to browse our menu and make reservations. Enjoy exceptional service and top-quality dining. Thank you for choosing Streak House!",
                    style = TextStyle(
                        fontFamily = FourthFont,
                        fontSize = 14.sp,
                        color = Color.Black                        // Set text color to black
                    ),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}
