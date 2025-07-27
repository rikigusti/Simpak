package com.example.simpak

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpak.adapter.ProposalAdapter
import com.example.simpak.databinding.ActivityProposalBinding
import com.example.simpak.model.Proposal
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.firestore.FirebaseFirestore

class ProposalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProposalBinding
    private lateinit var adapter: ProposalAdapter
    private val db = FirebaseFirestore.getInstance()
    private val proposalList = mutableListOf<Proposal>()
    private var currentFilter = "Semua"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProposalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSearch()
        setupTabLayout()
        setupBottomNavigation()

        binding.btnAddProposal.setOnClickListener {
            startActivity(Intent(this, AddProposalActivity::class.java))
        }

        loadProposalData()
    }

    private fun setupRecyclerView() {
        adapter = ProposalAdapter(proposalList) { proposal ->
            val intent = Intent(this, DetailProposalActivity::class.java)
            intent.putExtra("proposal", proposal)
            startActivity(intent)
        }

        binding.rvProposalList.layoutManager = LinearLayoutManager(this)
        binding.rvProposalList.adapter = adapter
    }

    private fun setupSearch() {
        binding.etSearchProposal.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterList()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupTabLayout() {
        val tabLayout = binding.tabLayoutProposal
        tabLayout.addTab(tabLayout.newTab().setText("Semua"))
        tabLayout.addTab(tabLayout.newTab().setText("Disetujui"))
        tabLayout.addTab(tabLayout.newTab().setText("Menunggu"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentFilter = tab?.text.toString()
                filterList()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun loadProposalData() {
        db.collection("proposals")
            .get()
            .addOnSuccessListener { result ->
                proposalList.clear()
                for (doc in result) {
                    val proposal = doc.toObject(Proposal::class.java).copy(id = doc.id)
                    proposalList.add(proposal)
                }
                filterList()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Gagal memuat data", Toast.LENGTH_SHORT).show()
            }
    }

    private fun filterList() {
        val keyword = binding.etSearchProposal.text.toString().lowercase()
        val filtered = proposalList.filter {
            (currentFilter == "Semua" || it.status.equals(currentFilter, ignoreCase = true)) &&
                    it.judul.lowercase().contains(keyword)
        }
        adapter.updateData(filtered)
    }

    private fun setupBottomNavigation() {
        val bottomNav = binding.bottomNavigation
        bottomNav.selectedItemId = R.id.nav_proposal

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                    true
                }
                R.id.nav_proposal -> true
                R.id.nav_search -> {
                    startActivity(Intent(this, PilihKelasActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
