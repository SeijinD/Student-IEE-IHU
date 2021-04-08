package eu.seijindemon.student_iee_ihu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_register.*
import www.sanju.motiontoast.MotionToast

class RegisterActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        checkPasswords()

        setupFirebase()

        register()

    }

    private fun register()
    {
        register_button.setOnClickListener {
            if(register_email.text.toString().trim().isEmpty())
            {
                MotionToast.Companion.createColorToast(
                    this,
                    "Warning",
                    "Input Email",
                    MotionToast.Companion.TOAST_WARNING,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular)
                )
            }
            else if(register_password.text.toString().trim().isEmpty() || register_verify_password.text.toString().trim().isEmpty())
            {
                MotionToast.Companion.createColorToast(
                    this,
                    "Warning",
                    "Input Password or Confirm Password",
                    MotionToast.Companion.TOAST_WARNING,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular)
                )
            }
            else if (register_password.text.toString().trim() != register_verify_password.text.toString().trim())
            {
                MotionToast.Companion.createColorToast(
                    this,
                    "Warning",
                    "Passwords are different",
                    MotionToast.Companion.TOAST_WARNING,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular)
                )
            }
            else if(register_firstName.text.toString().trim().isEmpty())
            {
                MotionToast.Companion.createColorToast(
                    this,
                    "Warning",
                    "Input FirstName",
                    MotionToast.Companion.TOAST_WARNING,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular)
                )
            }
            else if(register_lastName.text.toString().trim().isEmpty())
            {
                MotionToast.Companion.createColorToast(
                    this,
                    "Warning",
                    "Input LastName",
                    MotionToast.Companion.TOAST_WARNING,
                    MotionToast.Companion.GRAVITY_BOTTOM,
                    MotionToast.Companion.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular)
                )
            }
            else
            {
                createUser(
                    register_email.text.toString().trim(),
                    register_password.text.toString().trim(),
                    register_firstName.text.toString().trim(),
                    register_lastName.text.toString().trim()
                )
            }
        }
    }

    private fun createUser(email: String, password: String, firstName: String, lastName: String)
    {

    }

    private fun setupFirebase() {

    }

    private fun checkPasswords()
    {
        register_password.doOnTextChanged { text, start, before, count ->
            when {
                text!!.length < 10 -> {
                    register_password.error = "Password >= 10!"
                }
                text.length > 20 -> {
                    register_password.error = "Password <= 20!"
                }
                else -> {
                    register_password.error = null
                }
            }
        }

        register_verify_password.doOnTextChanged { text, start, before, count ->
            when {
                text!!.length < 10 -> {
                    register_verify_password.error = "Verify Password >= 10!"
                }
                text.length > 20 -> {
                    register_verify_password.error = "Verify Password <= 20!"
                }
                else -> {
                    register_verify_password.error = null
                }
            }
        }
    }
}