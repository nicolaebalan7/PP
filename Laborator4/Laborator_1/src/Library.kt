class Library(private val printer: Printer)
{

    private val books: MutableSet<Book> = mutableSetOf()

    fun getBooks() : Set<Book> { return books }

    fun addBook(book: Book)
    {
        books.add(book)
    }

    fun printBooksRaw() { printer.printBooksRaw(books) }
    fun printHTML() { printer.printHTML(books) }
    fun printJSON() { printer.printJSON(books) }

    fun findAllByAuthor(author: String) : Set<Book>
    {
        return books.filter { it.hasAuthor(author) }.toSet()
    }

    fun findAllByName(name: String) : Set<Book>
    {
        return books.filter { it.hasTitle(name) }.toSet()
    }

    fun findAllByPublisher(publisher: String) : Set<Book>
    {
        return books.filter { it.isPublishedBy(publisher) }.toSet()
    }
}