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

val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)
val PASSWORD_PATTERN: Pattern = Pattern.compile(
    "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"
)


