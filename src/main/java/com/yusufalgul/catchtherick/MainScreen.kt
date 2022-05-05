package com.yusufalgul.catchtherick

import android.content.Intent
import android.content.SharedPreferences
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.HandlerThread
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.yusufalgul.catchtherick.databinding.ActivityMainScreenBinding
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class MainScreen : AppCompatActivity() {

    private lateinit var binding : ActivityMainScreenBinding
    lateinit var sharedPreferences: SharedPreferences
    var score = 0
    var rickImageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable {  }







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        invisibleImages()

        sharedPreferences = this.getSharedPreferences("com.yusufalgul.catchtherick", MODE_PRIVATE)

        val highScorePreferences = sharedPreferences.getString("highScore","High Score : ")

        if(highScorePreferences.equals("High Score :")){
            binding.highScoreText.text = "High Score : "
        }else{
            binding.highScoreText.text = "High Score : $highScorePreferences"
        }


        val intent = intent
        val time = intent.getStringExtra("time")







        object : CountDownTimer(time!!.toLong()*1000,1000){
            override fun onTick(p0: Long) {
                binding.timeText.text = "Time : " + p0/1000
            }
            override fun onFinish() {
                //binding.timeText.text = "Time : 0"
                handler.removeCallbacks(runnable)
                for(rickImage in rickImageArray){
                    rickImage.visibility = View.INVISIBLE
                }

                val highScore = binding.scoreText.text.toString()
                if(highScore != null){
                    binding.highScoreText.text = "High Score : " + highScore
                    sharedPreferences.edit().putString("highScore",highScore).apply()
                }


                val alert = AlertDialog.Builder(this@MainScreen)
                alert.setTitle("Game Over")
                alert.setMessage("Restart the Game?")
                alert.setPositiveButton("Yes"){dialog, which ->
                    //Restart
                    val intent = Intent(this@MainScreen,OptionsActivity::class.java)
                    finish()
                    startActivity(intent)

                }
                alert.setNegativeButton("No"){dialog, which ->
                    Toast.makeText(this@MainScreen,"Game Over", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@MainScreen,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                }
                alert.show()
            }

        }.start()


        rickImageArray.add(binding.imageView)
        rickImageArray.add(binding.imageView2)
        rickImageArray.add(binding.imageView3)
        rickImageArray.add(binding.imageView4)
        rickImageArray.add(binding.imageView5)
        rickImageArray.add(binding.imageView6)
        rickImageArray.add(binding.imageView7)
        rickImageArray.add(binding.imageView8)
        rickImageArray.add(binding.imageView9)
        rickImageArray.add(binding.imageView10)
        rickImageArray.add(binding.imageView11)
        rickImageArray.add(binding.imageView12)
        rickImageArray.add(binding.imageView13)
        rickImageArray.add(binding.imageView14)
        rickImageArray.add(binding.imageView15)
        rickImageArray.add(binding.imageView16)

    }



    fun invisibleImages() {
        val intent = intent
        val delayMillis = intent.getLongExtra("level",0)
        println(delayMillis)
        runnable = object : Runnable {
            override fun run() {
                for(rickImage in rickImageArray) {
                    rickImage.visibility = View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(16)
                rickImageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,delayMillis)
            }
        }
        handler.post(runnable)
    }


    fun scoreFunction(view : View) {
        score = score + 2
        binding.scoreText.text = "$score"
    }




}