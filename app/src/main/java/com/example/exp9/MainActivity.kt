package com.example.exp9

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val btnSavePreference =
            findViewById<Button>(R.id.btnSavePreference)
        val btnLoadPreference =
            findViewById<Button>(R.id.btnLoadPreference)
        val tvPreferenceResult =
            findViewById<TextView>(R.id.tvPreferenceResult)

        val etStudentName =
            findViewById<EditText>(R.id.etStudentName)
        val etStudentUsn =
            findViewById<EditText>(R.id.etStudentUsn)
        val btnInsertStudent =
            findViewById<Button>(R.id.btnInsertStudent)
        val btnViewStudents =
            findViewById<Button>(R.id.btnViewStudents)
        val btnClearStudents =
            findViewById<Button>(R.id.btnClearStudents)
        val tvStudentResult =
            findViewById<TextView>(R.id.tvStudentResult)

        // SharedPreferences
        val preferences = getSharedPreferences(
            "UserPreferences",
            MODE_PRIVATE
        )

        btnSavePreference.setOnClickListener {
            val username = etUsername.text.toString().trim()

            if (username.isEmpty()) {
                etUsername.error = "Enter a username"
                return@setOnClickListener
            }

            preferences.edit()
                .putString("username", username)
                .apply()

            tvPreferenceResult.text = "Username saved successfully!"
            Toast.makeText(
                this,
                "Preference saved",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnLoadPreference.setOnClickListener {
            val username = preferences.getString("username", null)

            if (username == null) {
                tvPreferenceResult.text = "No saved username found."
            } else {
                tvPreferenceResult.text = "Saved username: $username"
                etUsername.setText(username)
            }
        }

        // SQLite
        btnInsertStudent.setOnClickListener {
            val name = etStudentName.text.toString().trim()
            val usn = etStudentUsn.text.toString().trim()

            if (name.isEmpty()) {
                etStudentName.error = "Enter student name"
                return@setOnClickListener
            }

            if (usn.isEmpty()) {
                etStudentUsn.error = "Enter student USN"
                return@setOnClickListener
            }

            try {
                val inserted = databaseHelper.insertStudent(name, usn)

                if (inserted) {
                    tvStudentResult.text =
                        "Student inserted successfully."
                    etStudentName.text.clear()
                    etStudentUsn.text.clear()
                } else {
                    tvStudentResult.text =
                        "Insert failed. Check for duplicate USN."
                }
            } catch (e: Exception) {
                tvStudentResult.text =
                    "Database error: ${e.message}"
            }
        }

        btnViewStudents.setOnClickListener {
            tvStudentResult.text = databaseHelper.getAllStudents()
        }

        btnClearStudents.setOnClickListener {
            val deleted = databaseHelper.clearStudents()

            tvStudentResult.text =
                if (deleted > 0) {
                    "Deleted $deleted student record(s)."
                } else {
                    "No student records to delete."
                }
        }
    }

    override fun onDestroy() {
        databaseHelper.close()
        super.onDestroy()
    }
}
