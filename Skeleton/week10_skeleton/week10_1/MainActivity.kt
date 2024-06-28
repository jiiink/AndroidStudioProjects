package com.example.week10_1

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.* // id를 바로 사용할 수 있도록 import


class MainActivity : AppCompatActivity() {
    var database: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doButton1.setOnClickListener{
            createDatabase()
        }
        // TODO : 버튼 이벤트 처리
    }

    //데이터 베이스 생성
    fun createDatabase(){
        // TODO : database 생성
        output1.append("데이터베이스 생성 또는 오픈함\n")
    }

    //테이블 생성
    fun createTable(){
        if(database == null){
            output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }
        // TODO : table 생성
        output1.append("테이블 생성함\n")
    }

    //데이터 추가
    fun addData(){
        if (database == null){
            output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }
        // TODO : 데이터 추가
        output1.append("데이터 추가함\n")
    }

    //데이터 조회
    fun queryData(){
        if(database == null){
            output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }
        // TODO : 데이터 조회
        output1.append("데이터 조회 결과\n")
    }
}