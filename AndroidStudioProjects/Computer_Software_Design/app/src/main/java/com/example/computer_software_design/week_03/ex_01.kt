package com.example.computer_software_design.week_03


open class Beverage() {
    open fun drink(money: Int): Int {
        println("슈퍼클래스의 drink함수")
        return money
    }
}

class Cola(var taxRate: Double, operation: (Double) -> Double) : Beverage() {
    var price = 600
    var tax = operation(taxRate)
    ///////////Todo//////////////
    override fun drink(money: Int): Int {
        println("콜라를 마십니다.")
        return (money - price - tax.toInt())
    }
}

fun Cola.reset_cost(cost: Int) {
    price = cost
}

fun main() {

    var sub1 = Cola(10.0){taxRate->
        30.0*(taxRate/100.0)
    }
    println("잔돈 : ${sub1.drink(2000)}")

    /////////////Todo///////////////
    sub1.reset_cost(300)
    println("잔돈 : ${sub1.drink(2000)}")
}

