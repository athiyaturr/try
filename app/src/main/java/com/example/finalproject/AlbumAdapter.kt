package com.example.finalproject

import android.graphics.Bitmap
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.model.AlbumModel

class AlbumAdapter(private val list:ArrayList<AlbumModel>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {
//    val data = listOf("We Go Up","Hot Sauce","Hello Future","Glitch Mode","Beat Box","Reload")
//    private var title = arrayOf("We Go Up","Hot Sauce","Hello Future","Glitch Mode","Beat Box","Reload")
//    private var artist = arrayOf("NCT DREAM","NCT DREAM","NCT DREAM","NCT DREAM","NCT DREAM","NCT DREAM")
//    private var price = arrayOf("$18.00","$22.00","$22.00","$27.50","$22.00","$22.00")
//    private val images = intArrayOf(R.drawable.wegoup,R.drawable.hotsauce,R.drawable.hellofuture,R.drawable.glitchmode,R.drawable.beatbox,R.drawable.reload)


    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {

        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.cardview_album, parent, false)

        return AlbumViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class AlbumViewHolder(v:View):RecyclerView.ViewHolder(v) {
        var itemImage: ImageView
        var itemId:TextView
        var itemTitle:TextView
        var itemArtist:TextView
        var itemPrice:TextView
        val buttonAdd : Button
        val context = v.context

        init {
            itemImage=v.findViewById(R.id.itemImage)
            itemId=v.findViewById(R.id.itemId)
            itemTitle=v.findViewById(R.id.itemTitle)
            itemArtist=v.findViewById(R.id.itemArtist)
            itemPrice=v.findViewById(R.id.itemPrice)
            buttonAdd = v.findViewById(R.id.btnAdd)

            buttonAdd.setOnClickListener{
                TransactionAdapter.amount = TransactionAdapter.listId.count()

                val jumlahData = TransactionAdapter.amount
                if (jumlahData == 0) {
                    TransactionAdapter.listId.plusAssign(itemId.text.toString().toInt())
                    TransactionAdapter.listTitle.plusAssign(itemTitle.text.toString())
                    TransactionAdapter.listArtist.plusAssign(itemTitle.text.toString())
                    TransactionAdapter.listPrice.plusAssign(itemPrice.text.toString().toInt())
                    TransactionAdapter.listImage.plusAssign(itemImage.drawable.toBitmap(80, 80, null))
                    TransactionAdapter.listAmount.plusAssign(1)
                    TransactionAdapter.price =
                        TransactionAdapter.price + itemPrice.text.toString().toInt()
                    Toast.makeText(v.context, "Purchase Order Success", Toast.LENGTH_SHORT).show()
                } else {
                    //cek data
                    val cek = TransactionAdapter.listId.find { data ->
                        itemId.text.toString().toInt().equals(data)
                    }

                    if (cek == null) {
                        TransactionAdapter.listId.plusAssign(itemId.text.toString().toInt())
                        TransactionAdapter.listTitle.plusAssign(itemTitle.text.toString())
                        TransactionAdapter.listArtist.plusAssign(itemArtist.text.toString())
                        TransactionAdapter.listPrice.plusAssign(itemPrice.text.toString().toInt())
                        TransactionAdapter.listImage.plusAssign(itemImage.drawable.toBitmap(80, 80, null))
                        TransactionAdapter.listAmount.plusAssign(1)
                        TransactionAdapter.price =
                            TransactionAdapter.price + itemPrice.text.toString().toInt()
                        Toast.makeText(v.context, "Purchase Order Success", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        var indek: Int =
                            TransactionAdapter.listId.indexOf(itemId.text.toString().toInt())
                        val qty: Int = TransactionAdapter.listAmount[indek] + 1
                        TransactionAdapter.listAmount.set(indek, qty)
                        TransactionAdapter.price =
                            TransactionAdapter.price + TransactionAdapter.listPrice[indek]
                        Toast.makeText(v.context, "Purchase Order Success", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        fun bind(data: AlbumModel){
            val id:Int = data.idAlbum
            val title:String = data.titleAlbum
            val artist:String = data.artistAlbum
            val price:Int = data.priceAlbum
            val image:Bitmap = data.imageAlbum

            itemImage.setImageBitmap(image)
            itemId.text = id.toString()
            itemTitle.text = title
            itemArtist.text = artist
            itemPrice.text = price.toString()

        }
    }
}