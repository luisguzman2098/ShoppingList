package com.example.shoppinglist.ui.shoppinglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.shoppinglist.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_authentication.*

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        //Setup
        Setup()
    }

    fun Setup() {
        title = "Authentication"

        btn_sing_up.setOnClickListener {
            if (ed_email.text?.isNotEmpty() ?: ed_password.text?.isNotEmpty() == true) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        ed_email.text.toString(), ed_password.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful) {
                            it.result.user?.email?.let { it1 -> showHome(it1,ProviderType.BASIC) }
                        } else {
                            showAlert()
                        }
                    }
            }
        }

        btn_login.setOnClickListener {
            if (ed_email.text?.isNotEmpty() ?: ed_password.text?.isNotEmpty() == true) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        ed_email.text.toString(), ed_password.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful) {
                            it.result.user?.email?.let { it1 -> showHome(it1,ProviderType.BASIC) }
                        } else {
                            showAlert()
                        }
                    }
            }
        }

    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al authentificar el usario")
        builder.setPositiveButton("Acceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        val homeIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}