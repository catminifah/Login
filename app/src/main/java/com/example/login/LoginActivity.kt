package com.example.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    private lateinit var ID: EditText
    private lateinit var Password: EditText
    private lateinit var LoginButton:Button
    private lateinit var sqliteHelper:SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        //ปุ่มไปยังหน้าregister
        val buttonregister=findViewById<Button>(R.id.Register)
        buttonregister.setOnClickListener{
            val Intent= Intent(this,RegisterActivity::class.java)
            startActivity(Intent)
        }
        ID=findViewById<EditText>(R.id.Username)
        Password=findViewById<EditText>(R.id.Password)
        LoginButton=findViewById<Button>(R.id.Login)
        sqliteHelper= SQLiteHelper(this)
        LoginButton.setOnClickListener {
            var id=ID.text.toString()
            var pass=Password.text.toString()
            //เช็คว่าได้กรอกข้อมูลทั้ง2ช่องมั้ย
            if(TextUtils.isEmpty(id)|| TextUtils.isEmpty(pass)){
                Toast.makeText(this,"ADD id and password",Toast.LENGTH_SHORT).show()
            }else{
                //ส่งค่าข้อมูลที่กรอก
                val checkuser=sqliteHelper.checkID(id,pass)
                if (checkuser==true){
                    //Toast.makeText(this,"Login Seccessful",Toast.LENGTH_SHORT).show()
                    var getname=sqliteHelper.getName(id,pass)
                    //Toast.makeText(this,"name = $getname",Toast.LENGTH_SHORT).show()
                    var getemail=sqliteHelper.getEmail(id,pass)
                    //Toast.makeText(this,"email = $getemail",Toast.LENGTH_SHORT).show()
                    var getphone=sqliteHelper.getPhone(id,pass)
                    //Toast.makeText(this,"phone = $getphone",Toast.LENGTH_SHORT).show()
                    val intent= Intent(this,MainPageActivity::class.java)
                    intent.putExtra("Name",getname)
                    //intent.putExtra("BD",bd)
                    intent.putExtra("Email",getemail)
                    intent.putExtra("Phone",getphone)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Worng id and password",Toast.LENGTH_SHORT).show()
                }
            }
        }
        //button exit
        val buttonbtnExit=findViewById<Button>(R.id.btnExit)
        buttonbtnExit.setOnClickListener {
            //set builder
            val eBulider= AlertDialog.Builder(this)
            //set title
            eBulider.setTitle("Exit")
            eBulider.setMessage("Are you sure you want Exit?")
            eBulider.setPositiveButton("Yes"){
                    Dialog,which->
                finish()
            }
            eBulider.setNegativeButton("No"){
                    Dialog,which->
                Toast.makeText(this,"You Want Exit press again this Button", Toast.LENGTH_SHORT).show()
            }
            //set Dialog show
            val createBulid = eBulider.create()
            createBulid.show()
        }
    }
}