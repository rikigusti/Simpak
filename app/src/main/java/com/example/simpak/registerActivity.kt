package com.example.simpak

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    // Deklarasi view
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var cbAgreement: CheckBox
    private lateinit var btnRegister: Button
    private lateinit var tvLogin: TextView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inisialisasi view berdasarkan ID dari XML
        etUsername = findViewById(R.id.etUsername)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        cbAgreement = findViewById(R.id.cbAgreement)
        btnRegister = findViewById(R.id.btnRegister)
        tvLogin = findViewById(R.id.tvLogin)
        btnBack = findViewById(R.id.btnBack)

        // Tombol kembali ke halaman onboarding
        btnBack.setOnClickListener {
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Arahkan ke halaman Login jika sudah punya akun
        tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Saat tombol register ditekan
        btnRegister.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            // Validasi input
            when {
                username.isEmpty() -> etUsername.error = "Nama Pengguna wajib diisi"
                email.isEmpty() -> etEmail.error = "Email wajib diisi"
                password.isEmpty() -> etPassword.error = "Kata sandi wajib diisi"
                !cbAgreement.isChecked -> Toast.makeText(
                    this,
                    "Silakan setujui syarat & ketentuan",
                    Toast.LENGTH_SHORT
                ).show()

                else -> {
                    // Jika semua valid, tampilkan sukses
                    Toast.makeText(this, "Pendaftaran berhasil!", Toast.LENGTH_SHORT).show()

                    // Arahkan ke halaman Login
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}
