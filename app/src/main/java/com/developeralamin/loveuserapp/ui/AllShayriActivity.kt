package com.developeralamin.loveuserapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.developeralamin.loveuserapp.adapter.AllShayriAdapter
import com.developeralamin.loveuserapp.databinding.ActivityAllShayriBinding
import com.developeralamin.loveuserapp.model.ShayriModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class AllShayriActivity : AppCompatActivity() {

    lateinit var binding: ActivityAllShayriBinding
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllShayriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        val settings = FirebaseFirestoreSettings.Builder()
            .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
            .build()
        db.firestoreSettings = settings

        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")


        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.catName.text = name.toString()

        db.collection("Love").document(id!!).collection("all")
            .addSnapshotListener { value, error ->

                val shayriList = arrayListOf<ShayriModel>()
                val data = value?.toObjects(ShayriModel::class.java)
                shayriList.addAll(data!!)

                binding.rcvAllShayari.layoutManager = LinearLayoutManager(this)
                binding.rcvAllShayari.adapter = AllShayriAdapter(this, shayriList)


            }
    }
}