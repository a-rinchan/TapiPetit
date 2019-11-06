package com.a_rin.tapioka

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

const val COUNT = 7

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageButtonList = arrayListOf<ImageButton>(
            tapi1, tapi2, tapi3, tapi4, tapi5, tapi6, tapi7, tapi8, tapi9,
            tapi10, tapi11, tapi12, tapi13, tapi14, tapi15, tapi16
        )

        var tapi_count = COUNT

        val mediaPlayer = MediaPlayer.create(this, R.raw.tapi_pon)

        for (i in imageButtonList) {
            i.visibility = View.INVISIBLE
        }

        var shuffledList = imageButtonList.shuffled().take(7)
        for (i in shuffledList) {
            if (shuffledList.contains(i) == true) {
                i.visibility = View.VISIBLE
            }
        }

        imageButtonList.forEachIndexed {index, imageButton ->  
            imageButton.setOnClickListener {
                if(imageButtonList.containsAll(shuffledList)){
                    imageButton.visibility = View.INVISIBLE
                    mediaPlayer.start()
                    tapi_count--
                    if(tapi_count == 0){
                        mediaPlayer.release()
                        val intent : Intent = Intent(this, ResultActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }

    }
}
