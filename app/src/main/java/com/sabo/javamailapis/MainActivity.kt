package com.sabo.javamailapis

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.sabo.javamailapis.Helpers.Loading
import com.sabo.javamailapis.Java.GMailSender
import com.sabo.javamailapis.Kotlin.GMailSender_Kotlin
import com.sabo.javamailapis.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        onTextWatcher()
        initAction()
    }

    private fun initAction() {
        binding.btnSend.setOnClickListener {
            binding.tvMsg.text = "Waiting to send...\nSubject : \nMessage : "
            val subject = binding.etSubject.text.toString()
            val messageBody = binding.etMessage.text.toString()
            val emailRecipient = binding.etEmail.text.toString()

            if (checkFields(subject, messageBody, emailRecipient)) {
                Loading.onStart(this)
                Thread(Runnable {
                    try {
                        /** Java */
//                        GMailSender().sendMail(
//                            subject,
//                            messageBody,
//                            emailRecipient
//                        )

                        /** Kotlin */
                        GMailSender_Kotlin().sendMail(
                            subject,
                            messageBody,
                            emailRecipient
                        )

                        this.runOnUiThread {
                            Loading.onStop()
                            binding.tvMsg.text = "Successfully send to email $emailRecipient.\nSubject : $subject\nMessage : $messageBody"
                        }

                    } catch (e: Exception) {
                        Log.d("SendEmail", e.message!!)
                        binding.tvMsg.text = "Failed send to email $emailRecipient.\n Subject : \nMessage :"
                    }
                }).start() /** Don't forget to start this Thread */
            }
        }
    }

    private fun checkFields(subject: String, messageBody: String, emailRecipient: String): Boolean {

        when {
            subject.isEmpty() -> {
                binding.tilSubject.error = errorMessage("subject")
                return false
            }
            messageBody.isEmpty() -> {
                binding.tilMessage.error = errorMessage("message")
                return false
            }
            emailRecipient.isEmpty() -> {
                binding.tilEmail.error = errorMessage("email")
                return false
            }
            !Patterns.EMAIL_ADDRESS.matcher(emailRecipient).matches() -> {
                binding.tilEmail.error = errorMessage("!valid")
                return false
            }
        }

        return true
    }

    private fun errorMessage(value: String): String {
        return when (value) {
            "!valid" -> "This email field is must contain a valid email address."
            else -> "This $value field is required."
        }
    }

    private fun onTextWatcher() {
        binding.etSubject.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty())
                    binding.tilSubject.error = ""
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        binding.etMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty())
                    binding.tilMessage.error = ""
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty())
                    binding.tilEmail.error = ""
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

}