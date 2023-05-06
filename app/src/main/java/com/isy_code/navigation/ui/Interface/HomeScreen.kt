package com.isy_code.navigation.ui.Interface

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.isy_code.navigation.Data.New
import com.isy_code.navigation.ui.theme.Scarlet
import com.skydoves.landscapist.glide.GlideImage


//Scaffold
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Scaffold1(data:ArrayList<New>,onRefreshAction : () -> Unit){
    Scaffold(
        topBar = { MyTopBar(onRefreshAction) },
        content = {
            LazyColumn{
                items(data){new->
                    content(new = new)
                }
            }
        }
    )
}

//TopBar

@Composable
fun MyTopBar(onRefreshBtnClicked : () -> Unit){
    TopAppBar(
        title = {
            Text(text = "News", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
        },
        backgroundColor = Color.White,
        elevation = 0.dp,
        actions = { IconButton(onClick = { onRefreshBtnClicked.invoke() }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription ="", modifier = Modifier.padding(horizontal = 3.dp) )
        } }
    )
}

//Content
@Composable
fun content(new: New){
    var showDescription by remember{ mutableStateOf(false) }
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
                    Text(text = if(showDescription) "show less" else "show more", color = Scarlet,modifier = Modifier.clickable { showDescription = !showDescription })
                }
            }
            AnimatedVisibility(visible = showDescription) {
                Text(text = new.content, color = Color.Black, modifier = Modifier.padding(start = 10.dp, end = 10.dp), textAlign = TextAlign.Justify, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun contents(){
    Card(
        elevation = 0.dp, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        content = {
            Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceAround){
                Spacer(modifier = Modifier.size(10.dp))
                Image(imageVector = Icons.Default.Email, contentDescription = "", modifier = Modifier.size(120.dp))
                Column {
                    Text(text = "lsfsdlfkjsdkfjsdflkjsdflsdfkjlsdfkjsdlfkjsdflkjlkjsdflkjsdflkjdsflksdfjlksdfjlksdfjlsdfkjsdlfksjdflksdfjlsdfkjsdflksdfllsdfkljsdklfjklsdjflskldfklsdfmlksdfmlksdmflksdmflksdmflklsdmfkmlk", maxLines = 3, fontStyle = FontStyle.Italic)
                    Text(text = "lqkesflskefjlskefj")
                }
            }
        }
    )
}
