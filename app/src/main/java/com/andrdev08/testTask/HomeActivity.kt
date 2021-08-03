package com.andrdev08.testTask

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

    companion object {
        var lastElement = false
        var decimalPoint = false
        const val emptyPlace = ""
        const val coma = ","
        const val aed = "AED"
        const val textDecimalPoint = "."
        const val MAX_VALUE_BDP = 9
        const val MAX_VALUE_ADP = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        supportActionBar!!.hide()
    }

    override fun pressingKeyboard(view: View) {
        when (view.id) {
            R.id.btnDelete -> onClear(view)
            R.id.btnDecimal -> setDecimalPoint(view)

            R.id.btn0 -> setNumberToScreen(view)
            R.id.btn1 -> setNumberToScreen(view)
            R.id.btn2 -> setNumberToScreen(view)
            R.id.btn3 -> setNumberToScreen(view)
            R.id.btn4 -> setNumberToScreen(view)
            R.id.btn5 -> setNumberToScreen(view)
            R.id.btn6 -> setNumberToScreen(view)
            R.id.btn7 -> setNumberToScreen(view)
            R.id.btn8 -> setNumberToScreen(view)
            R.id.btn9 -> setNumberToScreen(view)
        }
    }

    override fun setNumberToScreen(view: View) {
        aed(view)
        if (decimalPoint) {
            if (checkADP()) {
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
        } else {
            if (checkBDP()) {
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
    }

    override fun setDecimalPoint(view: View) {
        if (lastElement && !decimalPoint) {
            numberScreen.decimalPoint.text = textDecimalPoint
            whenDP()
        }
    }

    override fun onClear(view: View) {
        if (decimalPoint && numberScreen.textADPNumber.text.isNotEmpty()) {
            val currentValue = (numberScreen.textADPNumber.text.toString())
                .dropLast(1)
            numberScreen.textADPNumber.text = emptyPlace
            numberScreen.textADPNumber.text = currentValue
            if (numberScreen.textADPNumber.text.isEmpty()) {
                numberScreen.decimalPoint.text = emptyPlace
                decimalPoint = false
            }
        } else {
            if (lastElement && numberScreen.textBDPNumber.text.isNotEmpty()) {
                val currentValue =
                    (numberScreen.textBDPNumber.text.toString())
                        .replace(coma, emptyPlace)
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
    }

    override fun aed(view: View) {
        if (!lastElement && !decimalPoint && view != btn0) {
            numberScreen.textAED.text = aed
        }
    }

    private fun setNumberFormatBDP(view: View): String? {
        val currentValue =
            (numberScreen.textBDPNumber.text.toString()
                .replace(coma, emptyPlace))
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

    private fun clearAll() {
        numberScreen.textBDPNumber.text = emptyPlace
        numberScreen.textAED.text = emptyPlace
        lastElement = false
    }

    private fun whenDP(){
        lastElement = false
        decimalPoint = true
    }

    private fun checkBDP(): Boolean{
        return numberScreen.textBDPNumber.text.length < MAX_VALUE_BDP
    }
    private fun checkADP(): Boolean {
        return numberScreen.textADPNumber.text.length < MAX_VALUE_ADP
    }
}

