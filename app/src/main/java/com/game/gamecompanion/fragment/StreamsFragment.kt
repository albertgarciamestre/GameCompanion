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
import com.game.gamecompanion.R
import com.game.gamecompanion.activity.RegisterActivity
import com.game.gamecompanion.model.TWStreamsResponse
import com.game.gamecompanion.network.TwitchApiService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class StreamsFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("ProfileFragment", "++ onAttach ++")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ProfileFragment", "++ onCreate ++")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_streams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("ProfileFragment", "++ onViewCreated ++")

        TwitchApiService.service.getStreams().enqueue(object : Callback<TWStreamsResponse> {
            override fun onResponse(call: Call<TWStreamsResponse>, response: Response<TWStreamsResponse>) {
                response.body()?.data?.let { streams ->
                    for (stream in streams) {
                        Log.i("MainActivity", "Title: ${stream.title} and image: ${stream.imageUrl} and username: ${stream.username} and viewers: ${stream.viewerCount}")
                        Log.i("MainActivity", "Stream Url: https://www.twitch.tv/${stream.username}")
                    }
                }
            }

            override fun onFailure(call: Call<TWStreamsResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })



        initUI()
    }

    override fun onResume() {
        super.onResume()
        Log.i("ProfileFragment", "++ onResume ++")
        initUI()
    }

    override fun onStart() {
        super.onStart()
        Log.i("ProfileFragment", "++ onStart ++")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ProfileFragment", "++ onPause ++")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ProfileFragment", "++ onStop ++")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("ProfileFragment", "++ onDestroyView ++")
    }

    private fun initUI(){

    }
}
