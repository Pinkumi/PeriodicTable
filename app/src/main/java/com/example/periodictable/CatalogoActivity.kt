package com.example.periodictable

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class CatalogoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var familia by rememberSaveable { mutableStateOf("Todos") }
            var letra by rememberSaveable { mutableStateOf("Todos") }
            var clasificacion by rememberSaveable { mutableStateOf("Todos") }
            var estado by rememberSaveable { mutableStateOf("Todos") }
            var sinteticos by rememberSaveable { mutableStateOf(false) }
            var mostrarFiltros by rememberSaveable { mutableStateOf(true) }

            val familias = listOf(
                "Todos",
                "Alcalinos",
                "Alcalinotérreos",
                "Metales de transición",
                "Familia del boro",
                "Familia del carbono",
                "Familia del nitrógeno",
                "Calcógenos",
                "Halógenos",
                "Gases nobles",
                "Metaloides",
                "No metales"
            )

            val letras = listOf("Todos") +
                    ('A'..'Z').map { it.toString() }

            val clasificaciones = listOf(
                "Todos", "Metal", "No metal", "Metaloide"
            )

            val estados = listOf(
                "Todos", "Sólido", "Líquido", "Gas"
            )
            val resultados = ArrayList<Elemento>()

            for (elemento in DatosElementos.lista) {
                var mostrar = true

                if (familia != "Todos" && elemento.familia != familia) {
                    mostrar = false
                }

                if (letra != "Todos" &&
                    !elemento.nombre.uppercase().startsWith(letra)) {
                    mostrar = false
                }

                if (clasificacion != "Todos" &&
                    elemento.clasificacion != clasificacion) {
                    mostrar = false
                }

                if (estado != "Todos" && elemento.estado != estado) {
                    mostrar = false
                }

                if (sinteticos && !elemento.sintetico) {
                    mostrar = false
                }

                if (mostrar) {
                    resultados.add(elemento)
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Colores.fondo)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                item {
                    Text(
                        text = "Catálogo de elementos",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Colores.boton,
                                RoundedCornerShape(15.dp)
                            )
                            .padding(12.dp)
                    ) {

                        TextButton(
                            onClick = {
                                mostrarFiltros = !mostrarFiltros
                            },
                            modifier = Modifier.align(
                                Alignment.CenterHorizontally
                            )
                        ) {
                            Text(
                                text = if (mostrarFiltros)
                                    "Filtros ▲"
                                else
                                    "Filtros ▼",
                                color = Color.White,
                                fontSize = 21.sp
                            )
                        }

                        if (mostrarFiltros) {
                            MenuFiltro("Familia:", familias, familia) {
                                familia = it
                            }
                            MenuFiltro("Letra inicial:", letras, letra) {
                                letra = it
                            }
                            MenuFiltro(
                                "Clasificación:",
                                clasificaciones,
                                clasificacion
                            ) {
                                clasificacion = it
                            }
                            MenuFiltro("Estado físico:", estados, estado) {
                                estado = it
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = sinteticos,
                                    onCheckedChange = {
                                        sinteticos = it
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = Colores.verdeClaro,
                                        checkmarkColor = Colores.textoOscuro
                                    )
                                )

                                Text(
                                    "Solo sintéticos",
                                    color = Color.White
                                )
                            }

                            Button(
                                onClick = {
                                    familia = "Todos"
                                    letra = "Todos"
                                    clasificacion = "Todos"
                                    estado = "Todos"
                                    sinteticos = false
                                },
                                modifier = Modifier.align(
                                    Alignment.CenterHorizontally
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Colores.fondo
                                )
                            ) {
                                Text("Limpiar filtros")
                            }
                        }
                    }
                }

                item {
                    Text(
                        text = "Resultados encontrados: ${resultados.size}",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                if (resultados.isEmpty()) {
                    item {
                        Text(
                            text = "No se encontraron elementos con estos filtros.",
                            color = Color.White,
                            fontSize = 17.sp,
                            modifier = Modifier.padding(vertical = 35.dp)
                        )
                    }
                } else {
                    items(
                        items = resultados,
                        key = { it.numero }
                    ) { elemento ->

                        TarjetaElemento(elemento) {
                            val intent = Intent(
                                this@CatalogoActivity,
                                DetalleElementoActivity::class.java
                            )

                            intent.putExtra("numero", elemento.numero)
                            startActivity(intent)
                        }
                    }
                }

                item {
                    Column {
                        Text(
                            "Leyenda:",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            ColorLeyenda("Sólido", Colores.solido)
                            ColorLeyenda("Líquido", Colores.liquido)
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            ColorLeyenda("Gas", Colores.gas)
                            ColorLeyenda("Sintético", Colores.sintetico)
                        }
                    }
                }

                item {
                    TextButton(
                        onClick = { finish() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Regresar", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun MenuFiltro(
    titulo: String,
    opciones: List<String>,
    seleccionado: String,
    cambiar: (String) -> Unit
) {
    var abierto by remember { mutableStateOf(false) }

    Text(
        text = titulo,
        color = Color.White,
        fontSize = 13.sp
    )

    Box {
        Button(
            onClick = { abierto = true },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB2D68D),
                contentColor = Colores.textoOscuro
            )
        ) {
            Text(seleccionado)
            Spacer(modifier = Modifier.weight(1f))
        }

        DropdownMenu(
            expanded = abierto,
            onDismissRequest = { abierto = false }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        cambiar(opcion)
                        abierto = false
                    }
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun TarjetaElemento(
    elemento: Elemento,
    seleccionar: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                colorFamilia(elemento.familia),
                RoundedCornerShape(12.dp)
            )
            .clickable { seleccionar() }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(70.dp)
                .background(
                    colorEstado(elemento),
                    RoundedCornerShape(8.dp)
                )
                .border(
                    1.dp,
                    Colores.textoOscuro,
                    RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    elemento.numero.toString(),
                    fontSize = 11.sp
                )

                Text(
                    elemento.simbolo,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.width(15.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                elemento.nombre,
                color = Colores.textoOscuro,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                elemento.clasificacion,
                color = Colores.textoOscuro,
                fontSize = 13.sp
            )

            Text(
                if (elemento.sintetico)
                    "Sintético · ${elemento.estado}"
                else
                    elemento.estado,
                color = Colores.textoOscuro,
                fontSize = 12.sp
            )
        }

        Text(
            "›",
            fontSize = 30.sp,
            color = Colores.textoOscuro
        )
    }
}

@Composable
fun ColorLeyenda(nombre: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(13.dp)
                .background(color, RoundedCornerShape(3.dp))
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            nombre,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}
