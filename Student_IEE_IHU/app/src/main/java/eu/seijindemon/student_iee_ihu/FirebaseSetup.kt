package eu.seijindemon.student_iee_ihu

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirebaseSetup {

    lateinit var auth: FirebaseAuth
    lateinit var userReference: DatabaseReference
    lateinit var database: FirebaseDatabase
    lateinit var storage: FirebaseStorage
    lateinit var userStorage: StorageReference

    fun setupFirebase()
    {
        auth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()

        // Realtime Database
        userReference = database.reference.child("profile")


        // Storage
        userStorage = storage.reference.child("users_images")

    }



}