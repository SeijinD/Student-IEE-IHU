package eu.seijindemon.student_iee_ihu.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.WorkerThread
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
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
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.ui.not_network.NotNetworkActivity
import eu.seijindemon.student_iee_ihu.utils.FirebaseSetup
import eu.seijindemon.student_iee_ihu.utils.NetworkStatus
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_header.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseSetup.setupFirebase()

        setContentView(R.layout.activity_main)

        drawNavTool() // DrawLayout Menu, Navigation, Toolbar
        loadHeader()

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

    private fun drawNavTool() {
        // DrawLayout
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        findViewById<ImageView>(R.id.imageMenu).setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Nav
        val navigationView: NavigationView = findViewById(R.id.navigationView)
        navigationView.itemIconTintList = null
        val navController: NavController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(navigationView, navController)

        // Change Title in Toolbar
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            textTitle.text = destination.label
        }

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)
    }

    private fun loadHeader() {
        val headView: View = navigationView.getHeaderView(0)
        FirebaseSetup.userReference?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                headView.header_am.text = snapshot.child("am").value.toString()
                headView.header_email.text = snapshot.child("email").value.toString()

                val loadImage = snapshot.child("profile").value.toString()
                Glide.with(applicationContext)
                    .load(loadImage)
                    .apply(RequestOptions.circleCropTransform())
                    .error(R.drawable.default_profile)
                    .into(headView.imageProfile)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("TAG", error.message)
            }
        })
    }

}