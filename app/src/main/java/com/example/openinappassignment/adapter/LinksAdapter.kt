package com.example.openinappassignment.adapter

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openinappassignment.R
import com.example.openinappassignment.model.Link
import com.example.openinappassignment.utils.Utils

class LinksAdapter(private val list: List<Link>, private val context: Context) :
    RecyclerView.Adapter<LinksAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imvIcon: AppCompatImageView = itemView.findViewById(R.id.imvIcon)
        val vCopy: View = itemView.findViewById(R.id.vCopy)
        val txtLinkName: AppCompatTextView = itemView.findViewById(R.id.txtLinkName)
        val txtClicks: AppCompatTextView = itemView.findViewById(R.id.txtClicks)
        val txtTime: AppCompatTextView = itemView.findViewById(R.id.txtTime)
        val txtLink: AppCompatTextView = itemView.findViewById(R.id.txtLink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_links, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = list[position]
        holder.txtTime.text = item.created_at
        holder.txtLinkName.text = item.title
        holder.txtClicks.text = item.total_clicks.toString()
        holder.txtLink.text = item.web_link
        Glide.with(context).load(item.original_image).into(holder.imvIcon);

        holder.vCopy.setOnClickListener {
            Utils().copyTextToClipboard(context, item.web_link)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


}
