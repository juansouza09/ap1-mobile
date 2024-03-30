package com.jsolutions.imcads
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jsolutions.imcads.databinding.ActivityGrupoBinding

class GroupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGrupoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGrupoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageView = binding.buttonBackToMain
        imageView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
