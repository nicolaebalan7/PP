class Ticket(private val movie: Movie, private val paymentMethod: PaymentMethod)
{
    private var isPaid: Boolean = false

    fun purchase() : Boolean
    {
        isPaid = paymentMethod.pay(movie.getPrice())
        return isPaid
    }

    override fun toString() : String
    {
        return if (isPaid)
        {
            "Bilet platit pentru: " + movie.getTitle()
        }
        else
        {
            "Bilet neplatit pentru: " + movie.getTitle()
        }
    }
}