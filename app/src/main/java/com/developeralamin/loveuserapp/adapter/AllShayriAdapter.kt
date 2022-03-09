package com.developeralamin.loveuserapp.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.developeralamin.loveuserapp.R
import com.developeralamin.loveuserapp.databinding.ItemAllShayriBinding
import com.developeralamin.loveuserapp.model.ShayriModel
import com.developeralamin.loveuserapp.ui.AllShayriActivity


class AllShayriAdapter(
    val allShayriActivity: AllShayriActivity,
    val shayriList: ArrayList<ShayriModel>,
) : RecyclerView.Adapter<AllShayriAdapter.ShayriViewHolder>() {

    class ShayriViewHolder(val binding: ItemAllShayriBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShayriViewHolder {

        return ShayriViewHolder(ItemAllShayriBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ShayriViewHolder, position: Int) {

        if (position % 7 == 0) {
            holder.binding.mainBack.setBackgroundResource(R.drawable.gradient_1)
        } else if (position % 7 == 1) {
            holder.binding.mainBack.setBackgroundResource(R.drawable.gradient_2)
        } else if (position % 7 == 2) {
            holder.binding.mainBack.setBackgroundResource(R.drawable.gradient_3)
        } else if (position % 7 == 3) {
            holder.binding.mainBack.setBackgroundResource(R.drawable.gradient_4)
        } else if (position % 7 == 4) {
            holder.binding.mainBack.setBackgroundResource(R.drawable.gradient_5)
        } else if (position % 7 == 5) {
            holder.binding.mainBack.setBackgroundResource(R.drawable.gradient_6)
        } else if (position % 7 == 6) {
            holder.binding.mainBack.setBackgroundResource(R.drawable.gradient_7)
        }

        holder.binding.btnShare.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plane"
            share.putExtra(Intent.EXTRA_TEXT, shayriList[position].data)
            allShayriActivity.startActivity(share)

            Toast.makeText(allShayriActivity, "Shayari Share Successfully", Toast.LENGTH_SHORT).show()
        }

        holder.binding.btnCopy.setOnClickListener {
            val clipboardManager: ClipboardManager =
                allShayriActivity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val data = ClipData.newPlainText("text", shayriList[position].data) as ClipData
            clipboardManager.setPrimaryClip(data)

            Toast.makeText(allShayriActivity, "Shayari Copied Successfully", Toast.LENGTH_SHORT).show()
        }

        holder.binding.btnWhatapps.setOnClickListener {
            try {
                val whatsappShare = Intent(Intent.ACTION_SEND)
                whatsappShare.type = "text/plane"
                whatsappShare.setPackage("com.whatsapp")
                whatsappShare.putExtra(Intent.EXTRA_TEXT, shayriList[position].data)
                allShayriActivity.startActivity(whatsappShare)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        holder.binding.itemShayri.text = shayriList[position].data.toString()
    }

    override fun getItemCount() = shayriList.size

}