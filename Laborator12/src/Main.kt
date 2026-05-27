import java.io.File
import kotlin.math.sqrt

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

fun cifraCaesar(text: String, offset: Int): String
{
    return text.map { c ->
        when
        {
            c in 'a'..'z' -> ((c - 'a' + offset) % 26 + 'a'.code).toChar()
            c in 'A'..'Z' -> ((c - 'A' + offset) % 26 + 'A'.code).toChar()
            else -> c
        }
    }.joinToString("")
}

data class Point(val x: Double, val y: Double)

fun distance(a: Point, b: Point): Double
{
    return  sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y))
}


fun String.toPascalCase(): String =
    this.split(" ").joinToString(separator = "") { it.capitalize() }

class FunctorMap(private val map: MutableMap<Int, String>)
{
    fun map(transform: (String) -> String): FunctorMap
    {
        val newMap = mutableMapOf<Int, String>()
        map.entries.forEach { (key, value) -> newMap[key] = transform(value)
        }
        return FunctorMap(newMap)
    }

    fun toMap(): Map<Int, String> = map.toMap()
}


fun main()
{
    //PROBLEMA1
    val list = listOf(1, 25, 75, 39, 7, 2, 35, 3, 31, 7, 8)
    /*
        val filtru = list.filter { it >= 5 }
        println("Dupa filter: $filtru")

        val perechi = filtru.zipWithNext().filterIndexed { index, pair -> index % 2 == 0 }
        println("Dupa zip: $perechi")

        val produs = perechi.map { (a, b) -> a * b }
        println("Dupa inmultire: $produs")

        val suma = produs.sum()
        println("Suma finala: $suma")
     */
    val filtru = list.filter { it >= 5 }.zipWithNext().filterIndexed { index, pair -> index % 2 == 0 }.map { (a, b) -> a * b }.sum()
    println("Suma finala: $filtru")

    //PROBLEMA 2
    val offset = 3
    val continut = File("input.txt").readText()

    val rezultat = continut.split(" ").joinToString(" ") { cuvant -> val doarLitere = cuvant.filter { it.isLetter() }
        if (doarLitere.length in 4..7)
        {
            cifraCaesar(cuvant, offset)
        }
        else
        {
            cuvant
        }
    }

    println("Text criptat:")
    println(rezultat)
    File("output.txt").writeText(rezultat)


    //PROBLEMA 3
    val points = listOf(Point(0.0, 0.0), Point(0.0, 1.0), Point(1.0, 1.0), Point(1.0, 0.0))

    val perimeter =
        points.zipWithNext()
            .map { (a, b) -> distance(a, b) }
            .sum() + distance(points.last(), points.first())

    println("Perimetrul este: $perimeter")


    //PROBLEMA 4
    val Map = mutableMapOf(
        1 to "hello world",
        2 to "si plas plas",
        3 to "java script"
    )

    val rezult = FunctorMap(Map).map { "Test $it" }.map { it.toPascalCase() }

    println("Rezultat:")
    rezult.toMap().forEach { (key, value) -> println("$key -> $value")
    }
}