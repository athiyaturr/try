package com.example.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReportAdapter : RecyclerView.Adapter<ReportAdapter.MenuViewHolder>() {
    private var title = arrayOf("We Go Up","Glitch Mode")
    private var artist = arrayOf("NCT DREAM","NCT DREAM")
    private var version = arrayOf("Scratch","Glitch")
    //    private var price = arrayOf("$18.00","$22.00","$22.00","$27.50","$22.00","$22.00")
    private val images = intArrayOf(R.drawable.wegoup,R.drawable.glitchmode)


    override fun getItemCount(): Int {
        return  title.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {

        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.cardview_report, parent, false)

        return MenuViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {

//        holder.textNama.text = data[position]
        holder.itemTitle.text=title[position]
        holder.itemArtist.text=artist[position]
//        holder.itemPrice.text=price[position]
//        holder.itemVersion.onItemClickListener
        holder.itemImage.setImageResource(images[position])

    }

    inner class MenuViewHolder(v: View):RecyclerView.ViewHolder(v) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemArtist: TextView
//        var itemVersion: Spinner
//        var itemPrice: TextView

        init {
            itemImage=itemView.findViewById(R.id.itemImage)
            itemTitle=itemView.findViewById(R.id.itemTitle)
            itemArtist=itemView.findViewById(R.id.itemArtist)
//            itemVersion=itemView.findViewById(R.id.itemVersion)
//            itemPrice=itemView.findViewById(R.id.itemPrice)
        }
    }
}