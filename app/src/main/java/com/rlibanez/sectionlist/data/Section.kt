package com.rlibanez.sectionlist.data

import com.google.gson.annotations.SerializedName

data class Section(
    // El nombre de la columna de la db, por si queremos cambiar el nombre de la variable
    @SerializedName("a")
    val a: Double,
    val ag: Double,
    val al: Double,
    val avz: Double,
    val b: Double,
    val d: Double,
    val dmax: String,
    val emax: Int,
    val emin: Int,
    val en1002522004: Boolean,
    val en1002542004: Boolean,
    val en102252001: Boolean,
    val g: Double,
    val h: Double,
    val hi: Double,
    val it: Double,
    val iw: Double,
    val iy: Double,
    val iyc: Double,
    val iz: Double,
    val izc: Double,
    val name: String,
    val pmax: Int,
    val pmin: Int,
    val r1: Double,
    val r2: Double,
    val ss: Double,
    val tf: Double,
    val tw: Double,
    val wely: Double,
    val welz: Double,
    val wply: Double,
    val wplz: Double,
    val xS235: Boolean,
    val xS355: Boolean,
    val xS460: Boolean,
    val yS235: Boolean,
    val yS355: Boolean,
    val yS460: Boolean,
    val ym: Double,
    val ys: Double
)