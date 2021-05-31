package eu.seijindemon.student_iee_ihu.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.UserProfileChangeRequest
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.FirebaseSetup
import kotlinx.android.synthetic.main.activity_register.*
import www.sanju.motiontoast.MotionToast

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        FirebaseSetup.setupFirebase()

        register()
    }

    private fun register() {
        register_am.doOnTextChanged { text, start, before, count ->
            when {
                text!!.length < 8 -> register_am.error = "AM = 8!"
                text.length > 8 -> register_am.error = "AM = 8!"
                else -> register_am.error = null
            }
        }

        register_password.doOnTextChanged { text, start, before, count ->
            when {
                text!!.length < 10 -> register_password.error = "Password >= 10!"
                text.length > 20 -> register_password.error = "Password <= 20!"
                else -> register_password.error = null
            }
        }

        register_verify_password.doOnTextChanged { text, start, before, count ->
            when {
                text!!.length < 10 -> register_verify_password.error = "Verify Password >= 10!"
                text.length > 20 -> register_verify_password.error = "Verify Password <= 20!"
                else -> register_verify_password.error = null
            }
        }

        register_button.setOnClickListener {
            when {
                register_email.text.toString().trim().isEmpty() -> {
                    MotionToast.Companion.createColorToast(
                        this,
                        "Warning",
                        "Input Email",
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                register_am.text.toString().trim().isEmpty() -> {
                    MotionToast.Companion.createColorToast(
                        this,
                        "Warning",
                        "Input AM",
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                register_firstName.text.toString().trim().isEmpty() -> {
                    MotionToast.Companion.createColorToast(
                        this,
                        "Warning",
                        "Input FirstName",
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                register_lastName.text.toString().trim().isEmpty() -> {
                    MotionToast.Companion.createColorToast(
                        this,
                        "Warning",
                        "Input LastName",
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                register_password.text.toString().trim().isEmpty() || register_verify_password.text.toString().trim().isEmpty() -> {
                    MotionToast.Companion.createColorToast(
                        this,
                        "Warning",
                        "Input Password or Confirm Password",
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                register_password.text.toString().trim() != register_verify_password.text.toString().trim() -> {
                    MotionToast.Companion.createColorToast(
                        this,
                        "Warning",
                        "Passwords are different",
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                else -> {
                    createUser(
                        register_email.text.toString().trim(),
                        register_password.text.toString().trim(),
                        register_am.text.toString().trim(),
                        register_firstName.text.toString().trim(),
                        register_lastName.text.toString().trim()
                    )
                }
            }
        }
    }

    private fun createUser(email: String, password: String, am: String, firstName: String, lastName: String) {
        FirebaseSetup.auth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(this){ task ->
                when {
                    task.isSuccessful -> {
                        val currentUser = FirebaseSetup.auth?.currentUser

                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(am).build()

                        currentUser!!.updateProfile(profileUpdates)
                            .addOnCompleteListener(this){ task ->
                                if (task.isSuccessful) {
                                    Log.e("TAG", "Updated Profile")
                                }
                            }

                        val currentUserDb = FirebaseSetup.usersReference!!.child(currentUser.uid)
                        currentUserDb.child("am").setValue(am)
                        currentUserDb.child("firstname").setValue(firstName)
                        currentUserDb.child("lastname").setValue(lastName)
                        currentUserDb.child("email").setValue(email)

                        currentUser.sendEmailVerification().addOnCompleteListener{
                            MotionToast.Companion.createColorToast(
                                this,
                                "Successful",
                                "Verification Email has been sent.",
                                MotionToast.Companion.TOAST_SUCCESS,
                                MotionToast.Companion.GRAVITY_BOTTOM,
                                MotionToast.Companion.LONG_DURATION,
                                ResourcesCompat.getFont(this, R.font.helvetica_regular))
                        }

                        MotionToast.Companion.createColorToast(
                            this,
                            "Successful",
                            "Registration successful",
                            MotionToast.Companion.TOAST_SUCCESS,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(this, R.font.helvetica_regular))

                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()

                    }
                    else -> {
                        MotionToast.Companion.createColorToast(
                            this,
                            "Failed",
                            "Registration failed, please try again!",
                            MotionToast.Companion.TOAST_ERROR,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(this, R.font.helvetica_regular)
                        )
                    }
                }
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}