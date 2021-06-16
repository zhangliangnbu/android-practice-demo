package com.liang.androidskilldemo.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_service_manage.*


class ServiceManageActivity : AppCompatActivity() {

    /** Messenger for communicating with the service.  */
    var mService: Messenger? = null

    /** Flag indicating whether we have called bind on the service.  */
    var bound = false

    private var mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mService = Messenger(service)
            bound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mService = null
            bound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_manage)

        btnStartService.setOnClickListener { startService(Intent(applicationContext, MyService::class.java)) }
        btnStartIntentService.setOnClickListener { startService(Intent(applicationContext, MyIntentService::class.java)) }
    }

    override fun onStart() {
        super.onStart()
        bindService(Intent(this, MessengerService::class.java), mConnection,
                Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (bound) {
            unbindService(mConnection)
            bound = false
        }
    }

    fun sayHello(v: View?) {
        if (!bound) return
        // Create and send a message to the service, using a supported 'what' value
        val msg: Message = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0)
        try {
            mService!!.send(msg)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }
}