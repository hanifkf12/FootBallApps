package com.hanifkf.finalproject.Main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.hanifkf.finalproject.Favorites.FavoritesFragment
import com.hanifkf.finalproject.Matches.MatchesFragment
import com.hanifkf.finalproject.R
import com.hanifkf.finalproject.Teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(MatchesFragment(), savedInstanceState)

        navigation.setOnNavigationItemSelectedListener { item->
            when (item.itemId) {
                R.id.navigation_matches -> {
                   loadFragment(MatchesFragment(), savedInstanceState)

                }
                R.id.navigation_teams -> {
                    loadFragment(TeamsFragment(), savedInstanceState)

                }
                R.id.navigation_favorites -> {
                    loadFragment(FavoritesFragment(), savedInstanceState)

                }
            }
            false
        }
    }

    private fun loadFragment(fragment : Fragment, savedInstanceState: Bundle?){
        if(savedInstanceState==null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,fragment,fragment::class.java.simpleName)
                .commit()
        }
    }
}
