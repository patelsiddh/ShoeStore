package com.udacity.shoestore.utility

import timber.log.Timber

class Converter {

        fun toText(shoeSize: Double): String {
            return try {
                shoeSize.toString()
            } catch (ex: Exception) {
                Timber.e("Something wrong with Shoe Size Value")
                ""
            }
        }
}