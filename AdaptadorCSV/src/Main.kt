import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val dirFile = "resources/DatasetLimpio.csv"

    val fechasHoras: List<String> = leerDataSetFechaHoras(dirFile)

    exportarDataSetProcesado(
        tabla = formatearDataSet(
            datosFechasHoras = fechasHoras,
            dirFile = dirFile
        ),
        name = dirFile
            .replace("resources/","")
            .replace(".csv","")
    )


}

