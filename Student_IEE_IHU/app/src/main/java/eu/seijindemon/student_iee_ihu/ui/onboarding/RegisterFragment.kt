package eu.seijindemon.student_iee_ihu.ui.onboarding

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentRegisterBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.FirebaseSetup
import www.sanju.motiontoast.MotionToast

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override fun getViewBinding(): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(layoutInflater)
    }

    private val viewModel: OnBoardingViewModel by navGraphViewModels(R.id.nav_graph_onboarding) { defaultViewModelProviderFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(RegisterFragmentDirections.actionNavRegisterToNavLogin())
            }
        })

        FirebaseSetup.setupFirebase()

        register()
    }

    private fun register() {
        with(binding) {
            registerAm.doOnTextChanged { text, _, _, _ ->
                when {
                    text!!.length < 6 -> registerAm.error = "AM >= 6!"
                    text.length > 10 -> registerAm.error = "AM <= 10!"
                    else -> registerAm.error = null
                }
            }

            registerPassword.doOnTextChanged { text, _, _, _ ->
                when {
                    text!!.length < 10 -> registerPassword.error = "${getString(R.string.password)} >= 10!"
                    text.length > 20 -> registerPassword.error = "${getString(R.string.password)} <= 20!"
                    else -> registerPassword.error = null
                }
            }

            registerVerifyPassword.doOnTextChanged { text, _, _, _ ->
                when {
                    text!!.length < 10 -> registerVerifyPassword.error = "Verify Password >= 10!"
                    text.length > 20 -> registerVerifyPassword.error = "Verify Password <= 20!"
                    else -> registerVerifyPassword.error = null
                }
            }

            registerButton.setOnClickListener {
                when {
                    registerEmail.text.toString().trim().isEmpty() -> {
                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            getString(R.string.warning),
                            getString(R.string.input_email),
                            MotionToast.Companion.TOAST_WARNING,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                    }
                    registerAm.text.toString().trim().isEmpty() -> {
                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            getString(R.string.warning),
                            getString(R.string.input_am),
                            MotionToast.Companion.TOAST_WARNING,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                    }
                    registerAm.text.toString().length < 6 -> {
                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            getString(R.string.warning),
                            getString(R.string.am_is_small),
                            MotionToast.Companion.TOAST_WARNING,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                    }
                    registerFirstName.text.toString().trim().isEmpty() -> {
                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            getString(R.string.warning),
                            getString(R.string.input_firstname),
                            MotionToast.Companion.TOAST_WARNING,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                    }
                    registerLastName.text.toString().trim().isEmpty() -> {
                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            getString(R.string.warning),
                            getString(R.string.input_lastname),
                            MotionToast.Companion.TOAST_WARNING,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                    }
                    registerPassword.text.toString().trim().isEmpty() || registerVerifyPassword.text.toString().trim().isEmpty() -> {
                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            getString(R.string.warning),
                            getString(R.string.input_password_or_confirm_password),
                            MotionToast.Companion.TOAST_WARNING,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                    }
                    registerPassword.text.toString().length < 10 || registerVerifyPassword.text.toString().length < 10 -> {
                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            getString(R.string.warning),
                            getString(R.string.passwords_are_small),
                            MotionToast.Companion.TOAST_WARNING,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                    }
                    registerPassword.text.toString().trim() != registerVerifyPassword.text.toString().trim() -> {
                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            getString(R.string.warning),
                            getString(R.string.passwords_are_different),
                            MotionToast.Companion.TOAST_WARNING,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                    }
                    else -> {
                        createUser(
                            registerEmail.text.toString().trim(),
                            registerPassword.text.toString().trim(),
                            registerAm.text.toString().trim(),
                            registerFirstName.text.toString().trim(),
                            registerLastName.text.toString().trim()
                        )
                    }
                }
            }
        }
    }

    private fun createUser(email: String, password: String, am: String, firstName: String, lastName: String) {
        FirebaseSetup.auth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(requireActivity()){ task ->
                when {
                    task.isSuccessful -> {
                        val currentUser = FirebaseSetup.auth?.currentUser

                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(am).build()

                        currentUser!!.updateProfile(profileUpdates)
                            .addOnCompleteListener(requireActivity()){ task ->
                                if (task.isSuccessful) {
                                    Log.e("TAG", getString(R.string.updated_profile))
                                }
                            }

                        val currentUserDb = FirebaseSetup.usersReference!!.child(currentUser.uid)
                        currentUserDb.child("am").setValue(am)
                        currentUserDb.child("firstname").setValue(firstName)
                        currentUserDb.child("lastname").setValue(lastName)
                        currentUserDb.child("email").setValue(email)
                        currentUserDb.child("admin").setValue("no")

                        currentUser.sendEmailVerification().addOnCompleteListener{
                            MotionToast.Companion.createColorToast(
                                requireActivity(),
                                getString(R.string.successful),
                                getString(R.string.vefification_email),
                                MotionToast.Companion.TOAST_SUCCESS,
                                MotionToast.Companion.GRAVITY_BOTTOM,
                                MotionToast.Companion.LONG_DURATION,
                                ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                        }

                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            getString(R.string.successful),
                            getString(R.string.registration_successful),
                            MotionToast.Companion.TOAST_SUCCESS,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))

                        findNavController().navigate(RegisterFragmentDirections.actionNavRegisterToNavLogin())

                    }
                    else -> {
                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            getString(R.string.failed),
                            getString(R.string.registration_failed),
                            MotionToast.Companion.TOAST_ERROR,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular)
                        )
                    }
                }
            }
    }

}