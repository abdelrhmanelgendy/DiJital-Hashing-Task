package com.tasks.dijitalgarajihashingtask.util

import com.emmanuel.dijitalgaraj.quiz.SearchHashUseCase
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchHashUseCaseTest{

    @Test
    fun `test solving hashed email with empty email as input returns null`()
    {
        val email =""
        val searchHashUseCase = SearchHashUseCase()(email = email)
        assertEquals(searchHashUseCase,null)
    }

    @Test
    fun `test solving hashed email with empty hashed value as input returns null`()
    {
        val hashedValue =""
        val searchHashUseCase = SearchHashUseCase()(hashedValue = hashedValue)
        assertEquals(searchHashUseCase,null)
    }

}