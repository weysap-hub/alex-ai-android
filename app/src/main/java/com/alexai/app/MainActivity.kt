package com.alexai.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : Activity() {
    private lateinit var messages: LinearLayout
    private lateinit var input: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.rgb(18,18,20)
        window.navigationBarColor = Color.rgb(18,18,20)
        window.decorView.systemUiVisibility = 0
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.rgb(18,18,20))
            setPadding(18, 18, 18, 12)
        }
        val header = TextView(this).apply {
            text = "ALEX AI"
            textSize = 25f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(8, 16, 8, 20)
        }
        root.addView(header)
        val scroll = ScrollView(this)
        messages = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(4, 8, 4, 8)
        }
        scroll.addView(messages)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        addMessage("Привет! Я ALEX AI. Интерфейс готов. Для работы с облачной ИИ-моделью потребуется подключить сервер или API-ключ.", false)
        val bar = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER_VERTICAL }
        input = EditText(this).apply {
            hint = "Напиши сообщение…"
            setHintTextColor(Color.LTGRAY)
            setTextColor(Color.WHITE)
            textSize = 16f
            setSingleLine(false)
            maxLines = 4
            setPadding(14, 10, 14, 10)
            background = roundedBackground(Color.rgb(40,40,44))
        }
        bar.addView(input, LinearLayout.LayoutParams(0, -2, 1f))
        val send = Button(this).apply {
            text = "➤"
            setTextColor(Color.WHITE)
            background = roundedBackground(Color.rgb(45,105,220))
            setOnClickListener { sendMessage() }
        }
        bar.addView(send, LinearLayout.LayoutParams(58, 52))
        root.addView(bar)
        setContentView(root)
    }
    private fun roundedBackground(color: Int): android.graphics.drawable.Drawable {
        return android.graphics.drawable.GradientDrawable().apply {
            setColor(color); cornerRadius = 28f
        }
    }
    private fun addMessage(text: String, user: Boolean) {
        val bubble = TextView(this).apply {
            this.text = text
            textSize = 16f
            setTextColor(Color.WHITE)
            setPadding(14, 11, 14, 11)
            background = roundedBackground(if (user) Color.rgb(48,83,145) else Color.rgb(43,43,48))
        }
        val row = LinearLayout(this).apply {
            gravity = if (user) Gravity.END else Gravity.START
            setPadding(0, 6, 0, 6)
            addView(bubble, LinearLayout.LayoutParams(-2, -2).apply { width = (resources.displayMetrics.widthPixels * .82f).toInt() })
        }
        messages.addView(row)
    }
    private fun sendMessage() {
        val text = input.text.toString().trim()
        if (text.isEmpty()) return
        addMessage(text, true)
        input.setText("")
        addMessage("Сообщение принято. Для полноценного ответа ИИ нужно подключить облачный API. Сейчас это демонстрационная сборка интерфейса.", false)
    }
}
