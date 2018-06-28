package cn.gavinliu.study.jetpack

import android.arch.core.util.Function
import android.arch.lifecycle.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.util.Log
import android.widget.Chronometer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userData: MutableLiveData<User>

    private lateinit var teamNameData: LiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chronometer = findViewById<Chronometer>(R.id.chronometer)

        val chronometerViewModel = ViewModelProviders.of(this).get(ChronometerViewModel::class.java)

        if (chronometerViewModel.startTime == null) {
            val startTime = SystemClock.elapsedRealtime()
            chronometerViewModel.startTime = startTime
            chronometer.base = startTime
        } else {
            chronometer.base = chronometerViewModel.startTime!!
        }

        chronometer.start()

//        lifecycle.addObserver(MyLifecycleObserver())
//        lifecycle.addObserver(MyLifecycleObserver())
//        lifecycle.addObserver(MyGenericLifecycleObserver())

        userData = MutableLiveData()

        userData.observe(this, Observer { user ->
            username?.text = user?.name
        })
        userData.value = User(0, "Liu")

        teamNameData = Transformations.map(userData) { "${it.name} - Team" }
        teamNameData.observe(this, Observer {
            team?.text = it
        })

        Transformations.switchMap(userData) {
            val data = MutableLiveData<String>()
            data.value = "${it.name} - Team2"
            data
        }.observe(this, Observer {
            team2?.text = it
        })


        val networkUser = MutableLiveData<User>()
        val localCacheUser = MutableLiveData<User>()

        val fullUserData = MediatorLiveData<User>()
        fullUserData.addSource(localCacheUser, { fullUserData.value = it })
        fullUserData.addSource(networkUser, { fullUserData.value = it })


        fullUserData.observe(this, Observer {
            username2?.text = it?.name
        })


        Thread(Runnable {
            localCacheUser.postValue(User(2, "User Local"))

            Thread.sleep(2000)
            userData.postValue(User(1, "Gavin"))

            Thread.sleep(2000)
            userData.postValue(User(1, "Gavin2"))

            networkUser.postValue(User(2, "User NetWork"))
        }).start()
    }

}
