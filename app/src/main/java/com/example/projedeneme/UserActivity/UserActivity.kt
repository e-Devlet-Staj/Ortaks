package com.example.projedeneme.UserActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projedeneme.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        var ref = FirebaseDatabase.getInstance().reference
        var user=FirebaseAuth.getInstance().currentUser

        email_text.setText(user?.email)

        var sorgu=ref.child("user")
            .orderByKey()
            .equalTo(user?.uid)
        sorgu.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for (singleSnapshot in p0!!.children){
                    var tempUser=singleSnapshot.getValue(User::class.java)
                    name_text.setText(tempUser?.username)
                    surname_text.setText(tempUser?.surname)
                    phone_text.setText(tempUser?.phone)
                }

            }

        })

            button4.setOnClickListener{
                if (name_text.text.toString().isNotEmpty() && surname_text.text.toString().isNotEmpty() && email_text.text.toString().isNotEmpty() && phone_text.text.toString().isNotEmpty()){
                    FirebaseDatabase.getInstance().reference
                        .child("user")
                        .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                        .child("username")
                        .setValue(name_text.text.toString())
                    FirebaseDatabase.getInstance().reference
                        .child("user")
                        .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                        .child("phone")
                        .setValue(phone_text.text.toString())
                    FirebaseDatabase.getInstance().reference
                        .child("user")
                        .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                        .child("surname")
                        .setValue(surname_text.text.toString())


                    Toast.makeText( this@UserActivity,"Güncellendi"+FirebaseAuth.getInstance().currentUser?.email,
                        Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText( this@UserActivity,"Alan boş bırakılamaz"+FirebaseAuth.getInstance().currentUser?.email,
                        Toast.LENGTH_SHORT).show()
                }

            }



    }
}