package com.game.gamecompanion.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.game.gamecompanion.R
import com.game.gamecompanion.activity.RegisterActivity
import com.game.gamecompanion.Adapter.ChatAdapter
import com.game.gamecompanion.model.ChatModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_chat.*
import com.game.gamecompanion.util.COLLECCTION_MESSAGES
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 */
class MessagesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    private val adapter = ChatAdapter(emptyList())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToMessages()
        send.setOnClickListener {
            //TODO: Check if user is logged in
            if (FirebaseAuth.getInstance().currentUser == null) {
                //pasas activity por que el fragment no tiene la actividad
                val signUpIntent = Intent(
                    activity,
                    RegisterActivity::class.java
                ) //un intent es intento de acción permite pasar parametros basicamente es con estos parametros intento ir a X
                startActivity(signUpIntent)
                return@setOnClickListener
            }

            val text = message.text.toString()
            if(text.isNotBlank())
            {
                sendMessage(text)
            }
        }
        recycledChat.layoutManager = LinearLayoutManager(requireContext())
        recycledChat.adapter = adapter
    }
    private fun subscribeToMessages()
    {
        FirebaseFirestore.getInstance()
            .collection(COLLECCTION_MESSAGES).orderBy("timestamp")
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->

                if(!isAdded) return@addSnapshotListener
                val messages = querySnapshot?.documents
                    ?.map { it.toObject(ChatModel::class.java) ?: ChatModel() }
                    ?: emptyList()

                adapter.list = messages
                adapter.notifyDataSetChanged()

            }

    }

    private fun sendMessage(text: String) {
        val date = java.util.Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
        val formatedDate = formatter.format(date)
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            FirebaseFirestore.getInstance().collection("users")
                .document(user.uid ?: "")
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val document = documentSnapshot
                    val name = document["username"]

                    val chatMessage = ChatModel(
                        text = text,
                        timestamp = System.currentTimeMillis(),
                        createdAt = formatedDate,
                        name = name.toString(),
                        id = FirebaseAuth.getInstance().currentUser?.uid
                    )

                    FirebaseFirestore.getInstance()
                        .collection(COLLECCTION_MESSAGES)
                        .add(chatMessage)
                        .addOnSuccessListener {
                            Log.i("ChatFragment", "MessageAdded")
                            subscribeToMessages()
                        }
                        .addOnFailureListener {
                            Log.i("ChatFragment", "O no")
                        }
                }
        }

    }
}


