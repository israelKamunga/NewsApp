package com.isy_code.navigation.ui.theme

/*
@Composable
fun AnimatedSplashScreen(navController:NavHostController){
    var startAnimation by remember{ mutableStateOf(false) }
    val alphanim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(4000)
        navController.navigate(Screen.Home.route)
    }

    Splash(alpha = alphanim.value)
}


@Composable
fun Splash(alpha:Float){
   Column(
       modifier = Modifier
           .background(if (isSystemInDarkTheme()) Color.Black else Purple700)
           .fillMaxSize()
           .alpha(alpha),
            horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center
   ){
       Icon(imageVector = Icons.Default.Email, contentDescription = "",tint=Color.White, modifier = Modifier.size(120.dp))
       Text(text = "Email", fontWeight = FontWeight.Bold , color = Color.White)
   }
}

 */