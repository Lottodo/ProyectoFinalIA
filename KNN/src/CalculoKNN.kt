import kotlin.math.abs
import kotlin.math.sqrt


fun predecirRegresionKNN(point: DataPoint, dataPoints: List<DataPoint>, k: Int, distanceType: Int): Double {
    val dataConDistancia =
        if (distanceType == 1) { dataPoints.map { it to distanciaEuclidiana(point, it) }}
        else { dataPoints.map { it to distanciaManhattan(point, it) } }

    val dataOrdenada = dataConDistancia.sortedBy { it.second }
    val kVecinos = dataOrdenada.take(k)
    val volumePromedio = kVecinos.map { it.first.quality }.average()

    return volumePromedio
}

fun distanciaEuclidiana(p1: DataPoint, p2: DataPoint): Double{
    //fixed acidity, volatile acidity, citric acid, residual sugar, chlorides, free sulfur dioxide, total sulfur dioxide, density, pH, sulphates, alcohol, quality
    val dis_fix_acid = p1.fixed_acidity - p2.fixed_acidity
    val dis_vol_acid = p1.volatile_acidity - p2.volatile_acidity
    val dis_citric_acid = p1.citric_acid - p2.citric_acid
    val dis_res_sugar = p1.residual_sugar - p2.residual_sugar
    val dis_chlorides = p1.chlorides - p2.chlorides
    val dis_free_sulfur = p1.free_sulfur_dioxide - p2.free_sulfur_dioxide
    val dis_density = p1.density - p2.density
    val dis_pH = p1.pH - p2.pH
    val dis_sulphates = p1.sulphates - p2.sulphates
    val dis_alcohol = p1.alcohol - p2.alcohol


    val dis = sqrt((dis_fix_acid * dis_fix_acid) +
            (dis_vol_acid * dis_vol_acid) +
            (dis_citric_acid * dis_citric_acid) +
            (dis_res_sugar * dis_res_sugar) +
            (dis_chlorides * dis_chlorides) +
            (dis_free_sulfur * dis_free_sulfur) +
            (dis_density * dis_density) +
            (dis_pH * dis_pH) +
            (dis_sulphates * dis_sulphates) +
            (dis_alcohol * dis_alcohol))

    return dis
}

fun distanciaManhattan(p1: DataPoint, p2: DataPoint): Double {
    val dis_fix_acid = abs(p1.fixed_acidity - p2.fixed_acidity)
    val dis_vol_acid = abs(p1.volatile_acidity - p2.volatile_acidity)
    val dis_citric_acid = abs(p1.citric_acid - p2.citric_acid)
    val dis_res_sugar = abs(p1.residual_sugar - p2.residual_sugar)
    val dis_chlorides = abs(p1.chlorides - p2.chlorides)
    val dis_free_sulfur = abs(p1.free_sulfur_dioxide - p2.free_sulfur_dioxide)
    val dis_density = abs(p1.density - p2.density)
    val dis_pH = abs(p1.pH - p2.pH)
    val dis_sulphates = abs(p1.sulphates - p2.sulphates)
    val dis_alcohol = abs(p1.alcohol - p2.alcohol)

    val dis = dis_fix_acid + dis_vol_acid + dis_citric_acid +
            dis_res_sugar + dis_chlorides + dis_free_sulfur +
            dis_density + dis_pH + dis_sulphates + dis_alcohol

    return dis
}

