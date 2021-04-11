package com.example.navigationdrawertemplate.ui.conditionPage

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Process.myPid
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.navigationdrawertemplate.R
import com.example.navigationdrawertemplate.TherapyToolkitApplication
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.navigation_drawer_header.view.*

private const val TAG = "ConditionActivity"
class MainConditionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.condition_page)
        //TODO I make the assumption that the conditionId will be the index of its associated condition in TherapyToolkitApplication.conditions.
        //This will only be the case if the conditions were sorted alphabetically when the Conditions were instantiated...I think. Just documenting my assumptions.
        val bundle = intent.extras
        val originalConditionId: Int = bundle?.get("CONDITION") as Int
        val conditionId:Int
        conditionId=if(originalConditionId==-1) 0 else originalConditionId
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager,conditionId)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val tabs: TabLayout = findViewById(R.id.tabs)
        val conditionTitle = TherapyToolkitApplication.conditions[conditionId].title
        val actionBar = findViewById<Toolbar>(R.id.action_bar)



        viewPager.adapter = sectionsPagerAdapter

        tabs.setupWithViewPager(viewPager)
        tabs.tabTextColors = ColorStateList.valueOf(Color.WHITE)

        actionBar.title = conditionTitle
        actionBar.subtitle = conditionTitle
        actionBar.navigationContentDescription = conditionTitle
        actionBar.textAlignment = View.TEXT_ALIGNMENT_CENTER
        actionBar.setSubtitleTextColor(Color.WHITE)
        actionBar.setTitleTextColor(Color.WHITE)
        if(originalConditionId == -1){
            finish()
            finish() //not sure why this works, but it does.
//            Thread.sleep(2000)
            return
        }
//        Log.d(TAG, "conditionId: $conditionId")
//        actionBar.textView.text = conditionTitle
        /*val actionBarTextView = findViewById(R.id.app_compat_text_view)
      actionBarTextView.textView.text = conditionTitle*/
    }

    companion object {
        fun newIntent(packageContext: Context, conditionId: Int): Intent {
            return Intent(packageContext, MainConditionActivity::class.java).apply {
                putExtra("CONDITION", conditionId)
            }
        }
    }
}
