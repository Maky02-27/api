package com.example.mealrecipes.Meal_Module


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage


@Composable
fun FavoriteScreen(viewModel: MealViewModel = viewModel(), navController: NavHostController) {
    val favoriteMeals by viewModel.favoriteMeals.collectAsState()

    // Define colors
    val backgroundColor = Color.White           // Set background color to white
    val topBarTextBackgroundColor = Color.Black // Set top bar background color to black
    val textColor = Color.Black                 // Set text color to black
    val cardColor = Color.White                 // Set card background color to white
    val iconColor = Color.Red                   // Set icon color to red

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)  // Use white background
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(topBarTextBackgroundColor)
                    .fillMaxWidth()
                    .padding(8.dp)
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Order List",
                    style = TextStyle(
                        fontFamily = MainFont,
                        fontSize = 34.sp,
                        color = Color.White     // Set top bar text color to white
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 8.dp)
                )
            }

            if (favoriteMeals.isEmpty()) {
                Text(
                    text = "You haven't added any favorites yet.",
                    style = MaterialTheme.typography.bodyLarge.copy(color = textColor), // Set text color to black
                    modifier = Modifier.padding(top = 16.dp)
                )
            } else {
                LazyColumn {
                    items(favoriteMeals) { favorite ->
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .clickable { navController.navigate("MealDetail/${favorite.name}") },
                            colors = CardDefaults.cardColors(containerColor = cardColor) // Set card background color to white
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = favorite.thumbnail,
                                    contentDescription = favorite.name,
                                    modifier = Modifier
                                        .size(100.dp),
                                    contentScale = ContentScale.Crop
                                )
                                Column {
                                    Text(
                                        text = favorite.name,
                                        style = TextStyle(
                                            fontFamily = MainFont,
                                            fontSize = 24.sp,
                                            color = textColor // Set text color to black
                                        ),
                                        modifier = Modifier.padding(start = 20.dp)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Area: ${favorite.area}",
                                        style = TextStyle(
                                            fontFamily = FourthFont,
                                            fontSize = 14.sp,
                                            color = textColor // Set text color to black
                                        ),
                                        modifier = Modifier.padding(start = 20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(
                                    onClick = { viewModel.removeFavorite(favorite.id) },
                                    modifier = Modifier.background(Color.Transparent)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Delete,
                                        contentDescription = "Remove from favorites",
                                        tint = iconColor // Set icon color to red
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
