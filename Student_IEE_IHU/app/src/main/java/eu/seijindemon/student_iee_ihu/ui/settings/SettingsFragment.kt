package eu.seijindemon.student_iee_ihu.ui.settings

import android.content.ActivityNotFoundException
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
import com.tencent.mmkv.MMKV
import eu.seijindemon.student_iee_ihu.utils.FirebaseSetup
import eu.seijindemon.student_iee_ihu.ui.auth.LoginActivity
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import www.sanju.motiontoast.MotionToast
import java.util.*

class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        loadLanguage(view)
        loadTheme(view)

        FirebaseSetup.setupFirebase()

        view.logout_button.setOnClickListener { logout() }

        view.reset_password_button_settings.setOnClickListener { resetPassword() }

        view.reset_email_button_settings.setOnClickListener { resetEmail() }

        view.delete_account_button_settings.setOnClickListener { deleteAccount() }

        view.share_app.setOnClickListener { shareApp() }

        view.rate_app.setOnClickListener { rateApp() }

        view.report.setOnClickListener {  Navigation.findNavController(view).navigate(R.id.menuEmailForm) }

        view.about.setOnClickListener {  Navigation.findNavController(view).navigate(R.id.menuAbout) }

        view.privacy_policy.setOnClickListener { privacyPolicy() }

        view.english_rb.setOnClickListener{ setLocale("en",view) }

        view.greek_rb.setOnClickListener{ setLocale("el",view) }

        view.dark_rb.setOnClickListener{ setTheme("dark") }

        view.white_rb.setOnClickListener{ setTheme("white") }

        view.auto_rb.setOnClickListener{ setTheme("auto") }

        return view
    }

    private fun setTheme(theme: String){
        val kv = MMKV.mmkvWithID("themeMode")

        when (theme) {
            "dark" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                kv?.encode("int",1)
            }
            "white" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                kv?.encode("int",2)
            }
            "auto" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                kv?.encode("int",3)
            }
        }
    }

    private fun loadTheme(view: View) {
        val kv = MMKV.mmkvWithID("themeMode")

        when (kv?.decodeInt("int")) {
            1 -> view.dark_rb.isChecked = true
            2 -> view.white_rb.isChecked = true
            3 -> view.auto_rb.isChecked = true
        }
    }

    private fun loadLanguage(view: View) {
        val kv = MMKV.mmkvWithID("languageMode")

        when (kv?.decodeString("string")) {
            "el" -> view.greek_rb.isChecked = true
            "en" -> view.english_rb.isChecked = true
        }
    }

    private fun setLocale(Lang: String, view: View) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        activity?.baseContext?.resources?.updateConfiguration(config, activity?.baseContext?.resources?.displayMetrics)

        val kv = MMKV.mmkvWithID("languageMode")
        kv?.encode("string", Lang)

        activity?.finish()
        startActivity(activity?.intent)
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
        FirebaseSetup.auth?.sendPasswordResetEmail(FirebaseSetup.user?.email!!)
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
        FirebaseSetup.user?.delete()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    FirebaseSetup.userReference?.removeValue()
                    MotionToast.Companion.createColorToast(
                            this.requireActivity(),
                            "Successful",
                            "Account Deleted",
                            MotionToast.Companion.TOAST_SUCCESS,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(this.requireContext(), R.font.helvetica_regular))
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
                            ResourcesCompat.getFont(this.requireContext(), R.font.helvetica_regular))
                }
            }
    }

    private fun logout() {
        FirebaseSetup.auth?.signOut()
        startActivity(Intent(context, LoginActivity::class.java))
        activity?.finish()
    }


}