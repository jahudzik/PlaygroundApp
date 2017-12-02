package com.jahu.playground.mvp

import android.app.Fragment
import com.jahu.playground.PlaygroundApplication

open class BaseFragment : Fragment() {

    protected fun getAppComponent() = (activity.applicationContext as PlaygroundApplication).getAppComponent()

    override fun onDestroy() {
        super.onDestroy()
        val refWatcher = PlaygroundApplication.getRefWatcher(activity)
        refWatcher.watch(this)
    }
}
