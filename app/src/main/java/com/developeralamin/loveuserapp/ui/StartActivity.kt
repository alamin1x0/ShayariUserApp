package com.developeralamin.loveuserapp.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.developeralamin.loveuserapp.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnRate.setOnClickListener {
            val appName = this.packageName
            try {
                this.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appName")))
            } catch (anfe: ActivityNotFoundException) {
                this.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=$appName")))
            }

        }

        binding.btnMore.setOnClickListener {
            val appName: String = this.getPackageName()
            try {
                this.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appName")))
            } catch (anfe: ActivityNotFoundException) {
                this.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=$appName")))
            }

        }
    }
}