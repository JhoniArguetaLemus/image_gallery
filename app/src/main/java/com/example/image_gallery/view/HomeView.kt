package com.example.image_gallery.view

import android.annotation.SuppressLint
import android.provider.ContactsContract.Contacts.Photo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.image_gallery.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeView(){

    var selectedOption by remember { mutableStateOf("Inicio")}

    val options= listOf("Inicio", "San Miguel", "Ciudad Barrios", "San Salvador", "Morazan")


    val home_list= listOf(
        R.drawable.san_miguel2,
        R.drawable.san_miguel3,
        R.drawable.barrios1,
        R.drawable.barrios2
    )

    val sivar_list= listOf(
        R.drawable.sivar1,
        R.drawable.sivar2,
        R.drawable.sivar3,
        R.drawable.sivar4
    )


    val san_miguel= listOf(R.drawable.san_miguel1,
        R.drawable.san_miguel2,
        R.drawable.san_miguel3,


        )



    val ciudad_barrios = listOf(
        R.drawable.barrios1,
        R.drawable.barrios2,
        R.drawable.barrios3
    )

    val morazan= listOf(
        R.drawable.morazan1,
        R.drawable.morazan2,
        R.drawable.morazan3,
        R.drawable.morazan4
    )
    var drawerState= rememberDrawerState(DrawerValue.Closed)
    var scope= rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {



                ModalDrawerSheet {
                    Text("Gallery App", fontSize = 24.sp)
                    HorizontalDivider(modifier = Modifier.padding(20.dp))

                    options.forEach { option ->
                        NavigationDrawerItem(
                            label = { Text(option)},
                            selected = option==selectedOption,
                            onClick = {
                                selectedOption=option
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        )


                    }


                }



        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Gallery App")},
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Green
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if(drawerState.isOpen)drawerState.close() else drawerState.open()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Menu, "Menu")
                        }
                    }
                )
            }

        ) { paddingValues ->

            when(selectedOption){
                "Inicio"->PhotosView(home_list, paddingValues)
                "San Miguel"-> PhotosView(san_miguel, paddingValues)
                "Ciudad Barrios"-> PhotosView(ciudad_barrios, paddingValues)
                "San Salvador"-> PhotosView(sivar_list, paddingValues)
                "Morazan"-> PhotosView(morazan,paddingValues)
            }









        }


    }


}