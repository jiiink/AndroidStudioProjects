package com.example.week_10_ex_2

import android.content.ContentValues
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
//import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.io.*

class MainActivity : AppCompatActivity() {
    val databaseName = "movie0"
    var database: SQLiteDatabase? = null
    val tableName = "movie_reserved"
    lateinit var savebutton: Button
    lateinit var viewbutton: Button
//    lateinit var name: String
//    lateinit var posterImageUri: String
//    lateinit var synopsis: String
//    lateinit var director: String
//    lateinit var reserved_time: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savebutton = findViewById(R.id.savebutton)
        viewbutton = findViewById(R.id.viewbutton)

        createDatabase()
        createTable()

        // TODO : 버튼 이벤트 처리
        savebutton.setOnClickListener {
            saveMovie()
        }

        viewbutton.setOnClickListener {
            loadMovie()
        }
    }

    fun saveMovie(){

        // TODO : record에 저장할 데이터를 위젯으로부터 가져와 데이터베이스에 추가
//        val title = findViewById<TextView>(R.id.title)
        val name = findViewById<TextView>(R.id.title).text.toString()
        val posterImageUri = savePosterToFile(R.drawable.thumbnail)
        val synopsis = findViewById<TextView>(R.id.synopsis).text.toString()
        val director = findViewById<TextView>(R.id.director).text.toString()
        val reserved_time = findViewById<TextView>(R.id.reserved_time).text.toString()
//        deleteDate()
        addData(name,posterImageUri.toString(),director,synopsis,reserved_time)
    }

    fun updateData() {
//        if (checkDatabase()) return

        // TODO : 데이터베이스 생성
        val values = ContentValues().apply {
            put("name", "mike")
            put("age", "24")
            put("mobile", "010-4000-0000")
        }
        val arr : Array<String> = arrayOf("john")
        database?.update(tableName, values, "name=?", arr)
//        output1.append("데이터 갱신\n")
    }

    fun deleteDate() {
        val sql = "select _id,name,poster_image,director,synopsis,reserved_time from ${tableName}"
        val cursor = database?.rawQuery(sql, null)
        if (cursor != null) {
            cursor.count
            val count = cursor.count
            cursor.close()

            val delete = "delete from ${tableName} where id = ${count}"
            database?.execSQL(delete)
//            output1.append("데이터 삭제\n")
        }
    }

    fun savePosterToFile(drawable:Int):Uri{
        val drawable = ContextCompat.getDrawable(applicationContext,drawable)
        val bitmap = (drawable as BitmapDrawable).bitmap

        val wrapper = ContextWrapper(applicationContext)
        val imagesFolder = wrapper.getDir("images", Context.MODE_PRIVATE)
        val file = File(imagesFolder,"thumbnail.jpg")

        try{
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
            stream.flush()
            stream.close()
        }catch (e:IOException){
            e.printStackTrace()
        }
        return Uri.parse(file.absolutePath)
    }

    fun addData(name:String, poster_image:String, director:String, synopsis:String, reserved_time:String){
        if(database==null){
            println("데이터베이스를 먼저 오픈하세요\n")
            return
        }
        // TODO : 데이터 추가 sql문 작성 및 실행, INSERT INTO sql문을 사용
        val sql = "insert into $tableName" +
                "(name,poster_image,director,synopsis,reserved_time)" +
                "values" +
                "('$name','$poster_image','$director','$synopsis','$reserved_time')"

        database?.execSQL(sql)
        println("데이터 추가함\n")
    }

    fun createDatabase(){
        // TODO : database 생성
        database = openOrCreateDatabase(databaseName,
            MODE_PRIVATE, null)
        println("데이터베이스 생성 또는 오픈함")
    }

    fun createTable(){
        val sql = "create table if not exists ${tableName}"+
                "(_id integer PRIMARY KEY autoincrement, "+
                "name text, "+
                "poster_image text, "+
                "director text, "+
                "synopsis text, "+
                "reserved_time text)"

        if(database == null){
            return
        }
        // TODO : sql 실행
        database?.execSQL(sql)
        println("데이터베이스 생성\n")
    }

    fun loadMovie(){
        val movies = queryData()

        // TODO : query로 조회한 데이터를 ReservedActivity로 전달
        val intent = Intent(this, ReservedActivity::class.java)
        intent.putExtra("movies", movies)
        startActivity(intent)
    }

    fun queryData():ArrayList<ReservedMovie>?{
        val sql = "select _id, name, poster_image, director, synopsis, reserved_time from ${tableName}"

        if(database == null){
            println("데이터베이스를 먼저 오픈하세요.\n")
            return null
        }
        val list= arrayListOf<ReservedMovie>()
        val cursor = database?.rawQuery(sql,null)
        if(cursor!=null){
            for (index in 0 until cursor.count){
                cursor.moveToNext()
                // TODO : 레코드에서 데이터 추출
                val _id = cursor.getInt(0)
                val name = cursor.getString(1)
                val poster_image = cursor.getString(2)
                val director = cursor.getString(3)
                val synopsis = cursor.getString(4)
                val reserved_time = cursor.getString(5)
                println("레코드# ${index}: $_id, $name, $poster_image, $director, $synopsis, $reserved_time\n")

                val movie = ReservedMovie(_id,name,poster_image,director,synopsis,reserved_time)
                list.add(movie)
            }
            if (cursor.count == 0) {
                return null
            }
            cursor.close()
        }
        println("데이터 조회함\n")
        return list
    }
}

//data class ReservedMovie(
//    val _id:Int?,
//    val name:String?,
//    val poster_image:String?,
//    val director: String?,
//    val synopsis: String?,
//    val reserved_time: String?
//):Serializable
