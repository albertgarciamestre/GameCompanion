package com.game.gamecompanion.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.game.gamecompanion.fragment.ProfileFragment
import com.game.gamecompanion.R
import com.game.gamecompanion.fragment.MessagesFragment
import com.game.gamecompanion.fragment.NewsFragment
import com.game.gamecompanion.masktransformation.MaskTransformation
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()

        adView.loadAd(adRequest)
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

                    //Create Fragment
                    val profileFragment = MessagesFragment()

                    //Add/Replace Fragment
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(fragmentContainer.id, MessagesFragment())
                    fragmentTransaction.commit()
                }
                R.id.news -> {
                    my_toolbar.setLogo(R.drawable.ic_home_selected)
                    my_toolbar.setTitle("NEWS")

                    //Create Fragment
                    val profileFragment = NewsFragment()

                    //Add/Replace Fragment
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(fragmentContainer.id, NewsFragment())
                    fragmentTransaction.commit()
                }
                R.id.profile -> {
                    my_toolbar.setLogo(R.drawable.ic_profile_selected)
                    my_toolbar.setTitle("PROFILE")

                    //Create Fragment
                    val profileFragment = ProfileFragment()

                    //Add/Replace Fragment
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(fragmentContainer.id, profileFragment)
                    fragmentTransaction.commit()
                }
            }
            true
        }
    }
}
