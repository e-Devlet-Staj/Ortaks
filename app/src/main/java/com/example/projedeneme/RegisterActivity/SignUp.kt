package com.example.projedeneme.RegisterActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.projedeneme.HomeActivity.Welcome
import com.example.projedeneme.R
import com.example.projedeneme.UserActivity.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_in.button2
import kotlinx.android.synthetic.main.activity_sign_in.imageView2
import kotlinx.android.synthetic.main.activity_sign_in.textView7
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        imageView2.setOnClickListener()
        {
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
        }
        textView7.setOnClickListener()
        {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener()
        {

            registerNewUser(emailText.text.toString(),passwordText.text.toString())


        }

    }
    private fun registerNewUser(mail: String, passwd: String) {
        if(NameText.text.toString().isEmpty()){
            passwordText.error = "Bu alan boş bırakılamaz"
            passwordText.requestFocus()
            return
        }
        if(SurnameText.text.toString().isEmpty()){
            passwordText.error = "Bu alan boş bırakılamaz"
            passwordText.requestFocus()
            return
        }
        if(PhoneText.text.toString().isEmpty()){
            passwordText.error = "Bu alan boş bırakılamaz"
            passwordText.requestFocus()
            return
        }
        if(emailText.text.toString().isEmpty()){
            emailText.error = "Bu alan boş bırakılamaz"
            emailText.requestFocus()
            return
        }
        if(passwordText.text.toString().isEmpty()){
            passwordText.error = "Bu alan boş bırakılamaz"
            passwordText.requestFocus()
            return
        }
        if(passwordAgainText.text.toString().isEmpty()){
            passwordAgainText.error = "Bu alan boş bırakılamaz"
            passwordAgainText.requestFocus()
            return
        }
        if(passwordText.text.toString()!=passwordAgainText.text.toString())
        {
            passwordAgainText.error = "Şifrenizle eşleşmiyor"
            passwordAgainText.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText.text.toString()).matches()){
            emailText.error="Lütfen geçerli bir e-posta giriniz."
            emailText.requestFocus()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, passwd)
            .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(p0: Task<AuthResult>) {
                    if (p0.isSuccessful) {

                        var tempUser= User()
                        tempUser.username=NameText.text.toString()
                        tempUser.surname=SurnameText.text.toString()
                        tempUser.phone=PhoneText.text.toString()
                        tempUser.rate="0"
                        tempUser.profilePhoto=""
                        tempUser.user_id=FirebaseAuth.getInstance().currentUser?.uid

                        FirebaseDatabase.getInstance().reference
                            .child("user")
                            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                            .setValue(tempUser).addOnCompleteListener { task->
                                if (task.isSuccessful){
                                    Toast.makeText(this@SignUp, "Başarılı bir şekilde kaydedildi..."+FirebaseAuth.getInstance().currentUser?.email, Toast.LENGTH_SHORT).show()
                                    FirebaseAuth.getInstance().signOut()
                                    startActivity(Intent(this@SignUp, SignIn::class.java))
                                }
                                else
                                {
                                    Toast.makeText(this@SignUp, "Olmadı"+FirebaseAuth.getInstance().currentUser?.email, Toast.LENGTH_SHORT).show()
                                }

                            }

                    }
                    else
                        Toast.makeText(
                            this@SignUp,
                            "Bir Sorun Oluştu..."+p0.exception?.message,
                            Toast.LENGTH_SHORT
                        ).show()

                }

            })

    }


}