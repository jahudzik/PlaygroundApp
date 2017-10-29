package com.jahu.playground.extensions

import java.math.BigDecimal

fun Double.roundTo2DecimalPlaces() = BigDecimal(this).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()
