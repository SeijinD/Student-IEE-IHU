package eu.seijindemon.student_iee_ihu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_login.*
import www.sanju.motiontoast.MotionToast

class LoginActivity : AppCompatActivity() {

    lateinit var firebaseSetup: FirebaseSetup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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


        login_button.setOnClickListener{
            login()
        }

        reset_password_button.setOnClickListener {
            // TODO
        }

        create_account_button.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }


    }

    private fun login()
    {
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

    private fun loginUser(email: String, password: String)
    {
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