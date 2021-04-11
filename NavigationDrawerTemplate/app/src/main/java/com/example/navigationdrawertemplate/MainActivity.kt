package com.example.navigationdrawertemplate

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navigationdrawertemplate.ui.conditionPage.MainConditionActivity
import com.example.navigationdrawertemplate.ui.conditionPage.PlaceholderFragment
import com.example.navigationdrawertemplate.ui.diagnosisPredictor.DiagnosisPredictorFragment
import com.example.navigationdrawertemplate.ui.homePageConditions.ConditionsListFragment
import com.example.navigationdrawertemplate.ui.homePageConditions.FilterDialog
import com.example.navigationdrawertemplate.ui.homePageConditions.callbacks
import com.example.navigationdrawertemplate.ui.homePageConditions.textWhichUserSearchedFor
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), ConditionsListFragment.Callbacks, DiagnosisPredictorFragment.Callbacks {

    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_drawer)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_homepage_conditions,
//                R.id.nav_diagnosis_predictor,
//                R.id.nav_differential_tests
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        val search: MenuItem = menu.findItem(R.id.search)
        val searchView: SearchView = search.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(search: String): Boolean {
//                ConditionsListFragment.textWhichUserSearched = query
                textWhichUserSearchedFor = search
                Log.d(TAG,"onQueryTextSubmit: $search")
                callbacks?.onConditionSelected(-1)

                return true
            }

            override fun onQueryTextChange(search: String): Boolean {
//                adapter?.filter(newText)
                return true
            }
        })
        val filterButton: MenuItem = menu.findItem(R.id.filter_menu_option)
        filterButton.setOnMenuItemClickListener {
            FilterDialog().show(supportFragmentManager, "Filter")
            true
        }
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
     * starts individual condition page
     */
    override fun onConditionSelected(conditionId: Int) {

/*
        setContentView(R.layout.activity_main_tabs_template)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.conditions_recycler_view, fragment)
            .commit()
*/

        val intent = MainConditionActivity.newIntent(this, conditionId)
        startActivity(intent)
    }
}