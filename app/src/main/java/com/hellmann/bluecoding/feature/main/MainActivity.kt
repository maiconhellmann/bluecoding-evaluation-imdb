package com.hellmann.bluecoding.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.hellmann.bluecoding.R
import com.hellmann.bluecoding.databinding.ActivityMainBinding
import com.hellmann.bluecoding.feature.movie.theaternow.notification.TheaterNowNotificationController
import org.koin.android.ext.android.inject
import org.koin.core.inject

/**
 * Handle the navigation, menu, drawer, toolbar and start the broadcast for AlarmManager
 */
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val controller: TheaterNowNotificationController by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val navController = setupNavController() ?: return

        //Setup the navigation library: Drawer, bottonNav, toolbar and navigation
        setupActionBar(navController)
        setupDrawer(navController)
        setupBottomNavMenu(navController)
        setupNavigationMenu(navController)

        //Start the alarm manager to schedule a push notification
        controller.startAlarmManager()
    }

    private fun setupNavController() =
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?)?.navController

    private fun setupActionBar(navController: NavController) {
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    private fun setupDrawer(navController: NavController) {
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.movie_list_dest, R.id.favorite_movie_list_dest),
            binding.drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = binding.bottomNavView
        bottomNav.setupWithNavController(navController)
    }

    private fun setupNavigationMenu(navController: NavController) {
        val sideNavView = binding.navView
        sideNavView.setupWithNavController(navController)
    }
}