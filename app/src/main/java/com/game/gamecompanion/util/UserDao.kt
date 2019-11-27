package com.game.gamecompanion.util

import com.game.gamecompanion.model.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.Future

class UserDao {

    fun getAll(){
        FirebaseFirestore.getInstance()
            .collection("users")
            //Get all documents
            .get()
                //On Success
            .addOnSuccessListener {querySnapshot ->
                //Query Snapshots contains documents & metadata
                val documents = querySnapshot.documents
                val userList = ArrayList<UserModel>()
                documents.forEach{documentSnapshot ->
                    // Document snapshot contains data & metadata
                    val user = documentSnapshot.toObject(UserModel::class.java)
                    // take only if not null
                    user?.let {
                        userList.add(user)
                    }
                }
            }
    }
}