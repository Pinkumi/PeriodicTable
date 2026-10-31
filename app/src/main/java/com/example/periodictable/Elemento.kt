package com.example.periodictable

import androidx.compose.ui.graphics.Color

data class Elemento(
    val numero: Int,
    val simbolo: String,
    val nombre: String,
    val peso: String,
    val oxidacion: String,
    val familia: String,
    val clasificacion: String,
    val estado: String,
    val sintetico: Boolean = false
)

object Colores {
    val fondo = Color(0xFF00551C)
    val boton = Color(0xFF003B15)
    val texto = Color.White
    val verdeClaro = Color(0xFFB3FFF0)
    val textoOscuro = Color(0xFF143421)

    val solido = Color(0xFFFFE28A)
    val liquido = Color(0xFFFFA48F)
    val gas = Color(0xFF9FDCFF)
    val sintetico = Color(0xFFDDB5FF)
}

fun colorEstado(elemento: Elemento): Color {
    if (elemento.sintetico) {
        return Colores.sintetico
    }

    return when (elemento.estado) {
        "Sólido" -> Colores.solido
        "Líquido" -> Colores.liquido
        "Gas" -> Colores.gas
        else -> Color.LightGray
    }
}

fun colorFamilia(familia: String): Color {
    return when (familia) {
        "Alcalinos" -> Color(0xFFFFD48C)
        "Alcalinotérreos" -> Color(0xFFFFEBA8)
        "Metales de transición" -> Color(0xFFFFE394)
        "Halógenos" -> Color(0xFFC7B5FF)
        "Gases nobles" -> Color(0xFFB7DDFF)
        "Metaloides" -> Color(0xFFB8F1A5)
        "No metales" -> Color(0xFFCEFFA8)
        "Familia del boro" -> Color(0xFFFFD2A6)
        "Familia del carbono" -> Color(0xFFCDFFA8)
        "Familia del nitrógeno" -> Color(0xFFC3FFA8)
        "Calcógenos" -> Color(0xFFC9F7A9)
        else -> Color(0xFFFFD7A8)
    }
}

object DatosElementos {

    val lista = listOf(
        Elemento(1, "H", "Hidrógeno", "1.008", "+1, -1", "No metales", "No metal", "Gas"),
        Elemento(2, "He", "Helio", "4.003", "0", "Gases nobles", "No metal", "Gas"),
        Elemento(3, "Li", "Litio", "6.94", "+1", "Alcalinos", "Metal", "Sólido"),
        Elemento(4, "Be", "Berilio", "9.012", "+2", "Alcalinotérreos", "Metal", "Sólido"),
        Elemento(5, "B", "Boro", "10.81", "+3", "Metaloides", "Metaloide", "Sólido"),
        Elemento(6, "C", "Carbono", "12.01", "-4, +2, +4", "No metales", "No metal", "Sólido"),
        Elemento(7, "N", "Nitrógeno", "14.01", "-3, +3, +5", "Familia del nitrógeno", "No metal", "Gas"),
        Elemento(8, "O", "Oxígeno", "16.00", "-2, -1", "Calcógenos", "No metal", "Gas"),
        Elemento(9, "F", "Flúor", "19.00", "-1", "Halógenos", "No metal", "Gas"),
        Elemento(10, "Ne", "Neón", "20.18", "0", "Gases nobles", "No metal", "Gas"),

        Elemento(11, "Na", "Sodio", "22.99", "+1", "Alcalinos", "Metal", "Sólido"),
        Elemento(12, "Mg", "Magnesio", "24.31", "+2", "Alcalinotérreos", "Metal", "Sólido"),
        Elemento(13, "Al", "Aluminio", "26.98", "+3", "Familia del boro", "Metal", "Sólido"),
        Elemento(14, "Si", "Silicio", "28.09", "-4, +4", "Metaloides", "Metaloide", "Sólido"),
        Elemento(15, "P", "Fósforo", "30.97", "-3, +3, +5", "Familia del nitrógeno", "No metal", "Sólido"),
        Elemento(16, "S", "Azufre", "32.06", "-2, +4, +6", "Calcógenos", "No metal", "Sólido"),
        Elemento(17, "Cl", "Cloro", "35.45", "-1, +1, +3, +5, +7", "Halógenos", "No metal", "Gas"),
        Elemento(18, "Ar", "Argón", "39.95", "0", "Gases nobles", "No metal", "Gas"),
        Elemento(19, "K", "Potasio", "39.10", "+1", "Alcalinos", "Metal", "Sólido"),
        Elemento(20, "Ca", "Calcio", "40.08", "+2", "Alcalinotérreos", "Metal", "Sólido"),

        Elemento(21, "Sc", "Escandio", "44.96", "+3", "Metales de transición", "Metal", "Sólido"),
        Elemento(22, "Ti", "Titanio", "47.87", "+2, +3, +4", "Metales de transición", "Metal", "Sólido"),
        Elemento(23, "V", "Vanadio", "50.94", "+2, +3, +4, +5", "Metales de transición", "Metal", "Sólido"),
        Elemento(24, "Cr", "Cromo", "52.00", "+2, +3, +6", "Metales de transición", "Metal", "Sólido"),
        Elemento(25, "Mn", "Manganeso", "54.94", "+2, +3, +4, +7", "Metales de transición", "Metal", "Sólido"),
        Elemento(26, "Fe", "Hierro", "55.85", "+2, +3", "Metales de transición", "Metal", "Sólido"),
        Elemento(27, "Co", "Cobalto", "58.93", "+2, +3", "Metales de transición", "Metal", "Sólido"),
        Elemento(28, "Ni", "Níquel", "58.69", "+2, +3", "Metales de transición", "Metal", "Sólido"),
        Elemento(29, "Cu", "Cobre", "63.55", "+1, +2", "Metales de transición", "Metal", "Sólido"),
        Elemento(30, "Zn", "Zinc", "65.38", "+2", "Metales de transición", "Metal", "Sólido"),

        Elemento(31, "Ga", "Galio", "69.72", "+1, +3", "Familia del boro", "Metal", "Sólido"),
        Elemento(32, "Ge", "Germanio", "72.63", "+2, +4", "Metaloides", "Metaloide", "Sólido"),
        Elemento(33, "As", "Arsénico", "74.92", "-3, +3, +5", "Metaloides", "Metaloide", "Sólido"),
        Elemento(34, "Se", "Selenio", "78.97", "-2, +4, +6", "Calcógenos", "No metal", "Sólido"),
        Elemento(35, "Br", "Bromo", "79.90", "-1, +1, +3, +5", "Halógenos", "No metal", "Líquido"),
        Elemento(36, "Kr", "Kriptón", "83.80", "0, +2", "Gases nobles", "No metal", "Gas"),
        Elemento(37, "Rb", "Rubidio", "85.47", "+1", "Alcalinos", "Metal", "Sólido"),
        Elemento(38, "Sr", "Estroncio", "87.62", "+2", "Alcalinotérreos", "Metal", "Sólido"),
        Elemento(39, "Y", "Itrio", "88.91", "+3", "Metales de transición", "Metal", "Sólido"),
        Elemento(40, "Zr", "Circonio", "91.22", "+4", "Metales de transición", "Metal", "Sólido"),

        Elemento(41, "Nb", "Niobio", "92.91", "+3, +5", "Metales de transición", "Metal", "Sólido"),
        Elemento(42, "Mo", "Molibdeno", "95.95", "+2, +3, +4, +5, +6", "Metales de transición", "Metal", "Sólido"),
        Elemento(43, "Tc", "Tecnecio", "[98]", "+4, +7", "Metales de transición", "Metal", "Sólido", true),
        Elemento(44, "Ru", "Rutenio", "101.07", "+2, +3, +4, +8", "Metales de transición", "Metal", "Sólido"),
        Elemento(45, "Rh", "Rodio", "102.91", "+1, +3", "Metales de transición", "Metal", "Sólido"),
        Elemento(46, "Pd", "Paladio", "106.42", "+2, +4", "Metales de transición", "Metal", "Sólido"),
        Elemento(47, "Ag", "Plata", "107.87", "+1", "Metales de transición", "Metal", "Sólido"),
        Elemento(48, "Cd", "Cadmio", "112.41", "+2", "Metales de transición", "Metal", "Sólido"),
        Elemento(49, "In", "Indio", "114.82", "+1, +3", "Familia del boro", "Metal", "Sólido"),
        Elemento(50, "Sn", "Estaño", "118.71", "+2, +4", "Familia del carbono", "Metal", "Sólido")
    )
}
