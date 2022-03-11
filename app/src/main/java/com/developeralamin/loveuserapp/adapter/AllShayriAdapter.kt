package com.developeralamin.loveuserapp.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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

            val appLink = """
                
               https://play.google.com/store/apps/details?id=${allShayriActivity.getPackageName()}
                """.trimIndent()
            val sendInt = Intent(Intent.ACTION_SEND)
            sendInt.putExtra(Intent.EXTRA_SUBJECT, allShayriActivity.getString(R.string.app_name))
            sendInt.putExtra(Intent.EXTRA_TEXT,
                allShayriActivity.getString(R.string.share_app_message).toString() + appLink)
            sendInt.type = "text/plain"
            allShayriActivity.startActivity(Intent.createChooser(sendInt, "Share"))

        }

        holder.binding.btnCopy.setOnClickListener {
            val clipboardManager: ClipboardManager =
                allShayriActivity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val data = ClipData.newPlainText("text", shayriList[position].data) as ClipData
            clipboardManager.setPrimaryClip(data)

            Toast.makeText(allShayriActivity, "Shayari Copied Successfully", Toast.LENGTH_SHORT)
                .show()
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

        setAnimation(holder.binding.root)

        holder.binding.itemShayri.text = shayriList[position].data.toString()
    }

    fun setAnimation(view: View) {
        val animation: Animation = AnimationUtils.loadAnimation(allShayriActivity, android.R.anim.slide_in_left)
        view.animation = animation
    }

    override fun getItemCount() = shayriList.size

}