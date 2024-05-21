package com.example.mvvmandroomdatabase

import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

object SampleData {
    val data1 = ""
    val data2 = ""

    fun getDate(diff:Int):Date{
        val cal = GregorianCalendar()
        cal.add(Calendar.MILLISECOND,diff)
        return cal.time


    }
    val data:List<DataEntity>
        get() {
            val temp : MutableList<DataEntity> = ArrayList()   // isme data change nahi kar sakte hain
            temp.add(DataEntity(1, getDate(45), data1))
            temp.add(DataEntity(2, getDate(46), data2))
            return temp
}}