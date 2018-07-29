package ru.temoteam.a1exs.ynpress.presentation.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.uiThread
import ru.temoteam.a1exs.ynpress.R
import ru.temoteam.a1exs.ynpress.api.Parser
import ru.temoteam.a1exs.ynpress.api.Requester
import ru.temoteam.a1exs.ynpress.api.objects.Article
import ru.temoteam.a1exs.ynpress.ui.activity.ArticleActivity
import ru.temoteam.a1exs.ynpress.util.ImageLoader
import java.net.URL
import java.util.*

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class ReadRecyclerAdapter: RecyclerView.Adapter<ReadRecyclerAdapter.ReadVH>() {

    val articles = LinkedList<Article>()
    private var page = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadVH {
        return  ReadVH(LayoutInflater.from(parent.context).inflate(R.layout.item_read, parent, false))
    }

    override fun getItemCount(): Int {
        return articles.size+1
    }

    override fun onBindViewHolder(holder: ReadVH, position: Int) {
         if (position==articles.size){
             doAsync {
                 Log.i("LOADING",articles.size.toString())
                 page++
                 val new = Parser.parseRead(Requester.read(page)!!.text)
                 articles.addAll(new)
                 uiThread {
                     notifyDataSetChanged()
                     ImageLoader.addToPreLoad(new.map { it.imgURL!! })
                 }
             }
             holder.title.text = "Загрузка"
         }else{
             holder.title.text = articles[position].title
             holder.date.text = articles[position].date
             holder.category.text = articles[position].category
             holder.card.onClick {
                 holder.itemView.context.startActivity(ArticleActivity.getIntent(holder.itemView.context))
             }
             ImageLoader.getBitmapAsync(articles[position].imgURL!!) {holder.img.setImageBitmap(it)}


         }
    }


    class ReadVH(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val title = itemView!!.findViewById<TextView>(R.id.title)!!
        val date = itemView!!.findViewById<TextView>(R.id.date)!!
        val category = itemView!!.findViewById<TextView>(R.id.category)!!
        val img = itemView!!.findViewById<ImageView>(R.id.img)!!
        val card = itemView!!.findViewById<CardView>(R.id.card)!!
    }
}