package com.isy_code.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.isy_code.navigation.Data.New
import com.isy_code.navigation.Data.Screen
import com.isy_code.navigation.ui.Interface.Scaffold1
import com.isy_code.navigation.ui.Interface.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController, data:ArrayList<New>, onRefreshAction : () -> Unit){
    NavHost(navController = navController, startDestination = Screen.Splash.route){
        composable(route = Screen.Splash.route){
            SplashScreen(navController)
        }
        composable(route = Screen.Home.route){
            Scaffold1(data = data, onRefreshAction = {onRefreshAction})
        }
    }
}