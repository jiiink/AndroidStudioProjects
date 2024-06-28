package com.example.computer_software_design.week_02

fun Year(number:Int) :Int {
    /*
    what is lunar year?
    lunar year is
        - divisible by 400
        - divisible by 100 but not by 400
        - divisible by 4

    return
        0 : no lunar
        1 : lunar
     */

    return if (number % 400 == 0) 1;
    else if   (number % 100 == 0) 0;
    else if   (number % 4   == 0) 1;
    else                          0;
}

fun printer(number:Int) {
    if (number == 1) println("윤년이 맞습니다.");
    else             println("윤년이 아닙니다.");
}

fun main() {
    println("2000년은 윤년 일까?");
    printer(Year(2000));

    println("1900년은 윤년 일까?");
    printer(Year(1900));

    println("2020년은 윤년 일까?");
    printer(Year(2020));

    println("2013년은 윤년 일까?");
    printer(Year(2013));
}