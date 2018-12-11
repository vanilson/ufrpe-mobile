package mobile.vaab.kotlincitybookfirebase

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_upload.*
import java.util.*

class UploadActivity : AppCompatActivity() {

    var selected: Uri? = null

    var mAuth: FirebaseAuth? = null
    var firebaseDatabase: FirebaseDatabase? = null
    var dbRef: DatabaseReference? = null
    var mStorageRef: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        mAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        dbRef = firebaseDatabase!!.reference
        mStorageRef = FirebaseStorage.getInstance().reference
    }


    fun upload(view: View) {

        val uuid = UUID.randomUUID()
        val imageName = "images/$uuid.jpg"

        val storageReference = mStorageRef!!.child(imageName)

        var uploadTask = storageReference.putFile(selected!!)

        val uuidString = uuid.toString()

        uploadTask.addOnSuccessListener { taskSnapshot ->

            val user = mAuth!!.currentUser
            val userEmail = user!!.email.toString()
            val userComment = commentText.text.toString()

            dbRef!!.child("Posts").child(uuidString).child("userEmail").setValue(userEmail)
            dbRef!!.child("Posts").child(uuidString).child("cityName").setValue(userComment)

            storageReference.getDownloadUrl().addOnSuccessListener { uri ->
                dbRef!!.child("Posts").child(uuidString).child("imageUrl").setValue(uri.toString())
            }

        }

        uploadTask.addOnFailureListener { exception ->
            if (exception != null) {
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }

        }

        uploadTask.addOnCompleteListener { task ->
            if (task.isComplete) {
                Toast.makeText(applicationContext, "City added", Toast.LENGTH_LONG).show()

                val intent = Intent(applicationContext, ListActivity::class.java)
                startActivity(intent)

            }
        }
    }

    fun selectImage(view: View) {

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 2)
        }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 2)
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            selected = data.data


            try {

                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selected)
                imageView.setImageBitmap(bitmap)


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
