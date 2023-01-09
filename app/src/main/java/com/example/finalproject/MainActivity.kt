package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //hide title bar
        supportActionBar?.hide()

        //instance
        val bottomNav:BottomNavigationView = findViewById(R.id.bottomNavigationView)

        //set fragment
        val accountFragment=FragmentProfile()
        val addFragment=FragmentAdd()
        val transactionFragment=FragmentTransaction()
        val reportFragment = FragmentReport()
        val albumFragment = FragmentAlbum()

        //default fragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,albumFragment)
            commit()
        }

        currentFragment(albumFragment)

        //floating bottom menu
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container,albumFragment)
                commit()
            }
        }

        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.fragmentProfile->currentFragment(accountFragment)
                R.id.fragmentAdd->currentFragment(addFragment)
                R.id.fragmentTransaction->currentFragment(transactionFragment)
                R.id.fragmentReport->currentFragment(reportFragment)

            }
            true
        }
    }

    private fun currentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            commit()
        }

}