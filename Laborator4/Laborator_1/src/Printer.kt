interface Printer
{
    fun printBooksRaw(books: Set<Book>)
    fun printHTML(books: Set<Book>)
    fun printJSON(books: Set<Book>)
}