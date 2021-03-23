package com.necatisozer.dsc.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import coil.load
import com.necatisozer.dsc.myapplication.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = User(
            "Androider",
            "https://www.pngkey.com/png/full/114-1149878_setting-user-avatar-in-specific-size-without-breaking.png",
            false
        )

        binding.user = user
    }
}

data class User(val username: String, val photoUrl: String, val isPremium: Boolean)

@BindingAdapter("imageUrl")
fun ImageView.loadUrl(url: String) {
    load(url)
}

@BindingAdapter("isPremium")
fun View.setPremiumBackground(isPremium: Boolean) {
    if (isPremium) {
        setBackgroundColor(Color.GREEN)
    } else {
        setBackgroundColor(Color.RED)
    }
}