package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        //hide title bar
        getSupportActionBar()?.hide()

        //instance text
        val txtUsername: EditText = findViewById(R.id.txtUsername)
        val txtPassword: EditText = findViewById(R.id.txtPassword)
        //instance button login
        val btnRegister: Button = findViewById(R.id.btnRegister)

        //event button login
        btnRegister.setOnClickListener{
            //object class databaseHelper
            val databaseHelper = DatabaseHelper(this)

            val username = txtUsername.text.toString().trim()
            val password= txtPassword.text.toString().trim()

            databaseHelper.addAccount(username,password)

            val intentLogin = Intent(this@ProfileActivity,LoginActivity::class.java)
            startActivity(intentLogin)

            //check data -> email sudah terdaftar atau belum
//            val data:String = databaseHelper.checkData(username)
//            //jika belum terdaftar
//            if (data == null){
//                //insert data
//                databaseHelper.addAccount(
//                    username, password
//                )
//                //show loginActivity
//                val intentLogin = Intent(this@ProfileActivity,LoginActivity::class.java)
//                startActivity(intentLogin)
//            }
//            else{
//                //jika email belum terdaftar
//                Toast.makeText(this@ProfileActivity,"Register Failed!" + "Your email already registered",Toast.LENGTH_SHORT).show()
//            }

            //check login
//            val result:Boolean = databaseHelper.checkLogin(username, password)
//            if (result == true){
//                Toast.makeText(this@ProfileActivity, "Register Success",
//                    Toast.LENGTH_SHORT).show()
//                val intentLogin = Intent(this@ProfileActivity,
//                    MainActivity::class.java)
//                startActivity(intentLogin)
//            }else{
//                Toast.makeText(this@ProfileActivity, "Register Failed, Try Again!",
//                    Toast.LENGTH_SHORT).show()
//            }
        }
    }
}