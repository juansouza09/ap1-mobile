package com.jsolutions.imcads

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jsolutions.imcads.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpViews()
    }

    private fun setUpViews() {
        val result = intent.getDoubleExtra("result", 0.0)
        val formattedResult = String.format("%.2f", result)
        val resultText = validateData(result)

        binding.imcResultText.text = "Seu IMC é $formattedResult, considerado"
        binding.imcTagText.text = resultText

        binding.backButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateData(imc: Double): String {
        return when {
            imc < UNDERWEIGHT_GRAVE_LIMIT -> "Magreza Grave"
            imc < UNDERWEIGHT_MODERATE_LIMIT -> "Magreza moderada"
            imc < UNDERWEIGHT_LIGHT_LIMIT -> "Magreza Leve"
            imc < HEALTHY_LIMIT -> "Saudável"
            imc < OVERWEIGHT_LIMIT -> "Sobrepeso"
            imc < OBESITY_GRADE_1_LIMIT -> "Obesidade grau 1"
            imc < OBESITY_GRADE_2_LIMIT -> "Obesidade grau 2 (Severa)"
            else -> "Obesidade Grau III (Mórbida)"
        }
    }

    companion object {
        private const val UNDERWEIGHT_GRAVE_LIMIT = 16.0
        private const val UNDERWEIGHT_MODERATE_LIMIT = 17.0
        private const val UNDERWEIGHT_LIGHT_LIMIT = 18.5
        private const val HEALTHY_LIMIT = 25.0
        private const val OVERWEIGHT_LIMIT = 30.0
        private const val OBESITY_GRADE_1_LIMIT = 35.0
        private const val OBESITY_GRADE_2_LIMIT = 40.0
    }
}
