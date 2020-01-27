package com.game.gamecompanion.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.game.gamecompanion.R
import com.game.gamecompanion.model.NewsModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_secret.view.*

class Newsadapter(
    var list:List<NewsModel>

): RecyclerView.Adapter<Newsadapter.ViewHolder>(){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView=itemView.title
        val descriptionView=itemView.description
        //val imageView=itemView.image

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.item_secret,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.textView.text=list[position].title
        holder.descriptionView.text=list[position].description
       // Picasso.get().load(list[position].Url).into(holder.imageView)
    }

}



