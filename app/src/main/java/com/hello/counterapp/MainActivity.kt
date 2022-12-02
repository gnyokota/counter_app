package com.hello.counterapp

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.hello.counterapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainActivityBtnSubmit.setOnClickListener { submitNumber() }
        binding.mainActivityBtnRandomNumber.setOnClickListener { generateRandomNumber() }
        binding.mainActivityBtnIncrement.setOnClickListener { incrementDecrement(true) }
        binding.mainActivityBtnDecrement.setOnClickListener { incrementDecrement(false) }

        if(savedInstanceState !== null){
            binding.mainActivityTvNumber.text = savedInstanceState.getString("textNumber")
            binding.mainActivityTvSummary.text = savedInstanceState.getString("summary")
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("textNumber", binding.mainActivityTvNumber.text.toString())
        outState.putString("summary", binding.mainActivityTvSummary.text.toString())
    }

    private fun submitNumber() {
        var startingNumber = binding.mainActivityEtNumberInput.text.toString()
        if (startingNumber.isEmpty()) {
            startingNumber = (-100..100).random().toString()
            binding.mainActivityEtNumberInput.setText(startingNumber)
        }
        binding.mainActivityTvNumber.setText(startingNumber)
        hideKeyboard()
    }

    private fun generateRandomNumber() {
        val randomNumber = (-100..100).random()
        binding.mainActivityEtNumberInput.setText(randomNumber.toString())
    }

    private fun incrementDecrement(isPositive: Boolean) {
        val currentNumber = binding.mainActivityTvNumber.text.toString().toInt()
        var incrementValue = binding.mainActivityEtDecIncNumber.text.toString()

        if (incrementValue.isEmpty()) {
            incrementValue = (-100..100).random().toString()
            binding.mainActivityEtDecIncNumber.setText(incrementValue)
        }

        val calculatedNumber = when (isPositive) {
            true -> currentNumber + incrementValue.toInt()
            else -> currentNumber - incrementValue.toInt()
        }

        val message = when (isPositive) {
            true -> "$currentNumber + ($incrementValue) = $calculatedNumber"
            false -> "$currentNumber - ($incrementValue) = $calculatedNumber"
        }
        binding.mainActivityTvSummary.setText(message)
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.mainActivityTvNumber.windowToken, 0)
    }
}
