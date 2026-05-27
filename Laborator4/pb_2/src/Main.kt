fun main()
{
    val cinema = Cinema()

    val dune1 = Movie("Dune part1", "SF", 20.0)
    val dune2 = Movie("Dune part2", "SF", 25.0)
    val dune3 = Movie("Dune part3", "thriller", 50.0)

    cinema.addMovie(dune1)
    cinema.addMovie(dune2)
    cinema.addMovie(dune3)

    cinema.printMovies()

    val cashPayment = CashPayment(100.0)
    val ticket1 = cinema.buyTicket(dune1, cashPayment)
    println(ticket1.toString())

    val bankAccount = BankAccount(50.0, "1234-5678", "12/27", 123, "Ion Zapada")
    val cardPayment = CardPayment(bankAccount)
    val ticket2 = cinema.buyTicket(dune2, cardPayment)
    println(ticket2.toString())

    val ticket3 = cinema.buyTicket(dune3, cardPayment)
    println(ticket3.toString())
}