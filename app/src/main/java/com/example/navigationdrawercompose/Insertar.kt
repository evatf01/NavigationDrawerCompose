package com.example.navigationdrawercompose

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.URL

@ExperimentalAnimationApi
@Composable
fun PantallaInsert(){
    var textFieldValueId by rememberSaveable { mutableStateOf("") }
    var textFieldValueGrupo by rememberSaveable { mutableStateOf("") }
    var textFieldValueAlbum by rememberSaveable { mutableStateOf("") }
    var textFieldValueAnio by rememberSaveable { mutableStateOf("") }
    var bgColor by remember { mutableStateOf(Color(220,254,254))}
    val color = animateColorAsState(
        targetValue = bgColor,
        animationSpec = tween(
            durationMillis = 2000
        )
    )

    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(250, 250, 250 ))
        .padding(start=45.dp,top=15.dp,end=40.dp)) {
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = "INSERTAR",
            textAlign = TextAlign.Center,
            fontSize = 25.sp,

            color = Color.Black,

            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)

        )

        TextField(
            value = textFieldValueId,
            onValueChange = { nuevo ->
                textFieldValueId = nuevo
            },
            label = {
                Text(text = "Id")
            },
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(240, 240, 240)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(textAlign = TextAlign.Left)
        )

        TextField(
            value = textFieldValueGrupo,
            onValueChange = { nuevo ->
                textFieldValueGrupo = nuevo
            },
            label = {
                Text(text = "Grupo")
            },
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(240, 240, 240)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(textAlign = TextAlign.Left)
        )



        TextField(
            value = textFieldValueAlbum,
            onValueChange = { nuevo ->
                textFieldValueAlbum = nuevo
            },
            label = {
                Text(text = "Album")
            },
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(240, 240, 240)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(textAlign = TextAlign.Left)
        )
        TextField(
            value = textFieldValueAnio,
            onValueChange = { nuevo ->
                textFieldValueAnio = nuevo
            },
            label = {
                Text(text = "Año")
            },

            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(6.dp))

                .background(Color(240, 240, 240)),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(textAlign = TextAlign.Left, color = Color(0,0,0))
        )

        Button(
            modifier = Modifier
                .padding(start = 190.dp, top = 120.dp)
                .size(width = 95.dp, height = 65.dp)
                .clip(RoundedCornerShape(100.dp))
                .border(4.dp, color = Color.Black, shape = RoundedCornerShape(100.dp)),

            onClick = {
                if(bgColor==Color(220,254,254)){
                    bgColor = Color(140,170,254)
                }else{
                    bgColor = Color(220,254,254)
                }
                if (textFieldValueAlbum.isEmpty() || textFieldValueGrupo.isEmpty() || textFieldValueAnio
                        .isEmpty()){
                    Toast.makeText(context, "RELLENE LOS CAMPOS", Toast.LENGTH_SHORT).show()

                }else{
                    if(insertar(textFieldValueId,textFieldValueGrupo, textFieldValueAlbum,textFieldValueAnio)){
                        Toast.makeText(context, "Añadido correctamente", Toast.LENGTH_SHORT).show()
                        textFieldValueId=""
                        textFieldValueGrupo=""
                        textFieldValueAlbum=""
                        textFieldValueAnio=""
                    }else{
                        Toast.makeText(context, "Error al insertar los datos", Toast.LENGTH_SHORT).show()
                    }
                }
            },colors = ButtonDefaults.buttonColors(color.value),
            elevation = ButtonDefaults.elevation(8.dp)){
            Image(
                painterResource(id = R.drawable.checkmark) , contentDescription = null,
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp))

        }

    }

}


private fun insertar(id: String, grupo:String, album:String, anio:String) : Boolean{

    val url = "http://iesayala.ddns.net/evatf01/insertmusica.php/?Id=$id&Grupo=$grupo&Album=$album&Anio=$anio"
    return leerUrl(url)

}

private fun leerUrl(urlString:String) : Boolean{
    var boleano = true
    GlobalScope.launch(Dispatchers.IO)   {
        val response = try {
            URL(urlString)
                .openStream()
                .bufferedReader()
                .use { it.readText() }

        } catch (e: IOException) {
            "Error with ${e.message}."
            Log.d("io", e.message.toString())
            boleano = false

        } catch (e: Exception) {
            "Error with ${e.message}."
            Log.d("io", e.message.toString())

        }

    }
return boleano
}