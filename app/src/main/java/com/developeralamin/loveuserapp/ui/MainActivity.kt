package com.developeralamin.loveuserapp.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.developeralamin.loveuserapp.R
import com.developeralamin.loveuserapp.adapter.CategoryAdapter
import com.developeralamin.loveuserapp.databinding.ActivityMainBinding
import com.developeralamin.loveuserapp.model.CategoryModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        val settings = FirebaseFirestoreSettings.Builder()
            .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
            .build()
        db.firestoreSettings = settings

        db.collection("Love").addSnapshotListener { value, error ->

            val list = arrayListOf<CategoryModel>()
            val data = value?.toObjects(CategoryModel::class.java)
            list.addAll(data!!)

            binding.revCategroy.layoutManager = LinearLayoutManager(this)
            binding.revCategroy.adapter = CategoryAdapter(this, list)

        }


        binding.btnMenu.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
            } else {
                binding.drawerLayout.openDrawer(Gravity.LEFT)
            }
        }


        binding.navigationView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.share -> {
                    val appLink = """
                        
                        https://play.google.com/store/apps/details?id=${this.getPackageName()}
                        """.trimIndent()
                    val sendInt = Intent(Intent.ACTION_SEND)
                    sendInt.putExtra(Intent.EXTRA_SUBJECT, this.getString(R.string.app_name))
                    sendInt.putExtra(Intent.EXTRA_TEXT,
                        this.getString(R.string.share_app_message).toString() + appLink)
                    sendInt.type = "text/plain"
                    this.startActivity(Intent.createChooser(sendInt, "Share"))
                    true
                }
                R.id.rate -> {
                    val appName = this.packageName
                    try {
                        this.startActivity(Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$appName")))
                    } catch (anfe: ActivityNotFoundException) {
                        this.startActivity(Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=$appName")))
                    }
                    true
                }
                R.id.more -> {
                    val appName: String = this.getPackageName()
                    try {
                        this.startActivity(Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$appName")))
                    } catch (anfe: ActivityNotFoundException) {
                        this.startActivity(Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=$appName")))
                    }
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            binding.drawerLayout.closeDrawer(Gravity.LEFT)
        } else {
            super.onBackPressed()
        }
    }
}