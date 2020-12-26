package com.srk.nytimesmostpopular.ui

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.srk.nytimesmostpopular.BuildConfig
import com.srk.nytimesmostpopular.R
import com.srk.nytimesmostpopular.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel

        setupNavigation()

    }

    private fun setupNavigation() {

        setSupportActionBar(binding.appBarMain.toolbar)

        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
        ), binding.drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}