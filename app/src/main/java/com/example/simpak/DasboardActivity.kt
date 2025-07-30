package com.example.simpak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

// ✅ Import Activity dengan benar
import com.example.simpak.ChatActivity
import com.example.simpak.PilihKelasActivity
import com.example.simpak.ProposalActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Tombol "Gaskeun!" untuk ajukan proposal
        val btnAjukan = findViewById<Button>(R.id.btnAjukan)
        btnAjukan.setOnClickListener {
            val intent = Intent(this, ProposalActivity::class.java)
            startActivity(intent)
        }

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_home

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true

                R.id.nav_proposal -> {
                    startActivity(Intent(this, ProposalActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                R.id.nav_search -> {
                    startActivity(Intent(this, PilihKelasActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                R.id.nav_message -> {
                    startActivity(Intent(this, ChatActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_account -> {
                    startActivity(Intent(this, AkunActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
