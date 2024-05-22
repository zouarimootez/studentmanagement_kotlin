package com.example.studentlist

import android.app.AlertDialog
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_insert.*
import kotlinx.android.synthetic.main.component_button_insert_conf.*



class InsertActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        val dataHelper = DataHelper(this)

        // dialog to confirm insert new student

        b_insert.setOnClickListener{
            val nim = et_nim.text.toString()
            val name = et_name.text.toString()
            val faculty = et_faculty.text.toString()
            val gender = findViewById<RadioButton>(rg_gender.checkedRadioButtonId)
            if (nim == null || name == null || gender == null || faculty == null) {
                Toast.makeText(this,"All Fields Are Required , Please Try Again",Toast.LENGTH_SHORT).show()
            }
            else{
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("confirmation")
                .setMessage("Are you sure to insert it?")
                .setCancelable(true)
                .setPositiveButton("No"){dialog,which->
                }
                .setNegativeButton("Yes"){dialog,which->

                    // insert data to database
                    dataHelper.addStudent(Student(nim,name,gender.text.toString(),faculty))

                    // empty textview content
                    et_name.setText("")
                    et_nim.setText("")
                    et_faculty.setText("")
                    rg_gender.clearCheck()
                }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }}
    }
}