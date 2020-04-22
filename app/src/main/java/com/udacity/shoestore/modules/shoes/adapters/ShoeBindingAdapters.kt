package com.udacity.shoestore.modules.shoes.adapters

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.textfield.TextInputEditText

object ShoeBindingAdapters {

    @BindingAdapter("shoeSize")
    @JvmStatic
    fun setShoeSize(view: TextInputEditText, value: Double) {
        view.setText(if (value == 0.0) "" else value.toString())
    }

    @InverseBindingAdapter(attribute = "shoeSize")
    @JvmStatic
    fun getShoeSize(editText: TextInputEditText): Double {
        return try {
            editText.text.toString().toDouble()
        } catch (exc: NumberFormatException) {
            0.0
        }
    }

    @BindingAdapter("shoeSizeAttrChanged")
    @JvmStatic
    fun setShoeSizeListener(view: TextInputEditText, listener: InverseBindingListener?) {
        view.onFocusChangeListener = View.OnFocusChangeListener { focusedView, hasFocus ->
            val textView = focusedView as TextInputEditText
            if (hasFocus) textView.setText("")
            else listener?.onChange()
        }
    }


    @BindingAdapter("imageList")
    @JvmStatic
    fun setImageList(view: LinearLayout, images: List<Bitmap>) {
        images.forEach {
            val imageView = ImageView(view.context)
            imageView.setImageBitmap(it)
            view.addView(imageView)
        }
    }

}