package com.isy_code.navigation.ui.Interface

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.isy_code.navigation.Data.New
import com.isy_code.navigation.Data.ViewM
import com.isy_code.navigation.R
import com.isy_code.navigation.ui.theme.Scarlet
import com.skydoves.landscapist.glide.GlideImage


//Scaffold
@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Scaffold1(data:ArrayList<New>,viewM: ViewM){
    var dialogProperties = DialogProperties()
    var dataAvailable = viewM.dataAvailable.observeAsState()


    Scaffold(
        topBar = { MyTopBar(viewM) },
        content = {
            if(dataAvailable.value == false){
                CategoriesScreen(viewM)
                Image(painter = painterResource(id = R.drawable.no_connection), contentDescription = "", modifier = Modifier.fillMaxSize())
            }else{
                Column {
                    CategoriesScreen(viewM)
                    LazyColumn{
                        items(data){new->
                            content(new = new,dialogProperties)
                        }
                    }
                }
            }
        }
    )
}

//TopBar

@Composable
fun MyTopBar(viewM: ViewM){
    TopAppBar(
        title = {
            Text(text = "News", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
        },
        backgroundColor = Color.White,
        elevation = 0.dp,
        actions = { IconButton(onClick = { viewM.getAllNews() }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription ="", modifier = Modifier.padding(horizontal = 3.dp) )
        } }
    )
}

//Content
@Composable
fun content(new: New,dialogProperties: DialogProperties){
    var dialogShowed by remember{ mutableStateOf(false) }
    Card(
        elevation = 0.dp, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
    ){
        Column {
            Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Center){
                Spacer(modifier = Modifier.size(10.dp))
                com.skydoves.landscapist.glide.GlideImage(
                    imageModel = new.imageUrl,
                    modifier = Modifier.size(90.dp),
                    contentScale = ContentScale.Crop)
                Spacer(modifier = Modifier.size(10.dp))
                Column(modifier = Modifier
                    .padding(end = 10.dp)) {
                    Text(text = new.title, maxLines = 3, fontWeight = FontWeight.Bold, textAlign = TextAlign.Justify)
                    Text(text = new.author, fontStyle = FontStyle.Italic)
                    Text(text = if(dialogShowed) "show less" else "show more", color = Scarlet,modifier = Modifier.clickable { dialogShowed = !dialogShowed })
                }
            }
            if (dialogShowed){
                Dialog(onDismissRequest = { dialogShowed = !dialogShowed }, properties = dialogProperties) {
                    Surface(modifier = Modifier.wrapContentSize(),color = Color.White) {
                        Column(modifier = Modifier.wrapContentSize()){
                            com.skydoves.landscapist.glide.GlideImage(imageModel = new.imageUrl,contentScale = ContentScale.Crop,modifier = Modifier
                                .width(600.dp)
                                .height(300.dp)
                                .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                            )
                            Column(modifier = Modifier.padding(start = 10.dp,end = 10.dp,bottom = 10.dp)){
                                Text(text = new.title,style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
                                Text(text = new.author, style = MaterialTheme.typography.subtitle1,fontStyle = FontStyle.Italic)
                                Text(text = new.content, textAlign = TextAlign.Justify,style = MaterialTheme.typography.subtitle2)
                            }
                            Box(modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                                .padding(all = 0.dp)){
                                Button(onClick = {dialogShowed = !dialogShowed},modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .clip(
                                        RoundedCornerShape(
                                            topStart = 20.dp,
                                            topEnd = 20.dp,
                                            bottomStart = 20.dp,
                                            bottomEnd = 20.dp
                                        )
                                    )
                                    .fillMaxWidth(), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF2E00))) {
                                    Row(){
                                        //Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = Color.White)
                                        Text(text = "Cancel", color = Color.White,style = MaterialTheme.typography.h6)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun dialogUi(new: New){
    Column(modifier = Modifier.wrapContentSize()){
        com.skydoves.landscapist.glide.GlideImage(imageModel = new.imageUrl,contentScale = ContentScale.Crop,modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(10.dp))
        )
        Column(modifier = Modifier.padding(start = 10.dp,end = 10.dp,bottom = 10.dp)){
            Text(text = new.title,style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
            Text(text = new.author, style = MaterialTheme.typography.subtitle1)
            Text(text = new.content, textAlign = TextAlign.Justify,style = MaterialTheme.typography.caption)
        }
        Box(modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(all = 0.dp)){
            Button(onClick = {},modifier = Modifier
                .align(Alignment.BottomEnd)
                .clip(
                    RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                )
                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF2E00))) {
                Row(){
                    //Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = Color.White)
                    Text(text = "Cancel", color = Color.White,style = MaterialTheme.typography.h6)
                }
            }
        }
    }
}


@Composable
fun CategoriesScreen(viewM: ViewM){
    val categories = listOf(
        "all",
        "business",
        "sports",
        "world",
        "politics",
        "technology",
        "startup",
        "entertainment",
        "science",
        "automobile"
    )
    LazyRow(modifier = Modifier.wrapContentSize()){
        items(categories){categorie->
            when(categorie){
                "all"->{
                    Spacer(modifier = Modifier.size(10.dp))
                    OutlinedButton(onClick = {viewM.getAllNews()},) {
                        Text(text = categorie, color = Color(0xFFFF2E00))
                    }
                }
                "business"->{
                    Spacer(modifier = Modifier.size(10.dp))
                    OutlinedButton(onClick = {viewM.getAllNews()},) {
                        Text(text = categorie, color = Color(0xFFFF2E00))
                    }
                }
                "sports"->{
                    Spacer(modifier = Modifier.size(10.dp))
                    OutlinedButton(onClick = {viewM.getSportsNews()},) {
                        Text(text = categorie, color = Color(0xFFFF2E00))
                    }
                }
                "world"->{
                    Spacer(modifier = Modifier.size(10.dp))
                    OutlinedButton(onClick = {viewM.getWorldNews()},) {
                        Text(text = categorie, color = Color(0xFFFF2E00))
                    }
                }
                "politics"->{
                    Spacer(modifier = Modifier.size(10.dp))
                    OutlinedButton(onClick = {viewM.getPoliticsNews()},) {
                        Text(text = categorie, color = Color(0xFFFF2E00))
                    }
                }
                "technology"->{
                    Spacer(modifier = Modifier.size(10.dp))
                    OutlinedButton(onClick = {viewM.getTechnologyNews()},) {
                        Text(text = categorie, color = Color(0xFFFF2E00))
                    }
                }
                "startup"->{
                    Spacer(modifier = Modifier.size(10.dp))
                    OutlinedButton(onClick = {viewM.getStartupNews()},) {
                        Text(text = categorie, color = Color(0xFFFF2E00))
                    }
                }
                "entertainment"->{
                    Spacer(modifier = Modifier.size(10.dp))
                    OutlinedButton(onClick = {viewM.getEntertainmentNews()},) {
                        Text(text = categorie, color = Color(0xFFFF2E00))
                    }
                }
                "science"->{
                    Spacer(modifier = Modifier.size(10.dp))
                    OutlinedButton(onClick = {viewM.getScienceNews()},) {
                        Text(text = categorie, color = Color(0xFFFF2E00))
                    }
                }
                "automobile"->{
                    Spacer(modifier = Modifier.size(10.dp))
                    OutlinedButton(onClick = {viewM.getAutomobileNews()},) {
                        Text(text = categorie, color = Color(0xFFFF2E00))
                    }
                }
            }
        }
    }
}