package cn.gavinliu.study.jetpack

import android.arch.lifecycle.MutableLiveData

class CustomLiveData : MutableLiveData<Int>() {

    override fun onActive() {
        super.onActive()
        registerReceiver()
    }

    override fun onInactive() {
        super.onInactive()
        unregisterReceiver()
    }

    private fun registerReceiver() {

    }

    private fun unregisterReceiver() {

    }

}