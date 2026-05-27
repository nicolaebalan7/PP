class BankAccount(private var availableAmount: Double, private val cardNumber: String, private val expirationDate: String, private val cvvCode: Int, private val userName: String)
{
    fun updateAmount(value: Double) : Boolean
    {
        return if (availableAmount >= value)
        {
            availableAmount -= value
            true
        }
        else
        {
            false
        }
    }

    fun getAvailableAmount() : Double { return availableAmount }
    fun getCardNumber() : String { return cardNumber }
    fun getExpirationDate() : String { return expirationDate }
    fun getCvvCode() : Int { return cvvCode }
    fun getUserName() : String { return userName }
}