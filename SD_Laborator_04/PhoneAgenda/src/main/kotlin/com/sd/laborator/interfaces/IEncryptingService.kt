package com.sd.laborator.interfaces

import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

interface IEncryptingService {
    fun decrypt(cipherText: String): String
    fun encrypt(inputText: String): String
    fun check(inputText: String): Boolean
}