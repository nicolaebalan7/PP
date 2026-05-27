class CardPayment(private val bankAccount: BankAccount) : PaymentMethod
{

    override fun pay(fee: Double) : Boolean
    {
        return if (bankAccount.updateAmount(fee))
        {
            println("Plata cu cardul de " + fee + " lei efectuata cu succes!")
            true
        } else {
            println("Fonduri insuficiente!!")
            false
        }
    }
}