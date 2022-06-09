package com.fadhil.challenge.utils

object NumberUtil {

    fun Int.toStringInNumberFormat(): String {
        return String.format("%,d", this).replace(',', '.')
    }

}