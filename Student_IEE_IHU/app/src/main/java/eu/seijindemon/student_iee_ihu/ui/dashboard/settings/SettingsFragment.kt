package eu.seijindemon.student_iee_ihu.ui.dashboard.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.github.javiersantos.materialstyleddialogs.enums.Style
import com.tencent.mmkv.MMKV
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentSettingsBinding
import eu.seijindemon.student_iee_ihu.ui.app.AppActivity
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.ui.dashboard.DashboardFragment
import eu.seijindemon.student_iee_ihu.util.FirebaseSetup
import www.sanju.motiontoast.MotionToast
import java.util.*

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    override fun getViewBinding(): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadLanguage()
        loadTheme()

        FirebaseSetup.setupFirebase()

        setupListeners()

    }

    fun setupListeners() {
        with(binding) {
            logoutButton.setOnClickListener { logout() }

            resetPasswordButtonSettings.setOnClickListener { resetPassword() }

            resetEmailButtonSettings.setOnClickListener { findNavController().navigate(R.id.menuResetEmail) }

            deleteAccountButtonSettings.setOnClickListener { deleteAccount() }

            shareApp.setOnClickListener { shareApp() }

            rateApp.setOnClickListener { rateApp() }

            report.setOnClickListener {  findNavController().navigate(R.id.menuEmailForm) }

            about.setOnClickListener {  findNavController().navigate(R.id.menuAbout) }

            privacyPolicy.setOnClickListener { privacyPolicy() }

            englishRb.setOnClickListener{ setLocale("en") }

            greekRb.setOnClickListener{ setLocale("el") }

            darkRb.setOnClickListener{ setTheme("dark") }

            whiteRb.setOnClickListener{ setTheme("white") }

            autoRb.setOnClickListener{ setTheme("auto") }
        }
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

    private fun loadTheme() {
        with(binding) {
            val kv = MMKV.mmkvWithID("themeMode")

            when (kv?.decodeInt("int")) {
                1 -> darkRb.isChecked = true
                2 -> whiteRb.isChecked = true
                3 -> autoRb.isChecked = true
            }
        }
    }

    private fun loadLanguage() {
        with(binding) {
            val kv = MMKV.mmkvWithID("languageMode")

            when (kv?.decodeString("string")) {
                "el" -> greekRb.isChecked = true
                "en" -> englishRb.isChecked = true
            }
        }
    }

    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        activity?.baseContext?.resources?.updateConfiguration(config, activity?.baseContext?.resources?.displayMetrics)

        val kv = MMKV.mmkvWithID("languageMode")
        kv?.encode("string", lang)

        activity?.finish()
        startActivity(activity?.intent)
    }

    private fun shareApp() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Student IEE-IHU")
        intent.putExtra(Intent.EXTRA_TEXT, "Download this Application now: http://play.google.com/store/apps/details?id=${activity?.packageName}")
        startActivity(Intent.createChooser(intent, getString(R.string.share_with)))
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
                getString(R.string.warning),
                getString(R.string.privacy_policy_not_found),
                MotionToast.Companion.TOAST_WARNING,
                MotionToast.Companion.GRAVITY_BOTTOM,
                MotionToast.Companion.LONG_DURATION,
                ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
        }
    }


    private fun resetPassword() {
        MaterialStyledDialog.Builder(requireContext())
                .setTitle(R.string.reset_your_password)
                .setDescription(R.string.are_you_sure)
                .setNegativeText(R.string.no)
                .setPositiveText(R.string.yes)
                .onPositive { confirmResetPassword() }
                .setStyle(Style.HEADER_WITH_TITLE)
                .show()
    }

    private fun deleteAccount() {
        MaterialStyledDialog.Builder(requireContext())
                .setTitle(R.string.delete_your_account)
                .setDescription(R.string.are_you_sure)
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
                                getString(R.string.successful),
                                getString(R.string.send_reset_password_email),
                                MotionToast.Companion.TOAST_SUCCESS,
                                MotionToast.Companion.GRAVITY_BOTTOM,
                                MotionToast.Companion.LONG_DURATION,
                                ResourcesCompat.getFont(this.requireContext(), R.font.helvetica_regular))
                    }
                    else {
                        MotionToast.Companion.createColorToast(
                                this.requireActivity(),
                                getString(R.string.unsuccessful),
                                getString(R.string.no_send_reset_password_email),
                                MotionToast.Companion.TOAST_ERROR,
                                MotionToast.Companion.GRAVITY_BOTTOM,
                                MotionToast.Companion.LONG_DURATION,
                                ResourcesCompat.getFont(this.requireContext(), R.font.helvetica_regular))
                    }
                }
    }

    private fun confirmDelete() {
        var imageRef = ""
        try {
            if (DashboardFragment.imageRef!!.startsWith("https")) {
                imageRef = DashboardFragment.imageRef!!
            }
        } catch (e: NullPointerException) {
            Log.d("TAGG", e.toString())
        }


        if (imageRef.startsWith("https")) {
            FirebaseSetup.storage?.getReferenceFromUrl(imageRef)?.delete()?.addOnSuccessListener {
                Log.d("TAG", "onSuccess: deleted file");
            }?.addOnFailureListener {
                Log.d("TAG", "onFailure: did not delete file"); }
        }

        FirebaseSetup.user?.delete()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    FirebaseSetup.userReference?.removeValue()

                    MotionToast.Companion.createColorToast(
                        this.requireActivity(),
                        getString(R.string.successful),
                        getString(R.string.account_deleted),
                        MotionToast.Companion.TOAST_SUCCESS,
                        MotionToast.Companion.GRAVITY_BOTTOM,
                        MotionToast.Companion.LONG_DURATION,
                        ResourcesCompat.getFont(this.requireContext(), R.font.helvetica_regular))

                    startActivity(Intent(activity, AppActivity::class.java))
                    activity?.finish()
                }
                else {
                    MotionToast.Companion.createColorToast(
                            this.requireActivity(),
                            getString(R.string.unsuccessful),
                            getString(R.string.account_not_deleted),
                            MotionToast.Companion.TOAST_ERROR,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            ResourcesCompat.getFont(this.requireContext(), R.font.helvetica_regular))
                }
            }
    }

    private fun logout() {
        FirebaseSetup.auth?.signOut()
        startActivity(Intent(context, AppActivity::class.java))
        activity?.finish()
    }


}