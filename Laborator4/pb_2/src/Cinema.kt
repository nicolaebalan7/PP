class Cinema
{
    private val movies: MutableSet<Movie> = mutableSetOf()

    fun addMovie(movie: Movie)
    {
        movies.add(movie)
    }

    fun getMovies() : Set<Movie> { return movies }

    fun buyTicket(movie: Movie, paymentMethod: PaymentMethod) : Ticket
    {
        val ticket = Ticket(movie, paymentMethod)
        ticket.purchase()
        return ticket
    }

    fun printMovies()
    {
        println("Filme disponibile:")
        for (movie in movies)
        {
            println(" " + movie.toString())
        }
    }
}