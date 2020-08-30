
package com.example.projedeneme.RegisterActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projedeneme.HomeActivity.Welcome
import com.example.projedeneme.R
import com.example.projedeneme.ViewActivity.ViewRequests
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        imageView2.setOnClickListener()
        {
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
        }
        textView7.setOnClickListener()
        {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener()
        {
            signIn()
        }

    }
    private fun signIn() {
        if(email2Text.text.isNotEmpty() && editTextTextPassword.text.isNotEmpty()){

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email2Text.text.toString(),editTextTextPassword.text.toString())
                .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                    override fun onComplete(p0: Task<AuthResult>) {
                        if(p0.isSuccessful){

                            Toast.makeText( this@SignIn,"Giriş Başarılı"+FirebaseAuth.getInstance().currentUser?.email,Toast.LENGTH_SHORT).show()
                            var intentMain= Intent(this@SignIn,
                                ViewRequests::class.java)
                            startActivity(intentMain)
                        }
                        else{

                            Toast.makeText( this@SignIn,"Hatalı Giriş..."+p0.exception?.message,Toast.LENGTH_SHORT).show()
                        }

                    }
                })
        }
        else{
            Toast.makeText( this@SignIn,"Boş Alanları Doldurunuz...", Toast.LENGTH_SHORT).show()
        }
    }


}