import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val fechasHoras: List<String> = leerDataSetFechaHoras("resources/TestingDS.csv")
    exportarDataSetProcesado(formatearDataSet(fechasHoras), "TestingDSFormat")


}

