package com.example.week_07_ex_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import java.io.File
import android.widget.Toast
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.widget.Button
import com.example.week_07_ex_1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var downloadId: Long = -1L
    private lateinit var downloadManager: DownloadManager

    //브로드캐스트 리시버 선언
    private val onDownloadComplete = object : BroadcastReceiver() {
        //리시버 onReceive 구현
        override fun onReceive(context: Context, intent: Intent) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.action)) {
                if (downloadId == id) {
                    val query: DownloadManager.Query = DownloadManager.Query()
                    query.setFilterById(id)
                    val cursor = downloadManager.query(query)
                    if (!cursor.moveToFirst()) {
                        return
                    }

                    val columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                    val status = cursor.getInt(columnIndex)
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        Toast.makeText(context, "Download succeeded", Toast.LENGTH_SHORT).show()
                    } else if (status == DownloadManager.STATUS_FAILED) {
                        Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent.action)) {
                Toast.makeText(context, "Notification clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //다운로드 매니저 객체
        downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        //인텐트 필터 선언
        //todo//
        val intentFilter = IntentFilter().apply {
            addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
            addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED)
        }

        //리시버와 인텐트 필터 연결
        /////////todo///////////
        registerReceiver(onDownloadComplete, intentFilter, RECEIVER_NOT_EXPORTED)



        binding.downloadBtn.setOnClickListener {
            val file = File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS + "/"), "newletter.pdf")

            //다운받을 Url 주소
            val downloadUrl = "https://cse.pusan.ac.kr/sites/cse/download/201912_cse_newsletter_vol_29.pdf"

            // 다운로드 매니저 Request 설정
            //todo//
            val request = DownloadManager.Request(Uri.parse(downloadUrl)).apply {
                setTitle("Newsletter Download")
                setDescription("Downloading...")
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                setDestinationUri(Uri.fromFile(file))
            }

            // 설정된 Request 전송
            //todo//
            downloadId = downloadManager.enqueue(request)
        }

        binding.cancelBtn.setOnClickListener {
//            "/////////todo//////////"
            downloadManager.remove(downloadId)
        }

    }

    //리시버와 인텐트 필터 연결 해제
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(onDownloadComplete)
    }
}