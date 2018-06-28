package cn.gavinliu.study.jetpack

import android.arch.lifecycle.GenericLifecycleObserver
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.util.Log

class MyGenericLifecycleObserver : GenericLifecycleObserver {

    override fun onStateChanged(source: LifecycleOwner?, event: Lifecycle.Event?) {
        Log.d("onStateChanged", event?.name)
    }
}