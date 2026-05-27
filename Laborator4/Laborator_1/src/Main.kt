fun main() {
    val content1 = Content("Mihai Eminescu", "text", "Luceafarul", "editura1", 19.99)
    val content2 = Content("Ion Creanga", "text", "Amintiri din copilarie", "editura2", 29.99)
    val content3 = Content("Mihai Eminescu", "text", "La steaua", "editura3", 39.99)

    val book1 = Book(content1)
    val book2 = Book(content2)
    val book3 = Book(content3)

    val printer: Printer = LibraryPrinter()
    val library = Library(printer)

    library.addBook(book1)
    library.addBook(book2)
    library.addBook(book3)

    library.printBooksRaw()
    library.printHTML()
    library.printJSON()

    println("\nCarti de Eminescu:")
    for (book in library.findAllByAuthor("Mihai Eminescu")) {
        println(" " + book.getName())
    }
}