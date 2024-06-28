package com.example.week_10_ex_1

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

//import kotlinx.android.synthetic.main.activity_main.* // id를 바로 사용할 수 있도록 import


class MainActivity : AppCompatActivity() {
    var database: SQLiteDatabase? = null
    val tableName = "student"
    lateinit var output1: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        output1 = findViewById(R.id.output1)

        val doButton1: Button = findViewById<Button>(R.id.doButton1)
        val doButton2: Button = findViewById<Button>(R.id.doButton2)
        val doButton3: Button = findViewById<Button>(R.id.doButton3)
        val doButton4: Button = findViewById<Button>(R.id.doButton4)
        val doButton5: Button = findViewById<Button>(R.id.doButton5)
        val doButton6: Button = findViewById<Button>(R.id.doButton6)

        doButton1.setOnClickListener{
            createDatabase()
        }
        // TODO : 버튼 이벤트 처리
        doButton2.setOnClickListener{
            createTable()
        }
        doButton3.setOnClickListener{
            addData()
        }
        doButton4.setOnClickListener{
            updateData()
        }
        doButton5.setOnClickListener{
            queryData()
        }
        doButton6.setOnClickListener{
            deleteDate()
        }
    }

    //데이터 베이스 생성
    fun createDatabase(){
        // TODO : database 생성
        database = openOrCreateDatabase("people",
            MODE_PRIVATE, null)
        output1.append("데이터베이스 생성 또는 오픈함\n")
    }

    fun checkDatabase(): Boolean {
        if (database == null) {
            output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return true
        }
        return false
    }

    //테이블 생성
    fun createTable(){
        if (checkDatabase()) return
        // 테이블 존재 시, 테이블 삭제
        // 테이블 생성 sql 문 구성
        // sql 문 실행
        // GUI 의 Textview 내용 추가하기

        val createTable = "create table if not exists ${tableName}" +
                "( id integer PRIMARY KEY autoincrement, " +
                "name text, " +
                "age integer, " +
                "mobile text)"
        database?.execSQL(createTable)
        output1.append("테이블 생성함\n")
    }

    //데이터 추가
    fun addData(){
        if (checkDatabase()) return
        // TODO : 데이터 추가
        // sql insert 문 작성
        val insert = "insert into ${tableName}" +
                "(name,age,mobile)" +
                "values" +
                "('john','20','010-0000-0000')"
        // sql 문 실행
        database?.execSQL(insert)
        output1.append("데이터 추가함\n")
    }

    //데이터 조회
    fun queryData(){
        if (checkDatabase()) return
        // TODO : 데이터 조회
        // sql select 문 구성
        val select = "select id,name,age,mobile " +
                "from ${tableName}"
        // sql query 수행
        val cursor = database?.rawQuery(select, null)
        // 수행 결과가 존재하는지 유무 판단
        if (cursor != null) {
            for (index in 0 until cursor.count) {
                cursor.moveToNext()
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val age = cursor.getInt(2)
                val mobile = cursor.getString(3)
                output1.append("Record#${index} : " +
                    "$id,$name,$age,$mobile\n ")
            }
            cursor.close()
        }
        // for 구문을 이용해 moveToNext 메서드 호출을 레코드 숫자만큼 반복

        output1.append("데이터 조회 결과\n")
    }

    fun updateData() {
        if (checkDatabase()) return

        // TODO : 데이터베이스 생성
        val values = ContentValues().apply {
            put("name", "mike")
            put("age", "24")
            put("mobile", "010-4000-0000")
        }
        val arr : Array<String> = arrayOf("john")
        database?.update(tableName, values, "name=?", arr)
        output1.append("데이터 갱신\n")
    }

    fun deleteDate() {
        if (checkDatabase()) return

        val sql = "select id,name,age,mobile from ${tableName}"
        val cursor = database?.rawQuery(sql, null)
        if (cursor != null) {
            cursor.count
            val count = cursor.count
            cursor.close()

            val delete = "delete from ${tableName} where id = ${count}"
            database?.execSQL(delete)
            output1.append("데이터 삭제\n")
        }
    }
}