package eu.seijindemon.student_iee_ihu.ui.auth

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
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
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.ui.main.AdminMainActivity
import eu.seijindemon.student_iee_ihu.ui.main.MainActivity
import eu.seijindemon.student_iee_ihu.ui.not_network.NotNetworkActivity
import eu.seijindemon.student_iee_ihu.utils.FirebaseSetup
import eu.seijindemon.student_iee_ihu.utils.LoadingDialog
import eu.seijindemon.student_iee_ihu.utils.NetworkStatus
import eu.seijindemon.student_iee_ihu.utils.Permissions
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToast
import java.util.*

class LoginActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private val loading = LoadingDialog(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MMKV.initialize(this)
        loadLocale()
        loadTheme()

        setContentView(R.layout.activity_login)

        FirebaseSetup.setupFirebase()

        loading.startLoading()

        when (FirebaseSetup.auth?.currentUser) {
            null -> {
                loading.isDismiss()
            }
            else -> {
                FirebaseSetup.userReference?.child("admin")?.get()?.addOnSuccessListener { data ->
                    val isAdmin = data.value as String
                    if (isAdmin == "no") {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                        loading.isDismiss()
                        MotionToast.Companion.createColorToast(
                            this,
                            "Successful",
                            "Login...",
                            MotionToast.Companion.TOAST_SUCCESS,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.SHORT_DURATION,
                            ResourcesCompat.getFont(this, R.font.helvetica_regular))
                    } else if (isAdmin == "yes") {
                        startActivity(Intent(this, AdminMainActivity::class.java))
                        finish()
                        loading.isDismiss()
                        MotionToast.Companion.createColorToast(
                            this,
                            "Successful",
                            "Login...",
                            MotionToast.Companion.TOAST_SUCCESS,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.SHORT_DURATION,
                            ResourcesCompat.getFont(this, R.font.helvetica_regular))
                    }
                }?.addOnFailureListener {
                    Log.e("firebase", "Error getting data", it)
                    loading.isDismiss()
                }
            }
        }

        login_button.setOnClickListener{
            login()
        }

        reset_password_button.setOnClickListener {
            resetPassword()
        }

        create_account_button.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        login_help_button.setOnClickListener {
            loginHelpButton()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        loading.isDismiss()
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

    private fun setLocale(Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources?.updateConfiguration(config, baseContext.resources.displayMetrics)

        val kv = MMKV.mmkvWithID("languageMode")
        kv?.encode("string", Lang)
    }

    private fun loadLocale() {
        val kv = MMKV.mmkvWithID("languageMode")

        if (kv?.decodeString("string") == null) {
            kv?.encode("string","en")
        }

        setLocale(kv?.decodeString("string")!!)
    }

    private fun loginHelpButton() {
        MaterialStyledDialog.Builder(this)
            .setTitle(R.string.help_message)
            .setDescription(R.string.help_message_body)
            .setStyle(Style.HEADER_WITH_TITLE)
            .show()
    }

    @SuppressLint("CheckResult")
    private fun resetPassword() {
        val type = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        var email : String? = null
        MaterialDialog(this).show {
            title(R.string.reset_password)
            input(waitForPositiveButton = false, inputType = type, maxLength = 35, hintRes = R.string.email) { dialog, text ->
                val inputField = dialog.getInputField()
                val isValid = text.contains("@")

                inputField.error = if (isValid) null else "Must contains @ in email!"
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
                        this,
                        "Successful",
                        "Send Reset Password Email!",
                        MotionToast.Companion.TOAST_SUCCESS,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                else {
                    MotionToast.Companion.createColorToast(
                        this,
                        "UnSuccessful",
                        "No Send Reset Password Email",
                        MotionToast.Companion.TOAST_ERROR,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
            }
    }

    private fun login() {
        when {
            login_email.text.toString().trim().isEmpty() -> {
                MotionToast.Companion.createColorToast(
                    this,
                    "Warning",
                    "Input Email",
                    MotionToast.Companion.TOAST_WARNING,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular))
            }
            login_password.text.toString().trim().isEmpty() -> {
                MotionToast.Companion.createColorToast(
                    this,
                    "Warning",
                    "Input Password",
                    MotionToast.Companion.TOAST_WARNING,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular))
            }
            else -> {
                checkPermissions()
                //loginUser(login_email.text.toString().trim(), login_password.text.toString().trim())
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        FirebaseSetup.auth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    FirebaseSetup.setupFirebase() // update current user
                    FirebaseSetup.userReference?.child("admin")?.get()?.addOnSuccessListener { data ->
                        val currentUser = FirebaseSetup.auth?.currentUser
                        val isAdmin = data.value as String
                        if (isAdmin == "no") {
                            if (currentUser?.isEmailVerified!!) {
                                MotionToast.Companion.createColorToast(
                                    this,
                                    "Successful",
                                    "Login...",
                                    MotionToast.Companion.TOAST_SUCCESS,
                                    MotionToast.Companion.GRAVITY_BOTTOM,
                                    MotionToast.Companion.SHORT_DURATION,
                                    ResourcesCompat.getFont(this, R.font.helvetica_regular))

                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            } else if (!currentUser.isEmailVerified) {
                                MotionToast.Companion.createColorToast(
                                    this,
                                    "Warning",
                                    "Must verify email!",
                                    MotionToast.Companion.TOAST_WARNING,
                                    MotionToast.Companion.GRAVITY_BOTTOM,
                                    MotionToast.Companion.LONG_DURATION,
                                    ResourcesCompat.getFont(this, R.font.helvetica_regular))

                                MaterialStyledDialog.Builder(this)
                                    .setTitle("Are you want to send email verification again?")
                                    .setNegativeText(R.string.no)
                                    .setPositiveText(R.string.yes)
                                    .onPositive { sendVerificationAgain() }
                                    .setStyle(Style.HEADER_WITH_TITLE)
                                    .show()
                            }
                        }
                        else if (isAdmin == "yes"){
                            MotionToast.Companion.createColorToast(
                                this,
                                "Successful",
                                "Login...",
                                MotionToast.Companion.TOAST_SUCCESS,
                                MotionToast.Companion.GRAVITY_BOTTOM,
                                MotionToast.Companion.SHORT_DURATION,
                                ResourcesCompat.getFont(this, R.font.helvetica_regular))

                            startActivity(Intent(this, AdminMainActivity::class.java))
                            finish()
                        }
                    }?.addOnFailureListener {
                        Log.e("firebase", "Error getting data", it)
                    }
                }
                else {
                    MotionToast.Companion.createColorToast(
                        this,
                        "Failed",
                        "Try Again...",
                        MotionToast.Companion.TOAST_ERROR,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
            }
    }

    private fun sendVerificationAgain() {
        val currentUser = FirebaseSetup.auth?.currentUser
        currentUser?.sendEmailVerification()?.addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                MotionToast.Companion.createColorToast(
                    this,
                    "Successful",
                    "Verification Email has been sent.",
                    MotionToast.Companion.TOAST_SUCCESS,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular))
            }
            else
            {
                MotionToast.Companion.createColorToast(
                    this,
                    "Failed",
                    "Try Again...",
                    MotionToast.Companion.TOAST_ERROR,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular))
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
            SettingsDialog.Builder(this).build().show()
        }
        else {
            Permissions.requestBasicPermission(this)
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
        if (Permissions.hasBasicPermission(this)) {
            loginUser(login_email.text.toString().trim(), login_password.text.toString().trim())
        }
        else {
            Permissions.requestBasicPermission(this)
        }
    }

    override fun onResume() {
        super.onResume()

        CoroutineScope(Dispatchers.IO).launch {
            networkAvailable()
        }
    }

    @WorkerThread
    private suspend fun networkAvailable() {
        if (!NetworkStatus.networkAvailable(application)) {
            startActivity(Intent(this, NotNetworkActivity::class.java))
            finish()
        }
    }

}