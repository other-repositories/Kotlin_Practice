package com.example.colortilesviewsk

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

// тип для координат
data class Coord(val x: Int, val y: Int)

class MainActivity : AppCompatActivity() {

    lateinit var tiles: Array<Array<View>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tiles = arrayOf(
            arrayOf(findViewById(R.id.t00), findViewById(R.id.t01), findViewById(R.id.t02), findViewById(R.id.t03)),
            arrayOf(findViewById(R.id.t10), findViewById(R.id.t11), findViewById(R.id.t12), findViewById(R.id.t13)),
            arrayOf(findViewById(R.id.t20), findViewById(R.id.t21), findViewById(R.id.t22), findViewById(R.id.t23)),
            arrayOf(findViewById(R.id.t30), findViewById(R.id.t31), findViewById(R.id.t32), findViewById(R.id.t33))
        )

        initField()
    }


    fun getCoordFromString(s: String): Coord {
        val x = s[0].toString().toInt()
        val y = s[1].toString().toInt()
        // Вывод координат в лог
        println("Coordinates: x=$x, y=$y")
        return Coord(x, y)
    }


    fun changeColor(view: View) {
        val brightColor = resources.getColor(R.color.bright, theme)
        val darkColor = resources.getColor(R.color.dark, theme)
        val drawable = view.background as ColorDrawable
        if (drawable.color == brightColor) {
            view.setBackgroundColor(darkColor)
        } else {
            view.setBackgroundColor(brightColor)
        }
    }

    // Обработчик нажатия на тайл
    fun onClick(v: View) {
        val coord = getCoordFromString(v.tag.toString())
        changeColor(v)

        for (i in 0..3) {
            changeColor(tiles[coord.x][i])
            changeColor(tiles[i][coord.y])
        }

        checkVictory()
    }


    fun checkVictory() {

        val brightColor = resources.getColor(R.color.bright, theme)

        for (i in 0..3) {
            for (j in 0..3) {
                val drawable = tiles[i][j].background as ColorDrawable
                if (drawable.color != brightColor) {
                    return
                }
            }
        }


        println("Victory!")
    }

    fun initField() {
        val random = java.util.Random()

        for (i in 0..3) {
            for (j in 0..3) {
                val randomValue = random.nextInt(2)

                if (randomValue == 0) {
                    tiles[i][j].setBackgroundColor(resources.getColor(R.color.dark, theme))
                } else {
                    tiles[i][j].setBackgroundColor(resources.getColor(R.color.bright, theme))
                }
            }
        }
    }
}
