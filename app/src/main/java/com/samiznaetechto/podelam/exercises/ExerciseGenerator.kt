package com.samiznaetechto.podelam.exercises

import java.lang.Exception
import kotlin.random.*


class ExerciseGenerator {
    fun createExercise() : Exercise
    {
        val a = Random.nextInt(1..15)
        val b = Random.nextInt(5..30)
        return Exercise("$a + $b = ", a+b)
    }
}