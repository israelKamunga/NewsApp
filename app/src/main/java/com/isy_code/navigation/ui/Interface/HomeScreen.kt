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

    Scaffold(
        topBar = { MyTopBar(viewM) },
        content = {
            if(data.isEmpty()){
                Image(imageVector = Icons.Default.Refresh, contentDescription = "")
                Text(text = "Something went wrong")
            }else{
                Column {
                    CategoriesScreen()
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
        actions = { IconButton(onClick = { viewM.getData() }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription ="", modifier = Modifier.padding(horizontal = 3.dp) )
        } }
    )
}

//Content
@Composable
fun content(new: New,dialogProperties: DialogProperties){
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
            /*AnimatedVisibility(visible = showDescription) {
                Text(text = new.content, color = Color.Black, modifier = Modifier.padding(start = 10.dp, end = 10.dp), textAlign = TextAlign.Justify, fontWeight = FontWeight.Bold)
            }*/
            if (showDescription){
                Dialog(onDismissRequest = { showDescription = !showDescription }, properties = dialogProperties) {
                    //Icon(imageVector = Icons.Default.Email, contentDescription = "")
                    //dialogUi(new,showDescription)
                    //////////////////////////////////////////////
                    Column(//modifier = Modifier
                        //.fillMaxWidth()
                        //.wrapContentHeight()
                        //.background(Color.White)
                    ) {
                        /*Image(painter = painterResource(id = R.drawable.yourname), contentDescription = "", modifier = Modifier
                            .width(300.dp)
                            .wrapContentHeight()
                            .clip(RoundedCornerShape(10.dp)))*/
                        com.skydoves.landscapist.glide.GlideImage(imageModel = new.imageUrl,contentScale = ContentScale.Crop,modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            //.clip(RoundedCornerShape(10.dp))
                        )
                        Text(text = new.title)
                        Text(text = new.author)
                        Text(text = new.content)
                        Box(modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(all = 0.dp)){
                            Button(onClick = { showDescription = !showDescription },modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .clip(
                                    RoundedCornerShape(
                                        topStart = 30.dp,
                                        topEnd = 0.dp,
                                        bottomStart = 0.dp
                                    )
                                ), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF2E00))) {
                                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = Color.White)
                            }
                        }
                    }
                    ///////////////////////////////////////////
                }
            }
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun dialogUi(new:New,state:Boolean){
    Surface(modifier = Modifier.wrapContentSize()) {
        Box(modifier = Modifier
            .width(300.dp)
            .wrapContentHeight()
            .background(Color.White)) {
            /*Image(painter = painterResource(id = R.drawable.yourname), contentDescription = "", modifier = Modifier
                .width(300.dp)
                .wrapContentHeight()
                .clip(RoundedCornerShape(10.dp)))*/
            com.skydoves.landscapist.glide.GlideImage(imageModel = new.imageUrl,contentScale = ContentScale.Crop,modifier = Modifier
                .width(300.dp)
                .wrapContentHeight()
                .clip(RoundedCornerShape(10.dp)))
            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text(text = new.title)
                Text(text = new.author)
                Text(text = new.content)
                Box(modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(all = 0.dp)){
                    Button(onClick = {},modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .clip(RoundedCornerShape(topStart = 30.dp)), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF2E00))) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = Color.White)
                    }
                }
            }
        }
    }
}


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

@Preview(showSystemUi = true)
@Composable
fun CategoriesScreen(){
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
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedButton(onClick = {},) {
                Text(text = categorie, color = Color(0xFFFF2E00))
            }
        }
    }
}

/*OutlinedButton(onClick = {}) {
    Text(text = "all")
}
OutlinedButton(onClick = {}) {
    Text(text = "sports")
}
Spacer(modifier = Modifier.size(10.dp))
OutlinedButton(onClick = {}) {
    Text(text = "world")
}
Spacer(modifier = Modifier.size(10.dp))
OutlinedButton(onClick = {}) {
    Text(text = "politics")
}
Spacer(modifier = Modifier.size(10.dp))
OutlinedButton(onClick = {}) {
    Text(text = "technology")
}
Spacer(modifier = Modifier.size(10.dp))
OutlinedButton(onClick = {}) {
    Text(text = "startup")
}
Spacer(modifier = Modifier.size(10.dp))
OutlinedButton(onClick = {}) {
    Text(text = "entertainment")
}
Spacer(modifier = Modifier.size(10.dp))
OutlinedButton(onClick = {}) {
    Text(text = "science")
}
Spacer(modifier = Modifier.size(10.dp))
OutlinedButton(onClick = {}) {
    Text(text = "automobile")
}

 */
