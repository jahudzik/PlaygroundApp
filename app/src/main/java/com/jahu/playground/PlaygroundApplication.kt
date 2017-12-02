package com.jahu.playground

import android.app.Application
import android.content.Context
import android.os.StrictMode
import com.facebook.stetho.Stetho
import com.jahu.playground.di.AppComponent
import com.jahu.playground.di.AppModule
import com.jahu.playground.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import timber.log.Timber

class PlaygroundApplication : Application() {

    companion object {
        fun getRefWatcher(context: Context) = (context.applicationContext as PlaygroundApplication).refWatcher
    }

    private lateinit var refWatcher: RefWatcher
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        refWatcher = LeakCanary.install(this)
        initDagger()
        setupTimber()
        setupStetho()
        setupStrictMode()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun setupStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyFlashScreen()
                    .penaltyLog()
                    .build())
            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build())
        }
    }

    fun getAppComponent() = appComponent

}
