package hr.ferit.lv3_zad2

import android.app.Application
import android.content.Context

class InspiringPersonsApp : Application() {

    companion object {
        lateinit var ApplicationContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationContext = this
    }
}