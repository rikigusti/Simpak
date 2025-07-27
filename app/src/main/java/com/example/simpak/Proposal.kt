package com.example.simpak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Proposal(
    val id: String = "",
    val judul: String = "",
    val pengaju: String = "",
    val waktu: String = "",
    val status: String = "",
    val fotoUrl: String = ""
) : Parcelable
