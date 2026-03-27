package dam.exer_3



fun main(){
    val bounces = generateBounceSequence(100.0, 0.6)
        .takeWhile { it >= 1.0 }
        .map { String.format("%.2f", it) }
        .toList()

    println("Alturas : $bounces")
}


// Função que gera a sequência de rebotes
fun generateBounceSequence(initialHeight: Double, bounceFactor: Double): Sequence<Double> {
    return generateSequence(initialHeight) { previous ->
        previous * bounceFactor
    }
}