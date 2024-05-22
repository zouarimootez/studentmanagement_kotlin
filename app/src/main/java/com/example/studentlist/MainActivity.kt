package com.example.studentlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.component_button_insert.*
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataHelper = DataHelper(this)
        // display all student
        val studentlist = dataHelper.getAllStudent()
        var studentAdapter = StudentAdapter(this@MainActivity,studentlist)



        // to remove student (long press )
        rv_student.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = studentAdapter
        }

        // this function refresh screen when we swipe up to down and update the studint list

        refresh_swipe.setOnRefreshListener {
            refresh_swipe.isRefreshing=false
            studentAdapter.getUpdate()
        }


        // insert student button (insert activity )

        b_insert_student.setOnClickListener{
            val intent = Intent(this,InsertActivity::class.java)
            startActivity(intent)
        }




        val databasePath = dataHelper.getDatabasePath()
        // Display the database path in a Toast
        //Toast.makeText(this, "Database Path: $databasePath", Toast.LENGTH_LONG).show()

        // Alternatively, log the database path
        Log.d("DatabasePath", "Database Path: $databasePath")

    }

}
