package eu.seijindemon.student_iee_ihu.ui.main

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.tencent.mmkv.MMKV
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.ui.help.HelpFragment
import eu.seijindemon.student_iee_ihu.ui.not_network.NotNetworkActivity
import eu.seijindemon.student_iee_ihu.utils.FirebaseSetup
import eu.seijindemon.student_iee_ihu.utils.NetworkStatus
import kotlinx.android.synthetic.main.activity_admin_main.*
import kotlinx.android.synthetic.main.navigation_header_admin.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AdminMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseSetup.setupFirebase()

        MMKV.initialize(this)
        loadLocale()

        setContentView(R.layout.activity_admin_main)

        setupNav()
        loadHeader()
    }

    private fun setLocale(Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources?.updateConfiguration(config, baseContext.resources.displayMetrics)

        val kv = MMKV.mmkvWithID("languageMode")
        kv?.encode("string", Lang)
    }

    private fun loadLocale() {
        val kv = MMKV.mmkvWithID("languageMode")

        if (kv?.decodeString("string") == null) {
            kv?.encode("string","en")
        }

        setLocale(kv?.decodeString("string")!!)
    }

    private fun setupNav() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_admin_layout)
        val navigationView: NavigationView = findViewById(R.id.navigation_view_admin)
        val navController: NavController = Navigation.findNavController(this, R.id.nav_host_fragment_admin)

        NavigationUI.setupWithNavController(navigationView, navController)

        // Click menu image to open drawer menu
        image_menu_admin.setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Change Title in toolbar, if change fragment
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            text_title.text = destination.label
        }

        // Color in drawer menu icons
        navigationView.itemIconTintList = null

        open_apps.setOnClickListener{
            openApps()
        }

        help_button.setOnClickListener{
            navController.navigate(R.id.menuHelp)
        }

    }

    private fun openApps(){
        try {
            startActivity(Intent(packageManager.getLaunchIntentForPackage("gr.teithe.it.it_app")))
        }
        catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://apps.iee.ihu.gr/announcements")))
        }
    }

    private fun loadHeader() {
        val headView: View = navigation_view_admin.getHeaderView(0)
        FirebaseSetup.userReference?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                headView.header_am_admin.text = snapshot.child("am").value.toString()
                headView.header_email_admin.text = snapshot.child("email").value.toString()

                val loadImage = snapshot.child("profile").value.toString()
                Glide.with(applicationContext)
                    .load(loadImage)
                    .apply(RequestOptions.circleCropTransform())
                    .error(R.drawable.default_profile)
                    .into(headView.imageProfile_admin)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("TAG", error.message)
            }
        })
    }

    override fun onBackPressed() {
        if (drawer_admin_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_admin_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
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