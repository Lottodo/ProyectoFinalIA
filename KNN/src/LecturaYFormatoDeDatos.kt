import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

fun leerDataSet(direccionArchivo: String): (List<DataPoint>) {
    val archivo = File(direccionArchivo)
    val datos = mutableListOf<DataPoint>()

    archivo.readLines().forEachIndexed { i, linea ->
        if (i > 1) { // Skip the first row (header)
            val datosI = linea.split(",")

            val mes: Double = datosI.get(0).toDouble()
            val dia: Double = datosI.get(1).toDouble()
            val year: Double = datosI.get(2).toDouble()
            val hora: Double = datosI.get(3).toDouble()
            val impresiones: Double = datosI.get(4).toDouble()
            val alcance: Double = datosI.get(5).toDouble()
            val comentarios: Double = datosI.get(6).toDouble()
            val compartidos: Double = datosI.get(7).toDouble()
            val clics: Double = datosI.get(8).toDouble()
            val reacciones: Double = datosI.get(9).toDouble()

            datos.add(DataPoint(mes,dia,year,
                hora,impresiones,alcance,
                comentarios,compartidos,clics,
                reacciones))
        }
    }

    return datos.toList()
}

fun seleccionarArchivo(tituloVentana: String, archivoDefault: String): String {
    val fileChooser = JFileChooser()
    fileChooser.dialogTitle = tituloVentana
    val desktop = javax.swing.filechooser.FileSystemView.getFileSystemView().homeDirectory
    fileChooser.currentDirectory = desktop

    // Filtrar solo archivos .txt
    val filter = FileNameExtensionFilter("Archivos separados con coma", "csv")
    fileChooser.fileFilter = filter

    val result = fileChooser.showOpenDialog(null)
    if (result == JFileChooser.APPROVE_OPTION) {
        val selectedFilePath = fileChooser.selectedFile.absolutePath
        // Aquí puedes manejar la ruta del archivo seleccionado
        return selectedFilePath.toString()
    }
    return archivoDefault
}

fun leerDataSetID(direccionArchivo: String): String {
    return File(direccionArchivo).readLines().get(0)
}

fun crearArchivoDatosProcesados(tabla: List<String>) {

    File("resources/datosProcesados.csv").bufferedWriter().use { output ->
        tabla.forEach { linea ->
            output.write(linea)
            output.newLine()
        }
    }

    println("\nArchivo datosProcesados.csv creado en la carpeta resources del proyecto")
}