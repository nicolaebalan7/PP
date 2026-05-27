class Movie(private val title: String, private val gen: String, private val price: Double)
{
    fun getTitle() : String { return title }
    fun getPrice() : Double { return price }

    override fun toString() : String
    {
        return title + " (" + gen + ") - " + price + " lei"
    }
}