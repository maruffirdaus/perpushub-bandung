package com.perpushub.bandung.common.model

data class LibraryDetail(
    val id: Int,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double
) {
    companion object {
        val dummies = listOf(
            LibraryDetail(
                id = 0,
                name = "Dinas Arsip dan Perpustakaan Kota Bandung (Disarpus)",
                address = "Jl. Seram No.2, Citarum, Kec. Bandung Wetan, Kota Bandung, Jawa Barat 40115",
                latitude = -6.908003374130907,
                longitude = 107.61381915279485
            ),
            LibraryDetail(
                id = 1,
                name = "Perpustakaan Gasibu Jawa Barat",
                address = "Jl. Majapahit, Citarum, Kec. Bandung Wetan, Kota Bandung, Jawa Barat 40115",
                latitude = -6.899456637443859,
                longitude = 107.61827395582253
            )
        )
    }
}
