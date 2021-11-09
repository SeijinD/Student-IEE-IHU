package eu.seijindemon.student_iee_ihu.ui.dashboard.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
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
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentProfileBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.FirebaseSetup
import eu.seijindemon.student_iee_ihu.util.view.LoadingDialog
import www.sanju.motiontoast.MotionToast

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun getViewBinding(): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater)
    }

    private val requestCode = 438
    private var imageUri: Uri? = null
    private var imageRef: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseSetup.setupFirebase()
        loadProfile()
        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            profileImage.setOnClickListener {
                pickImage()
            }
            updateButton.setOnClickListener {
                updateProfile()
            }
        }
    }

    private fun loadProfile() {
        FirebaseSetup.userReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (context != null) {
                    with(binding) {
                        profileAm.setText(snapshot.child("am").value.toString())
                        profileFirstName.setText(snapshot.child("firstname").value.toString())
                        profileLastName.setText(snapshot.child("lastname").value.toString())
                        profilePhone.setText(snapshot.child("phone").value.toString())
                    }

                    val loadImage = snapshot.child("profile").value.toString()
                    imageRef = loadImage
                    Glide.with(requireActivity())
                        .load(loadImage)
                        .apply(RequestOptions.circleCropTransform())
                        .error(R.drawable.default_profile)
                        .into(binding.profileImage)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TAG", error.message)
            }
        })
    }

    private fun updateProfile() {
        with(binding) {
            when {
                profileAm.text.toString().trim().isEmpty() -> {
                    MotionToast.Companion.createColorToast(
                        requireActivity(),
                        getString(R.string.warning),
                        getString(R.string.input_am),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireActivity(), R.font.helvetica_regular))
                }
                profileAm.text.toString().length < 6 -> {
                    MotionToast.Companion.createColorToast(
                        requireActivity(),
                        getString(R.string.warning),
                        getString(R.string.am_is_small),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireActivity(), R.font.helvetica_regular))
                }
                profileFirstName.text.toString().trim().isEmpty() -> {
                    MotionToast.Companion.createColorToast(
                        requireActivity(),
                        getString(R.string.warning),
                        getString(R.string.input_firstname),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireActivity(), R.font.helvetica_regular))
                }
                profileLastName.text.toString().trim().isEmpty() -> {
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

                    mapAM["am"] = profileAm.text.toString()
                    mapFirstname["firstname"] = profileFirstName.text.toString()
                    mapLastname["lastname"] = profileLastName.text.toString()
                    mapPhone["phone"] = profilePhone.text.toString()

                    currentUserDb?.updateChildren(mapAM)
                    currentUserDb?.updateChildren(mapFirstname)
                    currentUserDb?.updateChildren(mapLastname)
                    currentUserDb?.updateChildren(mapPhone)

                    MotionToast.Companion.createColorToast(
                        requireActivity(),
                        getString(R.string.successful),
                        getString(R.string.updated_profile_info),
                        MotionToast.Companion.TOAST_SUCCESS,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                }
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

                    try {
                        val previousImgRef = FirebaseSetup.storage?.getReferenceFromUrl(imageRef!!)
                        previousImgRef?.delete()?.addOnSuccessListener {
                            Log.d("TAG", "onSuccess: deleted file")
                        }?.addOnFailureListener {
                            Log.d("TAG", "onFailure: did not delete file")
                        }
                    } catch (e: Exception) {
                        Log.d("TAG", e.toString())
                    }
                    loading.isDismiss()
                }
            }
        }
        else {
            MotionToast.Companion.createColorToast(
                requireActivity(),
                getString(R.string.warning),
                getString(R.string.no_image_uri_profile),
                MotionToast.Companion.TOAST_WARNING,
                MotionToast.Companion.GRAVITY_BOTTOM,
                MotionToast.Companion.LONG_DURATION,
                ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))

            loading.isDismiss()
        }
    }

}