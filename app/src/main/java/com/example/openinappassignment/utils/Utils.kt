package com.example.openinappassignment.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.LocalTime

class Utils {
    fun openWhatsApp(context: Context, phoneNumber: String, message: String) {
        val url = "https://wa.me/$phoneNumber?text=${Uri.encode(message)}"
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            setPackage("com.whatsapp")
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getGreeting(): String {
        val currentTime = LocalTime.now()
        val morningStart = LocalTime.of(0, 0)
        val morningEnd = LocalTime.of(11, 59)
        val afternoonStart = LocalTime.of(12, 0)
        val afternoonEnd = LocalTime.of(16, 0)
        val eveningStart = LocalTime.of(16, 1)
        val eveningEnd = LocalTime.of(23, 59)

        return when {
            currentTime.isAfter(morningStart) && currentTime.isBefore(morningEnd) -> "Good Morning"
            currentTime.isAfter(afternoonStart) && currentTime.isBefore(afternoonEnd) -> "Good Afternoon"
            currentTime.isAfter(eveningStart) && currentTime.isBefore(eveningEnd) -> "Good Evening"
            else -> "Hello"
        }
    }

    fun copyTextToClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("copied_text", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
    }

}