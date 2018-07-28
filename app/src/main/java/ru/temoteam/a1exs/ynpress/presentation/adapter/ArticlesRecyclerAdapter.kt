package ru.temoteam.a1exs.ynpress.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.temoteam.a1exs.ynpress.R
import ru.temoteam.a1exs.ynpress.api.objects.Article
import java.util.*

class ArticlesRecyclerAdapter(val articles: List<Article>): RecyclerView.Adapter<ArticlesRecyclerAdapter.ArticleVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleVH {
        return  ArticleVH(LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false))
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleVH, position: Int) {
        holder.title.text = articles[position].title
    }


    class ArticleVH(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val title = itemView!!.findViewById<TextView>(R.id.title)
    }
}