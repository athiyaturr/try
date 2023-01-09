package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //hide title bar
        getSupportActionBar()?.hide()

        //instance text
        val txtUsername:EditText = findViewById(R.id.txtUsername)
        val txtPassword:EditText = findViewById(R.id.txtPassword)
        //instance button login
        val btnLogin:Button = findViewById(R.id.btnLogin)
        val txtRegister:TextView = findViewById(R.id.txtRegister)

        //event button login
//        btnLogin.setOnClickListener{
//            //object class databaseHelper
//            val databaseHelper = DatabaseHelper(this)
////
////            val data:String = databaseHelper.checkData("siti")
////            Toast.makeText(this@LoginActivity, "Login Success",
////                Toast.LENGTH_SHORT).show()
////            val intentLogin = Intent(this@LoginActivity,
////                MainActivity::class.java)
////            startActivity(intentLogin)
////            if (data == null){
////                //insert data
////                databaseHelper.addAccount("siti","12345")
////            }
//
//            val username = txtUsername.text.toString().trim()
//            val password = txtPassword.text.toString().trim()
//
//            databaseHelper.checkLogin(username,password)
//            val intentLogin = Intent(this, MainActivity::class.java)
//            startActivity(intentLogin)
//
////            val result:Boolean = databaseHelper.checkLogin(username,password)
////            if (result == true){
////                Toast.makeText(this@LoginActivity, "Login Success",Toast.LENGTH_SHORT).show()
////                val intentLogin = Intent(this@LoginActivity,AddAlbumActivity::class.java)
////                startActivity(intentLogin)
////            }else{
////                Toast.makeText(this@LoginActivity,"Login Failed, Try Again!",Toast.LENGTH_SHORT).show()
////            }
//        }

        //event button login
        btnLogin.setOnClickListener {
            //object class databaseHelper
            val databaseHelper = DatabaseHelper(this)

            val name = txtUsername.text.toString().trim()
            val password = txtPassword.text.toString().trim()
//
            //check login
            val  result:Boolean = databaseHelper.checkLogin(name,password)
            if (result == true){
                Toast.makeText(this@LoginActivity, "Login Success",
                    Toast.LENGTH_SHORT).show()
                val intentLogin = Intent(this@LoginActivity,
                    MainActivity::class.java)
                startActivity(intentLogin)
            }else{
                Toast.makeText(this@LoginActivity, "Login Failed, Try Again!!!",
                    Toast.LENGTH_SHORT).show()
            }

//            val intentLogin = Intent(this@LoginActivity, MainActivity::class.java)
//            startActivity(intentLogin)
        }

        //event text register
        txtRegister.setOnClickListener {
            val intentRegister = Intent(this@LoginActivity,
            ProfileActivity::class.java)
            startActivity(intentRegister)
        }
    }
}