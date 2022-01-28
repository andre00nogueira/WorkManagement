package io.github.andre00nogueira.workmanagement_android.extensions

import java.security.MessageDigest

fun String.hash(): String =
    MessageDigest.getInstance("SHA256").digest(this.toByteArray())
        .fold("", { str, it -> str + "%02x".format(it) })