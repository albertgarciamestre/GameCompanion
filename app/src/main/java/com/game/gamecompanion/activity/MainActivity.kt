package com.game.gamecompanion.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.game.gamecompanion.fragment.ProfileFragment
import com.game.gamecompanion.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.chat -> {

                }
                R.id.news -> {

                }
                R.id.profile -> {
                    //Create Fragment
                    val profileFragment = ProfileFragment()

                    //Add/Replace Fragment
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.add(fragmentContainer.id, profileFragment)
                    fragmentTransaction.commit()
                }
                R.id.streams -> {

                }
            }
            true
        }
    }
}
