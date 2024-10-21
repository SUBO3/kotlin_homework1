package com.example.kotlin_homework1

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var tvText: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnMora: Button
    private lateinit var tvName: TextView
    private lateinit var tvWinner: TextView
    private lateinit var tvMyMora: TextView
    private lateinit var tvTargetMora: TextView
    private lateinit var showtext: TextView

    var timess = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edName = findViewById(R.id.edName)
        tvText = findViewById(R.id.tvText)
        radioGroup = findViewById(R.id.radioGroup)
        btnMora = findViewById(R.id.btnMora)
        tvName = findViewById(R.id.tvName)
        tvWinner = findViewById(R.id.tvWinner)
        tvMyMora = findViewById(R.id.tvMyMora)
        tvTargetMora = findViewById(R.id.tvTargetMora)
        showtext = findViewById(R.id.showtext)

        btnMora.setOnClickListener {
            if (edName.text.isEmpty()) {
                tvText.text = "請輸入玩家姓名"
                return@setOnClickListener
            }


            val playerName = edName.text.toString() // 取得玩家姓名

            val myMora = getmymora()
            val targetMora = (0..2).random() // 隨機生成電腦的出拳

            show1(playerName, myMora, targetMora)
            show2(playerName, myMora, targetMora)
        }
    }

    private fun getmymora(): Int {
        val myMora: Int
        if (radioGroup.checkedRadioButtonId == R.id.btnScissor) {
            myMora = 0
        } else if (radioGroup.checkedRadioButtonId == R.id.btnStone) {
            myMora = 1
        } else {
            myMora = 2
        }
        return myMora
    }

    private fun show1(playerName: String, myMora: Int, targetMora: Int) {
        tvName.text = "名字\n$playerName"
        tvMyMora.text = "我方出拳\n${getMoraString(myMora)}"
        tvTargetMora.text = "電腦出拳\n${getMoraString(targetMora)}"
    }

    private fun show2(playerName: String, myMora: Int, targetMora: Int) {
        if (myMora == targetMora) {
            tvWinner.text = "勝利者\n平手"
            tvText.text = "平局，請再試一次！"
            timess++
            showtext.text = "猜拳次數計數器:$timess"
        } else if ((myMora == 0 && targetMora == 2) ||
            (myMora == 1 && targetMora == 0) ||
            (myMora == 2 && targetMora == 1)) {
            tvWinner.text = "勝利者\n$playerName"
            tvText.text = "恭喜你獲勝了！！！"
            timess++
            showtext.text = "猜拳次數計數器:$timess"
        } else {
            tvWinner.text = "勝利者\n電腦"
            tvText.text = "可惜，電腦獲勝了！"
            timess++
            showtext.text = "猜拳次數計數器:$timess"
        }

    }

    private fun getMoraString(mora: Int): String {
        return if (mora == 0) {
            "剪刀"
        } else if (mora == 1) {
            "石頭"
        } else {
            "布"
        }
    }
}
