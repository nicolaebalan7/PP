class Book(private val data: Content)
{

    override fun toString() : String
    {
        return data.getName()
    }

    fun getName() : String { return data.getName() }
    fun getAuthor() : String { return data.getAuthor() }
    fun getPublisher() : String { return data.getPublisher() }
    fun getContent() : Content { return data }
    fun getPrice() : Double { return data.getPrice() }

    fun hasAuthor(author: String) : Boolean { return data.getAuthor() == author }
    fun hasTitle(title: String) : Boolean { return data.getName() == title }
    fun isPublishedBy(publisher: String) : Boolean { return data.getPublisher() == publisher }
}