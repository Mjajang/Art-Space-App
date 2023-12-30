package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                    ArtSpaceLayout()
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    var currentArtworkIndex by remember { mutableIntStateOf(0) }
    val totalArtworks = 7

    Column (
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        ArtWorkWall(currentArtworkIndex, modifier = Modifier.padding(vertical = 50.dp))
        ArtWorkDescriptor(
            artWorkTitle = "Still Life of Blue Rose and Other Flowers",
            artWorkArtist = "Owen Scott",
            artWorkYear = "2021",
        )
        DisplayController(
            onNextClick = {
                currentArtworkIndex++
                if (currentArtworkIndex >= totalArtworks) {
                    currentArtworkIndex = 0
                }
                          },
            onPreviousClick = {
                currentArtworkIndex--
                if (currentArtworkIndex < 0) {
                    currentArtworkIndex = totalArtworks - 1
                }
            }
        )
    }
}

@Composable
fun ArtWorkWall(currentArtworkIndex: Int, modifier: Modifier = Modifier) {
    val images = when (currentArtworkIndex) {
        0 -> R.drawable.img1
        1 -> R.drawable.img2
        2 -> R.drawable.img3
        3 -> R.drawable.img4
        4 -> R.drawable.img5
        5 -> R.drawable.img6
        else -> R.drawable.img7
    }

    Box(
        modifier = modifier
            .height(400.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = Color.Black,
            )
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = images),
            contentDescription = null,
            modifier = modifier
                .padding(23.dp)


        )
    }
}

@Composable
fun ArtWorkDescriptor(
    artWorkTitle: String,
    artWorkArtist: String,
    artWorkYear: String,
    modifier: Modifier = Modifier
) {
    Column (
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .background(color = Color(0xffECEBF4))
            .padding(16.dp)
            .fillMaxWidth()

    ){
        Text(
            text = artWorkTitle,
            fontSize = 24.sp,
            fontWeight = FontWeight.Light,
            color = Color.Black,
            modifier = modifier
                .padding(bottom = 10.dp)
            )
        Row {
            Text(
                text = artWorkArtist,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = modifier
                    .padding(end = 5.dp))
            Text(
                text = "($artWorkYear)",
                color = Color.Black,
                )
        }
    }
}

@Composable
fun DisplayController(
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 30.dp)
            .fillMaxWidth()

    ){
        Button(
            onClick = { onPreviousClick() },
            ) {
            Text(
                text = "Previous",
                modifier = modifier
                    .padding(horizontal = 8.dp)
            )
        }
        Button(
            onClick = {  onNextClick() },
            ) {
            Text(
                text = "Next",
                modifier = modifier
                    .padding(horizontal = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpaceLayout()
    }
}