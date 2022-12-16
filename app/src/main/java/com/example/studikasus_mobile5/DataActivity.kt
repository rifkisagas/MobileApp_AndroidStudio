package com.example.studikasus_mobile5

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.studikasus_mobile5.databinding.ActivityDataBinding
import com.google.firebase.firestore.FirebaseFirestore

class DataActivity : AppCompatActivity() {
    lateinit var binding : ActivityDataBinding
    lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        db.collection("kredits").document(intent.getStringExtra("idDoc").toString())
            .get()
            .addOnSuccessListener {
                binding.edtNominal.setText(it.data?.get("Nominal").toString())
                binding.edtTenor.setText(it.data?.get("Tenor").toString())
                binding.edtAngsuran.setText(it.data?.get("Angsuran").toString())\
                it.data?.get("").toString()
            }
            .addOnFailureListener{
                it.printStackTrace()
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        binding.btnDelete.setOnClickListener {
            delete()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun delete(){
        db.collection("Kredits")
            .document(intent.getStringExtra("idDoc").toString())
            .delete()
            .addOnSuccessListener { Log.d(TAG, "Doc Successfully Deleted!")
                    Toast.makeText(this, "Delete Berhasil", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{ e -> Log.w(TAG, "Deleting Error",e)
                Toast.makeText(this, "Gagal Delete", Toast.LENGTH_SHORT).show()
            }

    }
}