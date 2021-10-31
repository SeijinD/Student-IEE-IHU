package eu.seijindemon.student_iee_ihu.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.ui.auth.LoginActivity
import eu.seijindemon.student_iee_ihu.utils.FirebaseSetup
import kotlinx.android.synthetic.main.fragment_reset_email.view.*
import www.sanju.motiontoast.MotionToast

@AndroidEntryPoint
class ResetEmailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_reset_email, container, false)

        FirebaseSetup.setupFirebase()

        view.old_email_reset.setText(FirebaseSetup.user?.email)

        view.update_email_button.setOnClickListener {

            if (view.password_reset_email.text.toString().trim().isEmpty()) {
                MotionToast.Companion.createColorToast(
                    requireActivity(),
                    getString(R.string.warning),
                    getString(R.string.input_password),
                    MotionToast.Companion.TOAST_WARNING,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
            }
            else if (view.new_reset_email.text.toString().trim().isEmpty()) {
                MotionToast.Companion.createColorToast(
                    requireActivity(),
                    getString(R.string.warning),
                    getString(R.string.input_new_email),
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
                                        getString(R.string.successful),
                                        getString(R.string.update_email),
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
                                        getString(R.string.unsuccessful),
                                        getString(R.string.not_update_email),
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
                            getString(R.string.error),
                            getString(R.string.password_is_wrong),
                            MotionToast.Companion.TOAST_ERROR,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                    }
                    else -> {
                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            getString(R.string.error),
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