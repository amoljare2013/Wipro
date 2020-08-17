package com.amol.jare.wipro.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amol.jare.wipro.R
import com.amol.jare.wiproapp.model.Album
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_list.view.*


class ListAdapter(private val itemList: List<Album>, private val onItemClick: ((Album) -> Unit)?) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var context: Context? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list, parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            album: Album,
            onItemClick: ((Album) -> Unit)?
        ) {
            val album = itemList.get(adapterPosition)

            Glide.with(context!!)
                .load(album.image?.get(2)?.text)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.ivProfile)

            itemView.tvName.setText(album.name)
            itemView.tvDetails.setText(album.artist)


            itemView.setOnClickListener {
                onItemClick?.invoke(itemList[adapterPosition])
            }
        }

    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        holder.bind(itemList[position], onItemClick)
    }

}