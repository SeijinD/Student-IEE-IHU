package eu.seijindemon.student_iee_ihu.ui.profile

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
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import eu.seijindemon.student_iee_ihu.utils.FirebaseSetup
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.LoadingDialog
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import www.sanju.motiontoast.MotionToast
import java.lang.Exception

class ProfileFragment : Fragment() {

    private val requestCode = 438
    private var imageUri: Uri? = null

    private var imageRef: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)

        FirebaseSetup.setupFirebase()

        loadProfile()

        view.profile_image.setOnClickListener { pickImage() }

        view.update_button.setOnClickListener { updateProfile() }

        return view
    }

    private fun loadProfile() {
        FirebaseSetup.userReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (context != null) {
                    profile_am.setText(snapshot.child("am").value.toString())
                    profile_firstName.setText(snapshot.child("firstname").value.toString())
                    profile_lastName.setText(snapshot.child("lastname").value.toString())
                    profile_phone.setText(snapshot.child("phone").value.toString())

                    val loadImage = snapshot.child("profile").value.toString()
                    imageRef = loadImage
                    Glide.with(requireActivity())
                        .load(loadImage)
                        .apply(RequestOptions.circleCropTransform())
                        .error(R.drawable.default_profile)
                        .into(profile_image)
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
                        getString(R.string.warning),
                        getString(R.string.input_am),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireActivity(), R.font.helvetica_regular))
            }
            profile_firstName.text.toString().trim().isEmpty() -> {
                MotionToast.Companion.createColorToast(
                        requireActivity(),
                        getString(R.string.warning),
                        getString(R.string.input_firstname),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireActivity(), R.font.helvetica_regular))
            }
            profile_lastName.text.toString().trim().isEmpty() -> {
                MotionToast.Companion.createColorToast(
                        requireActivity(),
                        getString(R.string.warning),
                        getString(R.string.input_lastname),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireActivity(), R.font.helvetica_regular))
            }
            else -> {
                val currentUserDb = FirebaseSetup.userReference

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

                MotionToast.Companion.createColorToast(
                    this.requireActivity(),
                    getString(R.string.successful),
                    getString(R.string.updated_profile_info),
                    MotionToast.Companion.TOAST_SUCCESS,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(this.requireContext(), R.font.helvetica_regular))
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
                getString(R.string.wait),
                getString(R.string.image_uploading_),
                MotionToast.Companion.TOAST_INFO,
                MotionToast.Companion.GRAVITY_BOTTOM,
                MotionToast.Companion.LONG_DURATION,
                ResourcesCompat.getFont(this.requireContext(), R.font.helvetica_regular))
            uploadImageToDatabase()
        }
    }

    private fun uploadImageToDatabase() {

        val loading = LoadingDialog(requireActivity())
        loading.startLoading()

        if(imageUri != null) {
            val fileRef = FirebaseSetup.userStorage!!.child(System.currentTimeMillis().toString() + ".jpg")
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
                    val currentUserDb = FirebaseSetup.userReference
                    currentUserDb?.updateChildren(mapProfileImg)

                    val previousImgRef = FirebaseSetup.storage?.getReferenceFromUrl(imageRef!!)
                    previousImgRef?.delete()?.addOnSuccessListener {
                        Log.d("TAG", "onSuccess: deleted file");
                    }?.addOnFailureListener {
                        Log.d("TAG", "onFailure: did not delete file"); }
                }
                loading.isDismiss()
            }
        }
    }


}