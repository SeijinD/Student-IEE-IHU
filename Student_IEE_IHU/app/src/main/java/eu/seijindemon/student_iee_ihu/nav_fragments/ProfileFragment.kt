package eu.seijindemon.student_iee_ihu.nav_fragments

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import eu.seijindemon.student_iee_ihu.utils.FirebaseSetup
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import www.sanju.motiontoast.MotionToast

class ProfileFragment : Fragment() {

    private lateinit var firebaseSetup: FirebaseSetup

    private val requestCode = 438
    private var imageUri: Uri? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)

        firebaseSetup =  FirebaseSetup()
        firebaseSetup.setupFirebase()

        loadProfile()

        view.profile_image.setOnClickListener { pickImage() }

        view.update_button.setOnClickListener { updateProfile() }

        return view
    }

    private fun loadProfile() {
        firebaseSetup.userReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (context != null) {
                    profile_am.setText(snapshot.child("am").value.toString())
                    profile_firstName.setText(snapshot.child("firstname").value.toString())
                    profile_lastName.setText(snapshot.child("lastname").value.toString())
                    profile_phone.setText(snapshot.child("phone").value.toString())

                    if (snapshot.hasChild("profile")) {
                        val loadImage = snapshot.child("profile").value.toString()
                        Glide.with(requireActivity()).load(loadImage).apply(RequestOptions.circleCropTransform()).into(profile_image)
                    }
                    else {
                        Glide.with(requireActivity()).load(R.drawable.default_profile).apply(RequestOptions.circleCropTransform()).into(profile_image)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TAG", error.message)
            }
        })
    }

    private fun updateProfile() {
        when {
            profile_am.text.toString().trim().isEmpty() -> {
                MotionToast.Companion.createColorToast(
                        requireActivity(),
                        "Warning",
                        "Input AM",
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireActivity(), R.font.helvetica_regular))
            }
            profile_firstName.text.toString().trim().isEmpty() -> {
                MotionToast.Companion.createColorToast(
                        requireActivity(),
                        "Warning",
                        "Input FirstName",
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireActivity(), R.font.helvetica_regular))
            }
            profile_lastName.text.toString().trim().isEmpty() -> {
                MotionToast.Companion.createColorToast(
                        requireActivity(),
                        "Warning",
                        "Input LastName",
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireActivity(), R.font.helvetica_regular))
            }
            else -> {
                val currentUserDb = firebaseSetup.userReference

                val mapAM = HashMap<String, Any>()
                val mapFirstname = HashMap<String, Any>()
                val mapLastname = HashMap<String, Any>()
                val mapPhone = HashMap<String, Any>()

                mapAM["am"] = profile_am.text.toString()
                mapFirstname["firstname"] = profile_firstName.text.toString()
                mapLastname["lastname"] = profile_lastName.text.toString()
                mapPhone["phone"] = profile_phone.text.toString()

                currentUserDb?.updateChildren(mapAM)
                currentUserDb?.updateChildren(mapFirstname)
                currentUserDb?.updateChildren(mapLastname)
                currentUserDb?.updateChildren(mapPhone)
            }
        }
    }

    private fun pickImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == this.requestCode && resultCode == Activity.RESULT_OK && data!!.data != null) {
            imageUri = data.data
            MotionToast.Companion.createColorToast(
                this.requireActivity(),
                "Wait",
                "Image Uploading...",
                MotionToast.Companion.TOAST_INFO,
                MotionToast.Companion.GRAVITY_BOTTOM,
                MotionToast.Companion.LONG_DURATION,
                ResourcesCompat.getFont(this.requireContext(), R.font.helvetica_regular))
            uploadImageToDatabase()
        }
    }

    private fun uploadImageToDatabase() {
        val prograssBar = ProgressDialog(context)
        prograssBar.setMessage("Image is uploading, please wait...")
        prograssBar.show()

        if(imageUri != null) {
            val fileRef = firebaseSetup.userStorage!!.child(System.currentTimeMillis().toString() + ".jpg")
            val uploadTask: StorageTask<*>
            uploadTask = fileRef.putFile(imageUri!!)

            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation fileRef.downloadUrl
            }).addOnCompleteListener{ task ->
                if(task.isSuccessful) {
                    val downloadUrl = task.result
                    val url = downloadUrl.toString()

                    val mapProfileImg = HashMap<String, Any>()
                    mapProfileImg["profile"] = url
                    val currentUSerDb = firebaseSetup.userReference
                    currentUSerDb?.updateChildren(mapProfileImg)
                }
                prograssBar.dismiss()
            }
        }
    }


}