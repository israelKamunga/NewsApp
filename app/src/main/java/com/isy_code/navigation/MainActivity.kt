package com.isy_code.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.isy_code.navigation.Data.New
import com.isy_code.navigation.Data.ViewM
import com.isy_code.navigation.ui.Interface.Scaffold1
import com.isy_code.navigation.ui.Interface.Splash
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var viewmodel = ViewModelProvider(this).get(ViewM::class.java)

        setContent {
            val result by viewmodel.resultat.observeAsState()
            val navController = rememberNavController()
            SetupNavGraph(navController = navController,result?: arrayListOf(),viewmodel)
            //Scaffold1(result?: arrayListOf(), onRefreshAction = { viewmodel.getData() })
        }

    }
}

