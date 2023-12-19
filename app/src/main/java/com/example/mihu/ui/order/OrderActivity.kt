package com.example.mihu.ui.order

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.mihu.R
import com.example.mihu.data.Result
import com.example.mihu.databinding.ActivityOrderBinding
import com.example.mihu.ui.ViewModelFactory
import com.example.mihu.ui.history.HistoryFragment
import com.example.mihu.utils.ValidateType
import com.example.mihu.utils.loadImage
import com.example.mihu.utils.showToast
import com.example.mihu.utils.validate
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    private val viewModel: OrderViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val categoryName = intent.getStringExtra(NAME_KEY)
        val categoryImage = intent.getStringExtra(IMAGE_KEY)

        binding.tvJob.text = categoryName
        if (categoryImage != null) {
            binding.ivJob.loadImage(this, categoryImage)
        }
        viewModel.token.observe(this) { token ->
            setupAction(token)
        }
    }

    private fun setupAction(
        token: String
    ) {
        binding.apply {
            btnOrder.setOnClickListener {
                if (validateForm()) {
                    val categoryId = intent.getStringExtra(ID_KEY)
                    val name = intent.getStringExtra(NAME_KEY)
                    val message = etMessage.text.toString()
                    val price = etPrice.text.toString().toDoubleOrNull()
                    if (name != null && categoryId != null && price != null) {
                        viewModel.postOrder(token, name, message, categoryId, price)
                            .observe(this@OrderActivity) { result ->
                                if (result != null) {
                                    when (result) {
                                        is Result.Loading -> {
                                            showLoading(true)
                                            btnOrder.isEnabled = false

                                        }

                                        is Result.Error -> {
                                            showLoading(false)
                                            progressBar.isVisible = false
                                            showToast(result.error.toString())
                                        }

                                        is Result.Success -> {
                                            showLoading(false)
                                            progressBar.isVisible = false
                                            MaterialAlertDialogBuilder(this@OrderActivity).apply {
                                                setTitle(getString(R.string.your_order_submitted))
                                                setMessage(getString(R.string.please_wait_for))
                                                setPositiveButton("Ok") { dialog, _ ->
                                                    dialog.dismiss()
                                                    val intent = Intent(
                                                        this@OrderActivity,
                                                        HistoryFragment::class.java
                                                    )
                                                    intent.flags =
                                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                    startActivity(intent)
                                                }
                                            }.create().show()
                                        }
                                    }
                                }
                            }
                    }
                }
            }
        }
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.btnOrder.isEnabled = !isLoading
    }

    private fun validateForm(): Boolean {
        binding.apply {
            val validates = listOf(
                etPrice.validate("Name", ValidateType.REQUIRED),
                etMessage.validate("Password", ValidateType.REQUIRED),
            )

            return !validates.contains(false)
        }
    }


    companion object {
        const val ID_KEY = "category_id"
        const val NAME_KEY = "category_name"
        const val IMAGE_KEY = "category_image"
    }
}
