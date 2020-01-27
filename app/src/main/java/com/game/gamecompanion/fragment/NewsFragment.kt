package com.game.gamecompanion.fragment

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.game.gamecompanion.Adapter.Newsadapter
import com.game.gamecompanion.R
import com.game.gamecompanion.activity.RegisterActivity
import com.game.gamecompanion.model.NewsModel
import com.game.gamecompanion.model.StreamsResponse
import com.game.gamecompanion.network.TwitchApiService
import com.google.android.gms.common.internal.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_profile.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class NewsFragment : Fragment() {

    private val adapter= Newsadapter(emptyList())

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("NewsFragment", "++ onAttach ++")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("NewsFragment", "++ onCreate ++")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseFirestore.getInstance().collection("news")
            .addSnapshotListener { newQuerySnapshot, firebaseFirestoreException ->
                newQuerySnapshot?.let {
                    val news = newQuerySnapshot?.toObjects(NewsModel::class.java)
                    adapter.list = news
                    adapter.notifyDataSetChanged()
                }
            }

        Log.i("NewsFragment", "++ onViewCreated ++")

        newsR.layoutManager = LinearLayoutManager(requireContext())
        newsR.adapter = adapter
    }



    override fun onResume() {
        super.onResume()
        Log.i("NewsFragment", "++ onResume ++")
        initUI()
    }

    override fun onStart() {
        super.onStart()
        Log.i("NewsFragment", "++ onStart ++")
    }

    override fun onPause() {
        super.onPause()
        Log.i("NewsFragment", "++ onPause ++")
    }

    override fun onStop() {
        super.onStop()
        Log.i("NewsFragment", "++ onStop ++")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("NewsFragment", "++ onDestroyView ++")
    }


    private fun initUI(){

    }
}
