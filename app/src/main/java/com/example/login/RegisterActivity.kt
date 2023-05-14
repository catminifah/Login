package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
//import com.example.myapplicationlogin.databinding.MainPageBinding
//import com.example.myapplicationlogin.databinding.RegisterPageBinding
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
import java.util.*

class RegisterActivity : AppCompatActivity() {
//    private lateinit var binding: RegisterPageBinding
//    private lateinit var database:DatabaseReference
    private lateinit var IDuser:EditText
    private lateinit var Passworduser:EditText
    private lateinit var confirmPassworduser:EditText
    private lateinit var Username:EditText
    //private lateinit var BirthDate:EditText
    private lateinit var dateEdt: EditText
    private lateinit var Email:EditText
    private lateinit var Phonenumber:EditText
    private lateinit var RegisterButton:Button
    private lateinit var sqliteHelper:SQLiteHelper
    private var boolean: Boolean=false
//      private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        // on below line creating a variable.
        //
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)
        // on below line we are initializing our variables.
        dateEdt = findViewById(R.id.idEdtDate)

        // on below line we are adding
        // click listener for our edit text.
        dateEdt.setOnClickListener {

            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    dateEdt.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }
        initView()
//        val id=IDuser.text.toString()
//        val pass=Passworduser.text.toString()
//        val name=Username.text.toString()
//        val bd=dateEdt.text.toString()
//        val email=Email.text.toString()
//        val phone=Phonenumber.text.toString()
        sqliteHelper= SQLiteHelper(this)
        RegisterButton.setOnClickListener {addUser()
        }
        //RegisterButton.setOnClickListener{getUser()}

    }
    private fun getUser() {
        val userList=sqliteHelper.getAllUser()
        Log.e("ppp","${userList.size}")
    }
    private fun addUser() {
        val id=IDuser.text.toString()
        val pass=Passworduser.text.toString()
        val checkPassworduser=confirmPassworduser.text.toString()
        val name=Username.text.toString()
        val bd=dateEdt.text.toString()
        val email=Email.text.toString()
        val phone=Phonenumber.text.toString()
        if (id.isEmpty()||pass.isEmpty()||checkPassworduser.isEmpty()||pass!=checkPassworduser||name.isEmpty()||bd.isEmpty()||email.isEmpty()||phone.isEmpty()||phone.length!=10){
            Toast.makeText(this,"field",Toast.LENGTH_SHORT).show()
        }else{
            val user=UserModel(Phone = phone, ID = id, Pass = pass, name = name, /*BD = bd,*/ Email = email)
            val status=sqliteHelper.insertUser(user)
            //check insert
            if (status>-1){
                Toast.makeText(this,"User Added...",Toast.LENGTH_SHORT).show()
                val intent= Intent(this,MainPageActivity::class.java)
                intent.putExtra("Name",Username.text.toString())
                //intent.putExtra("BD",bd)
                intent.putExtra("Email",Email.text.toString())
                intent.putExtra("Phone",Phonenumber.text.toString())
                startActivity(intent)
                boolean=true
                //clearEditText()
            }else{
                Toast.makeText(this,"field User Add",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun clearEditText(){
        IDuser.setText("")
        Passworduser.setText("")
        confirmPassworduser.setText("")
        Username.setText("")
        dateEdt.setText("")
        Email.setText("")
        Phonenumber.setText("")
        IDuser.requestFocus()
    }

    private fun initView(){
        IDuser=findViewById<EditText>(R.id.IDName)
        Passworduser=findViewById<EditText>(R.id.Password)
        confirmPassworduser=findViewById<EditText>(R.id.confirmPassword)
        Username=findViewById<EditText>(R.id.NameUser)
        dateEdt=findViewById<EditText>(R.id.idEdtDate)
        Email=findViewById<EditText>(R.id.Email)
        Phonenumber=findViewById<EditText>(R.id.Phone)
        RegisterButton=findViewById<Button>(R.id.RegisterButton)
    }
}