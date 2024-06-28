package com.example.computer_software_design.week_02

fun main() {
    val value = readLine();
    println("입력 받을 숫자: ${value!!}");

    for (i:Int in 1..value.toInt()) {
        if (i%2 == 1) print("$i ");
    }
}