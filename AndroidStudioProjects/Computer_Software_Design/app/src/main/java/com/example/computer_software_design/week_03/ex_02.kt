package com.example.computer_software_design.week_03

enum class BeerType{LIGHT_LAGER, LIGHT_HYBRID, BOCK, AMBER_HYBRID, FRUIT}
enum class WineType{WHITE, ROSE, RED, SPARKLING, DESSERT}
class Beer(var name:String, var beerType:BeerType,var cost:Int)
class Wine(var name:String, var wineType:WineType,var cost:Int)

fun Beer.print() {
    println("맥주이름 : ${name} , 맥주타입 : ${beerType} , 맥주가격 : ${cost}")
} ////////////////Todo////////////////
fun Beer.change_price(cost: Int) {
    this.cost = cost
} ////////////////Todo////////////////
fun Wine.euro_to_won(converter: (Int, Int) -> Int) {
    val euro = cost
    println("유로 : $euro , 원화 : ${converter(euro, 1350)}")
} ////////////////Todo////////////////
fun main() {

    var beer1 = Beer("Hite",BeerType.FRUIT,200)
    var beer2 = Beer("Cass",BeerType.LIGHT_HYBRID,200)

    beer1.change_price(600)
    beer1.print()
    beer2.change_price(600)
    beer2.print()

    var wine1 = Wine("Cabernet",WineType.RED,10)
    var wine2 = Wine("Chardonnay",WineType.WHITE,12)

    wine1.euro_to_won { a,b->
        a*b
    }

}
