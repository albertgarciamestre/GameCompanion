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
import com.game.gamecompanion.activity.LogInActivity
import com.game.gamecompanion.activity.MainActivity
import com.game.gamecompanion.activity.RegisterActivity
import com.game.gamecompanion.masktransformation.MaskTransformation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ProfileFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("ProfileFragment", "++ onViewCreated ++")
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
        if(FirebaseAuth.getInstance().currentUser == null) {
            userProfileContent.visibility = View.GONE
            logoutButton.visibility = View.GONE
            logInButton.visibility = View.VISIBLE
            logInButton.setOnClickListener {
                //Open Register Activity
                startActivity(Intent(requireContext(), LogInActivity::class.java))
            }
            registerButton.visibility = View.VISIBLE
            registerButton.setOnClickListener {
                //Open Register Activity
                startActivity(Intent(requireContext(), RegisterActivity::class.java))
            }
        } else{
            // TODO: Show Profile
            Picasso.get()
                .load(R.drawable.profile_test)
                .transform(MaskTransformation(requireContext(), 150, R.drawable.ic_profile_icon))
                .into(Image_Test)
            userProfileContent.visibility = View.VISIBLE

            val user = FirebaseAuth.getInstance().currentUser
            user?.let {
                val name = user.email
                ProfileName.setText(name.toString())
            }
            registerButton.visibility = View.GONE
            logInButton.visibility = View.GONE
            logoutButton.visibility = View.VISIBLE
            logoutButton.setOnClickListener{
                FirebaseAuth.getInstance().signOut()
                // TODO: Show success to user
                initUI()
            }
        }
    }
}
