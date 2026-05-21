/* problema 1 laborator
fun Int.NrPrim(): Boolean
{
    if (this < 2) return false
    if (this == 2) return true
    if (this % 2 == 0) return false
    var i = 3
    while (i * i <= this) {
        if (this % i == 0) return false
        i += 2
    }
    return true
}

fun main() {
    println(2.NrPrim())
    println(7.NrPrim())
    println(10.NrPrim())
    println(1.NrPrim())
}*/


//problema 1 acasa
fun main()
{
    val list = listOf(1, 25, 75, 39, 7, 2, 35, 3, 31, 7, 8)

    val filtru = list.filter { it >= 5 }
    println("Dupa filter: $filtru")

    val perechi = filtru.zipWithNext().filterIndexed { index, pair -> index % 2 == 0 }
    println("Dupa zip: $perechi")

    val produs = perechi.map { (a, b) -> a * b }
    println("Dupa inmultire: $produs")

    val suma = produs.sum()
    println("Suma finala: $suma")
}