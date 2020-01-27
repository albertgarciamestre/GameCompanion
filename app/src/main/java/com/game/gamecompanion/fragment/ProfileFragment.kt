package com.game.gamecompanion.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.game.gamecompanion.R
import com.game.gamecompanion.activity.LogInActivity
import com.game.gamecompanion.activity.MainActivity
import com.game.gamecompanion.activity.RegisterActivity
import com.game.gamecompanion.masktransformation.MaskTransformation
import com.game.gamecompanion.model.UserModel
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_streams.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException


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
            userProfileContent.visibility = View.VISIBLE

            val usersStorageReference = FirebaseStorage.getInstance().getReference("Imges")
            val user = FirebaseAuth.getInstance().currentUser
            user?.let {
                FirebaseFirestore.getInstance().collection("users")
                    .document(user.uid ?: "")
                    .get()
                    .addOnSuccessListener {documentSnapshot ->
                        val document = documentSnapshot
                        val name = document["username"]
                        ProfileName.setText(name.toString())
                        profile_description.setText(document["Description"].toString())
                        val avatarStorageReference = FirebaseStorage.getInstance().getReference(document["avatarUrl"].toString())
                        avatarStorageReference.downloadUrl.addOnSuccessListener{}.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Got URL!!
                                val downloadUri = task.result
                                Log.w("ProfileFragment", "downloadUri: ${downloadUri}")
                                // TODO: Save to user profile
                                Picasso.get()
                                    .load(downloadUri.toString())
                                    .transform(MaskTransformation(requireContext(), 100, R.drawable.ic_profile_icon))
                                    .into(Image_Test)

                            }

                        }

                        Image_Test.setOnClickListener()
                        {
                            showPictureDialog()
                        }

                        EdirProfBtn.setOnClickListener()
                        {
                            FirebaseFirestore.getInstance().collection("users")
                                .document(user.uid ?: "")
                                .update("username",ProfileName.text.toString())
                                .addOnSuccessListener {
                                    // Success!
                                    Toast.makeText(requireContext(), "Username updated to actual one: ${ProfileName.text} ", Toast.LENGTH_LONG).show()
                                }
                        }

                        PublishProfBtn.setOnClickListener()
                        {
                            FirebaseFirestore.getInstance().collection("users")
                                .document(user.uid ?: "")
                                .update("Description",profile_description.text.toString())
                                .addOnSuccessListener {
                                    // Success!
                                    Toast.makeText(requireContext(), "Description updated to the actual one", Toast.LENGTH_LONG).show()
                                }
                        }






                    }

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


    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(requireContext())
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    private fun takePhotoFromCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.resolveActivity(requireActivity().packageManager)
        try {
            startActivityForResult(cameraIntent, 10)
        }
        catch(e: SecurityException){
            Toast.makeText(requireContext(), "Allow Camera Permission", Toast.LENGTH_LONG).show()
        }
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        try {
            startActivityForResult(galleryIntent, 20)
        }
        catch(e: SecurityException){
            Toast.makeText(requireContext(), "Allow Storage Permission", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 10 && resultCode == Activity.RESULT_OK)
        {
            //Camera Result
            val image = data?.extras?.get("data") as? Bitmap
            image?.let{
                uploadToCloud(it)
            }
        }
        else if(requestCode == 20 && data != null)
        {
            val contentURI = data!!.data
            val bitmap = MediaStore.Images.Media.getBitmap( requireContext().contentResolver, contentURI)
            uploadToCloud(bitmap)
        }
    }

    private fun uploadToCloud(bitmap: Bitmap){
        //Log.i("ProfileFragment", "File upload success!")
        // Get Download Url
        //Pictures/Layou_River.jpg
        val usersStorageReference = FirebaseStorage.getInstance().getReference("Imges")
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            FirebaseFirestore.getInstance().collection("users")
                .document(user.uid ?: "")
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val document = documentSnapshot
                    val imageBytes = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageBytes)
                    //val file = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.toString() + "/" + "Layou_River.jpg")
                    //Log.i("ProfileFragment", file.path.toString())
                    val avatarStorageReference = FirebaseStorage.getInstance().getReference("Imges/${user.uid}.jpg")
                    //val uri = Uri.fromFile(file)
                    val uploadTask = avatarStorageReference.putBytes(imageBytes.toByteArray())
                    //val uploadTask = avatarStorageReference.putFile(uri)
                    uploadTask .addOnSuccessListener {
                        // All good!
                        Log.i("ProfileFragment", "File upload success!")
                        avatarStorageReference.downloadUrl.addOnSuccessListener{}.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Got URL!!
                                val downloadUri = task.result
                                Log.w("ProfileFragment", "downloadUri: ${downloadUri}")
                                // TODO: Save to user profile
                                Picasso.get()
                                    .load(downloadUri.toString())
                                    .transform(
                                        MaskTransformation(
                                            requireContext(),
                                            100,
                                            R.drawable.ic_profile_icon
                                        )
                                    )
                                    .into(Image_Test)

                            }
                        }

                    }
                        .addOnFailureListener {
                            // Handle unsuccessful uploads
                            Log.w("ProfileFragment", "Error uploading file :(")
                        }
                    FirebaseFirestore.getInstance().collection("users")
                        .document(user.uid ?: "")
                        .update("avatarUrl", avatarStorageReference.path)
                        .addOnSuccessListener {
                            // Success!
                            Toast.makeText(requireContext(), "Image Changed", Toast.LENGTH_LONG).show()
                        }
                }
        }

    }
}
