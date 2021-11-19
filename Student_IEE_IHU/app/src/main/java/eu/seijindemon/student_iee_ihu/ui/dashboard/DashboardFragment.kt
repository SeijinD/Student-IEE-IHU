package eu.seijindemon.student_iee_ihu.ui.dashboard

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.core.view.doOnLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.tencent.mmkv.MMKV
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentDashboardBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.util.BASE_URL_SITE_EL
import eu.seijindemon.student_iee_ihu.util.FirebaseSetup
import java.util.*

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    override fun getViewBinding(): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(layoutInflater)
    }

    private val navController: NavController by lazy { Navigation.findNavController(binding.navDashboardFragment) }

    companion object {
        var imageRef: String? = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
                else {
                    requireActivity().finish()
                }
            }
        })

        FirebaseSetup.setupFirebase()

        MMKV.initialize(requireContext())
        loadLocale()

        setupNav()
        loadHeader()

    }

    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        activity?.baseContext?.resources?.updateConfiguration(config, activity?.baseContext?.resources?.displayMetrics)

        val kv = MMKV.mmkvWithID("languageMode")
        kv?.encode("string", lang)
    }

    private fun loadLocale() {
        val kv = MMKV.mmkvWithID("languageMode")

        if (kv?.decodeString("string") == null) {
            kv?.encode("string","en")
        }

        setLocale(kv?.decodeString("string")!!)
    }

    private fun setupNav() {
        with(binding) {
            navDashboardFragment.doOnLayout {
                val drawerLayout: DrawerLayout = drawerLayout
                val navigationView: NavigationView = navigationView
                NavigationUI.setupWithNavController(navigationView, navController)

                // Click menu image to open drawer menu
                imageMenu.setOnClickListener{
                    drawerLayout.openDrawer(GravityCompat.START)
                }

                // Change Title in toolbar, if change fragment
                navController.addOnDestinationChangedListener{ _, destination, _ ->
                    textTitle.text = destination.label
                }

                // Color in drawer menu icons
                navigationView.itemIconTintList = null

                openApps.setOnClickListener{
                    openApps()
                }

                exams.setOnClickListener{
                    openExams()
                }

                helpButton.setOnClickListener{
                    navController.navigate(R.id.menuHelp)
                }
            }
        }
    }

    private fun openExams() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(BASE_URL_SITE_EL +"exams-program/")))
    }

    private fun openApps() {
        val intent = activity?.packageManager?.getLaunchIntentForPackage("gr.teithe.it.it_app")
        if (intent != null) {
            startActivity(intent)
        }
        else {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://apps.iee.ihu.gr/announcements")))
        }
    }

    private fun loadHeader() {
        val headView: View = binding.navigationView.getHeaderView(0)
        val headerAm = headView.findViewById<TextView>(R.id.header_am)
        val headerEmail = headView.findViewById<TextView>(R.id.header_email)
        val imageProfile = headView.findViewById<ImageView>(R.id.image_profile)

        FirebaseSetup.userReference?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                headerAm.text = snapshot.child("am").value.toString()
                headerEmail.text = snapshot.child("email").value.toString()

                val loadImage = snapshot.child("profile").value.toString()
                imageRef = loadImage
                Glide.with(requireContext())
                    .load(loadImage)
                    .apply(RequestOptions.circleCropTransform())
                    .error(R.drawable.default_profile)
                    .into(imageProfile)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("TAG", error.message)
            }
        })
    }

}