package com.andrdev08.testTask

import android.view.View

interface NumberClicker {
    fun pressingKeyboard(view: View)
    fun onClear(view: View)
    fun setNumberToScreen(view: View)
    fun setDecimalPoint(view: View)
    fun aed(view: View)
}