package com.abdullah.univeraproject.utils

import java.util.regex.Pattern


fun String?.isNull(): Boolean {
    return this == null
}

fun String?.isEmail(): Boolean {
    return this != null && EMAIL_ADDRESS_PATTERN.matcher(this).matches()
}

fun String?.isPassword(): Boolean {
    return this != null && PASSWORD_PATTERN.matcher(this).matches()
}

val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
val PASSWORD_PATTERN: Pattern =
    Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$")


