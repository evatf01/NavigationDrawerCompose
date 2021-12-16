package com.example.navigationdrawercompose

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import com.example.navigationdrawercompose.modelo.ModeloArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun PantallaMostrar(){

        var lista= cargarJson()
        LazyColumn()
        {
            items(lista) { usu ->
                Column(modifier = Modifier
                    .fillMaxWidth().height(height = 135.dp)
                    .padding(start=20.dp,end=20.dp,bottom = 10.dp,top=10.dp).background(Color(232, 250, 255))
                    .border(
                        2.dp, Color.Black,
                        shape = RoundedCornerShape(30.dp))) {
                    Spacer(modifier = Modifier.size(5.dp))
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(text = "ID: "+usu.Id, textAlign = TextAlign.Center,color = Color.Black)

                    }
                    Spacer(modifier = Modifier.size(5.dp))
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(text = "GRUPO: "+usu.Grupo, textAlign = TextAlign.Center,color = Color.Black)
                    }
                    Spacer(modifier = Modifier.size(5.dp))

                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(text = "ALBUM: "+usu.Album, textAlign = TextAlign.Center,color = Color.Black)
                    }
                    Spacer(modifier = Modifier.size(5.dp))
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(text = "AÑO: "+usu.Anio, textAlign = TextAlign.Center,color = Color.Black)
                    }
                }


            }
        }
    }

@Composable
fun cargarJson(): ModeloArray {
    val context = LocalContext.current
    var albums by rememberSaveable { mutableStateOf(ModeloArray()) }
    val album = AlbumInstance.albumInterface.albumInformation()

    album.enqueue(object : Callback<ModeloArray> {
        override fun onResponse(
            call: Call<ModeloArray>,
            response: Response<ModeloArray>
        ) {
            val albumInfo: ModeloArray? = response.body()
            if (albumInfo != null) {
                albums = albumInfo

            }

        }

        override fun onFailure(call: Call<ModeloArray>, t: Throwable)
        {

        }

    })
    Log.d("datos",albums.toString())
    return albums

}


