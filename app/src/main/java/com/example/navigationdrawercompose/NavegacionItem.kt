package com.example.navigationdrawercompose

sealed class NavegacionItem(var route: String, var icon: Int, var title: String)
{
    object Insertar : NavegacionItem("insertar", R.drawable.aniadir, "Insertar")
    object Borrar : NavegacionItem("eliminar", R.drawable.delete, "Eliminar")
    object Mostrar : NavegacionItem("mostrar", R.drawable.leer, "Mostrar")

}