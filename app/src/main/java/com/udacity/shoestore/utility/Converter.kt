package com.udacity.shoestore.utility

import android.widget.TextView
import androidx.databinding.InverseMethod
import timber.log.Timber
import java.text.ParseException

object Converter {
       @InverseMethod("textToDecimal")
        fun decimalToText(view: TextView, shoeOldSize: Double, shoeSize: Double): String {
            try {
                val editTextValue = view.text.toString()
                val parsedValue = editTextValue.toDouble()
                if (parsedValue == shoeSize)
                    return view.text.toString()
            }
            catch (ex: ParseException) {
                Timber.e("Something wrong with shoe size number")
            }
           return shoeSize.toString()
        }

    fun textToDecimal(view: TextView, shoeOldSize: Double, shoeSize: String): Double {
        try {
            return shoeSize.toDouble()
        }
        catch (ex: Exception){
            Timber.e("Bad shoe size number")
            return shoeOldSize
        }
    }
}