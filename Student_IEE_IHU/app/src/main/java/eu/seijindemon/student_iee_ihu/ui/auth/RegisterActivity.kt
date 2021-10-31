package eu.seijindemon.student_iee_ihu.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.ui.not_network.NotNetworkActivity
import eu.seijindemon.student_iee_ihu.utils.FirebaseSetup
import eu.seijindemon.student_iee_ihu.utils.NetworkStatus
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToast

@AndroidEntryPoint
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
                text!!.length < 6 -> register_am.error = "AM >= 6!"
                text.length > 10 -> register_am.error = "AM <= 10!"
                else -> register_am.error = null
            }
        }

        register_password.doOnTextChanged { text, start, before, count ->
            when {
                text!!.length < 10 -> register_password.error = "${getString(R.string.password)} >= 10!"
                text.length > 20 -> register_password.error = "${getString(R.string.password)} <= 20!"
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
                        getString(R.string.warning),
                        getString(R.string.input_email),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                register_am.text.toString().trim().isEmpty() -> {
                    MotionToast.Companion.createColorToast(
                        this,
                        getString(R.string.warning),
                        getString(R.string.input_am),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                register_am.text.toString().length < 6 -> {
                    MotionToast.Companion.createColorToast(
                        this,
                        getString(R.string.warning),
                        getString(R.string.am_is_small),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                register_firstName.text.toString().trim().isEmpty() -> {
                    MotionToast.Companion.createColorToast(
                        this,
                        getString(R.string.warning),
                        getString(R.string.input_firstname),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                register_lastName.text.toString().trim().isEmpty() -> {
                    MotionToast.Companion.createColorToast(
                        this,
                        getString(R.string.warning),
                        getString(R.string.input_lastname),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                register_password.text.toString().trim().isEmpty() || register_verify_password.text.toString().trim().isEmpty() -> {
                    MotionToast.Companion.createColorToast(
                        this,
                        getString(R.string.warning),
                        getString(R.string.input_password_or_confirm_password),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                register_password.text.toString().length < 10 || register_verify_password.text.toString().length < 10 -> {
                    MotionToast.Companion.createColorToast(
                        this,
                        getString(R.string.warning),
                        getString(R.string.passwords_are_small),
                        MotionToast.Companion.TOAST_WARNING,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.helvetica_regular))
                }
                register_password.text.toString().trim() != register_verify_password.text.toString().trim() -> {
                    MotionToast.Companion.createColorToast(
                        this,
                        getString(R.string.warning),
                        getString(R.string.passwords_are_different),
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
                                this,
                                getString(R.string.successful),
                                getString(R.string.vefification_email),
                                MotionToast.Companion.TOAST_SUCCESS,
                                MotionToast.Companion.GRAVITY_BOTTOM,
                                MotionToast.Companion.LONG_DURATION,
                                ResourcesCompat.getFont(this, R.font.helvetica_regular))
                        }

                        MotionToast.Companion.createColorToast(
                            this,
                            getString(R.string.successful),
                            getString(R.string.registration_successful),
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
                            getString(R.string.failed),
                            getString(R.string.registration_failed),
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