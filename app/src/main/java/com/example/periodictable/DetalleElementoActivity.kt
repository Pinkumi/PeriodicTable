package com.example.periodictable


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DetalleElementoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val numero = intent.getIntExtra("numero", 1)
        val elemento = DatosElementos.lista.find {
            it.numero == numero
        }
        if (elemento == null) {
            finish()
            return
        }

        setContent {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Colores.fondo)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "Elemento",
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(40.dp))

                Column(
                    modifier = Modifier
                        .size(205.dp)
                        .background(
                            colorEstado(elemento),
                            RoundedCornerShape(18.dp)
                        )
                        .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = elemento.numero.toString(),
                        color = Color.Black,
                        fontSize = 21.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Text(
                        text = elemento.simbolo,
                        color = Color.Black,
                        fontSize = 82.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            elemento.nombre,
                            color = Color.Black,
                            fontSize = 14.sp
                        )

                        Text(
                            elemento.peso,
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(35.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    DatoElemento("Estado físico:", elemento.estado)

                    DatoElemento(
                        "Número atómico:",
                        elemento.numero.toString()
                    )

                    DatoElemento(
                        "Peso atómico:",
                        "${elemento.peso} u"
                    )

                    DatoElemento(
                        "Estados de oxidación:",
                        elemento.oxidacion
                    )

                    DatoElemento(
                        "Familia:",
                        elemento.familia
                    )

                    DatoElemento(
                        "Clasificación:",
                        elemento.clasificacion
                    )

                    DatoElemento(
                        "Sintético:",
                        if (elemento.sintetico) "Sí" else "No"
                    )
                }

                Spacer(modifier = Modifier.height(35.dp))

                Button(
                    onClick = {
                        finish()
                    },
                    modifier = Modifier.width(160.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Colores.boton
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        "Regresar",
                        color = Color.White
                    )
                }
            }
        }
    }
}
@Composable
fun DatoElemento(nombre: String, valor: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = nombre,
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = valor,
            color = Color(0xFFCCF4D4),
            fontSize = 15.sp
        )
    }
}
