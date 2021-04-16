package eu.seijindemon.student_iee_ihu.nav_fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.github.javiersantos.materialstyleddialogs.enums.Style
import eu.seijindemon.student_iee_ihu.AdminMainActivity
import eu.seijindemon.student_iee_ihu.FirebaseSetup
import eu.seijindemon.student_iee_ihu.LoginActivity
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_settings.view.*
import www.sanju.motiontoast.MotionToast

class SettingsFragment : Fragment() {

    lateinit var firebaseSetup: FirebaseSetup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_settings, container, false)

        firebaseSetup =  FirebaseSetup()
        firebaseSetup.setupFirebase()

        view.logout_button.setOnClickListener {
            logout()
        }

        view.reset_password_button_settings.setOnClickListener {
            resetPassword()
        }

        view.reset_email_button_settings.setOnClickListener {
            resetEmail()
        }

        view.delete_account_button_settings.setOnClickListener {
            deleteAccount()
        }


        return view
    }

    private fun resetPassword()
    {
        MaterialStyledDialog.Builder(requireContext())
                .setTitle("Reset Your Password!")
                .setDescription("Are you sure?")
                .setNegativeText(R.string.no)
                .setPositiveText(R.string.yes)
                .onPositive { confirmResetPassword() }
                .setStyle(Style.HEADER_WITH_TITLE)
                .show()
    }

    private fun resetEmail()
    {
        MaterialStyledDialog.Builder(requireContext())
                .setTitle("Reset Your Email!")
                .setDescription("Are you sure?")
                .setNegativeText(R.string.no)
                .setPositiveText(R.string.yes)
                .onPositive { confirmResetEmail() }
                .setStyle(Style.HEADER_WITH_TITLE)
                .show()
    }

    private fun deleteAccount()
    {
        MaterialStyledDialog.Builder(requireContext())
                .setTitle("Delete Your Account!")
                .setDescription("Are you sure?")
                .setNegativeText(R.string.no)
                .setPositiveText(R.string.yes)
                .onPositive { confirmDelete() }
                .setStyle(Style.HEADER_WITH_TITLE)
                .show()

    }

    private fun confirmResetPassword()
    {

    }

    private fun confirmResetEmail()
    {

    }

    private fun confirmDelete()
    {
        firebaseSetup.user?.delete()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful)
                {
                    firebaseSetup.userReference?.removeValue()
                    MotionToast.Companion.createColorToast(
                            this.requireActivity(),
                            "Successful",
                            "Account Deleted",
                            MotionToast.Companion.TOAST_SUCCESS,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(this.requireContext(), R.font.helvetica_regular)
                    )
                    startActivity(Intent(activity, LoginActivity::class.java))
                    activity?.finish()
                }
                else
                {
                    MotionToast.Companion.createColorToast(
                            this.requireActivity(),
                            "UnSuccessful",
                            "Delete Not Deleted",
                            MotionToast.Companion.TOAST_ERROR,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(this.requireContext(), R.font.helvetica_regular)
                    )
                }
            }
    }

    private fun logout()
    {
        firebaseSetup.auth?.signOut()
        startActivity(Intent(context, LoginActivity::class.java))
        activity?.finish()
    }


}