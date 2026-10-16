package com.example.exp9

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "StudentDB.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_STUDENTS = "students"
        private const val COL_ID = "id"
        private const val COL_NAME = "name"
        private const val COL_USN = "usn"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_STUDENTS (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NAME TEXT NOT NULL,
                $COL_USN TEXT NOT NULL UNIQUE
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_STUDENTS")
        onCreate(db)
    }

    fun insertStudent(name: String, usn: String): Boolean {
        val values = ContentValues().apply {
            put(COL_NAME, name)
            put(COL_USN, usn)
        }

        val result = writableDatabase.insert(
            TABLE_STUDENTS,
            null,
            values
        )

        return result != -1L
    }

    fun getAllStudents(): String {
        val result = StringBuilder()
        val db = readableDatabase

        db.query(
            TABLE_STUDENTS,
            arrayOf(COL_ID, COL_NAME, COL_USN),
            null,
            null,
            null,
            null,
            "$COL_ID ASC"
        ).use { cursor ->
            while (cursor.moveToNext()) {
                val id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(COL_ID)
                )
                val name = cursor.getString(
                    cursor.getColumnIndexOrThrow(COL_NAME)
                )
                val usn = cursor.getString(
                    cursor.getColumnIndexOrThrow(COL_USN)
                )

                result.append("ID: $id\n")
                    .append("Name: $name\n")
                    .append("USN: $usn\n\n")
            }
        }

        return if (result.isEmpty()) {
            "No student records found."
        } else {
            result.toString().trim()
        }
    }

    fun clearStudents(): Int {
        return writableDatabase.delete(
            TABLE_STUDENTS,
            null,
            null
        )
    }
}
