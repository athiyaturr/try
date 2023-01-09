package com.example.finalproject

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import com.example.finalproject.model.AlbumModel
import com.example.finalproject.model.ReportModel
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DatabaseHelper(var context:Context): SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
){
    companion object{
        private val DATABASE_NAME = "albumshop"
        private val DATABASE_VERSION = 6

        //account table
        private val TABLE_ACCOUNT = "account"

        private val COLUMN_NAME = "name"
        private val COLUMN_PASSWORD = "password"

        //album table
        private val TABLE_ALBUM = "album"

        private val COLUMN_KODE = "kode"
        private val COLUMN_TITLE = "title"
        private val COLUMN_UNIT = "unit"
        private val COLUMN_PRICE = "price"
        private val COLUMN_IMAGE = "image"

        //transaction table
        private val TABLE_TRANS = "trans"

        private val COLUMN_IDTRANS = "idTrans"
        private val COLUMN_DATE = "date"
        private val COLUMN_ADMIN = "admin"

        //detail transaction table
        private val TABLE_DETAILTRANS = "detailTrans"

        private val COLUMN_ID_DETAIL = "idDetailTrans"
        private val COLUMN_ID_TRANS = "idTransaction"
        private val COLUMN_ID_ORDER = "kode"
        private val COLUMN_PRICE_ORDER = "prices"
        private val COLUMN_AMOUNT = "amount"
    }

    private val CREATE_ACCOUNT_TABLE = ("CREATE TABLE " + TABLE_ACCOUNT + "(" + COLUMN_NAME + " TEXT PRIMARY KEY, "
            + COLUMN_PASSWORD + " TEXT)")

    private val DROP_ACCOUNT_TABLE = "DROP TABLE IF EXISTS $TABLE_ACCOUNT"

    private val CREATE_ALBUM_TABLE = ("CREATE TABLE " + TABLE_ALBUM + "(" + COLUMN_KODE + " TEXT PRIMARY KEY, "
            + COLUMN_TITLE + " TEXT, " + COLUMN_UNIT + " TEXT, " + COLUMN_PRICE + " TEXT, " + COLUMN_IMAGE + " TEXT)")

    private val DROP_ALBUM_TABLE = "DROP TABLE IF EXISTS $TABLE_ALBUM"

    //create table transaksi sql query
    private val CREATE_TRANSACTION_TABLE = ("CREATE TABLE " + TABLE_TRANS + "("
            + COLUMN_IDTRANS + " INT PRIMARY KEY, "+ COLUMN_DATE +" TEXT, "
            + COLUMN_ADMIN + " TEXT)")

    //drop table transaksi sql query
    private val DROP_TABLE_TRANSACTION = "DROP TABLE IF EXISTS $TABLE_TRANS"

    //create table detail transaksi sql query
    private val CREATE_DET_TRANS_TABLE = ("CREATE TABLE " + TABLE_DETAILTRANS + "("
            + COLUMN_ID_DETAIL + " INT PRIMARY KEY, "+ COLUMN_ID_TRANS +" INT, "
            + COLUMN_ID_ORDER + " INT, "+ COLUMN_PRICE_ORDER + " INT, "
            + COLUMN_AMOUNT + " INT)")

    //drop table detail transaksi sql query
    private val DROP_DET_TRANS_TABLE = "DROP TABLE IF EXISTS $TABLE_DETAILTRANS"

    private val INSERT_AKUN = "INSERT INTO " + TABLE_ACCOUNT + " VALUES ('siti','12345')"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(CREATE_ACCOUNT_TABLE)
        p0?.execSQL(CREATE_ALBUM_TABLE)
        p0?.execSQL(CREATE_TRANSACTION_TABLE)
        p0?.execSQL(CREATE_DET_TRANS_TABLE)
        p0?.execSQL(INSERT_AKUN)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(DROP_ACCOUNT_TABLE)
        p0?.execSQL(DROP_ALBUM_TABLE)
        p0?.execSQL(DROP_TABLE_TRANSACTION)
        p0?.execSQL(DROP_DET_TRANS_TABLE)
        onCreate(p0)
    }

    fun checkLogin(name:String, password:String):Boolean{
        val colums = arrayOf(COLUMN_NAME)
        val db = this.readableDatabase
        //selection criteria
        val selection = "$COLUMN_NAME = ? AND $COLUMN_PASSWORD = ?"
        //selection arguments
        val selectionArgs = arrayOf(name,password)

        val cursor = db.query(TABLE_ACCOUNT,
            colums,
            selection,
            selectionArgs,
            null,
            null,
            null)

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        //check data available or not
        return if(cursorCount > 0)
            true
        else
            false
    }

    fun addAccount(name:String, password: String){
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_NAME,name)
        values.put(COLUMN_PASSWORD, password)

        val result = db.insert(TABLE_ACCOUNT,null,  values)
        //show message
        if(result==(0).toLong()){
            Toast.makeText(context, "Register Failed", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "Register Success, " + "Please Login Using Your New Account", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    @SuppressLint("Range")
//    fun checkData(name: String):String{
//        val colums = arrayOf(COLUMN_NAME)
//        val db = this.readableDatabase
//        val selection = "$COLUMN_NAME = ?"
//        val selectionArgs = arrayOf(name)
//        var name:String = ""
//
//        val cursor = db.query(TABLE_ACCOUNT,
//        colums,
//        selection,
//        selectionArgs,
//        null,
//        null,
//        null)
//
//        if (cursor.moveToFirst()){
//            name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
//        }
//        cursor.close()
//        db.close()
//        return name
//    }

    fun addAlbum(album:AlbumModel){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_KODE, album.idAlbum)
        values.put(COLUMN_TITLE, album.titleAlbum)
        values.put(COLUMN_UNIT, album.artistAlbum)
        values.put(COLUMN_PRICE, album.priceAlbum)

        //prepare image
        val byteOutputStream = ByteArrayOutputStream()
        val imageInByte:ByteArray
        album.imageAlbum.compress(Bitmap.CompressFormat.JPEG,100,byteOutputStream)
        imageInByte = byteOutputStream.toByteArray()
        values.put(COLUMN_IMAGE, imageInByte)

        val result = db.insert(TABLE_ALBUM,null, values)
        //show message
//        if (result==(0).toLong()){
//            Toast.makeText(context,"Add Album Failed",Toast.LENGTH_SHORT).show()
//        }else{
//            Toast.makeText(context,"Add Album Success",Toast.LENGTH_SHORT).show()
//        }
        db.close()
    }

    @SuppressLint("Range")
    fun showAlbum():ArrayList<AlbumModel>{
        val listModel = ArrayList<AlbumModel>()
        val db = this.readableDatabase
        var cursor:Cursor?=null
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_ALBUM, null)
        }catch (se: SQLiteException){
            db.execSQL(CREATE_ALBUM_TABLE)
            return ArrayList()
        }

        var id:Int
        var title:String
        var artist:String
        var price:Int
        var imageArray:ByteArray
        var imageBmp:Bitmap

        if (cursor.moveToFirst()){
            do {
                //get data text
                id = cursor.getInt(cursor.getColumnIndex(COLUMN_KODE))
                title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
                artist = cursor.getString(cursor.getColumnIndex(COLUMN_UNIT))
                price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE))
                //get data image
                imageArray = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE))
                //convert ByteArray to Bitmap
                val byteInputStream = ByteArrayInputStream(imageArray)
                imageBmp = BitmapFactory.decodeStream(byteInputStream)
                val model = AlbumModel(idAlbum = id, titleAlbum = title, artistAlbum = artist, priceAlbum = price, imageAlbum = imageBmp)
                listModel.add(model)
            }while (cursor.moveToNext())
        }

        return listModel
    }

    //add new transaksi
    @SuppressLint("Range")
    fun addTransaction(){
        val dbInsert = this.writableDatabase
        val dbSelect = this.readableDatabase
        //declare var
        var lastIdTrans = 0
        var lastIdDetail = 0
        var newIdTrans = 0
        var newIdDetail = 0
        val values = ContentValues()
        //get last idTransaksi
        val cursorTrans: Cursor = dbSelect.rawQuery(
            "SELECT  * FROM $TABLE_TRANS", null)

        val cursorDetail: Cursor = dbSelect.rawQuery(
            "SELECT *  FROM $TABLE_DETAILTRANS", null)

        if (cursorTrans.moveToLast()) {
            lastIdTrans = cursorTrans.getInt(0) //to get id, 0 is the column index
        }

        if (cursorDetail.moveToLast()) {
            lastIdDetail = cursorDetail.getInt(0) //to get id, 0 is the column index
        }


        //set data
        newIdTrans = lastIdTrans + 1
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = sdf.format(Date())
        val admin = "siti"
        //insert data transaksi
        values.put(COLUMN_IDTRANS, newIdTrans)
        values.put(COLUMN_DATE, date)
        values.put(COLUMN_ADMIN, admin)
        val result = dbInsert.insert(TABLE_TRANS,null, values)
        //show message
        if (result==(0).toLong()){
            Toast.makeText(context, "Add transaction Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Add transaction Success",Toast.LENGTH_SHORT).show()
        }

        newIdDetail = lastIdDetail + 1
        var i = 0
        val values2 = ContentValues()
        while(i < TransactionAdapter.listId.count()){
            values2.put(COLUMN_ID_DETAIL, newIdDetail)
            values2.put(COLUMN_ID_TRANS, newIdTrans)
            values2.put(COLUMN_ID_ORDER, TransactionAdapter.listId[i])
            values2.put(COLUMN_PRICE_ORDER, TransactionAdapter.listPrice[i])
            values2.put(COLUMN_AMOUNT, TransactionAdapter.listAmount[i])
            val result2 = dbInsert.insert(TABLE_DETAILTRANS,null, values2)
            //show message
            if (result2==(0).toLong()){
                Toast.makeText(context, "Add detail Failed", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(context, "Add detail Success",Toast.LENGTH_SHORT).show()
            }
            newIdDetail += 1
            i+=1
        }
        dbSelect.close()
        dbInsert.close()
    }
    @SuppressLint("Range")
    fun showReport():ArrayList<ReportModel>{
        val listModel=ArrayList<ReportModel>()
        val db=this.readableDatabase
        var cursor:Cursor?=null
        try {
            cursor=db.rawQuery("SELECT * FROM " + TABLE_DETAILTRANS, null)
        }catch (se:SQLiteException){
            db.execSQL(CREATE_DET_TRANS_TABLE)
            return ArrayList()
        }

        var idDetail: Int
        var idTrans:Int
        var idOrder:Int
        var priceOrder:Int
        var amount:Int

        if (cursor.moveToFirst()){
            do {
                idDetail=cursor.getInt(cursor.getColumnIndex(COLUMN_ID_DETAIL))
                idTrans=cursor.getInt(cursor.getColumnIndex(COLUMN_ID_TRANS))
                idOrder=cursor.getInt(cursor.getColumnIndex(COLUMN_ID_ORDER))
                priceOrder=cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE_ORDER))
                amount=cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT))
            }while (cursor.moveToNext())
        }

        return listModel
    }

//    fun deleteAlbum(){
//        val db=this.readableDatabase
//        var idAlbum:Int
//
//        db.delete(TABLE_ALBUM,idAlbum.toString())
//    }
}
