package com.mrright.realm_example

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration

@HiltAndroidApp
class RealmExApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val config = RealmConfiguration.Builder().apply {
            deleteRealmIfMigrationNeeded()
            name("school.realm")
        }.build()

        Realm.setDefaultConfiguration(config)

    }

}