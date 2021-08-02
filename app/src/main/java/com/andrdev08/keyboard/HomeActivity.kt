package com.andrdev08.keyboard

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.keyboard.*
import kotlinx.android.synthetic.main.value_display_screen.view.*
import java.text.NumberFormat
import java.util.*

class HomeActivity : AppCompatActivity(), NumberClicker {

    private var lastElement = false
    private var decimalPoint = false
    private val emptyPlace = ""
    private val coma = ","
    private val MAX_VALUE_BDP = 9
    private val MAX_VALUE_ADP = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        val actionBar = supportActionBar
        actionBar!!.hide()
    }

    private fun setDecimalPoint(view: View) {
        if (lastElement && !decimalPoint) {
            numberScreen.decimalPoint.text = "."
            lastElement = false
            decimalPoint = true
        }
    }

    private fun onClear(view: View) {
        if (decimalPoint && numberScreen.textADPNumber.text.isNotEmpty()) {
            clearAfterDecimalPoint()
        } else {
            clearBeforeDecimalPoint()
        }
    }

    private fun aed(view: View) {
        if (!lastElement && !decimalPoint && view != btn0) {
            numberScreen.textAED.text = "AED"
        }
    }

    private fun setAfterDecimalPoint(view: View) {
        if (numberScreen.textADPNumber.text.length < MAX_VALUE_ADP) {
            if (lastElement) {
                if (view != btn0) {
                    numberScreen.textADPNumber.append((view as TextView).text)
                    lastElement = true
                }
            } else {
                numberScreen.textADPNumber.append((view as TextView).text)
                lastElement = true
            }
        }
    }

    private fun setBeforeDecimalPoint(view: View) {
        if (numberScreen.textBDPNumber.text.length < MAX_VALUE_BDP) {
            if (lastElement) {
                numberScreen.textBDPNumber.text = setNumberFormatBDP(view)
                lastElement = true
            } else {
                if (view != btn0) {
                    if (numberScreen.textBDPNumber.text.toString() == emptyPlace) {
                        numberScreen.textBDPNumber.text = setNumberFormatADP(view)
                        lastElement = true
                    }
                }
            }
        }
    }

    private fun clearAfterDecimalPoint() {
        val currentValue = (numberScreen.textADPNumber.text.toString()).dropLast(1)
        numberScreen.textADPNumber.text = emptyPlace
        numberScreen.textADPNumber.text = currentValue

        if (numberScreen.textADPNumber.text.isEmpty()) {
            numberScreen.decimalPoint.text = emptyPlace
            decimalPoint = false
        }
    }

    private fun clearBeforeDecimalPoint() {
        if (lastElement && numberScreen.textBDPNumber.text.isNotEmpty()) {
            val currentValue =
                (numberScreen.textBDPNumber.text.toString()).replace(coma, emptyPlace)
                    .dropLast(1)
            if (currentValue != emptyPlace) {
                val value = Integer.parseInt(currentValue)
                numberScreen.textBDPNumber.text = emptyPlace
                numberScreen.textBDPNumber.text =
                    NumberFormat.getNumberInstance(Locale.US).format(value)
            } else {
              clearAll()
            }
        }
    }

    private fun setNumberFormatBDP(view: View): String? {
        val currentValue =
            (numberScreen.textBDPNumber.text.toString().replace(coma, emptyPlace))
        val number = (view as TextView).text.toString()
        val resultingValue = Integer.parseInt(currentValue + number)
        numberScreen.textBDPNumber.text = emptyPlace
        return NumberFormat.getNumberInstance(Locale.US).format(resultingValue)
    }

    private fun setNumberFormatADP(view: View): String? {
        val number = Integer.parseInt((view as TextView).text.toString())
        numberScreen.textBDPNumber.text = emptyPlace
        return NumberFormat.getNumberInstance(Locale.US).format(number)
    }

    private fun clearAll(){
        numberScreen.textBDPNumber.text = emptyPlace
        numberScreen.textAED.text = emptyPlace
        lastElement = false
    }

    override fun pressingKeyboard(view: View) {
        when (view.id) {

            R.id.btnDelete -> onClear(view)
            R.id.btnDecimal -> setDecimalPoint(view)

            R.id.btn0 -> {
                aed(view)
                if (decimalPoint) { setAfterDecimalPoint(view) } else { setBeforeDecimalPoint(view) }
            }
            R.id.btn1 -> {
                aed(view)
                if (decimalPoint) { setAfterDecimalPoint(view) } else { setBeforeDecimalPoint(view) }
            }
            R.id.btn2 -> {
                aed(view)
                if (decimalPoint) { setAfterDecimalPoint(view) } else { setBeforeDecimalPoint(view) }
            }
            R.id.btn3 -> {
                aed(view)
                if (decimalPoint) { setAfterDecimalPoint(view) } else { setBeforeDecimalPoint(view) }
            }
            R.id.btn4 -> {
                aed(view)
                if (decimalPoint) { setAfterDecimalPoint(view) } else { setBeforeDecimalPoint(view) }
            }
            R.id.btn5 -> {
                aed(view)
                if (decimalPoint) { setAfterDecimalPoint(view) } else { setBeforeDecimalPoint(view) }
            }
            R.id.btn6 -> {
                aed(view)
                if (decimalPoint) { setAfterDecimalPoint(view) } else { setBeforeDecimalPoint(view) }
            }
            R.id.btn7 -> {
                aed(view)
                if (decimalPoint) { setAfterDecimalPoint(view) } else { setBeforeDecimalPoint(view) }
            }
            R.id.btn8 -> {
                aed(view)
                if (decimalPoint) { setAfterDecimalPoint(view) } else { setBeforeDecimalPoint(view) }
            }
            R.id.btn9 -> {
                aed(view)
                if (decimalPoint) { setAfterDecimalPoint(view) } else { setBeforeDecimalPoint(view) }
            }
        }
    }
}

