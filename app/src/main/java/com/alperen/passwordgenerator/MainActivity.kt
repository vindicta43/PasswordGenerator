package com.alperen.passwordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // visual elements
        val cbSymbol = findViewById<CheckBox>(R.id.cbSymbol)
        val cbNumber = findViewById<CheckBox>(R.id.cbNumber)
        val cbBigLetter = findViewById<CheckBox>(R.id.cbBigLetter)
        val cbSmallLetter = findViewById<CheckBox>(R.id.cbSmallLetter)
        val cbTurkishLetter = findViewById<CheckBox>(R.id.cbTurkishLetter)
        val etLength = findViewById<EditText>(R.id.etLength)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnGenerate = findViewById<Button>(R.id.btnGenerate)
        btnGenerate.setOnClickListener {

            var passwordArray = mutableListOf<String>()
            var passwordString = ""

            // at least one checkbox must be checked
            if (cbSymbol.isChecked ||
                cbNumber.isChecked ||
                cbBigLetter.isChecked ||
                cbSmallLetter.isChecked ||
                cbTurkishLetter.isChecked) {

                if (cbSymbol.isChecked)
                    Consts.symbols.forEach {
                        passwordArray.add(it)
                    }

                if (cbNumber.isChecked)
                    Consts.numbers.forEach {
                        passwordArray.add(it)
                    }

                if (cbBigLetter.isChecked)
                    Consts.bigLetters.forEach {
                        passwordArray.add(it)
                    }

                if (cbSmallLetter.isChecked)
                    Consts.smallLetters.forEach {
                        passwordArray.add(it)
                    }

                if (cbTurkishLetter.isChecked)
                    Consts.turkishLetters.forEach {
                        passwordArray.add(it)
                    }

                // password length must be not null and bigger than zero
                if (!etLength.text.isNullOrEmpty() && !etLength.text.equals("0")) {
                    var length = etLength.text.toString().toInt()
                    for (i in 0 until length) {
                        var randomNum = (0 until passwordArray.size-1).random()
                        passwordString += passwordArray[randomNum]
                    }
                    etPassword.setText(passwordString)
                }
                else {
                    Toast.makeText(this, "Parola uzunluğu boş ya da 0 dan küçük", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "En az bir seçenek işaretli olmalı", Toast.LENGTH_SHORT).show()
            }
        }

        val btnClipboard = findViewById<Button>(R.id.btnClipboard)
        btnClipboard.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("text", etPassword.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Panoya kopyalandı", Toast.LENGTH_SHORT).show()
        }
    }
}