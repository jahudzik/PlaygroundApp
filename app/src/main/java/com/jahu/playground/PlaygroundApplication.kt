package com.jahu.playground

import android.app.Application
import android.content.Context
import android.os.StrictMode
import com.facebook.stetho.Stetho
import com.jahu.playground.di.AppComponent
import com.jahu.playground.di.AppModule
import com.jahu.playground.di.DaggerAppComponent
import com.jahu.playground.trivia.TriviaService
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

private const val TRIVIA_API_BASE_URL = "https://opentdb.com"

class PlaygroundApplication : Application() {

    companion object {
        fun getRefWatcher(context: Context) = (context.applicationContext as PlaygroundApplication).refWatcher
    }

    private lateinit var triviaService: TriviaService
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
        setupTriviaService()
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

    private fun setupTriviaService() {
        val retrofit = Retrofit.Builder()
                .baseUrl(TRIVIA_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        triviaService = retrofit.create(TriviaService::class.java)
    }

    fun getAppComponent() = appComponent

    fun getTriviaService(): TriviaService = triviaService

}
