package eu.seijindemon.student_iee_ihu.ui.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.ui.auth.LoginActivity
import eu.seijindemon.student_iee_ihu.utils.FirebaseSetup
import kotlinx.android.synthetic.main.fragment_reset_email.view.*
import www.sanju.motiontoast.MotionToast

class ResetEmailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_reset_email, container, false)

        FirebaseSetup.setupFirebase()

        view.old_email_reset.setText(FirebaseSetup.user?.email)

        view.update_email_button.setOnClickListener {

            if (view.password_reset_email.text.toString().trim().isEmpty()) {
                MotionToast.Companion.createColorToast(
                    requireActivity(),
                    "Warning",
                    "Input Password",
                    MotionToast.Companion.TOAST_WARNING,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
            }
            else if (view.new_reset_email.text.toString().trim().isEmpty()) {
                MotionToast.Companion.createColorToast(
                    requireActivity(),
                    "Warning",
                    "Input New Email",
                    MotionToast.Companion.TOAST_WARNING,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
            }
            else
            {
                val email = view.new_reset_email.text.toString().trim()
                val password = view.password_reset_email.text.toString().trim()

                if (FirebaseSetup.user != null) {
                    updateEmail(email, password)
                }
            }
        }


        return view
    }

    private fun updateEmail(email: String, password: String) {
        FirebaseSetup.user?.let { it ->
            val userCredential: AuthCredential = EmailAuthProvider.getCredential(it.email!!, password)
            it.reauthenticate(userCredential).addOnCompleteListener {
                when {
                    it.isSuccessful -> {
                        FirebaseSetup.user?.updateEmail(email)
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    MotionToast.Companion.createColorToast(
                                        requireActivity(),
                                        "Successful",
                                        "Update Email!",
                                        MotionToast.Companion.TOAST_SUCCESS,
                                        MotionToast.Companion.GRAVITY_BOTTOM,
                                        MotionToast.Companion.LONG_DURATION,
                                        ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))

                                    val currentUserDb = FirebaseSetup.userReference
                                    val updateEmail = HashMap<String, Any>()
                                    updateEmail["email"] = email
                                    currentUserDb?.updateChildren(updateEmail)

                                    FirebaseSetup.auth?.signOut()
                                    startActivity(Intent(context, LoginActivity::class.java))
                                    activity?.finish()
                                } else {
                                    MotionToast.Companion.createColorToast(
                                        requireActivity(),
                                        "UnSuccessful",
                                        "Not Update Email",
                                        MotionToast.Companion.TOAST_ERROR,
                                        MotionToast.Companion.GRAVITY_BOTTOM,
                                        MotionToast.Companion.LONG_DURATION,
                                        ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                                }
                            }
                    }
                    it.exception is FirebaseAuthInvalidCredentialsException -> {
                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            "Error",
                            "Password is wrong!",
                            MotionToast.Companion.TOAST_ERROR,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                    }
                    else -> {
                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            "Error",
                            "${it.exception?.message}",
                            MotionToast.Companion.TOAST_ERROR,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                    }
                }
            }
        }
    }

}