package com.game.gamecompanion.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.game.gamecompanion.R
//import com.game.gamecompanion.GlideMoudule.MyAppGlideModule
import com.game.gamecompanion.masktransformation.MaskTransformation
import com.game.gamecompanion.model.TWStreamsResponse
import com.game.gamecompanion.network.TwitchApiService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_streams.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        Log.i("MainActivity", "++ onViewCreated ++")


        TwitchApiService.service.getStreams().enqueue(object : Callback<TWStreamsResponse> {
            override fun onResponse(call: Call<TWStreamsResponse>, response: Response<TWStreamsResponse>) {
                response.body()?.data?.let { streams ->
                    Picasso.get()
                        .load(streams[0].imageUrl)
                        .transform(
                            MaskTransformation(
                                requireContext(),
                                20,
                                R.drawable.ic_streams_background
                            )
                        )
                        .into(ImageStream0)

                    streamer0.text = streams[0].username
                    viewers0.text = streams[0].viewerCount.toString()
                    title_stream0.text = streams[0].title

                    ImageStream0.setOnClickListener {
                        val openURL = Intent(android.content.Intent.ACTION_VIEW)
                        openURL.data = Uri.parse("https://www.twitch.tv/${streams[0].username}")
                        startActivity(openURL)
                    }

                    Picasso.get()
                        .load(streams[1].imageUrl)
                        .transform(
                            MaskTransformation(
                                requireContext(),
                                20,
                                R.drawable.ic_streams_background
                            )
                        )
                        .into(ImageStream1)

                    streamer1.text = streams[1].username
                    viewers1.text = streams[1].viewerCount.toString()
                    title_stream1.text = streams[1].title

                    ImageStream1.setOnClickListener {
                        val openURL = Intent(android.content.Intent.ACTION_VIEW)
                        openURL.data = Uri.parse("https://www.twitch.tv/${streams[1].username}")
                        startActivity(openURL)
                    }

                    Picasso.get()
                        .load(streams[2].imageUrl)
                        .transform(
                            MaskTransformation(
                                requireContext(),
                                20,
                                R.drawable.ic_streams_background
                            )
                        )
                        .into(ImageStream2)

                    streamer2.text = streams[2].username
                    viewers2.text = streams[2].viewerCount.toString()
                    title_stream2.text = streams[2].title

                    ImageStream2.setOnClickListener {
                        val openURL = Intent(android.content.Intent.ACTION_VIEW)
                        openURL.data = Uri.parse("https://www.twitch.tv/${streams[2].username}")
                        startActivity(openURL)
                    }

                    Picasso.get()
                        .load(streams[2].imageUrl)
                        .transform(
                            MaskTransformation(
                                requireContext(),
                                20,
                                R.drawable.ic_streams_background
                            )
                        )
                        .into(ImageStream2)

                    streamer2.text = streams[2].username
                    viewers2.text = streams[2].viewerCount.toString()
                    title_stream2.text = streams[2].title

                    ImageStream2.setOnClickListener {
                        val openURL = Intent(android.content.Intent.ACTION_VIEW)
                        openURL.data = Uri.parse("https://www.twitch.tv/${streams[2].username}")
                        startActivity(openURL)
                    }

                    Picasso.get()
                        .load(streams[3].imageUrl)
                        .transform(
                            MaskTransformation(
                                requireContext(),
                                30,
                                R.drawable.ic_streams_background
                            )
                        )
                        .into(ImageStream3)

                    streamer3.text = streams[3].username
                    viewers3.text = streams[3].viewerCount.toString()
                    title_stream3.text = streams[3].title

                    ImageStream3.setOnClickListener {
                        val openURL = Intent(android.content.Intent.ACTION_VIEW)
                        openURL.data = Uri.parse("https://www.twitch.tv/${streams[3].username}")
                        startActivity(openURL)
                    }

                    Picasso.get()
                        .load(streams[4].imageUrl)
                        .transform(
                            MaskTransformation(
                                requireContext(),
                                40,
                                R.drawable.ic_streams_background
                            )
                        )
                        .into(ImageStream4)

                    streamer4.text = streams[4].username
                    viewers4.text = streams[4].viewerCount.toString()
                    title_stream4.text = streams[4].title

                    ImageStream4.setOnClickListener {
                        val openURL = Intent(android.content.Intent.ACTION_VIEW)
                        openURL.data = Uri.parse("https://www.twitch.tv/${streams[4].username}")
                        startActivity(openURL)
                    }

                    Picasso.get()
                        .load(streams[5].imageUrl)
                        .transform(
                            MaskTransformation(
                                requireContext(),
                                50,
                                R.drawable.ic_streams_background
                            )
                        )
                        .into(ImageStream5)

                    streamer5.text = streams[5].username
                    viewers5.text = streams[5].viewerCount.toString()
                    title_stream5.text = streams[5].title

                    ImageStream5.setOnClickListener {
                        val openURL = Intent(android.content.Intent.ACTION_VIEW)
                        openURL.data = Uri.parse("https://www.twitch.tv/${streams[5].username}")
                        startActivity(openURL)
                    }

                    Picasso.get()
                        .load(streams[6].imageUrl)
                        .transform(
                            MaskTransformation(
                                requireContext(),
                                60,
                                R.drawable.ic_streams_background
                            )
                        )
                        .into(ImageStream6)

                    streamer6.text = streams[6].username
                    viewers6.text = streams[6].viewerCount.toString()
                    title_stream6.text = streams[6].title

                    ImageStream6.setOnClickListener {
                        val openURL = Intent(android.content.Intent.ACTION_VIEW)
                        openURL.data = Uri.parse("https://www.twitch.tv/${streams[6].username}")
                        startActivity(openURL)
                    }

                    Picasso.get()
                        .load(streams[7].imageUrl)
                        .transform(
                            MaskTransformation(
                                requireContext(),
                                70,
                                R.drawable.ic_streams_background
                            )
                        )
                        .into(ImageStream7)

                    streamer7.text = streams[7].username
                    viewers7.text = streams[7].viewerCount.toString()
                    title_stream7.text = streams[7].title

                    ImageStream7.setOnClickListener {
                        val openURL = Intent(android.content.Intent.ACTION_VIEW)
                        openURL.data = Uri.parse("https://www.twitch.tv/${streams[7].username}")
                        startActivity(openURL)
                    }

                    Picasso.get()
                        .load(streams[8].imageUrl)
                        .transform(
                            MaskTransformation(
                                requireContext(),
                                80,
                                R.drawable.ic_streams_background
                            )
                        )
                        .into(ImageStream8)

                    streamer8.text = streams[8].username
                    viewers8.text = streams[8].viewerCount.toString()
                    title_stream8.text = streams[8].title

                    ImageStream8.setOnClickListener {
                        val openURL = Intent(android.content.Intent.ACTION_VIEW)
                        openURL.data = Uri.parse("https://www.twitch.tv/${streams[8].username}")
                        startActivity(openURL)
                    }

                    Picasso.get()
                        .load(streams[9].imageUrl)
                        .transform(
                            MaskTransformation(
                                requireContext(),
                                90,
                                R.drawable.ic_streams_background
                            )
                        )
                        .into(ImageStream9)

                    streamer9.text = streams[9].username
                    viewers9.text = streams[9].viewerCount.toString()
                    title_stream9.text = streams[9].title

                    ImageStream9.setOnClickListener {
                        val openURL = Intent(android.content.Intent.ACTION_VIEW)
                        openURL.data = Uri.parse("https://www.twitch.tv/${streams[9].username}")
                        startActivity(openURL)
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
