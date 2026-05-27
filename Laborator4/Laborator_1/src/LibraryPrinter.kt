class LibraryPrinter : Printer
{

    override fun printBooksRaw(books: Set<Book>)
    {
        println("Raw:")
        for (book in books)
        {
            println(book.getName() + " - " + book.getAuthor() + " - " + book.getPublisher())
        }
    }

    override fun printHTML(books: Set<Book>)
    {
        println("HTML:")
        println("<ul>")
        for (book in books)
        {
            println("  <li>" + book.getName() + " by " + book.getAuthor() + "</li>")
        }
        println("</ul>")
    }

    override fun printJSON(books: Set<Book>)
    {
        println("JSON:")
        println("[")
        for (book in books)
        {
            println("  {\"name\": \"" + book.getName() + "\", \"author\": \"" + book.getAuthor() + "\"}")
        }
        println("]")
    }
}