package com.example.mihu.ui.worker.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.mihu.R
import com.example.mihu.data.Result
import com.example.mihu.databinding.ActivityDetailJobWorkerBinding
import com.example.mihu.ui.ViewModelFactory
import com.example.mihu.ui.worker.home.HomeWorkerActivity
import com.example.mihu.utils.loadImage
import com.example.mihu.utils.showToast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DetailJobWorkerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailJobWorkerBinding
    private val viewModel: DetailJobWorkerViewModel by viewModels {
        ViewModelFactory.getInstance(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailJobWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryName = intent.getStringExtra(NAME_KEY)
        val categoryImage = intent.getStringExtra(IMAGE_KEY)
        val description = intent.getStringExtra(MESSAGE)

        binding.tvMessage.text = description
        binding.tvJob.text = categoryName
        binding.ivJob.setImageResource(R.drawable.ic_toolsimg)


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
                val jobId = intent.getStringExtra(ID_KEY)

                if (jobId != null) {
                    viewModel.takeJob(token, jobId)
                        .observe(this@DetailJobWorkerActivity) { result ->
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
                                        MaterialAlertDialogBuilder(this@DetailJobWorkerActivity).apply {
                                            setTitle(getString(R.string.job_take))
                                            setMessage(getString(R.string.please_job))
                                            setPositiveButton("Ok") { dialog, _ ->
                                                dialog.dismiss()
                                                val intent = Intent(
                                                    this@DetailJobWorkerActivity,
                                                    HomeWorkerActivity::class.java
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


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.btnOrder.isEnabled = !isLoading
    }


    companion object {
        const val ID_KEY = "_id"
        const val NAME_KEY = "category_name"
        const val IMAGE_KEY = "category_image"
        const val MESSAGE = "description"
        const val PRICE = "price"
    }
}
