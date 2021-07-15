package chapter1

object Listing_1_7 {
    class CreditCard {
        fun charge(price: Float): Unit = TODO()
    }

    data class Coffee(val price: Float = 2.50F)

    //tag::init[]
    fun buyCoffee(cc: CreditCard): Coffee {
        val cup = Coffee()
        cc.charge(cup.price)
        return cup
    }
    //end::init[]
}
