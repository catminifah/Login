package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainPageActivity : AppCompatActivity() {
    var txetname: TextView? =null
    var txetEmail: TextView? =null
    var txetPhone: TextView? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        txetname=findViewById<TextView>(R.id.textname)
        txetEmail=findViewById<TextView>(R.id.textemail)
        txetPhone=findViewById<TextView>(R.id.txetPhone)
        var intent=intent
        txetname!!.text="Full name : "+intent.getStringExtra("Name")
        txetEmail!!.text="Email : "+intent.getStringExtra("Email")
        txetPhone!!.text="Telephone number : "+intent.getStringExtra("Phone")
        //ปุ่มไปยังหน้าlogin
        val buttonlogin=findViewById<Button>(R.id.Backlogin)
        buttonlogin.setOnClickListener{
            val Intent= Intent(this,LoginActivity::class.java)
            startActivity(Intent)
        }
    }
}