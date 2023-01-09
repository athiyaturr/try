package com.example.finalproject

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.finalproject.model.AlbumModel

class AddAlbumActivity : AppCompatActivity() {
    lateinit var image: ImageView
    companion object {
        val IMAGE_REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_album)

        //hide title bar
        getSupportActionBar()?.hide()

        //instance
        image = findViewById(R.id.imageAdd)
        val textId:EditText = findViewById(R.id.albumId)
        val textTitle:EditText = findViewById(R.id.albumTitle)
        val textArtist:EditText = findViewById(R.id.albumArtist)
        val textPrice:EditText = findViewById(R.id.albumPrice)
        val btnAdd: Button = findViewById(R.id.buttonAddImage)
        val btnSave: Button = findViewById(R.id.buttonSave)

        btnAdd.setOnClickListener{
            pickImageGalery()
        }

        btnSave.setOnClickListener{
            val databaseHelper = DatabaseHelper(this)

            val id:Int = textId.text.toString().toInt()
            val title:String = textTitle.text.toString().trim()
            val artist:String = textArtist.text.toString().trim()
            val price:Int = textPrice.text.toString().toInt()
            val bitmapDrawable:BitmapDrawable = image.drawable as BitmapDrawable
            val bitmap:Bitmap = bitmapDrawable.bitmap

            val albumModel = AlbumModel(bitmap,id,title,artist,price)
            databaseHelper.addAlbum(albumModel)
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pickImageGalery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type ="image/"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            image.setImageURI(data?.data)
        }
    }
}