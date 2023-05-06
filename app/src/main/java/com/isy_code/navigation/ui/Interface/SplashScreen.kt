package com.isy_code.navigation.ui.Interface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.isy_code.navigation.R
import com.isy_code.navigation.Data.Screen
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController){
    Splash()
    LaunchedEffect(key1 = true){
        delay(3000)
        navController.navigate(Screen.Home.route)
    }
}

@Composable
fun Splash(){

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFFF2E00))
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center))
        {
            Icon(
                painter = painterResource(id = R.drawable.ic_book),
                contentDescription = "",
                modifier = Modifier.size(90.dp),
                tint = Color.White)
                Text(text = "News", color = Color.White, fontSize = 20.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
            }
            CircularProgressIndicator(
                modifier = Modifier
                    .size(size = 30.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = -20.dp),
                color = Color.White,
                strokeWidth = 6.dp
            )
    }
}


@Preview(showSystemUi = true)
@Composable
fun prev(){
    Splash()
}