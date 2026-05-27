class Content( private var author: String, private var text: String, private var name: String, private var publisher: String, private var price: Double)
{
    fun getAuthor() : String { return author }
    fun setAuthor(value: String) { author = value }

    fun getText() : String { return text }
    fun setText(value: String) { text = value }

    fun getName() : String { return name }
    fun setName(value: String) { name = value }

    fun getPublisher() : String { return publisher }
    fun setPublisher(value: String) { publisher = value }

    fun getPrice() : Double { return price }
    fun setPrice(value: Double) { price = value }

}