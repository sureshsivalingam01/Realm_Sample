package com.mrright.realm_example.data.realm.daos

import android.util.Log
import com.mrright.realm_example.models.Student
import io.realm.Case
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait

class StudentDao {

    suspend fun insert(realm: Realm, student: Student) {
        realm.executeTransactionAwait {
            it.insert(student)
        }
    }




}