package com.hello.counterapp

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var numberText: TextView
    private lateinit var numberToSubmit: EditText
    private lateinit var numberToInterval: EditText
    private lateinit var summaryText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton: Button = findViewById(R.id.main_activity_btn_submit)
        val randomButton: Button = findViewById(R.id.main_activity_btn_random_number)
        val incrementButton: Button = findViewById(R.id.main_activity_btn_increment)
        val decrementButton: Button = findViewById(R.id.main_activity_btn_decrement)

        submitButton.setOnClickListener { submitNumber() }
        randomButton.setOnClickListener { generateRandomNumber() }
        incrementButton.setOnClickListener { incrementDecrement(true) }
        decrementButton.setOnClickListener { incrementDecrement(false) }

        numberText = findViewById(R.id.main_activity_tv_number)
        numberToSubmit = findViewById(R.id.main_activity_et_number_input)
        numberToInterval = findViewById(R.id.main_activity_et_dec_inc_number)
        summaryText = findViewById(R.id.main_activity_tv_summary)
    }

    private fun submitNumber() {
        var startingNumber = numberToSubmit.text.toString()
        if (startingNumber.isEmpty()) {
            startingNumber = (-100..100).random().toString()
            numberToSubmit.setText(startingNumber)
        }
        numberText.setText(startingNumber)
        hideKeyboard()
    }

    private fun generateRandomNumber() {
        val randomNumber = (-100..100).random()
        numberText.setText(randomNumber.toString())
    }

    private fun incrementDecrement(isPositive: Boolean) {
        val currentNumber = numberText.text.toString().toInt()
        var incrementValue = numberToInterval.text.toString()

        if (incrementValue.isEmpty()) {
            incrementValue = (-100..100).random().toString()
            numberToInterval.setText(incrementValue)
        }

        val calculatedNumber = when (isPositive) {
            true -> currentNumber + incrementValue.toInt()
            else -> currentNumber - incrementValue.toInt()
        }

        val message = when (isPositive) {
            true -> "$currentNumber + ($incrementValue) = $calculatedNumber"
            false -> "$currentNumber - ($incrementValue) = $calculatedNumber"
        }
        summaryText.setText(message)
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(numberText.windowToken, 0)
    }
}
