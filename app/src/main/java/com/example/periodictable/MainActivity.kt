package com.example.periodictable

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var mostrarDescripcion by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Colores.fondo)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Tabla Periódica",
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(50.dp))

                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo de la aplicación",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                )

                Spacer(modifier = Modifier.height(65.dp))

                Button(
                    onClick = {
                        val intent = Intent(
                            this@MainActivity,
                            CatalogoActivity::class.java
                        )
                        startActivity(intent)
                    },
                    modifier = Modifier.width(180.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Colores.boton
                    )
                ) {
                    Text("Catálogo", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = {
                        mostrarDescripcion = true
                    },
                    modifier = Modifier.width(180.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Colores.boton
                    )
                ) {
                    Text("Descripción", fontSize = 18.sp)
                }
            }

            if (mostrarDescripcion) {
                AlertDialog(
                    onDismissRequest = {
                        mostrarDescripcion = false
                    },
                    title = {
                        Text("Tabla Periódica")
                    },
                    text = {
                        Text(
                            "Esta aplicación permite consultar los primeros " +
                                    "50 elementos de la tabla periódica, conocer " +
                                    "sus propiedades y buscarlos utilizando filtros."
                        )
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                mostrarDescripcion = false
                            }
                        ) {
                            Text("Cerrar")
                        }
                    }
                )
            }
        }
    }
}
