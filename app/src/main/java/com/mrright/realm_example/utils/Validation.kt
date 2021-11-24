package com.mrright.realm_example.utils

import com.mrright.realm_example.models.Standard


fun String.validateName(): String? {
    return when {
        this.isEmpty() -> "Enter your Name"
        this.length > 26 -> "Name Should not exceed more than 26"
        else -> null
    }
}

fun String.validateStandard(): String? {
    return when (Standard.valueOf(this)) {
        Standard.NONE -> "Select Standard"
        else -> null
    }
}

fun String.validateAddress(): String? {
    return when {
        this.isEmpty() -> "Enter your Address"
        this.length < 10 -> "Address Should be more than 10 characters"
        else -> null
    }
}

