package com.mrright.realm_example.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Student : RealmObject() {

    @PrimaryKey
    var id: String = ""

    var name: String = ""

    var std: String = Standard.NONE.name

    var address: String = ""

    override fun toString(): String {
        return "Student {$id, $name, $std, $address}"
    }

}

enum class Standard {
    TEN,
    NINE,
    EIGHT,
    SEVEN,
    SIX,
    FIVE,
    FOUR,
    THREE,
    TWO,
    ONE,
    NONE,
}