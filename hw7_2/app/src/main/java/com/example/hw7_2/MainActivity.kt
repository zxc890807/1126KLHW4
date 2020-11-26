package com.example.hw7_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ed_height:EditText=findViewById(R.id.ed_height)
        val ed_weight:EditText = findViewById(R.id.ed_weight)
        val btn_boy:RadioButton = findViewById(R.id.btn_boy)
        val btn_girl:RadioButton=findViewById(R.id.radioButton2)
        val btn_calculate:Button=findViewById(R.id.btn_calculate)
        val tv_weight:TextView = findViewById(R.id.tv_weight)
        val tv_bmi:TextView = findViewById(R.id.tv_bmi)
        val tv_progress:TextView = findViewById(R.id.tv_progress)
        val ll_progress:LinearLayout = findViewById(R.id.ll_progress)
        val progressBar2:ProgressBar = findViewById(R.id.progressBar2)
        var progress = 0

        btn_calculate.setOnClickListener {
            when{
                //判斷使用者是否輸入身高與體重
                ed_height.length()<1 -> Toast.makeText(this,"請輸入身高",
                        Toast.LENGTH_SHORT).show()
                ed_weight.length()<1 -> Toast.makeText(this,"請輸入體重",
                        Toast.LENGTH_SHORT).show()
                else-> {
                    //初始化『標準體重』與『體脂肪』
                    tv_weight.text = "標準體重\n無"
                    tv_bmi.text = "體脂肪\n無"
                    //初始化進度條
                    progressBar2.progress = 0
                    tv_progress.text = "0%"
                    progress=0
                    //顯示進度條
                    ll_progress.visibility = View.VISIBLE

                    GlobalScope.launch(Dispatchers.Main) {
                        while (progress < 100)
                        {
                            delay(50)
                            progress++
                            progressBar2.progress = progress
                            tv_progress.text = String.format("%d%%", progress)
                        }
                        ll_progress.visibility = View.GONE
                        //讀取身高與體重
                        val cal_height = ed_height.text.toString().toDouble()
                        val cal_weight = ed_weight.text.toString().toDouble()
                        var cal_standweight: Double=0.0
                        var cal_bodyfat: Double=0.0
                        //判斷性別，跳用各自的計算公式
                        if (btn_boy.isChecked) {
                            cal_standweight = (cal_height - 80) * 0.7
                            cal_bodyfat = (cal_weight - 0.88 * cal_standweight) / cal_weight * 100
                        } else if (btn_girl.isChecked){
                            cal_standweight = (cal_height - 70) * 0.6
                            cal_bodyfat = (cal_weight - 0.82 * cal_standweight) / cal_weight * 100
                        }
                        //顯示計算結果
                        tv_weight.text = String.format("標準體重 \n%.2f", cal_standweight)
                        tv_bmi.text = String.format("體脂肪 \n%.2f", cal_bodyfat)
                    }

                    //隱藏鍵盤
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(it.windowToken,0)

                }
            }
        }
    }
}
