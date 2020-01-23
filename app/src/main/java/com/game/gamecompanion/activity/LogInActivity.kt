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

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.loginActivityButton
import kotlinx.android.synthetic.main.activity_login.emailloginText
import kotlinx.android.synthetic.main.activity_login.passwordloginText
import kotlinx.android.synthetic.main.activity_register.*

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginActivityButton.setOnClickListener {

            // 1: Read Fields -> Username, email, password
            val email = emailloginText.text.toString()
            val password = passwordloginText.text.toString()

            // 2: Validate
            if(email.isBlank() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(emailloginText.context, "Email Required", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(password.isBlank() && !isPasswordValid(password)){
                // TODO: Error
                Toast.makeText(emailloginText.context, "Password must be at least 4 characters and 1 letter and 1 digit", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            // Send to Firebase
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {authResult ->

                    FirebaseAnalytics.getInstance(this).logEvent("IniciandoSesion",null)
                    FirebaseFirestore.getInstance().collection("users")
                        .document(authResult.user?.uid ?: "")
                        .get()
                        .addOnSuccessListener {


                            // Success!
                            Toast.makeText(emailloginText.context, "OK!", Toast.LENGTH_LONG).show()

                            //Close Activity
                            finish()
                        }
                        .addOnFailureListener{
                            Toast.makeText(emailloginText.context, it.localizedMessage, Toast.LENGTH_LONG).show()
                        }
                }
                .addOnFailureListener {
                    // Error
                    Toast.makeText(emailloginText.context, it.localizedMessage, Toast.LENGTH_LONG).show()
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
