package com.liang.androidskilldemo.kotlinforandroiddevelopers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_kotlin_for_android_book.*
import org.jetbrains.anko.doAsync
import java.net.URL

class KotlinForAndroidBookActivity : AppCompatActivity() {

    data class PersonModel(val name: String, val age: Int)

    private var spName:String by DelegateExt.preference(this, "person_name", "default")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_for_android_book)

        // copy data
        val p1 = PersonModel("liang", 20)
        val p2 = p1.copy(age = 22)
        println(p2)

        // async and net
        doAsync {
            val data = URL("https://www.baidu.com").readText()
            val pre = data.subSequence(0, 100)
            runOnUiThread { tv_data_from_net.text = pre }
        }

        // by map
        val pm = mapOf("name" to "light", "age" to 24)
        val name by pm
        println(name)

        // delegate
        spName = p1.name
        println(spName)
        // 抓取本地文件可以得到
        // <?xml version='1.0' encoding='utf-8' standalone='yes' ?>
        //<map>
        //    <string name="person_name">liang</string>
        //</map>
    }
}
