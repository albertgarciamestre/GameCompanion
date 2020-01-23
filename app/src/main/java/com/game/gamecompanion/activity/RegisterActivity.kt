package com.game.gamecompanion.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.game.gamecompanion.model.UserModel
import com.game.gamecompanion.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        createButton.setOnClickListener {

            // 1: Read Fields -> Username, email, password
            val username = usernameRegisterText.text.toString()
            val email = emailRegisterText.text.toString()
            val password = passwordRegisterText.text.toString()

            // 2: Validate
            if(username.isBlank()){
                // Show Error
                //usernameRegisterText.error = getString(R.string.register_error_invalid_username)
                //or
                Toast.makeText(usernameRegisterText.context, getString(R.string.register_error_invalid_username), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(email.isBlank() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(usernameRegisterText.context, "Email Required", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(password.isBlank() && !isPasswordValid(password)){
                // TODO: Error
                Toast.makeText(usernameRegisterText.context, "Password must be at least 4 characters and 1 letter and 1 digit", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Send to Firebase
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {authResult ->

                    FirebaseAnalytics.getInstance(this).logEvent("CreandoUsuario",null)

                    // Create User Profile
                    val user = UserModel(
                        id = authResult.user?.uid,
                        email = email,
                        username = username,
                        avatarUrl = "Imges/default_profile.png",
                        Description = "I want a description of me!!!"
                    )
                    FirebaseFirestore.getInstance().collection("users")
                        .document(authResult.user?.uid ?: "")
                        .set(user)
                        .addOnSuccessListener {
                            // Success!
                            Toast.makeText(usernameRegisterText.context, "OK!", Toast.LENGTH_LONG).show()

                            //Close Activity
                            finish()
                        }
                        .addOnFailureListener{
                            Toast.makeText(usernameRegisterText.context, it.localizedMessage, Toast.LENGTH_LONG).show()
                        }
                }
                .addOnFailureListener {
                    // Error
                    Toast.makeText(usernameRegisterText.context, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
        }
    }


    private fun isPasswordValid(password: String) : Boolean{
        //return(password.length >= 4 && password.contains("[a-zA-Z0-9]"))
        if(password.count() < 6){
            return false
        }

        var hasDigit = false
        var hasLetter = false

        for(char in password){
            if(char.isDigit()){
                hasDigit = true
            }
            if(char.isLetter()){
                char.isLetter()
            }
        }
        return hasLetter && hasDigit
    }
}
