package com.abdullah.univeraproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.abdullah.univeraproject.di.retrofit.RetrofitServiceInstance
import com.abdullah.univeraproject.di.service.MessageService
import com.abdullah.univeraproject.fragments.AlbumFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView

    @set:Inject
    internal var mSharedPreferences: SharedPreferences? = null

    @set:Inject
    internal lateinit var retrofitServiceInstance: RetrofitServiceInstance


    companion object {


        private var activeFragment: Fragment? = null
        fun loadFragment(fragment: Fragment, mainActivity: FragmentActivity) {
            if (activeFragment != null && fragment.javaClass == activeFragment!!.javaClass) {

                return
            }
            activeFragment = fragment
            val transaction = mainActivity.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        loadFragment(AlbumFragment(), this)
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            loadFragment(AlbumFragment.newInstance(), this)
            true
        }
        ContextCompat.startForegroundService(
            this,
            Intent(applicationContext, MessageService::class.java)
        )

    }


}





