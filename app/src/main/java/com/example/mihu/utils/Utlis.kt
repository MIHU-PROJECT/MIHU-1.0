package com.example.mihu.utils

import android.content.Context
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

object ValidateType {
    const val REQUIRED = 0
    const val MIN_CHAR = 1
    const val EMAIL = 3
}

fun EditText.validate(
    name: String = "field",
    validateType: Int,
    msg: String? = null
): Boolean {
    val text = this.text.toString().trim()
    when (validateType) {
        ValidateType.REQUIRED -> {
            if (text.isEmpty()) {
                var errorMessage = msg
                if (errorMessage == null) {
                    errorMessage =
                        context.getString(
                            com.example.mihu.R.string.err_msg_required,
                            name
                        )
                }
                this.error = errorMessage
                return false
            }
        }


        ValidateType.EMAIL -> {
            if (!text.contains("@")) {
                var errorMessage = msg
                if (errorMessage == null) {
                    errorMessage =
                        context.getString(com.example.mihu.R.string.err_msg_email)
                }
                this.error = errorMessage
                return false
            }
        }
    }
    return true
}

fun ImageView.loadImage(context: Context, url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun AppCompatActivity.showToast(msg: String, isLong: Boolean = false) {
    if (isLong) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    } else {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}