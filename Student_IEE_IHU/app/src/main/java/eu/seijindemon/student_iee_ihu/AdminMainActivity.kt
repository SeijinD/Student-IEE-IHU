package eu.seijindemon.student_iee_ihu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
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
import eu.seijindemon.student_iee_ihu.utils.FirebaseSetup
import kotlinx.android.synthetic.main.activity_admin_main.*
import kotlinx.android.synthetic.main.navigation_header_admin.view.*

class AdminMainActivity : AppCompatActivity() {

    lateinit var firebaseSetup: FirebaseSetup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseSetup =  FirebaseSetup()
        firebaseSetup.setupFirebase()

        setContentView(R.layout.activity_admin_main)

        drawNavTool() // DrawLayout Menu, Navigation, Toolbar
        loadHeader()

    }

    private fun drawNavTool()
    {
        // DrawLayout
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerAdminLayout)
        findViewById<ImageView>(R.id.imageMenuAdmin).setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Nav
        val navigationView: NavigationView = findViewById(R.id.navigationViewAdmin)
        navigationView.itemIconTintList = null
        val navController: NavController = Navigation.findNavController(this, R.id.navHostFragmentAdmin)
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
        val headView: View = navigationViewAdmin.getHeaderView(0)
        firebaseSetup.userReference?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                headView.header_am_admin.text = snapshot.child("am").value.toString()
                headView.header_email_admin.text = snapshot.child("email").value.toString()
                if (snapshot.hasChild("profile")) {
                    val loadImage = snapshot.child("profile").value.toString()
                    Glide.with(applicationContext).load(loadImage).apply(RequestOptions.circleCropTransform()).into(headView.imageProfile_admin)
                }
                else {
                    Glide.with(applicationContext).load(R.drawable.default_profile).apply(RequestOptions.circleCropTransform()).into(headView.imageProfile_admin)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("TAG", error.message)
            }
        })
    }


}