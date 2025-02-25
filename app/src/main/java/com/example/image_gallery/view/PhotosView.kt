package com.example.image_gallery.view


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable

fun PhotosView(images:List<Int>, paddingValues: PaddingValues){

    var selectedImage by remember { mutableStateOf<Int?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    if(showDialog && selectedImage !=null){
        Dialog(onDismissRequest = {showDialog=false}) {
            Box(
                modifier=Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .wrapContentSize()
                    .background(Color.White.copy(alpha = 0.7f))

            ){

                Image(painter = painterResource(selectedImage!!),
                    contentDescription = null,
                    modifier = Modifier.size(400.dp)
                )
            }

        }
    }

    Column(
        Modifier.padding(paddingValues)
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            Modifier.fillMaxWidth()
        ) {

            items(images){image->

                Image(painter = painterResource(image),
                    contentDescription = null,
                    Modifier.padding(10.dp)

                        .border(1.dp, Color.Green)
                        .size(200.dp)
                        .clickable {

                            selectedImage=image
                            showDialog=true
                        },
                    contentScale = ContentScale.Fit
                )


            }

        }

    }

}