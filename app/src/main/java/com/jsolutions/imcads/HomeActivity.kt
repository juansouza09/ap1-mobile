package com.jsolutions.imcads

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jsolutions.imcads.data.models.User
import com.jsolutions.imcads.databinding.ActivityHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            validateInputData()
        }

        binding.buttonBackToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun sendData() {
        val height = binding.heightEditText.editText?.text.toString().toDouble()
        val weight = binding.weightEditText.editText?.text.toString().toDouble()

        val user = User(height = height, weight = weight)

        GlobalScope.launch(Dispatchers.IO) {
            App.database.userDao().insert(user)
        }

        // Cálculo do IMC
        val bmi = calculateBMI(weight, height)

        // Envia dados para a ResultActivity
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("result", bmi)
        startActivity(intent)

        Log.d("MainActivity", "BMI calculated: $bmi")
    }

    private fun calculateBMI(
        weight: Double,
        height: Double,
    ): Double {
        return weight / (height * height)
    }

    private fun validateInputData() {
        val heightInput = binding.heightEditText.editText?.text.toString()
        val weightInput = binding.weightEditText.editText?.text.toString()

        val validHeight = heightInput.isNotEmpty() && heightInput.toDouble() in 1.0..2.3
        val validWeight = weightInput.isNotEmpty() && weightInput.toDouble() in 30.0..200.0

        if (validHeight && validWeight) {
            sendData()
        } else {
            if (!validHeight) {
                binding.heightEditText.error = "Insira uma altura válida (entre 1.0 e 2.3)"
            }
            if (!validWeight) {
                binding.weightEditText.error = "Insira um peso válido (entre 30.0 e 200.0)"
            }
        }
    }
}
