package com.emmanuel.dijitalgaraj.quiz

import java.math.BigInteger
import java.security.MessageDigest

class SearchHashUseCase {


   operator fun invoke( email: String="",hashedValue: String=""): String? {
       if (email.isEmpty()||hashedValue.isEmpty())
       {
           return null
       }
        val midCounter = 32
        var currentWordLength = (hashedValue.length) / midCounter
        var currentStartIndex = 0
        val solvedHashWord: StringBuilder = java.lang.StringBuilder()

        while (currentWordLength != 0) {
            val currentHash: String =
                hashedValue.subSequence(currentStartIndex, currentStartIndex + midCounter).toString()
            val solvedHashedWord: String =
                calcTheTotalSum(currentHash, solvedHashWord.toString(), email)
            val bestDuo: String = calcTheTotalSum(currentHash, solvedHashWord.toString(), email)
            if (bestDuo == "inCorrectInput") {
                break
            }
            solvedHashWord.append(solvedHashedWord)
            currentStartIndex += midCounter
            --currentWordLength
        }
        return solvedHashWord.toString()
    }


    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }


    private fun getTheHashFormula(plainText: String, email: String): String {
        return md5((md5(email) + plainText + md5(plainText)))
    }

    private fun calcTheTotalSum(currentHash: String, currentWord: String, email: String): String {
        val allPossibleCharsList = listOf(
            'A',
            'B',
            'C',
            'D',
            'E',
            'F',
            'G',
            'H',
            'I',
            'J',
            'K',
            'L',
            'M',
            'N',
            'O',
            'P',
            'Q',
            'R',
            'S',
            'T',
            'U',
            'V',
            'W',
            'X',
            'Y',
            'Z',
            'a',
            'b',
            'c',
            'd',
            'e',
            'f',
            'g',
            'h',
            'i',
            'j',
            'k',
            'l',
            'm',
            'n',
            'o',
            'p',
            'q',
            'r',
            's',
            't',
            'u',
            'v',
            'w',
            'x',
            'y',
            'z',
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            '+',
            '-',
            '—',
            '.',
            '_',
            '@',
            ',',
            ')',
            '('
        )
        var start = 0
        var end = 0

        while (start < allPossibleCharsList.size) {
            while (end < allPossibleCharsList.size) {
                val charOne = allPossibleCharsList[start]
                val charTwo = allPossibleCharsList[end]
                val word: String =
                    StringBuilder(currentWord).append(charOne).append(charTwo).toString()
                val wordHash: String = getTheHashFormula(word, email)
                if (wordHash == currentHash) {
                    return StringBuilder("").append(charOne).append(charTwo).toString()
                }
                end++
            }
            end = 0
            start++
        }
        return "inCorrectInput"
    }
}

