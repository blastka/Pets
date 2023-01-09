package com.example.myapplication

import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest {
    @Test
    fun test_first_start_not_authorization(){
        val repository = FakeRepository(false)
        val communication = FakeMainCommunication.Base()
        val viewModel = MainViewModel(repository, communication)
        viewModel.init(isFirstRun = true)
        assertEquals(true, communication.checkCalledCount(1))
        assertEquals(true, communication.isSame(UiState.NotAuthorization))
        viewModel.init(isFirstRun = false)
        assertEquals(true, communication.checkCalledCount(1))
    }

    @Test
    fun test_start_with_authorization(){
        val repository = FakeRepository(true)
        val communication = FakeMainCommunication.Base()
        val viewModel = MainViewModel(repository, communication)
        viewModel.init(isFirstRun = true)
        assertEquals(true, communication.checkCalledCount(1))
        assertEquals(true, communication.isSame(UiState.Authorization))
        viewModel.init(isFirstRun = false)
        assertEquals(true, communication.checkCalledCount(1))
    }
}

/**
 * Он не лезет в sharedPreference
 */
private class FakeRepository(private val authorization: Boolean): MainRepository{
    fun authorization(): Boolean{
        return authorization
    }
}

/**
 * Обертка над livedata
 */
private interface FakeMainCommunication: MainCommunication{
    fun checkCalledCount(count: Int): Boolean
    fun isSame(uiState: UiState): Boolean

    class Base(): FakeMainCommunication{
        private lateinit var state: UiState
        private var callCount = 0

        override fun checkCalledCount(count: Int): Boolean {
            TODO("Not yet implemented")
        }

        override fun isSame(uiState: UiState): Boolean {
            return state.equals(uiState)
        }

        override fun put(value: UiState){
            callCount++
            state = value
        }

    }
}

