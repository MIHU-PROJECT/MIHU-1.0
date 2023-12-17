package com.example.mihu.ui.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.mihu.R
import com.example.mihu.data.Result
import com.example.mihu.databinding.ActivityRegisterBinding
import com.example.mihu.ui.ViewModelFactory
import com.example.mihu.ui.login.LoginActivity
import com.example.mihu.ui.worker.login.LoginWorkerActivity
import com.example.mihu.utils.ValidateType
import com.example.mihu.utils.showToast
import com.example.mihu.utils.validate
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RegisterActivity : AppCompatActivity() {
    private val viewModel: RegisterViewModel by viewModels { ViewModelFactory.getInstance(this) }
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        playAnimation()
        setupAction()
        val myTextView: TextView = findViewById(R.id.tv_register)
        myTextView.setOnClickListener {
            val intent = Intent(this, LoginWorkerActivity::class.java)
            startActivity(intent)
        }
    }

    private fun playAnimation() {

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f)
            .setDuration(100)
        val message =
            ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(100)
        val nameTextView = ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f)
            .setDuration(100)
        val nameEditTextLayout =
            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f)
                .setDuration(100)
        val nameEditText = ObjectAnimator.ofFloat(binding.nameEditText, View.ALPHA, 1f)
            .setDuration(100)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f)
                .setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f)
                .setDuration(100)
        val emailEditText =
            ObjectAnimator.ofFloat(binding.emailEditText, View.ALPHA, 1f)
                .setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f)
                .setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f)
                .setDuration(100)
        val passwordEditText =
            ObjectAnimator.ofFloat(binding.passwordEditText, View.ALPHA, 1f)
                .setDuration(100)
        val signupButton = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f)
            .setDuration(100)

        val name = AnimatorSet().apply {
            playTogether(
                nameTextView,
                nameEditTextLayout,
                nameEditText
            )
        }
        val email = AnimatorSet().apply {
            playTogether(
                emailTextView,
                emailEditTextLayout,
                emailEditText
            )
        }
        val password = AnimatorSet().apply {
            playTogether(
                passwordTextView,
                passwordEditTextLayout,
                passwordEditText
            )
        }

        AnimatorSet().apply {
            playSequentially(
                title,
                name,
                message,
                email,
                password,
                signupButton
            )
            start()
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.apply {
            signupButton.setOnClickListener {
                if (validateForm()) {
                    val name = nameEditText.text.toString()
                    val email = emailEditText.text.toString()
                    val password = passwordEditText.text.toString()

                    viewModel.register(name, email, password)
                        .observe(this@RegisterActivity) { result ->
                            if (result != null) {
                                when (result) {
                                    is Result.Loading -> {
                                        showLoading(true)
                                        signupButton.isEnabled = false

                                    }

                                    is Result.Error -> {
                                        showLoading(false)
                                        pbRegister.isVisible = false
                                        showToast(result.error.toString())
                                    }

                                    is Result.Success -> {
                                        showLoading(false)
                                        pbRegister.isVisible = false
                                        MaterialAlertDialogBuilder(this@RegisterActivity)
                                            .setTitle(getString(R.string.your_account_has_been_created))
                                            .setMessage(getString(R.string.please_login_to_your_account))
                                            .setPositiveButton("Login") { dialog, _ ->
                                                dialog.dismiss()
                                                val intent =
                                                    Intent(
                                                        this@RegisterActivity,
                                                        LoginActivity::class.java
                                                    )
                                                intent.flags =
                                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                startActivity(intent)
                                            }
                                            .create().show()
                                    }
                                }
                            }
                        }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbRegister.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.signupButton.isEnabled = !isLoading
    }

    private fun validateForm(): Boolean {
        binding.apply {
            val validates = listOf(
                emailEditText.validate("Email", ValidateType.REQUIRED),
                emailEditText.validate("Email", ValidateType.EMAIL),
                nameEditText.validate("Name", ValidateType.REQUIRED),
                passwordEditText.validate("Password", ValidateType.REQUIRED),
                passwordEditText.validate("Password", ValidateType.MIN_CHAR),
            )

            return !validates.contains(false)
        }
    }
}