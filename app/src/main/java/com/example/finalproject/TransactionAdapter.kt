package com.example.finalproject

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class TransactionAdapter: RecyclerView.Adapter<TransactionAdapter.TransaksiViewHolder>()  {

    companion object{
        var amount:Int = 0
        var listId = mutableListOf<Int>()
        var listTitle = mutableListOf<String>()
        var listArtist = mutableListOf<String>()
        var listPrice = mutableListOf<Int>()
        var listImage = mutableListOf<Bitmap>()
        var listAmount = mutableListOf<Int>()

        var price:Int = 0;
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionAdapter.TransaksiViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.cardview_transaction,
            parent, false)

        return TransaksiViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: TransactionAdapter.TransaksiViewHolder, position: Int) {
        holder.idAlbum.text = listId[position].toString()
        holder.titleAlbum.text= listTitle[position]
        holder.artistAlbum.text = listArtist[position]
        holder.priceAlbum.text = listPrice[position].toString()
        holder.amount.text = listAmount[position].toString()
        holder.image.setImageBitmap(listImage[position])

    }

    override fun getItemCount(): Int {
        return listId.size
    }

    inner class TransaksiViewHolder(v: View):RecyclerView.ViewHolder(v) {
        val image:ImageView
        val idAlbum:TextView
        val titleAlbum:TextView
        val artistAlbum:TextView
        val priceAlbum:TextView
        val amount:TextView
        val add:Button
        val remove:Button
        val btnDelete: Button
        val context = v.context

        init {
            image = v.findViewById(R.id.itemImage)
            idAlbum = v.findViewById(R.id.itemId)
            titleAlbum = v.findViewById(R.id.itemTitle)
            artistAlbum = v.findViewById(R.id.itemArtist)
            priceAlbum = v.findViewById(R.id.itemPrice)
            amount = v.findViewById(R.id.amount)
            add = v.findViewById(R.id.btnPlus)
            remove = v.findViewById(R.id.btnMinus)
            btnDelete = v.findViewById(R.id.btnDelete)

            add.setOnClickListener {
                val qty:Int = amount.text.toString().toInt()
                Toast.makeText(v.context,amount.text.toString(),Toast.LENGTH_SHORT).show()
                amount.text = (qty + 1).toString()
                price += priceAlbum.text.toString().toInt()
//                Toast.makeText(v.context,price.toString(), Toast.LENGTH_SHORT).show()
                FragmentTransaction.txtOrder.text = price.toString()
                FragmentTransaction.txtTax.text = (price * 0.10).toString()
                FragmentTransaction.txtTotal.text = (price + (price * 0.10)).toString()
            }

            remove.setOnClickListener {
                val qty:Int = amount.text.toString().toInt()
                if(qty > 1){
                    amount.text = (qty - 1).toString()
                    price -= priceAlbum.text.toString().toInt()
                    FragmentTransaction.txtOrder.text = price.toString()
                    FragmentTransaction.txtTax.text = (price * 0.10).toString()
                    FragmentTransaction.txtTotal.text = (price + (price * 0.10)).toString()
                }
            }
        }

    }

}