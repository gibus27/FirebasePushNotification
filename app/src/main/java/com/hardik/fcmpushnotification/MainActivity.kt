package com.hardik.fcmpushnotification

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import java.io.IOException

class MainActivity : AppCompatActivity() {

  private val senderID = "YOUR_SENDER_ID"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    getToken()
  }

  private fun getToken() {

    Thread(Runnable {
      try {
        FirebaseMessaging.getInstance().token
          .addOnCompleteListener { task ->
            if (task.isSuccessful) {
              if (task.result != null && !TextUtils.isEmpty(task.result)) {
                val token: String = task.result!!
                println("Token --> $token")
              }
            }
          }
      } catch (e: IOException) {
        e.printStackTrace()
      }
    }).start()
  }

}
