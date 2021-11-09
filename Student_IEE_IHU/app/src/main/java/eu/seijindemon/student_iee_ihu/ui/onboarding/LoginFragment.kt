package eu.seijindemon.student_iee_ihu.ui.onboarding

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.WhichButton.POSITIVE
import com.afollestad.materialdialogs.actions.setActionButtonEnabled
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.github.javiersantos.materialstyleddialogs.enums.Style
import com.tencent.mmkv.MMKV
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentLoginBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.FirebaseSetup
import eu.seijindemon.student_iee_ihu.util.view.LoadingDialog
import eu.seijindemon.student_iee_ihu.util.Permission
import eu.seijindemon.student_iee_ihu.util.Run
import www.sanju.motiontoast.MotionToast
import java.util.*

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(), EasyPermissions.PermissionCallbacks {

    override fun getViewBinding(): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

    private val viewModel: OnBoardingViewModel by navGraphViewModels(R.id.nav_graph_onboarding) { defaultViewModelProviderFactory }

    private lateinit var loading: LoadingDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MMKV.initialize(requireContext())
        loadLocale()
        loadTheme()
        FirebaseSetup.setupFirebase()
        setupListeners()

        loading = LoadingDialog(requireActivity())
        loading.startLoading()

        val user = FirebaseSetup.auth?.currentUser

        Run.after(2_000) {
            when (user) {
                null -> {
                    loading.isDismiss()
                }
                else -> {
                    val currentUser = FirebaseSetup.auth?.currentUser
                    if (currentUser?.isEmailVerified!!) {
                        loading.isDismiss()

                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            getString(R.string.successful),
                            getString(R.string.login_),
                            MotionToast.Companion.TOAST_SUCCESS,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.SHORT_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular)
                        )

                        findNavController().navigate(LoginFragmentDirections.actionNavLoginToNavGraphDashboard())

                    } else if (!currentUser.isEmailVerified) {
                        loading.isDismiss()

                        MotionToast.Companion.createColorToast(
                            requireActivity(),
                            getString(R.string.warning),
                            getString(R.string.must_verify_email),
                            MotionToast.Companion.TOAST_WARNING,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.SHORT_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular)
                        )

                        Run.after(2_000) {
                            MaterialStyledDialog.Builder(requireContext())
                                .setTitle(getString(R.string.send_email_again))
                                .setNegativeText(R.string.no)
                                .setPositiveText(R.string.yes)
                                .onPositive { sendVerificationAgain() }
                                .setStyle(Style.HEADER_WITH_TITLE)
                                .show()
                        }
                    }
                    loading.isDismiss()
                }
            }
        }

    }

    private fun setupListeners() {
        with(binding) {
            loginButton.setOnClickListener{
                login()
            }

            resetPasswordButton.setOnClickListener {
                resetPassword()
            }

            createAccountButton.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionNavLoginToNavRegister())
            }
        }
    }

    private fun loadTheme() {
        val kv = MMKV.mmkvWithID("themeMode")
        if (kv?.decodeInt("int") == 0){
            kv.encode("int",3)
        }

        when (kv?.decodeInt("int")) {
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            3 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        activity?.baseContext?.resources?.updateConfiguration(config, activity?.baseContext?.resources?.displayMetrics)

        val kv = MMKV.mmkvWithID("languageMode")
        kv?.encode("string", lang)
    }

    private fun loadLocale() {
        val kv = MMKV.mmkvWithID("languageMode")

        if (kv?.decodeString("string") == null) {
            kv?.encode("string","en")
        }

        setLocale(kv?.decodeString("string")!!)
    }

    @SuppressLint("CheckResult")
    private fun resetPassword() {
        val type = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        var email : String? = null
        MaterialDialog(requireContext()).show {
            title(R.string.reset_password)
            input(waitForPositiveButton = false, inputType = type, maxLength = 35, hintRes = R.string.email) { dialog, text ->
                val inputField = dialog.getInputField()
                val isValid = text.contains("@")

                inputField.error = if (isValid) null else getString(R.string.must_contains)
                dialog.setActionButtonEnabled(POSITIVE, isValid)
                email = text.toString()
            }
            negativeButton(R.string.no)
            positiveButton(R.string.yes)
            positiveButton { confirmResetPassword(email!!) }
        }
    }

    private fun confirmResetPassword(email: String) {
        FirebaseSetup.auth?.sendPasswordResetEmail(email)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    MotionToast.Companion.createColorToast(
                        requireActivity(),
                        getString(R.string.successful),
                        getString(R.string.send_reset_password_email),
                        MotionToast.Companion.TOAST_SUCCESS,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                }
                else {
                    MotionToast.Companion.createColorToast(
                        requireActivity(),
                        getString(R.string.unsuccessful),
                        getString(R.string.no_send_reset_password_email),
                        MotionToast.Companion.TOAST_ERROR,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                }
            }
    }

    private fun login() {
        with(binding) {
            when {
                loginEmail.text.toString().trim().isEmpty() -> {
                    MotionToast.Companion.createColorToast(
                        requireActivity(),
                        getString(R.string.warning),
                        getString(R.string.input_email),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                }
                loginPassword.text.toString().trim().isEmpty() -> {
                    MotionToast.Companion.createColorToast(
                        requireActivity(),
                        getString(R.string.warning),
                        getString(R.string.input_password),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
                }
                else -> {
                    checkPermissions()
                    //loginUser(login_email.text.toString().trim(), login_password.text.toString().trim())
                }
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        FirebaseSetup.auth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                FirebaseSetup.setupFirebase() // update current user
                val currentUser = FirebaseSetup.auth?.currentUser
                if (currentUser?.isEmailVerified!!) {
                    MotionToast.Companion.createColorToast(
                        requireActivity(),
                        getString(R.string.successful),
                        getString(R.string.login_),
                        MotionToast.Companion.TOAST_SUCCESS,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.SHORT_DURATION,
                        ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular)
                    )

                        findNavController().navigate(LoginFragmentDirections.actionNavLoginToNavGraphDashboard())

                } else if (!currentUser.isEmailVerified) {
                    MotionToast.Companion.createColorToast(
                        requireActivity(),
                        getString(R.string.warning),
                        getString(R.string.must_verify_email),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular)
                    )

                    MaterialStyledDialog.Builder(requireContext())
                        .setTitle(getString(R.string.send_email_again))
                        .setNegativeText(R.string.no)
                        .setPositiveText(R.string.yes)
                        .onPositive { sendVerificationAgain() }
                        .setStyle(Style.HEADER_WITH_TITLE)
                        .show()
                }
            }
        }
    }

    private fun sendVerificationAgain() {
        val currentUser = FirebaseSetup.auth?.currentUser
        currentUser?.sendEmailVerification()?.addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                MotionToast.Companion.createColorToast(
                    requireActivity(),
                    getString(R.string.successful),
                    getString(R.string.vefification_email),
                    MotionToast.Companion.TOAST_SUCCESS,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
            }
            else
            {
                MotionToast.Companion.createColorToast(
                    requireActivity(),
                    getString(R.string.failed),
                    getString(R.string.try_again_),
                    MotionToast.Companion.TOAST_ERROR,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(requireContext()).build().show()
        }
        else {
            Permission.requestBasicPermission(requireActivity())
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
//        MotionToast.Companion.createColorToast(
//            this,
//            "Successful",
//            "Permissions Granted!",
//            MotionToast.Companion.TOAST_SUCCESS,
//            MotionToast.Companion.GRAVITY_BOTTOM,
//            MotionToast.Companion.LONG_DURATION,
//            ResourcesCompat.getFont(this, R.font.helvetica_regular))
    }


    private fun checkPermissions() {
        with(binding) {
            if (Permission.hasBasicPermission(requireContext())) {
                loginUser(loginEmail.text.toString().trim(), loginPassword.text.toString().trim())
            }
            else {
                Permission.requestBasicPermission(requireActivity())
            }
        }
    }

}