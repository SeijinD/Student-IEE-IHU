package eu.seijindemon.student_iee_ihu

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.WhichButton.POSITIVE
import com.afollestad.materialdialogs.actions.setActionButtonEnabled
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import eu.seijindemon.student_iee_ihu.utils.FirebaseSetup
import kotlinx.android.synthetic.main.activity_login.*
import www.sanju.motiontoast.MotionToast
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseSetup: FirebaseSetup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
        loadTheme()
        setContentView(R.layout.activity_login)

        firebaseSetup =  FirebaseSetup()
        firebaseSetup.setupFirebase()

        val currentUser = firebaseSetup.auth?.currentUser

        when {
            currentUser != null -> {
                when {
                    currentUser.email == "georgekara2010@yahoo.gr" -> {
                        startActivity(Intent(this, AdminMainActivity::class.java))
                        finish()
                    }
                    currentUser.isEmailVerified -> {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    else -> {
                        MotionToast.Companion.createColorToast(
                            this,
                            "Warning",
                            "Must verify email!",
                            MotionToast.Companion.TOAST_WARNING,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(this, R.font.helvetica_regular)
                        )
                    }
                }
            }
        }


        login_button.setOnClickListener{ login() }

        reset_password_button.setOnClickListener { resetPassword() }

        create_account_button.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

    }

    private fun loadTheme() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)

        when (sharedPreferences.getInt("ThemeMode", 0)) {
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            3 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    private fun setLocale(Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources?.updateConfiguration(config, baseContext.resources.displayMetrics)
        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun loadLocale() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        if (language != null) {
            setLocale(language)
        }
    }

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
        firebaseSetup.auth?.sendPasswordResetEmail(email)
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
                loginUser(login_email.text.toString().trim(), login_password.text.toString().trim())
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        firebaseSetup.auth!!.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task ->
                val currentUser = firebaseSetup.auth?.currentUser
                when {
                    task.isSuccessful -> {
                        MotionToast.Companion.createColorToast(
                            this,
                            "Successful",
                            "Login...",
                            MotionToast.Companion.TOAST_SUCCESS,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(this, R.font.helvetica_regular))

                        when {
                            email == "georgekara2010@yahoo.gr" -> {
                                startActivity(Intent(this, AdminMainActivity::class.java))
                                finish()
                            }
                            currentUser?.isEmailVerified!! -> {
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }
                            !currentUser.isEmailVerified -> {
                                MotionToast.Companion.createColorToast(
                                    this,
                                    "Warning",
                                    "Must verify email!",
                                    MotionToast.Companion.TOAST_WARNING,
                                    MotionToast.Companion.GRAVITY_BOTTOM,
                                    MotionToast.Companion.LONG_DURATION,
                                    ResourcesCompat.getFont(this, R.font.helvetica_regular)
                                )
                            }
                        }
                    }
                    else -> {
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
    }
}