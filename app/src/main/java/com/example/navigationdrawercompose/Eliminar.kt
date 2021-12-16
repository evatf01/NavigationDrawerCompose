package com.example.navigationdrawercompose

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
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

@Composable
fun PantallaEliminar(){
    var textFieldValueId by rememberSaveable { mutableStateOf("") }
    var bgColor by remember { mutableStateOf(Color(241, 112, 95))}
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
        .padding(start=45.dp,top=15.dp,end=40.dp)

    ) {
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = "ELIMINAR",
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


        Button(
            modifier = Modifier
                .padding(start = 190.dp, top = 70.dp)
                .size(width = 95.dp, height = 65.dp)
                .clip(RoundedCornerShape(100.dp))
                .border(4.dp, color = Color.Black, shape = RoundedCornerShape(100.dp)),
            onClick = {
                if(bgColor==Color(241, 112, 95)){
                    bgColor = Color(250,40,10)
                }else{
                    bgColor = Color(241, 112, 95)
                }
                if (textFieldValueId.isEmpty() ){
                    Toast.makeText(context, "RELLENE LOS CAMPOS", Toast.LENGTH_SHORT).show()

                }else{
                    if(borrar(textFieldValueId)){
                        Toast.makeText(context, "Borrado correctamente", Toast.LENGTH_SHORT).show()
                        textFieldValueId=""

                    }else{
                        Toast.makeText(context, "Error al insertar los datos", Toast.LENGTH_SHORT).show()

                    }

                }

            },colors = ButtonDefaults.buttonColors(
                backgroundColor =color.value,
            )){
            Image(
                painterResource(id = R.drawable.delete) , contentDescription = null,
                modifier = Modifier
                    .height(25.dp)
                    .width(25.dp))

        }

    }

}

fun borrar(id: String) : Boolean{

    val url = "http://iesayala.ddns.net/evatf01/eliminarmusica.php/?Id=$id"
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