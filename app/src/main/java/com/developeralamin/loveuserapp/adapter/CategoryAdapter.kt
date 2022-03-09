package com.developeralamin.loveuserapp.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developeralamin.loveuserapp.databinding.ItemCategoryBinding
import com.developeralamin.loveuserapp.model.CategoryModel
import com.developeralamin.loveuserapp.ui.AllShayriActivity
import com.developeralamin.loveuserapp.ui.MainActivity

class CategoryAdapter(val mainActivity: MainActivity, val list: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CatViewHolder>() {

    class CatViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    val colorList = arrayListOf<String>("#7158e2",
        "#00a8ff",
        "#fbc531",
        "#05c46b",
        "#575fcf",
        "#ff793f",
        "#18dcff")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {

        if (position % 7 == 0) {
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorList[0]))
        } else if (position % 7 == 1) {
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorList[1]))
        } else if (position % 7 == 2) {
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorList[2]))
        } else if (position % 7 == 3) {
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorList[3]))
        } else if (position % 7 == 4) {
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorList[4]))
        } else if (position % 7 == 5) {
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorList[5]))
        } else if (position % 7 == 6) {
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorList[6]))
        }

        holder.binding.itemText.text = list[position].name.toString()

        holder.binding.root.setOnClickListener {
            val intent = Intent(mainActivity, AllShayriActivity::class.java)
            intent.putExtra("id", list[position].id)
            intent.putExtra("name", list[position].name)
            mainActivity.startActivity(intent)
        }
    }

    override fun getItemCount() = list.size
}