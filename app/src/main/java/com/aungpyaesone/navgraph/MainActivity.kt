package com.aungpyaesone.navgraph

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(toolbar)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setUpBottomNav(navController)

        appConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment,R.id.deepLinkFragment),
            drawer_layout
        )
      //  appConfiguration= AppBarConfiguration(navController.graph)

        setupActionBar(navController,appConfiguration)
        setupNavigationMenu(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater  = menuInflater
        inflater.inflate(R.menu.overflow_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.host_fragment))
                || super.onOptionsItemSelected(item)
    }


    // steup bottom navigation view with navController
    private fun setUpBottomNav(navController:NavController){
        bottom_nav_view?.setupWithNavController(navController)
    }

    // setup navigation view with navController
    private fun setupNavigationMenu(navController: NavController){
        nav_view?.setupWithNavController(navController)
    }


    // setup action bar with navController
    private fun setupActionBar(navController: NavController,appBarConfiguration: AppBarConfiguration){
        // calling NavigationUI.setupActionBarWithNavController()
        setupActionBarWithNavController(navController,appBarConfiguration)
    }


    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.host_fragment).navigateUp(appConfiguration)
    }



}