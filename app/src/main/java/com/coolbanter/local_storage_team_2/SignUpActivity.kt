package com.coolbanter.local_storage_team_2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.coolbanter.local_storage_team_2.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity() {

    // Initialization of variables
    private lateinit var binding : ActivitySignUpBinding
    private lateinit var name: EditText
    private lateinit var emailAddress: EditText
    private lateinit var schoolName: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        name = binding.nameEt
        emailAddress = binding.emailAddressEt
        schoolName = binding.schoolNameEt
        password = binding.passwordEt


        binding.btnSignUp.setOnClickListener {
            saveData()

        }
    }

         private fun saveData() {
                if (name.text.isEmpty()) {
                    name.error = "Please enter your name"
                    return
                }
                if (emailAddress.text.isEmpty()) {
                    emailAddress.error = "Please enter your email address"
                    return
                }
                 
                if (schoolName.text.isEmpty()) {
                    schoolName.error = "Please enter the name of your school"
                    return
                }

                if (password.text.isEmpty()) {
                    password.error = "Please enter password"
                    return
                }

                 if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress.text.toString()).matches()) {
                     emailAddress.error = "not a valid email!"
                 }



                val  mypref = getSharedPreferences("mypref", Context.MODE_PRIVATE)

                val editor = mypref.edit()

                 if (name.text.toString().isNotEmpty() &&
                         emailAddress.text.isNotEmpty() &&
                         Patterns.EMAIL_ADDRESS.matcher(emailAddress.text.toString()).matches() &&
                         schoolName.text.toString().isNotEmpty() &&
                         password.text.toString().isNotEmpty()) {


                     editor.putString("name", name.text.toString())
                     editor.putString("email", emailAddress.text.toString())
                     editor.putString("school", schoolName.text.toString())
                     editor.putString("password", password.text.toString())

                     editor.apply()
                     startActivity(Intent(this, LoginActivity::class.java))
                     Toast.makeText(this, "Sign up successful, please login", Toast.LENGTH_LONG).show()
                 }

            }
}
