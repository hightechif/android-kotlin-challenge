package com.fadhil.challenge.constant

object ErrorMessage {

    private const val userInactive = "Anda sudah berulang kali memasukan Verifikasi yang salah, akun telah dinonaktifkan. Mohon hubungi admin."
    private const val serverError = "Sistem sedang mengalami gangguan."
    private const val oldPasswordError = "Kata sandi lama salah."
    private const val confirmationPasswordError = "Kata sandi baru dan kata sandi konfirmasi tidak sama."
    private const val formatPasswordError = "Kata sandi baru harus lebih dari 6 karakter."
    private const val newPasswordError = "Kata sandi lama dan kata sandi baru yang dimasukan tidak boleh sama."

    const val connectionError = "Koneksi sedang bermasalah, cek kembali koneksimu."
    const val systemError = "Terjadi kesalahan pada sistem."
    const val Http503 = "Sedang ada gangguan sistem, coba lagi lain waktu."
    const val Http403 = "Sesi anda telah berakhir."
    const val userVerified = "Email yang anda masukan sudah terverifikasi. Silakan login untuk menggunakan aplikasi."
    const val userNotFound = "Email yang anda masukan tidak terdaftar, silahkan hubungi admin."
    const val verificationFailed = "verifikasi gagal, akun anda telah dinonaktifkan. mohon hubungi admin"
    const val userNotVerified = "Email yang anda masukan belum diverifikasi, silahkan verifikasi sekarang."
    const val userLoginFreeze = "Anda salah input akun lebih dari 3x, aktivitas akan kami bekukan selama 1 menit"

    fun errorCode(code: String) =
        when(code) {
            "D001" -> userVerified
            "D002" -> userNotFound
            "D004" -> userInactive
            "D005" -> userNotVerified
            "D006" -> verificationFailed
            "D007" -> oldPasswordError
            "D008" -> confirmationPasswordError
            "D009" -> formatPasswordError
            "D010" -> newPasswordError
            "D011" -> userLoginFreeze
            else -> serverError
        }

}