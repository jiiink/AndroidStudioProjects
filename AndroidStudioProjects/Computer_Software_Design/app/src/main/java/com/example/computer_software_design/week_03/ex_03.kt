package com.example.computer_software_design.week_03

import androidx.compose.ui.text.toUpperCase

fun change(a:String):String {
    var islower: Boolean = true
    var notlower = ""
    ////////////////Todo////////////////
    try {
        for (i in a) {
            if (i !in 'a'..'z') {
                notlower += i
                islower = false
            }
        }
        if (!islower) {
            throw Exception(notlower)
        }
    } catch (e : Exception) {
        return "error with ${e.message}"
    }

    return a.toUpperCase()
}

fun main(){
    var a = "abcd"
    println(change(a))

    var b = "EfgH"
    println(change(b))

    var c = "!@%$"
    println(change(c))
}
