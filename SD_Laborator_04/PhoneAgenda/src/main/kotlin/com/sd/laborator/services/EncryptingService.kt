package com.sd.laborator.services

import com.sd.laborator.interfaces.IEncryptingService
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Service
class EncryptingService : IEncryptingService {

    override fun decrypt(cipherText: String): String {
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.DECRYPT_MODE, key, iv)
        val plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText))
        return String(plainText)
    }

    override fun encrypt(inputText: String): String {
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.ENCRYPT_MODE, key, iv)
        val cipherText = cipher.doFinal(inputText.toByteArray())
        return Base64.getEncoder().encodeToString(cipherText)
    }

    override fun check(inputText: String): Boolean {
        if (encrypt(inputText)==cipherText)
            return true
        else return false
    }
    private val algorithm = "AES/CBC/PKCS5Padding"
    private val key = SecretKeySpec("1234567890123456".toByteArray(), "AES")
    private val iv = IvParameterSpec(ByteArray(16))

    private val cipherText = encrypt("parola")

    //assert(inputText == plainText)
}