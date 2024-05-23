import kotlin.math.abs
import kotlin.math.sqrt


fun predecirRegresionKNN(point: DataPoint, dataPoints: List<DataPoint>, k: Int, distanceType: Int): Double {
    val dataConDistancia =
        if (distanceType == 1) { dataPoints.map { it to distanciaEuclidiana(point, it) }}
        else { dataPoints.map { it to distanciaManhattan(point, it) } }

    val dataOrdenada = dataConDistancia.sortedBy { it.second }
    val kVecinos = dataOrdenada.take(k)
    val volumePromedio = kVecinos.map { it.first.reacciones }.average()

    return volumePromedio
}

fun distanciaEuclidiana(p1: DataPoint, p2: DataPoint): Double{
    //Mes,Dia,Year,Hora,Impresiones,Alcance,Comentarios,Compartidos,Clics,Reacciones
    val dis_mes = p1.mes - p2.mes
    val dis_dia = p1.dia - p2.dia
    val dis_year = p1.year - p2.year
    val dis_hora = p1.hora - p2.hora
    val dis_impresiones = p1.impresiones - p2.impresiones
    val dis_alcance = p1.alcance - p2.alcance
    val dis_comentarios = p1.comentarios - p2.comentarios
    val dis_compartidos = p1.compartidos - p2.compartidos
    val dis_clics = p1.clics - p2.clics


    val dis = sqrt((dis_mes * dis_mes) +
            (dis_dia * dis_dia) +
            (dis_year * dis_year) +
            (dis_hora * dis_hora) +
            (dis_impresiones * dis_impresiones) +
            (dis_alcance * dis_alcance))// +
            //(dis_comentarios * dis_comentarios) +
            //(dis_compartidos * dis_compartidos) +
            //(dis_clics * dis_clics))

    return dis
}

fun distanciaManhattan(p1: DataPoint, p2: DataPoint): Double {
    val dis_mes = abs(p1.mes - p2.mes)
    val dis_dia = abs(p1.dia - p2.dia)
    val dis_year = abs(p1.year - p2.year)
    val dis_hora = abs(p1.hora - p2.hora)
    val dis_impresiones = abs(p1.impresiones - p2.impresiones)
    val dis_alcance = abs(p1.alcance - p2.alcance)
    val dis_comentarios = abs(p1.comentarios - p2.comentarios)
    val dis_compartidos = abs(p1.compartidos - p2.compartidos)
    val dis_clics = abs(p1.clics - p2.clics)

    val dis = dis_mes + dis_dia + dis_year +
            dis_hora + dis_impresiones + dis_alcance// +
            //dis_comentarios + dis_compartidos + dis_clics

    return dis
}

