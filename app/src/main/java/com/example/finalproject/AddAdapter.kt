package com.example.finalproject

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.model.AddModel
import com.example.finalproject.model.AlbumModel

class AddAdapter(private val list:ArrayList<AlbumModel>):RecyclerView.Adapter<AddAdapter.AddViewHolder>(){

//    private var title = arrayOf("We Go Up","Hot Sauce","Hello Future","Glitch Mode","Beat Box","Reload")
//    private var artist = arrayOf("NCT DREAM","NCT DREAM","NCT DREAM","NCT DREAM","NCT DREAM","NCT DREAM")
//    private var price = arrayOf("$18.00","$22.00","$22.00","$27.50","$22.00","$22.00")
//    private val images = intArrayOf(R.drawable.wegoup,R.drawable.hotsauce,R.drawable.hellofuture,R.drawable.glitchmode,R.drawable.beatbox,R.drawable.reload)


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddViewHolder {

        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.cardview_add, parent, false)

        return AddViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: AddViewHolder, position: Int) {
        holder.bind(list[position])

    }

    inner class AddViewHolder(v:View):RecyclerView.ViewHolder(v) {
        var itemImage: ImageView
        var itemId:TextView
        var itemTitle:TextView
        var itemArtist:TextView
        var itemPrice:TextView
        val buttonEdit : Button
        val buttonDel:Button
        val context = v.context

        init {
            itemImage=v.findViewById(R.id.itemImage)
            itemId=v.findViewById(R.id.itemId)
            itemTitle=v.findViewById(R.id.itemTitle)
            itemArtist=v.findViewById(R.id.itemArtist)
            itemPrice=v.findViewById(R.id.itemPrice)
            buttonEdit=v.findViewById(R.id.btnEdit)
            buttonDel=v.findViewById(R.id.btnDelete)

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