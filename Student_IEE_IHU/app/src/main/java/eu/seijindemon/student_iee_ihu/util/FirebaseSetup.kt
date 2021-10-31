package eu.seijindemon.student_iee_ihu.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirebaseSetup {
    companion object{

        var auth: FirebaseAuth? = null
        var user: FirebaseUser? = null
        var usersReference: DatabaseReference? = null
        var userReference: DatabaseReference? = null
        private var database: FirebaseDatabase? = null
        var storage: FirebaseStorage? = null
        var userStorage: StorageReference? = null

        fun setupFirebase()
        {
            auth = FirebaseAuth.getInstance()
            if (auth != null)
            {
                user = auth?.currentUser
            }

            database = FirebaseDatabase.getInstance()
            storage = FirebaseStorage.getInstance()

            // Realtime Database
            usersReference = database!!.reference.child("profile")
            if (auth?.currentUser != null)
            {
                userReference = usersReference?.child(auth?.currentUser!!.uid)
            }


            // Storage
            userStorage = storage!!.reference.child("users_images")

        }

    }

}