package com.abdullah.univeraproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.abdullah.univeraproject.di.retrofit.RetrofitServiceInstance
import com.abdullah.univeraproject.di.service.MessageService
import com.abdullah.univeraproject.fragments.AlbumFragment
import com.abdullah.univeraproject.fragments.RegisterFragment
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
        installSplashScreen().setKeepOnScreenCondition {
            checkPermission(android.Manifest.permission.CAMERA, 200)
            checkPermission(android.Manifest.permission.INTERNET, 200)

        }
        setContentView(R.layout.activity_main)

        loadFragment(AlbumFragment(), this)
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(AlbumFragment.newInstance(), this)
                    true
                }

                else -> {
                    loadFragment(RegisterFragment.newInstance(), this)
                    true
                }
            }
        }
        ContextCompat.startForegroundService(
            this,
            Intent(applicationContext, MessageService::class.java)
        )

    }

//    private val notificationManager by lazy {
//        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as MessageService
//    }


    private fun checkPermission(permission: String, requestCode: Int): Boolean {
        return if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
            true
        } else {
            Toast.makeText(this@MainActivity, "Permission already granted", Toast.LENGTH_SHORT)
                .show()
            false
        }
    }

}





