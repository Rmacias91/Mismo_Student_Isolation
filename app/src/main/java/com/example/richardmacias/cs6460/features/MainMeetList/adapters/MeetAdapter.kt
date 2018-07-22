package com.example.richardmacias.cs6460.features.MainMeetList.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.richardmacias.cs6460.Constants.Constants
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard
import com.example.richardmacias.cs6460.R
import kotlinx.android.synthetic.main.card_layout.view.*

class MeetAdapter(private val myDataset: MutableList<MeetCard>, private val listener: onItemClickListener) : RecyclerView.Adapter<MeetAdapter.ViewHolder>() {

    interface onItemClickListener {
        fun itemClick(position: Int)
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_layout, parent, false) as CardView
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCard = myDataset[position]
        holder.cardView.setOnClickListener { listener.itemClick(position) }
        holder.cardView.title_card.text = currentCard.title
        holder.cardView.category_card.text = currentCard.category
        holder.cardView.description_card.text = currentCard.description
        val imageSrc:Int
        imageSrc = when (currentCard.category) {
            Constants.CATEGORY_AFTER_SCHOOL -> R.drawable.social
            Constants.CATEGORY_LUNCH -> R.drawable.lunch
            else -> R.drawable.social_tree
        }
        holder.cardView.image_card.setImageResource(imageSrc)
    }

    override fun getItemCount() = myDataset.size


}