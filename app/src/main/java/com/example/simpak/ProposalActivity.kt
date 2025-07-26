package com.example.simpak

import AddProposalActivity
import Proposal
import ProposalAdapter
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class ProposalActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProposalAdapter
    private lateinit var searchEditText: EditText
    private lateinit var tabLayout: TabLayout

    private val proposalList = listOf(
        Proposal("Proposal Kegiatan A", "Robertson Connie", "16 hours", "Menunggu"),
        Proposal("Proposal Kegiatan B", "Aldrin Mars", "2 days", "Disetujui"),
        Proposal("Proposal Kegiatan C", "Nandita A.", "5 hours", "Ditolak")
    )

    private var currentFilteredList = proposalList.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proposal)

        val btnAddProposal = findViewById<ImageButton>(R.id.btnAddProposal)
        btnAddProposal.setOnClickListener {
            val intent = Intent(this, AddProposalActivity::class.java)
            startActivity(intent)
        }

        // Inisialisasi View
        searchEditText = findViewById(R.id.etSearchProposal)
        recyclerView = findViewById(R.id.rvProposalList)
        tabLayout = findViewById(R.id.tabFilter)

        // Setup RecyclerView
        adapter = ProposalAdapter(currentFilteredList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Tambahkan Tab Filter
        tabLayout.addTab(tabLayout.newTab().setText("Semua"))
        tabLayout.addTab(tabLayout.newTab().setText("Disetujui"))
        tabLayout.addTab(tabLayout.newTab().setText("Menunggu"))
        tabLayout.addTab(tabLayout.newTab().setText("Ditolak"))

        // Listener untuk Tab Filter
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val selected = tab.text.toString()
                filterProposal(selected, searchEditText.text.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // Listener pencarian
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val selectedTab = tabLayout.getTabAt(tabLayout.selectedTabPosition)?.text.toString()
                filterProposal(selectedTab, s.toString())
            }
        })

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_proposal
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_proposal -> true
                else -> false
            }
        }
    }

    // Fungsi untuk filter berdasarkan status dan kata kunci
    private fun filterProposal(status: String, query: String) {
        currentFilteredList = proposalList.filter {
            (status == "Semua" || it.status == status) &&
                    (it.title.contains(query, ignoreCase = true) ||
                            it.applicant.contains(query, ignoreCase = true))
        }.toMutableList()
        adapter.updateData(currentFilteredList)
    }
}
