package ru.temoteam.a1exs.ynpress.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.temoteam.a1exs.ynpress.R

class AchievementsRecyclerAdapter(val achivements:List<String>):RecyclerView.Adapter<AchievementsRecyclerAdapter.AchievementsVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementsVH {
        return AchievementsRecyclerAdapter.AchievementsVH(LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false))
    }

    override fun getItemCount(): Int {
        return achivements.size
    }

    override fun onBindViewHolder(holder: AchievementsVH, position: Int) {
        holder.title.text = achivements[position]
    }

    class AchievementsVH(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val title = itemView!!.findViewById<TextView>(R.id.title)
    }
}