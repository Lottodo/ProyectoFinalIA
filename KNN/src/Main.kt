//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {



    //Preparacion de banderas, lectura y procesamiento
    var leerTodos = false
    var salir = false
    var opcion: String

    val tabla = mutableListOf(String())
    tabla.add(listOf("ID","Prediccion","Realidad")
        .joinToString(","))
    tabla.removeAt(0)


    //Pedir datos
    val archivoEntrenamiento = seleccionarArchivo("Seleccionar datos de entrenamiento",
        "resources/winequality-red_entrenamiento.csv")
    println("Archivo '$archivoEntrenamiento' con ID: ${leerDataSetID(archivoEntrenamiento)} seleccionado como entrenamiendo")
    val archivoPrueba = seleccionarArchivo("Seleccionar datos de prueba",
        "resources/winequality-red_prueba.csv")
    println("Archivo '$archivoPrueba' con ID: ${leerDataSetID(archivoPrueba)} seleccionado como prueba")

    val datosEntrenamiento = leerDataSet(archivoEntrenamiento)
    val datosPrueba = leerDataSet(archivoPrueba)

    print("Valor de K:\n \t -> ")
    val k = Integer.valueOf(readlnOrNull())

    print("Método de distancias: \t 1.EUCLIDIANA \t 2.MANHATTAN\n \t -> ")
    val metodo = Integer.valueOf(readlnOrNull())


    //Imprimir en pantalla datos procesados
    for (i in datosPrueba.indices) {
        tabla.add(generarFilaTabla(i, datosPrueba, datosEntrenamiento, k, metodo))
        println("\n${imprimirDatos(i, datosPrueba, datosEntrenamiento, k, metodo)}")

        if (leerTodos == false) {

            print("[C]ontinuar con el siguiente dato \t [T]erminar de procesar datos \t [S]alir\n \t -> ")
            opcion = readLine().toString()
            when (opcion.uppercase()) {
                "C" -> println("")
                "T" -> leerTodos = true
                "S" -> salir = true
                else -> println("\nIngresa un valor valido")
            }
        }
        if (salir) break
    }

    crearArchivoDatosProcesados(tabla)

    // R2, MAE, MSE, RMSE, MAPE
    val evaluaciones = generarEvaluaciones()
    println("\nR2: ${evaluaciones[0]}")
    println("MAE: ${evaluaciones[1]}")
    println("RMSE: ${evaluaciones[2]}")
    println("RAE: ${evaluaciones[3]}")
    println("RRSE: ${evaluaciones[4]}")

}