package com.example.computer_software_design.week_02

enum class Food{pizza, burger, chicken};

class Lunch(var menu:Food, var price:Int) {
    fun choice_lunch() {
        println("menu : ${this.menu}, price : ${this.price}");
    }
}

fun main() {
    var lunch: MutableList<Lunch> = mutableListOf();
    lunch.add(Lunch(Food.pizza, 15000));
    lunch.add(Lunch(Food.burger, 7000));
    lunch.add(Lunch(Food.chicken, 25000));

    var myLunch = lunch.filter { it.price < 10000 }
    for (i in myLunch) {
        i.choice_lunch();
    }
}
