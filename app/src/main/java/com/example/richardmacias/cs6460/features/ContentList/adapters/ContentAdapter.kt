package com.example.richardmacias.cs6460.features.ContentList.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.richardmacias.cs6460.R
import com.example.richardmacias.cs6460.features.ContentList.models.ContentCard
import kotlinx.android.synthetic.main.card_layout.view.*

class ContentAdapter(private val cards:MutableList<ContentCard>,
                     private val listener:onItemClickListener) : RecyclerView.Adapter<ContentAdapter.ViewHolder>(){

    interface onItemClickListener{
        fun itemClick(position:Int, isArticle:Boolean)
    }

     class ViewHolder(val cardView: CardView): RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_layout, parent, false) as CardView
        return ContentAdapter.ViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCard = cards[position]
        holder.cardView.setOnClickListener{listener.itemClick(position,currentCard.isArticle)}
        holder.cardView.title_card.text = currentCard.title
        holder.cardView.category_card.text = currentCard.category
        holder.cardView.description_card.text = currentCard.description
        holder.cardView.image_card.setImageResource(R.drawable.social)
    }


}