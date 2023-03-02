package com.sanam.yavarpour.pixaby

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sanam.yavarpour.image_search.presentation.SearchScreen
import com.sanam.yavarpour.pixaby.navigation.Route
import com.sanam.yavarpour.pixaby.ui.theme.PixabyTheme
import kotlinx.coroutines.InternalCoroutinesApi

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@InternalCoroutinesApi
@Composable
fun PixabaySearchApp() {
    PixabyTheme {
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
        ) {
            NavHost(
                navController = navController,
                startDestination = Route.SEARCH_SCREEN
            ) {
                composable(
                    route = Route.SEARCH_SCREEN
                ) {
                    SearchScreen(
                        scaffoldState = scaffoldState,
                        viewModel = hiltViewModel()
                    )
                }
            }
        }
    }
}

