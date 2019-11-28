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
        my_toolbar.setLogo(null)
        my_toolbar.setTitle(null)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        bottomNavigationView.setItemIconTintList(null)
        bottomNavigationView.itemTextAppearanceInactive
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.chat -> {
                    my_toolbar.setLogo(R.drawable.ic_messages_selected)
                    my_toolbar.setTitle("MESSAGES")
                }
                R.id.news -> {
                    my_toolbar.setLogo(R.drawable.ic_home_selected)
                    my_toolbar.setTitle("NEWS")
                }
                R.id.profile -> {
                    my_toolbar.setLogo(R.drawable.ic_profile_selected)
                    my_toolbar.setTitle("PROFILE")
                    //Create Fragment
                    val profileFragment = ProfileFragment()

                    //Add/Replace Fragment
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.add(fragmentContainer.id, profileFragment)
                    fragmentTransaction.commit()
                }
            }
            true
        }
    }
}
