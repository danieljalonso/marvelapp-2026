package edu.iesam.marvelapp.core.cache

import android.content.Context
import android.content.SharedPreferences
import edu.iesam.marvelapp.core.time.AppTime
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AppCacheTTLTest {

    private val context: Context = mockk()
    private val sharedPref: SharedPreferences = mockk()
    private val editor: SharedPreferences.Editor = mockk(relaxed = true)
    private val appTime: AppTime = mockk()

    private lateinit var appCache: AppCache

    @Before
    fun setUp() {
        // Ojo
    }

    @Test
    fun getSharedPref() {
    }

    @Test
    fun updateTime() {
    }

    @Test
    fun isExpired() {
    }

}