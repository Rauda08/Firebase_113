package com.example.pam15

import android.app.Application
import com.example.pam15.di.MahasiswaContainer

class MahasiswaApp : Application() {
    //fungsinya untuk menympan instance ContainerApp
    lateinit var containerApp: MahasiswaContainer

    override fun onCreate() {
        super.onCreate()
        containerApp = MahasiswaContainer(this)
    }
}