package eu.seijindemon.student_iee_ihu.refactor.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentResetEmailBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.ui.auth.LoginActivity
import eu.seijindemon.student_iee_ihu.util.FirebaseSetup
import www.sanju.motiontoast.MotionToast

@AndroidEntryPoint
class ResetEmailFragment : BaseFragment<FragmentResetEmailBinding>() {

    override fun getViewBinding(): FragmentResetEmailBinding {
        return FragmentResetEmailBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseSetup.setupFirebase()

        setupViews()
        setupListeners()

    }

    fun setupViews() {
        with(binding) {
            oldEmailReset.setText(FirebaseSetup.user?.email)
        }
    }

    fun setupListeners(){
        with(binding) {
            updateEmailButton.setOnClickListener {

                if (passwordResetEmail.text.toString().trim().isEmpty()) {
                    MotionToast.Companion.createColorToast(
                        requireActivity(),
                        getString(R.string.warning),
                        getString(R.string.input_password),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                }
                else if (newResetEmail.text.toString().trim().isEmpty()) {
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
                    val email = newResetEmail.text.toString().trim()
                    val password = passwordResetEmail.text.toString().trim()

                    if (FirebaseSetup.user != null) {
                        updateEmail(email, password)
                    }
                }
            }
        }
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