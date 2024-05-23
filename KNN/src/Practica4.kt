/*
fun predecirRegresionKNN(punto: DataPoint, puntosDeDatos: List<DataPoint>, k: Int, tipoDistancia: Int): Double {

    val datosConDistancia = when (tipoDistancia) {
        1 -> puntosDeDatos.map { it to distanciaEuclidiana(punto, it) } // Lista de Pares, DataPoint y Distancia
        2 -> puntosDeDatos.map { it to distanciaManhattan(punto, it) } // Lista de Pares, DataPoint y Distancia
        else -> { puntosDeDatos.map { it to distanciaEuclidiana(punto, it) } }
    }

    val datosOrdenados = datosConDistancia.sortedBy { it.second } // Ordena la Lista segun la Distancia
    val kVecinos = datosOrdenados.take(k) // Toma los k vecinos
    val valoresKVecinos: List<Int> = kVecinos.map { it.first.volume } // Extrae los valores de k vecinos

    // Promedio de los valores de los k vecinos
    val promedio = valoresKVecinos.sum() / valoresKVecinos.size.toDouble()


    return promedio
}
*/