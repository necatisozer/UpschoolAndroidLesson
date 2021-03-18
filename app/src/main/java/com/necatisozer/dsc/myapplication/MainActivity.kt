package com.necatisozer.dsc.myapplication

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.necatisozer.dsc.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextEmail.doOnTextChanged { text, start, before, count ->
            binding.textInputLayoutEmail.error = null
        }

        binding.editTextPassword.doOnTextChanged { text, start, before, count ->
            binding.textInputLayoutPassword.error = null
        }

        binding.buttonLogin.setOnClickListener {
            validate()
        }

    }

    private fun validate() {
        val isEmailValid = validateEmail()
        val isEmailPassword = validatePassword()

        if (isEmailValid && isEmailPassword) {
            showSuccess()
        }
    }

    private fun validateEmail(): Boolean {
        val email = binding.editTextEmail.text.toString()

        if (email.isEmpty()) {
            binding.textInputLayoutEmail.error = getString(R.string.email_empty_error)
            return false
        }

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
            binding.textInputLayoutEmail.error = getString(R.string.email_invalid_error)
            return false
        }

        binding.textInputLayoutEmail.error = null
        return true
    }

    private fun validatePassword(): Boolean {
        val password = binding.editTextPassword.text.toString()

        if (password.length < 8) {
            binding.textInputLayoutPassword.error = getString(R.string.email_invalid_password)
            return false
        }

        binding.textInputLayoutPassword.error = null
        return true
    }

    private fun showSuccess() {
        val email = binding.editTextEmail.text.toString()
        val name = email.substringBefore("@")
        val successMessage = getString(R.string.login_success_message, name)
        toast(successMessage)
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}