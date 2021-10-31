package eu.seijindemon.student_iee_ihu.ui.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
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
import dagger.hilt.android.AndroidEntryPoint
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
import kotlin.concurrent.schedule

@AndroidEntryPoint
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

        val user = FirebaseSetup.auth?.currentUser

        Timer().schedule(3_000) {
            when (user) {
                null -> {
                    loading.isDismiss()
                }
                else -> {
                    FirebaseSetup.userReference?.child("admin")?.get()?.addOnSuccessListener { data ->
                        val isAdmin = data.value as String
                        val currentUser = FirebaseSetup.auth?.currentUser
                        if (isAdmin == "no") {
                            if (currentUser?.isEmailVerified!!) {
                                loading.isDismiss()

                                MotionToast.Companion.createColorToast(
                                    this@LoginActivity,
                                    getString(R.string.successful),
                                    getString(R.string.login_),
                                    MotionToast.Companion.TOAST_SUCCESS,
                                    MotionToast.Companion.GRAVITY_BOTTOM,
                                    MotionToast.Companion.SHORT_DURATION,
                                    ResourcesCompat.getFont(this@LoginActivity, R.font.helvetica_regular))

                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                finish()
                            } else if (!currentUser.isEmailVerified) {
                                loading.isDismiss()

                                MotionToast.Companion.createColorToast(
                                    this@LoginActivity,
                                    getString(R.string.warning),
                                    getString(R.string.must_verify_email),
                                    MotionToast.Companion.TOAST_WARNING,
                                    MotionToast.Companion.GRAVITY_BOTTOM,
                                    MotionToast.Companion.LONG_DURATION,
                                    ResourcesCompat.getFont(this@LoginActivity, R.font.helvetica_regular))

                                MaterialStyledDialog.Builder(this@LoginActivity)
                                    .setTitle(getString(R.string.send_email_again))
                                    .setNegativeText(R.string.no)
                                    .setPositiveText(R.string.yes)
                                    .onPositive { sendVerificationAgain() }
                                    .setStyle(Style.HEADER_WITH_TITLE)
                                    .show()
                            }
                        } else if (isAdmin == "yes") {
                            loading.isDismiss()

                            MotionToast.Companion.createColorToast(
                                this@LoginActivity,
                                getString(R.string.successful),
                                getString(R.string.login_),
                                MotionToast.Companion.TOAST_SUCCESS,
                                MotionToast.Companion.GRAVITY_BOTTOM,
                                MotionToast.Companion.SHORT_DURATION,
                                ResourcesCompat.getFont(this@LoginActivity, R.font.helvetica_regular))

                            startActivity(Intent(this@LoginActivity, AdminMainActivity::class.java))
                            finish()
                        }
                    }?.addOnFailureListener {
                        Log.e("firebase", "Error getting data", it)
                        loading.isDismiss()
                    }
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
                        this,
                        getString(R.string.successful),
                        getString(R.string.send_reset_password_email),
                        MotionToast.Companion.TOAST_SUCCESS,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                else {
                    MotionToast.Companion.createColorToast(
                        this,
                        getString(R.string.unsuccessful),
                        getString(R.string.no_send_reset_password_email),
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
                    getString(R.string.warning),
                    getString(R.string.input_email),
                    MotionToast.Companion.TOAST_WARNING,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular))
            }
            login_password.text.toString().trim().isEmpty() -> {
                MotionToast.Companion.createColorToast(
                    this,
                    getString(R.string.warning),
                    getString(R.string.input_password),
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
                                    getString(R.string.successful),
                                    getString(R.string.login_),
                                    MotionToast.Companion.TOAST_SUCCESS,
                                    MotionToast.Companion.GRAVITY_BOTTOM,
                                    MotionToast.Companion.SHORT_DURATION,
                                    ResourcesCompat.getFont(this, R.font.helvetica_regular))

                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            } else if (!currentUser.isEmailVerified) {
                                MotionToast.Companion.createColorToast(
                                    this,
                                    getString(R.string.warning),
                                    getString(R.string.must_verify_email),
                                    MotionToast.Companion.TOAST_WARNING,
                                    MotionToast.Companion.GRAVITY_BOTTOM,
                                    MotionToast.Companion.LONG_DURATION,
                                    ResourcesCompat.getFont(this, R.font.helvetica_regular))

                                MaterialStyledDialog.Builder(this)
                                    .setTitle(getString(R.string.send_email_again))
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
                                getString(R.string.successful),
                                getString(R.string.login_),
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
                        getString(R.string.failed),
                        getString(R.string.try_again_),
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
                    getString(R.string.successful),
                    getString(R.string.vefification_email),
                    MotionToast.Companion.TOAST_SUCCESS,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular))
            }
            else
            {
                MotionToast.Companion.createColorToast(
                    this,
                    getString(R.string.failed),
                    getString(R.string.try_again_),
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