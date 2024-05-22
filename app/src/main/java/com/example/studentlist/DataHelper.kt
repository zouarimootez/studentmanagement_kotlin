package com.example.studentlist

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.lang.Exception
import kotlin.coroutines.coroutineContext



class DataHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,DATABASE_VERSION){
    companion object{
        private val DATABASE_NAME = "studentDB2.db"
        private val DATABASE_VERSION = 3
        val TABLE_NAME = "STUDENT"
        val KEY_NIM = "nim"
        val KEY_NAME = "name"
        val KEY_GENDER = "gender"
        val KEY_FAC = "faculty"
    }

    /*
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + "NIM" + " INTEGER PRIMARY KEY," +
                "NAME" + " TEXT," +
                "GENDER" + " TEXT,"+
                "FACULTY" + " TEXT)")
        db.execSQL(CREATE_TABLE)
    } */


    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE " +
                TABLE_NAME + " ("
                + "NIM INTEGER PRIMARY KEY, " +
                "NAME TEXT NOT NULL, " +
                "GENDER TEXT NOT NULL, " +
                "FACULTY TEXT NOT NULL)")
        db.execSQL(CREATE_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME)
        onCreate(db)
    }

    fun getAllStudent():ArrayList<Student>{
        var db = this.writableDatabase
        var studentList : ArrayList<Student> = ArrayList()
        val selectAll = "SELECT * FROM "+ TABLE_NAME
        val cursor = db.rawQuery(selectAll,null)
        if (cursor.moveToFirst()){
            do{
                val student = Student()
                student.nim = cursor.getString(0)
                student.name = cursor.getString(1)
                student.gender = cursor.getString(2)
                student.faculty = cursor.getString(3)
                studentList.add(student)
            } while (cursor.moveToNext())
        }
        return studentList
    }



    fun addStudent(student: Student):Boolean{




        var db = this.writableDatabase
        var values = ContentValues()
        values.put(KEY_NIM,student.nim)
        values.put(KEY_NAME,student.name)
        values.put(KEY_GENDER,student.gender)
        values.put(KEY_FAC,student.faculty)
        val success = db.insert(TABLE_NAME,null,values)
        db.close()
        if (success.toInt() == -1){
            Toast.makeText(context,"Error Occurred During Insert, Please Try Again",Toast.LENGTH_SHORT).show()
            return false
        }
        else{
            Toast.makeText(context,"Insert Success",Toast.LENGTH_SHORT).show()
            return true
        }
    }


    fun deleteStudent(student: Student){
        var db = this.writableDatabase
        val nim = arrayOf(student.nim.toString())
        db.delete(TABLE_NAME,KEY_NIM+" = ? ",nim)
        db.close()
    }



    // Method to get the database path
    fun getDatabasePath(): String {
        val databasePath = context.getDatabasePath(DATABASE_NAME).absolutePath
        return databasePath
    }

}