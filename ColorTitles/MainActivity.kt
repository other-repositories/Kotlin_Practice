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
        // Инициализация массива тайлов с использованием их ID
        tiles = arrayOf(
            arrayOf(findViewById(R.id.t00), findViewById(R.id.t01), findViewById(R.id.t02), findViewById(R.id.t03)),
            arrayOf(findViewById(R.id.t10), findViewById(R.id.t11), findViewById(R.id.t12), findViewById(R.id.t13)),
            arrayOf(findViewById(R.id.t20), findViewById(R.id.t21), findViewById(R.id.t22), findViewById(R.id.t23)),
            arrayOf(findViewById(R.id.t30), findViewById(R.id.t31), findViewById(R.id.t32), findViewById(R.id.t33))
        )
        // Заполнение поля случайными цветами
        initField()
    }

    // Функция для получения координат из строки
    fun getCoordFromString(s: String): Coord {
        val x = s[0].toString().toInt()
        val y = s[1].toString().toInt()
        // Вывод координат в лог
        println("Coordinates: x=$x, y=$y")
        return Coord(x, y)
    }

    // Функция для изменения цвета тайла
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

        // Изменение цвета всех тайлов вокруг выбранного тайла
        for (i in 0..3) {
            changeColor(tiles[coord.x][i])
            changeColor(tiles[i][coord.y])
        }

        // Проверка наличия победы
        checkVictory()
    }

    // Функция для проверки победы
    fun checkVictory() {
        // Предположим, что победа достигается, если все тайлы одного цвета
        val brightColor = resources.getColor(R.color.bright, theme)

        for (i in 0..3) {
            for (j in 0..3) {
                val drawable = tiles[i][j].background as ColorDrawable
                if (drawable.color != brightColor) {
                    // Если хотя бы один тайл не является ярким, игра не выиграна
                    return
                }
            }
        }

        // Если все тайлы являются яркими, выигрыш
        println("Victory!")
    }

    // Инициализация поля случайными плитками
    fun initField() {
        val random = java.util.Random()

        for (i in 0..3) {
            for (j in 0..3) {
                // Генерация случайного числа (0 или 1)
                val randomValue = random.nextInt(2)

                // Задание цвета тайла в зависимости от случайного числа
                if (randomValue == 0) {
                    // Темный цвет
                    tiles[i][j].setBackgroundColor(resources.getColor(R.color.dark, theme))
                } else {
                    // Светлый цвет
                    tiles[i][j].setBackgroundColor(resources.getColor(R.color.bright, theme))
                }
            }
        }
    }
}
