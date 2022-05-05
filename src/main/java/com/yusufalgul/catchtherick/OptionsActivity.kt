package com.yusufalgul.catchtherick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import com.yusufalgul.catchtherick.databinding.ActivityOptionsBinding

class OptionsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOptionsBinding
    var delayMillis = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            if(R.id.easyButton == i ){
                val intent = Intent(this@OptionsActivity,MainScreen::class.java)
                intent.putExtra("level", binding.easyButton.text.toString().toLong())
                startActivity(intent)
                println("easy")

            }
            if(R.id.mediumButton == i){
                val intent = Intent(this@OptionsActivity,MainScreen::class.java)
                intent.putExtra("level",binding.mediumButton.text.toString().toLong())
                startActivity(intent)
                println("medium")
            }
            if(R.id.hardButton == i){
                val intent = Intent(this@OptionsActivity,MainScreen::class.java)
                intent.putExtra("level",binding.hardButton.text.toString().toLong())
                startActivity(intent)
                println("hard")

            }

        }


    }
    fun nextButton(view : View){
            if(binding.timeSelect.text.isNotEmpty()){
                val intent = Intent(this@OptionsActivity,MainScreen::class.java)
                intent.putExtra("time",binding.timeSelect.text.toString())
                startActivity(intent)
            }else{
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Warning")
                alert.setMessage("This text must not be empty.Do you want to try again?")
                alert.setPositiveButton("Yes") {dialog, which->
                    val intent = Intent(this@OptionsActivity,OptionsActivity::class.java)
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No"){dialog, which->
                    val intent = Intent(this@OptionsActivity,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                }
                alert.show()
            }
    }







}