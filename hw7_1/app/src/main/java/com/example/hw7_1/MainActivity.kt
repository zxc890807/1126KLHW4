package com.example.hw7_1

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var rabprogress = 0
    private var turprogress:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var seekBar: SeekBar = findViewById(R.id.seekBar)
        var seekBar2:SeekBar = findViewById(R.id.seekBar2)
        var btn_start: Button = findViewById(R.id.btn_start)

        btn_start.setOnClickListener(View.OnClickListener { view ->
            btn_start.setEnabled(false)
            rabprogress = 0
            turprogress = 0
            seekBar.setProgress(0)
            seekBar2.setProgress(0)


            GlobalScope.launch(Dispatchers.Main) {
                while (turprogress < 100 && rabprogress < 100) {
                    delay(100)
                    rabprogress += (Math.random() * 3).toInt()
                    seekBar.progress = rabprogress
                }
                if (rabprogress >= 100 && turprogress < 100) {
                    Toast.makeText(this@MainActivity,"兔子勝利", Toast.LENGTH_SHORT).show()
                    //啟動Button
                    btn_start.isEnabled = true
                }
            }

            GlobalScope.launch(Dispatchers.Main) {
                while (turprogress < 100 && rabprogress < 100) {
                    delay(100)
                    turprogress += (Math.random() * 3).toInt()
                    seekBar2.progress = turprogress
                }
                if (turprogress >= 100 && rabprogress < 100) {
                    Toast.makeText(this@MainActivity, "烏龜勝利", Toast.LENGTH_SHORT).show()
                    //啟動Button
                    btn_start.isEnabled = true
                }
            }
        })
    }
}