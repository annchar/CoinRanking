package com.annchar.coinranking.extentions

fun Number.toDecimals(digits: Int = 2): String {
    return String.format("%,.${digits}f", this.toDouble())
}

fun Number.toDecimalsNoComma(digits: Int = 2): String {
    return String.format("%.${digits}f", this.toDouble())
}

fun Number.toDecimalsUnits(digits: Int = 2): String {
    val price = this.toDouble()
    val oneBillion = 1000000000
    val oneMillion = 1000000
    return if (price >= oneBillion) {
        String.format("%,.${digits}f", (price / oneBillion)) + "B"
    } else if (price >= oneMillion) {
        String.format("%,.${digits}f", (price / oneMillion)) + "M"
    } else {
        String.format("%,.${digits}f", price)
    }
}
