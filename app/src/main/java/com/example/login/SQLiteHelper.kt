package com.example.login
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class SQLiteHelper(context:Context):SQLiteOpenHelper(context,DATABASE_USER,null,DATABASE_VERSION) {
    companion object{
        private const val DATABASE_VERSION=1
        private const val DATABASE_USER="user.db"
        private const val TBL_USER="tbl_user"
        private const val ID="id"
        private const val Password="pass"
        private const val Name="name"
        private const val BirthDate="bd"
        private const val Email="email"
        private const val Phone="phone"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        //val createTBLUSER=("CREATE TABLE "+ TBL_USER +"(" + Phone +" TEXT PRIMARY KEY,"+ ID +" TEXT,"+ Password +" TEXT,"+ Name +" TEXT," + BirthDate +" TEXT,"+ Email +" TEXT"+")")
        val createTBLUSER=("CREATE TABLE "+ TBL_USER +"(" + Phone +" TEXT PRIMARY KEY,"+ ID +" TEXT,"+ Password +" TEXT,"+ Name +" TEXT," + Email +" TEXT"+")")
        db?.execSQL(createTBLUSER)
    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_USER")
        onCreate((db))
    }
    fun insertUser(user: UserModel): Long {
        val db=this.writableDatabase
        val contentValues=ContentValues()
        contentValues.put(Phone,user.Phone)
        contentValues.put(ID,user.ID)
        contentValues.put(Password,user.Pass)
        contentValues.put(Name,user.name)
        //contentValues.put(BirthDate,user.BD)
        contentValues.put(Email,user.Email)
        val success =db.insert(TBL_USER,null,contentValues)
        db.close()
        return success
    }
    @SuppressLint("Range")
    fun getAllUser():ArrayList<UserModel>{
        val userList : ArrayList<UserModel> = ArrayList()
        val selectQurey="SELECT * FROM $TBL_USER"
        val db=this.readableDatabase
        val  cursor:Cursor?
        try {
            cursor=db.rawQuery(selectQurey,null)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            db.execSQL(selectQurey)
            return ArrayList()
        }
        var id:String
        var pass:String
        var name:String
        //var bd:String
        var email:String
        var phone:String
        if (cursor.moveToFirst()) {
            do {
                phone = cursor.getString(cursor.getColumnIndex("phone"))
                id = cursor.getString(cursor.getColumnIndex("id"))
                pass = cursor.getString(cursor.getColumnIndex("pass"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                //bd = cursor.getString(cursor.getColumnIndex("bd"))
                email = cursor.getString(cursor.getColumnIndex("email"))
                var us = UserModel(Phone = phone, Pass = pass, name = name,/* BD = bd,*/ Email = email, ID = id)
                userList.add(us)
            } while (cursor.moveToNext())
        }
        return userList
    }
        //เช็คไอดีว่ามีอยู่จริงมั้ย?
    fun checkID(id:String,pass:String):Boolean{
        val db=this.readableDatabase
        val selectQurey="select * from $TBL_USER where $ID = '$id' and $Password = '$pass'"
        val  cursor:Cursor?
        cursor=db.rawQuery(selectQurey,null)
        if (cursor.count<=0){
            cursor.close()
            return false
        }else{
            cursor.close()
            return true
        }
    }
    //ส่งค่าstring ฟิวชั่นfunเช็คไอดีกะgetalluser
    //ลาก่อยนะฟิวชั่นนนนนนนนนT-T
    @SuppressLint("Range")
    fun getName(id:String,pass:String): String? {
        val db=this.readableDatabase
        val selectQurey="select * from $TBL_USER where $ID = '$id' and $Password = '$pass'"
        //SELECT * FROM $TBL_USER WHERE $ID = $id AND WHERE $Password = $pass//ก่อนemuพังอันนี้ใช้ได้...
        val  cursor:Cursor?
        cursor=db.rawQuery(selectQurey,null)
        var name:String=""
        if (cursor.count<=0){
            cursor.close()
            return ""
        }
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndex("name"))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return name
    }
    //แสดง name ได้แบ้วววววววววววววววววววววววว มาแก้2ตัวเจ้ากรรมกันต่อ
    @SuppressLint("Range")
    fun getEmail(id:String,pass:String): String? {
        val db=this.readableDatabase
        val selectQurey="select * from $TBL_USER where $ID = '$id' and $Password = '$pass'"
        val  cursor:Cursor?
        cursor=db.rawQuery(selectQurey,null)
        var email:String=""
        if (cursor.count<=0){
            cursor.close()
            return ""
        }
        if (cursor.moveToFirst()) {
            do {
                email = cursor.getString(cursor.getColumnIndex("email"))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return email
    }
    @SuppressLint("Range")
    fun getPhone(id:String,pass:String): String? {
        val db=this.readableDatabase
        val selectQurey="select * from $TBL_USER where $ID = '$id' and $Password = '$pass'"
        val  cursor:Cursor?
        cursor=db.rawQuery(selectQurey,null)
        var phone:String=""
        if (cursor.count<=0){
            cursor.close()
            return ""
        }
        if (cursor.moveToFirst()) {
            do {
                phone = cursor.getString(cursor.getColumnIndex("phone"))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return phone
    }
    //ทำผิดเด้อออ
//    @SuppressLint("Range")
//    fun getINFOUser(id: EditText, password: EditText):ArrayList<UserModel>{
//        val userList : ArrayList<UserModel> = ArrayList()
//        val selectQurey="SELECT $Name,$Email,$Phone FROM $TBL_USER WHERE $ID LIKE $id AND WHERE $Password LIKE $password"
//        val db=this.readableDatabase
//        val  cursar:Cursor?
//        try {
//            cursar=db.rawQuery(selectQurey,null)
//        }catch (e:java.lang.Exception){
//            e.printStackTrace()
//            db.execSQL(selectQurey)
//            return ArrayList()
//        }
//        var name:String
//        //var bd:String
//        var email:String
//        var phone:String
//        if (cursar.moveToFirst()) {
//            do {
//                phone = cursar.getString(cursar.getColumnIndex("phone"))
//                name = cursar.getString(cursar.getColumnIndex("name"))
//                //bd = cursar.getString(cursar.getColumnIndex("bd"))
//                email = cursar.getString(cursar.getColumnIndex("email"))
//                var us = UserModel(Phone = phone, name = name,/* BD = bd,*/ Email = email)
//                userList.add(us)
//            } while (cursar.moveToNext())
//        }
//        return userList
//    }
}