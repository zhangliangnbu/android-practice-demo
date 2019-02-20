package com.liang.androidskilldemo.ipc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_serialization_sample.*

class SerializationSampleActivity : AppCompatActivity() {

    private val serializer by lazy {
        Serializer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serialization_sample)

        btn_serialization.setOnClickListener { testSerialization() }
        btn_deserialization.setOnClickListener { testDeserializable() }
        btn_parcelable.setOnClickListener { testParcelable() }
        btn_deparcelable.setOnClickListener { testDeparcelable() }
    }

    private fun testSerialization() {
        serializer.saveSerializable(SerializableUser(1, "Tim", true))
    }

    private fun testDeserializable() {
        val user = serializer.getSerializable() as SerializableUser
        tv_deserialization_result.text = user.toString()
    }

    private fun testParcelable() {
        val parcelable =  ParcelableUser(11, "Lucy", false, ParcelableBook("001", "Wondering Earth", 100f))
        serializer.saveParcelable(parcelable)
//        SerializableSample2Activity.open(this, parcelable)
    }

    private fun testDeparcelable() {
        val user = serializer.getParcelable() as ParcelableUser
        tv_deparcelable_result.text = user.toString()
    }
}
