import java.io.File
import kotlin.math.abs
import kotlin.math.sqrt

fun generarFilaTabla(i: Int, datosPrueba: List<DataPoint>, datosEntrenamiento: List<DataPoint>, k: Int, metodo: Int): String {
    val prediccion = String.format("%.1f", predecirRegresionKNN(datosPrueba.get(i), datosEntrenamiento, k, metodo))
    val realidad = datosPrueba.get(i).quality
    val id = i+1

    //Tabla

    val evals = generarEvaluaciones()
    val dato = listOf("$id",prediccion, realidad)

    val fila = dato.joinToString(",")

    return fila
}

fun imprimirDatos(i: Int, datosPrueba: List<DataPoint>, datosEntrenamiento: List<DataPoint>, k: Int, metodo: Int): String {
    val prediccion = String.format("%.1f", predecirRegresionKNN(datosPrueba.get(i), datosEntrenamiento, k, metodo))
    val realidad = datosPrueba.get(i).quality
    val id = i+1

    //Puro texto
    val texto = "\nDato $id" +
            " - Prediccion: clase $prediccion" +
            ", Realidad: clase $realidad"


    //Tabla
    val cabecera = listOf("ID","Prediccion","Realidad")//,"0","1","2","3","4")
    //val evals = generarEvaluaciones(prediccion, realidad)
    val dato = listOf("$id",prediccion, realidad.toString())
    //    evals.get(0),
    //    evals.get(1),
    //    evals.get(2),
    //    evals.get(3),
    //    evals.get(4),)

    val tabla = "\n${cabecera.joinToString(" | ", prefix = "| ", postfix = " |") { it.padEnd(10) }}" +
            "\n${dato.joinToString(" | ", prefix = "| ", postfix = " |") { it.padEnd(10) }}"



    return texto + tabla
}

fun generarEvaluaciones(): List<String> {
    val evals = mutableListOf("R2","MAE","RMSE","RAE","RRSE")

    //Sacar valores de datosProcesados, solo row 1 en adelante y col 1 y 2

    val yReal = leerDatosProcesados(real = true)
    val yPred = leerDatosProcesados(real = false)

    evals.set(0, calcularR2(yReal, yPred).toString())
    evals.set(1, calcularMAE(yReal, yPred).toString())
    evals.set(2, calcularRMSE(yReal, yPred).toString())
    evals.set(3, calcularRAE(yReal, yPred).toString())
    evals.set(4, calcularRRSE(yReal, yPred).toString())

    return evals
}

fun leerDatosProcesados(real: Boolean): (List<Double>) {
    val archivo = File("resources/datosProcesados.csv")
    val datos: MutableList<Double> = mutableListOf()

    archivo.readLines().forEachIndexed { i, linea ->
        if (i > 1) { // Skip the first row (header)
            val datosI = linea.split(",")

            val y: Double
            if (real)
                y = datosI[2].toDouble()
            else //prediccion
                y = datosI[1].toDouble()

            datos.add(y)
        }



    }

    return datos.toList()
}

fun calcularR2(yReal: List<Double>, yPred: List<Double>): Double {
    val n = yReal.size
    val mediaY = yReal.average()
    var sumSquareResiduals = 0.0
    var sumSquareTotal = 0.0
    for (i in 0 until n) {
        //sumSquareResiduals += (yReal[i] - yPred[i]) * (yReal[i] - yPred[i])
        //sumSquareTotal += (yReal[i] - mediaY) * (yReal[i] - mediaY)
        sumSquareResiduals += (yPred[i] - mediaY) * (yPred[i] - mediaY)
        sumSquareTotal += (yReal[i] - mediaY) * (yReal[i] - mediaY)
    }
    return (sumSquareResiduals / sumSquareTotal)
}

fun calcularMAE(yReal: List<Double>, yPred: List<Double>): Double {
    val n = yReal.size
    var mae = 0.0
    for (i in 0 until n) {
        mae += Math.abs(yReal[i] - yPred[i])
    }
    return mae / n
}

fun calcularRMSE(yReal: List<Double>, yPred: List<Double>): Double {
    val n = yReal.size
    var mse = 0.0
    for (i in 0 until n) {
        mse += (yPred[i] - yReal[i]) * (yPred[i] - yReal[i])
    }
    return sqrt(mse / n)
}

fun calcularRAE(yReal: List<Double>, yPred: List<Double>): Double {
    val n = yReal.size
    val mediaY = yReal.average()
    var num = 0.0
    var denom = 0.0
    for (i in 0 until n) {
        num += abs(yReal[i] - yPred[i])
        denom += abs(yReal[i] - mediaY)
    }
    return (num / denom)
}

fun calcularRRSE(yReal: List<Double>, yPred: List<Double>): Double {
    val n = yReal.size
    val mediaY = yReal.average()
    var sumSquareResiduals = 0.0
    var sumObservasion = 0.0
    for (i in 0 until n) {
        sumSquareResiduals += (yReal[i] - yPred[i]) * (yReal[i] - yPred[i])
        sumObservasion += yPred[i]
    }
    return sqrt(sumSquareResiduals / sumObservasion)
}



