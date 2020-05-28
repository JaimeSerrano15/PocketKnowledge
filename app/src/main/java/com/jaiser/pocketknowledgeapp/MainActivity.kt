package com.jaiser.pocketknowledgeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.MaterialToolbar
import com.jaiser.pocketknowledgeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        val NavController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(NavController, drawerLayout)
    }

    fun setUp() {
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout
        //toolbar = binding.toolbar

        val navController = this.findNavController(R.id.myNavHostFragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            navigationSetUp(destination.id)
        }

        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }

    private fun navigationSetUp(id: Int) {
        when (id) {
            R.id.loginFragment, R.id.registerFragment -> {
                supportActionBar?.hide()
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
            else -> {
                supportActionBar?.show()
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
        }
    }
}
