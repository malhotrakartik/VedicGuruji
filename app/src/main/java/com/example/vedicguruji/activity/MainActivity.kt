package com.example.vedicguruji.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.vedicguruji.R
import com.example.vedicguruji.fragment.HomeFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinateLayout: CoordinatorLayout
    lateinit var appToolbar: androidx.appcompat.widget.Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    var previousMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        drawerLayout = findViewById(R.id.drawerLayout)
        coordinateLayout = findViewById(R.id.coordinatorLayout)
        appToolbar = findViewById(R.id.appToolbar)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationView)

        setUpToolBar()

        openHome()

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            if (previousMenuItem != null) {
                previousMenuItem?.isChecked = false
            }
            it.isChecked = true
            this.previousMenuItem = it

            when (it.itemId) {
                R.id.home -> {
                    openHome()
                }


            }
            return@setNavigationItemSelectedListener true


        }

    }

    private fun setUpToolBar() {
        setSupportActionBar(appToolbar)
        supportActionBar?.title = "title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id=item.itemId

        if(id == android.R.id.home)
        {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    fun openHome(){
        val fragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()

        transaction
            .replace(R.id.frameLayout,fragment)

            .commit()
        drawerLayout.closeDrawers()

        supportActionBar?.title = "Vedic Guruji"
        navigationView.setCheckedItem(R.id.home)

    }
}
