package eu.seijindemon.student_iee_ihu.nav_fragments

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.github.javiersantos.materialstyleddialogs.enums.Style
import eu.seijindemon.student_iee_ihu.utils.FirebaseSetup
import eu.seijindemon.student_iee_ihu.LoginActivity
import eu.seijindemon.student_iee_ihu.MainActivity
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import www.sanju.motiontoast.MotionToast
import java.util.*

class SettingsFragment : Fragment() {

    private lateinit var firebaseSetup: FirebaseSetup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        loadLanguage(view)
        loadTheme(view)

        firebaseSetup =  FirebaseSetup()
        firebaseSetup.setupFirebase()

        view.logout_button.setOnClickListener { logout() }

        view.reset_password_button_settings.setOnClickListener { resetPassword() }

        view.reset_email_button_settings.setOnClickListener { resetEmail() }

        view.delete_account_button_settings.setOnClickListener { deleteAccount() }

        view.share_app.setOnClickListener { shareApp() }

        view.rate_app.setOnClickListener { rateApp() }

        view.report.setOnClickListener { report(view) }

        view.about.setOnClickListener { about(view) }

        view.privacy_policy.setOnClickListener { privacyPolicy() }

        view.english_rb.setOnClickListener{ setLocale("en") }

        view.greek_rb.setOnClickListener{ setLocale("el") }

        view.dark_rb.setOnClickListener{ setTheme("dark") }

        view.white_rb.setOnClickListener{ setTheme("white") }

        view.auto_rb.setOnClickListener{ setTheme("auto") }

        return view
    }

    private fun setTheme(theme: String){
        val sharedPreferences = activity?.getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val sharedPreferencesEdit = sharedPreferences?.edit()

        when (theme) {
            "dark" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPreferencesEdit?.putInt("ThemeMode", 1)
                sharedPreferencesEdit?.apply()
            }
            "white" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPreferencesEdit?.putInt("ThemeMode", 2)
                sharedPreferencesEdit?.apply()
            }
            "auto" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                sharedPreferencesEdit?.putInt("ThemeMode", 3)
                sharedPreferencesEdit?.apply()
            }
        }
    }

    private fun loadTheme(view: View) {
        val sharedPreferences = activity?.getSharedPreferences("Settings", Activity.MODE_PRIVATE)

        when (sharedPreferences?.getInt("ThemeMode", 0)) {
            1 -> {
                view.dark_rb.isChecked = true
            }
            2 -> {
                view.white_rb.isChecked = true
            }
            3 -> {
                view.auto_rb.isChecked = true
            }
        }
    }

    private fun loadLanguage(view: View) {
        val sharedPreferences = activity?.getSharedPreferences("Settings", Activity.MODE_PRIVATE)

        when (sharedPreferences?.getString("My_Lang", "")) {
            "el" -> {
                view.greek_rb.isChecked = true
            }
            ("en") -> {
                view.english_rb.isChecked = true
            }
        }
    }

    private fun setLocale(Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        activity?.baseContext?.resources?.updateConfiguration(config, activity?.baseContext?.resources?.displayMetrics)
        val editor = activity?.getSharedPreferences("Settings", Context.MODE_PRIVATE)?.edit()
        editor?.putString("My_Lang", Lang)
        editor?.apply()

        logout()
    }

    private fun shareApp() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Student IEE-IHU")
        intent.putExtra(Intent.EXTRA_TEXT, "Download this Application now: http://play.google.com/store/apps/details?id=${activity?.packageName}")
        startActivity(Intent.createChooser(intent, "Share with"))
    }

    private fun rateApp() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${activity?.packageName}")))
        }
        catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=${activity?.packageName}")))
        }
    }

    private fun report(view: View) {
        report.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.menuEmailForm) }
    }

    private fun about(view: View) {
        about.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.menuAbout) }
    }

    private fun privacyPolicy() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://my-informations.flycricket.io/privacy.html")))
        }
        catch (e: ActivityNotFoundException) {
            MotionToast.Companion.createColorToast(
                requireActivity(),
                "Warning",
                "Privacy Policy Not Found!",
                MotionToast.Companion.TOAST_WARNING,
                MotionToast.Companion.GRAVITY_BOTTOM,
                MotionToast.Companion.LONG_DURATION,
                ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
        }
    }


    private fun resetPassword() {
        MaterialStyledDialog.Builder(requireContext())
                .setTitle("Reset Your Password!")
                .setDescription("Are you sure?")
                .setNegativeText(R.string.no)
                .setPositiveText(R.string.yes)
                .onPositive { confirmResetPassword() }
                .setStyle(Style.HEADER_WITH_TITLE)
                .show()
    }

    private fun resetEmail() {
        MaterialStyledDialog.Builder(requireContext())
                .setTitle("Reset Your Email!")
                .setDescription("Are you sure?")
                .setNegativeText(R.string.no)
                .setPositiveText(R.string.yes)
                .onPositive { confirmResetEmail() }
                .setStyle(Style.HEADER_WITH_TITLE)
                .show()
    }

    private fun deleteAccount() {
        MaterialStyledDialog.Builder(requireContext())
                .setTitle("Delete Your Account!")
                .setDescription("Are you sure?")
                .setNegativeText(R.string.no)
                .setPositiveText(R.string.yes)
                .onPositive { confirmDelete() }
                .setStyle(Style.HEADER_WITH_TITLE)
                .show()
    }

    private fun confirmResetPassword() {
        firebaseSetup.auth?.sendPasswordResetEmail(firebaseSetup.user?.email!!)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        MotionToast.Companion.createColorToast(
                                this.requireActivity(),
                                "Successful",
                                "Send Reset Password Email!",
                                MotionToast.Companion.TOAST_SUCCESS,
                                MotionToast.Companion.GRAVITY_BOTTOM,
                                MotionToast.Companion.LONG_DURATION,
                                ResourcesCompat.getFont(this.requireContext(), R.font.helvetica_regular))
                    }
                    else {
                        MotionToast.Companion.createColorToast(
                                this.requireActivity(),
                                "UnSuccessful",
                                "No Send Reset Password Email",
                                MotionToast.Companion.TOAST_ERROR,
                                MotionToast.Companion.GRAVITY_BOTTOM,
                                MotionToast.Companion.LONG_DURATION,
                                ResourcesCompat.getFont(this.requireContext(), R.font.helvetica_regular))
                    }
                }
    }

    private fun confirmResetEmail() {

    }

    private fun confirmDelete() {
        firebaseSetup.user?.delete()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
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
                else {
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

    private fun logout() {
        firebaseSetup.auth?.signOut()
        startActivity(Intent(context, LoginActivity::class.java))
        activity?.finish()
    }


}