package com.example.termproject

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class DiaryDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "diaries.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "diary"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_TITLE TEXT, " +
                "$COLUMN_CONTENT TEXT, " +
                "$COLUMN_DATE INTEGER, " +
                "$COLUMN_IMAGE BLOB)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addDiary(title: String, content: String, date: Long, image: ByteArray?) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_CONTENT, content)
            put(COLUMN_DATE, date)
            put(COLUMN_IMAGE, image)
        }
        db.insert(TABLE_NAME, null, values)
    }

    fun deleteDiary(id: Int): Boolean {
        val db = writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(id.toString())) > 0
    }

    fun getAllDiaries(): List<Diary> {
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, "$COLUMN_DATE DESC")
        val diaries = mutableListOf<Diary>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(COLUMN_TITLE))
                val content = getString(getColumnIndexOrThrow(COLUMN_CONTENT))
                val date = getLong(getColumnIndexOrThrow(COLUMN_DATE))
                val image = getBlob(getColumnIndexOrThrow(COLUMN_IMAGE))
                diaries.add(Diary(id, title, content, date, image))
            }
        }
        cursor.close()
        return diaries
    }

    fun getDiariesByTitleOrDate(query: String): List<Diary> {
        val db = this.readableDatabase
        val cursor: Cursor

        // Check if the query string is a valid date
        val date: Long? = try {
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(query)?.time
        } catch (e: ParseException) {
            null
        }

        if (date != null) {
            // If the query is a valid date, search by date
            cursor = db.query(
                TABLE_NAME,
                arrayOf(COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT, COLUMN_DATE, COLUMN_IMAGE),
                "$COLUMN_DATE = ?",
                arrayOf(date.toString()),
                null,
                null,
                null
            )
        } else {
            // Otherwise, search by title
            cursor = db.query(
                TABLE_NAME,
                arrayOf(COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT, COLUMN_DATE, COLUMN_IMAGE),
                "$COLUMN_TITLE LIKE ?",
                arrayOf("%$query%"),
                null,
                null,
                null
            )
        }

        val diaries = mutableListOf<Diary>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val diaryTitle = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
            val date = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE))
            val image = cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_IMAGE))
            val diary = Diary(id, diaryTitle, content, date, image)
            diaries.add(diary)
        }
        cursor.close()
        db.close()
        return diaries
    }
}


data class Diary(val id: Int, val title: String, val content: String, val date: Long, val image: ByteArray?)
