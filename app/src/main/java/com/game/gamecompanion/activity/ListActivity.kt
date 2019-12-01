package com.game.gamecompanion.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.game.gamecompanion.SecretsListAdapter

import com.game.gamecompanion.R
import kotlinx.android.synthetic.main.activity_list.*
import layout.SecretModel

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        welcomeTextView.text =  intent.getStringExtra("username")

        recyclerview.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        val secretsAdapter = SecretsListAdapter()
        val secretsList = arrayListOf(
            SecretModel(title = "Secret 1", description = "Secret description 1"),
            SecretModel(title = "Secret 2", description = "Secret description 2"),
            SecretModel(title = "Secret 3", description = "Secret description 3"),
            SecretModel(title = "Secret 4", description = "Secret description 4"),
            SecretModel(title = "Secret 5", description = "Secret description 5")
        )
        secretsAdapter.elements = secretsList
        // Recyclerview <> Adapter
        recyclerview.adapter = secretsAdapter
    }
}
