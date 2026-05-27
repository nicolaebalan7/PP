class CashPayment(private var availableAmount: Double) : PaymentMethod
{

    override fun pay(fee: Double) : Boolean
    {
        return if (availableAmount >= fee)
        {
            availableAmount -= fee
            println("Plata cash de " + fee + " lei efectuata cu succes!")
            true
        }
        else
        {
            println("Fonduri insuficiente!!")
            false
        }
    }
}