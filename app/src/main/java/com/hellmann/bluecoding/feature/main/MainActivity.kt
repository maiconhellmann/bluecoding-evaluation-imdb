package com.hellmann.bluecoding.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.hellmann.bluecoding.R
import com.hellmann.bluecoding.databinding.ActivityMainBinding
import com.hellmann.bluecoding.feature.authentication.UserGuestAuthenticationFragmentDirections
import com.hellmann.bluecoding.feature.movie.theaternow.notification.TheaterNowNotificationController
import com.hellmann.bluecoding.feature.viewmodel.ViewState
import com.hellmann.bluecoding.util.extensions.toast
import com.hellmann.bluecoding.util.extensions.visible
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Handle the navigation, menu, drawer, toolbar and start the broadcast for AlarmManager
 */
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val controller: TheaterNowNotificationController by inject()
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val navController = getNavController() ?: return

        addListeners(navController)

        //Setup the navigation library: Drawer, bottonNav, toolbar and navigation
        setupActionBar(navController)
        setupDrawer(navController)
        setupBottomNavMenu(navController)
        setupNavigationMenu(navController)

        //Start the alarm manager to schedule a push notification
        controller.startAlarmManager()

        setupViewModel()

    }

    private fun addListeners(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.user_guest_authentication_dest) {
                binding.toolbar.visible(false)
                binding.bottomNavView.visible(false)
            } else {
                binding.toolbar.visible(true)
                binding.bottomNavView.visible(true)
            }
        }
    }

    private fun setupViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getAuthentication()

        viewModel.state.observe(this, Observer { state->
            when (state) {
                is ViewState.Failed -> {
                    this.toast("Error authenticating a guest user")
                    finish()
                }
                is ViewState.Success -> {
                    getNavController()?.navigate(UserGuestAuthenticationFragmentDirections.actionOpenMovieList())
                }
            }
        })
    }

    private fun getNavController() =
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