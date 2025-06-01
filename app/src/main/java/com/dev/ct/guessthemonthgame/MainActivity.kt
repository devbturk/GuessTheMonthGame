package com.dev.ct.guessthemonthgame

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dev.ct.guessthemonthgame.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // View Binding ile XML öğelerine erişim
    private lateinit var binding: ActivityMainBinding

    // Kullanıcının tahmin hakkı (başlangıçta 3)
    private var remainingTries = 3

    // Rastgele seçilecek ay
    private var selectedMonth = ""

    // Ayların listesi
    private val months = arrayOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding ile layout'u bağla
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Uygulama başlarken rastgele bir ay seç
        selectedMonth = months[Random.nextInt(months.size)]
    }

    // "Guess" butonuna tıklanınca çalışır
    fun buttonGuess(view: View) {
        // Kullanıcının tahmini (küçük harfe çevirilip boşluklar kırpılır)
        val userGuess = binding.editTextText.text.toString().trim().lowercase()

        // Tahmin doğruysa
        if (selectedMonth.lowercase() == userGuess) {
            binding.textView.text = "Congrats! The month was $selectedMonth"
            binding.button.isEnabled = false // Buton devre dışı
        }
        // Tahmin yanlışsa
        else {
            remainingTries-- // Hak 1 azalır
            if (remainingTries > 0) {
                // Kalan hakla uyarı ver
                binding.textView.text = "Wrong! Try again. Remaining: $remainingTries"
            } else {
                // Hak bittiğinde doğru cevabı göster
                binding.textView.text = "Out of tries! The correct month was $selectedMonth"
                binding.button.isEnabled = false // Buton devre dışı
            }
        }
    }

    // "Restart" butonuna tıklanınca çalışır
    fun buttonRestart(view: View) {
        // Yeni rastgele ay seç
        selectedMonth = months[Random.nextInt(months.size)]

        // Tahmin hakkını sıfırla
        remainingTries = 3

        // Giriş alanını temizle
        binding.editTextText.text.clear()

        // Metni sıfırla
        binding.textView.text = "Guess the month!"

        // Butonu tekrar aktif et
        binding.button.isEnabled = true
    }
}