package com.example.richardmacias.cs6460.MainMeetList

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.richardmacias.cs6460.Data.Card
import com.example.richardmacias.cs6460.R
import kotlinx.android.synthetic.main.card_layout.view.*

class CustomAdapter(private val myDataset: MutableList<Card>, private val listener:onItemClickListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

     interface onItemClickListener{
         fun itemClick(position:Int)
    }

        class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)


        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): CustomAdapter.ViewHolder {
            // create a new view
            val cardView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_layout, parent, false) as CardView
            // set the view's size, margins, paddings and layout parameters
            return ViewHolder(cardView)
        }


        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val currentCard = myDataset[position]
            holder.cardView.setOnClickListener{listener.itemClick(position)}
            holder.cardView.title_card.text = currentCard.title
            holder.cardView.category_card.text = currentCard.category
            holder.cardView.description_card.text = currentCard.description
            holder.cardView.image_card.setImageResource(R.drawable.dummyimage)
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = myDataset.size


}