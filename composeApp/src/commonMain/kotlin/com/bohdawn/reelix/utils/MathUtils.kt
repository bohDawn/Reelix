package com.bohdawn.reelix.utils
import kotlin.math.pow
import kotlin.math.round

fun Double.roundTo(dec: Int): Double {
    return round(this * 10.0.pow(dec)) / 10.0.pow(dec)
}