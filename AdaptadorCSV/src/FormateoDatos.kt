import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException


fun leerDataSetFechaHoras(direccionArchivo: String): List<String> {
    val archivo = File(direccionArchivo)
    val fechaHoras = mutableListOf<String>()

    //Preparar datos
    val fechasFormato = mutableListOf<String>()
    val horasFormato = mutableListOf<String>()

    archivo.readLines().forEachIndexed { i, linea ->
        if (i > 0) { // Skip the first row (header)
            val datosI = linea.split(",")

            fechasFormato.add(datosI.get(0).split(" ").get(0))
            horasFormato.add(datosI.get(0).split(" ").get(1))
        }
    }

    //Formatear datos
    val dia = mutableListOf<String>()
    val mes = mutableListOf<String>()
    val year = mutableListOf<String>()
    fechasFormato.forEach { fechaFormato ->
        mes.add(fechaFormato.split("/").get(0))
        dia.add(fechaFormato.split("/").get(1))
        year.add(fechaFormato.split("/").get(2))
    }

    val hora = mutableListOf<String>()
    horasFormato.forEach { horaFormato ->
        val horaMinuto = ((horaFormato.split(":").get(0).toInt() * 60) +
                horaFormato.split(":").get(1).toInt()).toString()

        hora.add(horaMinuto)
    }

    val datosFechasHoras = mutableListOf<String>()

    dia.forEachIndexed { i, linea ->
        datosFechasHoras.add(
            mes[i] + "," +
            dia[i] + "," +
            year[i] + "," +
            hora[i]
            )
    }

    return datosFechasHoras

}

fun crearArchivoDatosProcesados(tabla: List<String>, dirFile: String) {

    File(dirFile).bufferedWriter().use { output ->
        tabla.forEach { linea ->
            output.write(linea)
            output.newLine()
        }
    }

    println("\nArchivo datosProcesados.csv creado en la carpeta resources del proyecto")
}

fun formatearDataSet(datosFechasHoras: List<String>, dirFile: String): List<String> {

    val archivo = File(dirFile)
    val datos = mutableListOf<String>()
    datos.add(archivo.readLines().get(0).replace("FechaHora", "Mes,Dia,Year,Hora"))

    archivo.readLines().forEachIndexed {i, linea ->
        if (i > 0) {
            val datosI = linea.split(",")

            //0-6
            datos.add(
                datosFechasHoras[i-1] + "," +
                        datosI.get(1) + "," +
                        datosI.get(2) + "," +
                        datosI.get(3) + "," +
                        datosI.get(4) + "," +
                        datosI.get(5) + "," +
                        datosI.get(6)
            )
        }
    }

    return datos.shuffled()
}

fun exportarDataSetProcesado(tabla: List<String>, name: String) {

    File("resources/" + name + "Formateado.csv").bufferedWriter().use { output ->
        tabla.forEach { linea ->
            output.write(linea)
            output.newLine()
        }
    }

    println("\nArchivo creado en la carpeta resources del proyecto")
}