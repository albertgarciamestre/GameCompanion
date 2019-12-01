package com.game.gamecompanion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_secret.view.*
import layout.SecretModel

class SecretsListAdapter: RecyclerView.Adapter<SecretsListAdapter.ViewHolder>() {

    var elements = ArrayList<SecretModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_secret, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return elements.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val element = elements[position]

        //Update Views
        holder.title.text = element.title
        holder.description.text = element.description
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val title = itemView.title
        val description = itemView.description
    }
}