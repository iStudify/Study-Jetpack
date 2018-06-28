package cn.gavinliu.study.jetpack

import android.arch.lifecycle.ViewModel
import android.util.Log

class ChronometerViewModel : ViewModel() {

    var startTime: Long? = null

    override fun onCleared() {
        super.onCleared()
        Log.d("ChronometerViewModel", "onCleared")
    }

}