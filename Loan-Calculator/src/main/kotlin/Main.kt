import kotlin.math.pow

fun main() {

    // Testing
    testCalcMonthlyPayment()
    testCalcLoanAmount()

    val info = GetInfo().getAllInfo()
//    println(info[0])
//    if (toFind == "Monthly Payment"){
//        calcMonthlyPayment(info)
//    }
//    else if (toFind == "Loan Amount"){
//        calcLoanAmount(info)
//    }
//    else{
//        println("Error")
//    }

    when (info[0]){
        "Monthly Payment" -> calcMonthlyPayment(info)
        "Loan Amount" -> calcLoanAmount(info)
    }

}

// TODO: Function to calculate interest rate
//fun calcInterestRate() {
//    val loanAmount = 10000
//    val numberOfPayments = 60.0
//    val monthlyPayment = 200.38
//    val partTwo = (1.0).pow(1 / numberOfPayments) - 1
//    val partOne = ((1.0 / loanAmount).pow(1 / numberOfPayments)) / 3
//    val interestRate = (partOne / partTwo)
//    println(interestRate)
//}

// TODO: Function to calculate the number of payments

fun calcLoanAmount(info : Array<Any>){
    val numberOfPayments = info[3] as Float
    val interestRate = info[1] as Float
    val monthlyPayment = info[2] as Float
    val loanAmount = (interestRate * ((1.0f + interestRate).pow(numberOfPayments))) / (monthlyPayment * (((1.0f + interestRate).pow(numberOfPayments)) - 1.0f))
    val finalLoanAmount = 1 / loanAmount
    println("$" + "%.2f".format(finalLoanAmount))
}

// I tried this, but it resulted in a stack overflow
//private operator fun Any.minus(any: Any): Any {
//    return any - any
//}
//
//private operator fun Any.div(any: Any): Any {
//    return any / any
//}
//
//private fun Any.pow(any: Any): Any {
//    return any.pow(any)
//}
//
//private operator fun Any.plus(any: Any): Any {
//    return any + any
//}
//
//private operator fun Any.times(any: Any): Any {
//    return any * any
//}

fun calcMonthlyPayment(info : Array<Any>){
    val numberOfPayments = info[3] as Float
    val interestRate = info[1] as Float
    val loanAmount = info[2] as Float
    val monthlyPayment = ((loanAmount * (interestRate * ((1.0f + interestRate).pow(numberOfPayments)))) / ((1.0f + interestRate).pow(numberOfPayments) - 1.0f))
    println("$" + "%.2f".format(monthlyPayment))
}

fun testCalcMonthlyPayment(){
    val numberOfPayments = 60.0
    val interestRate = .00625
    val loanAmount = 10000
    val monthlyPayment = ((loanAmount * (interestRate * ((1.0 + interestRate).pow(numberOfPayments)))) / ((1.0 + interestRate).pow(numberOfPayments) - 1))
//    println("$" + "%.2f".format(monthlyPayment))
    assert(monthlyPayment == 200.38)
}

fun testCalcLoanAmount(){
    val numberOfPayments = 60.0
    val interestRate = .00625
    val monthlyPayment = 200.38
    val loanAmount = (interestRate * ((1.0 + interestRate).pow(numberOfPayments))) / (monthlyPayment * (((1.0 + interestRate).pow(numberOfPayments)) - 1.0))
    val finalLoanAmount = 1 / loanAmount
//    println("$" + "%.2f".format(finalLoanAmount))
    assert(finalLoanAmount >= 10000 || finalLoanAmount <= 10001)
}

class GetInfo {
    fun getAllInfo(): Array<Any> {
        var keepGoing = true
        var answer = ""

        while (keepGoing) {
//            println(
//                "What do you need to find? " +
//                        "1. Interest Rate" +
//                        "2. Loan Amount" +
//                        "3. Monthly Payment" +
//                        "4. Number of Payments"
//            )

            println()
            println("What do you need to find? ")
            println("1. Loan Amount")
            println("2. Monthly payment")
            println()

            answer = readLine()!!
            val answerOptions = arrayOf("1", "2")
            if (answer in answerOptions) {
                keepGoing = false
            }
        }

        val info = when (answer){
//            "1" -> findInterestRate()
            "1" -> findLoanAmount()
            "2" -> findMonthlyPayment()
//            "4" -> findNumberOfPayments()
            else -> error()
        }
        return info
    }

//    private fun findInterestRate(): Any{
//        val loanAmount = getLoanAmount()
//        val monthlyPayment = getMonthlyPayment()
//        val numberOfPayments = getNumberOfPayments()
//        return mapOf("Find" to "Interest Rate", "Interest Rate" to "Find Me", "Loan Amount" to loanAmount, "Monthly Payment" to monthlyPayment, "Number of Payments" to numberOfPayments)
//    }

    private fun error(): Array<Any>{
        return arrayOf("", 0, 0, 0)
    }

    private fun findLoanAmount(): Array<Any>{
        val interestRate = getInterestRate()
        val monthlyPayment = getMonthlyPayment()
        val numberOfPayments = getNumberOfPayments()
//        return mapOf("Find" to "Loan Amount", "Interest Rate" to  interestRate, "Loan Amount" to "Find Me", "Monthly Payment" to monthlyPayment, "Number of Payments" to numberOfPayments)
        return arrayOf("Loan Amount", interestRate, monthlyPayment, numberOfPayments)
    }

    private fun findMonthlyPayment(): Array<Any>{
        val interestRate = getInterestRate()
        val loanAmount = getLoanAmount()
        val numberOfPayments = getNumberOfPayments()
//        return mapOf("Find" to "Monthly Payment", "Interest Rate" to  interestRate, "Loan Amount" to loanAmount, "Monthly Payment" to "Find Me", "Number of Payments" to numberOfPayments)
        return arrayOf("Monthly Payment", interestRate, loanAmount, numberOfPayments)
    }

//    private fun findNumberOfPayments(): Any{
//        val interestRate = getInterestRate()
//        val loanAmount = getLoanAmount()
//        val monthlyPayment = getMonthlyPayment()
//        return mapOf("Find" to "Number of Payment", "Interest Rate" to  interestRate, "Loan Amount" to loanAmount, "Monthly Payment" to monthlyPayment, "Number of Payments" to "Find Me")
//    }

    private fun getInterestRate(): Any {
        // !! to make value not return null
        println("Enter the interest rate: ")
        return readLine()!!.toFloat()
    }

    private fun getLoanAmount(): Any {
        println("Enter how much your loan is valued at: ")
        return readLine()!!.toFloat()
    }

    private fun getMonthlyPayment(): Any {
        println("Enter your monthly payment amount: ")
        return readLine()!!.toFloat()
    }

    private fun getNumberOfPayments(): Any {
        println("Enter the amount of payments during the loan: ")
        return readLine()!!.toFloat()
    }

}