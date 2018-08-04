package com.vepe.navigation.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.vepe.navigation.R
import kotlinx.android.synthetic.main.act_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var username: String
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        username = MainActivityArgs.fromBundle(intent?.extras).username

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController

        setupToolbar()
        setupDrawer()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        // sets hamburger menu icon to toolbar and connects it to drawer
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
    }

    private fun setupDrawer() {
        NavigationUI.setupWithNavController(nav_view, navController)

        // sets username in drawer header
        nav_view.getHeaderView(0).apply {
            findViewById<TextView>(R.id.viewUserEmail)?.text = username
            findViewById<Button>(R.id.viewSignOut)?.setOnClickListener {
                finish()
                navController.navigate(R.id.loginActivity)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.deep_link -> {
                createNotification()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun createNotification() {
        // create pending intent for notification
        val args = Bundle()
        args.putString("deep_link_message", "Hi, I came here through deep link!")
        val intent = navController.createDeepLink()
                .setDestination(R.id.deepLinkFragment) // destination in nav graph
                .setArguments(args)
                .createPendingIntent()
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager?.createNotificationChannel(NotificationChannel(
                        "deeplink", "Deep Links", NotificationManager.IMPORTANCE_HIGH))
        }

        // create notification
        val builder = NotificationCompat.Builder(applicationContext, "deeplink")
                .setContentTitle("Navigation")
                .setContentText("Deep link to settings example")
                .setSmallIcon(R.drawable.ic_link)
                .setContentIntent(intent)
                .setAutoCancel(true)
        notificationManager?.notify(0, builder.build())
    }

    // back button support
    override fun onSupportNavigateUp() = NavigationUI.navigateUp(drawer_layout,
            Navigation.findNavController(this, R.id.my_nav_host_fragment))
}
