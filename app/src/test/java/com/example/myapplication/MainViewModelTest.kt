package com.example.myapplication

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.myapplication.animal.domain.AnimalFact
import com.example.myapplication.animal.domain.AnimalInteractor
import com.example.myapplication.animal.domain.AnimalResult
import com.example.myapplication.animal.presentation.*
import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest {
    @Test
    fun test_init() {
        val interactor = FakeAnimalInteractor()
        val communication = FakeAnimalCommunication.Base()
        val viewModel = AnimalViewModel(
            FakeManageResources(),
            interactor,
            communication,
            AnimalResultMapper(communication, AnimalUiMapper())
        )
        viewModel.init(isFirstRun = true)
        interactor.changeExpectedResult(AnimalResult.Success())
        assertEquals(1, communication.progressCalledList.size)
        assertEquals(true, communication.progressCalledList[0])

        assertEquals(2, communication.progressCalledList.size)
        assertEquals(false, communication.progressCalledList[1])

        assertEquals(1, communication.stateCalledList.size)
        assertEquals(UiState.Success(), communication.stateCalledList[0])

        assertEquals(0, communication.animalList.size)
        assertEquals(1, communication.timesShowList)

        interactor.changeExpectedResult(AnimalResult.Failure("No internet connection."))
        viewModel.fetchFact()

        assertEquals(3, communication.progressCalledList.size)
        assertEquals(true, communication.progressCalledList[2])

        assertEquals(1, interactor.fetchAboutAnimalCalledList.size)

        assertEquals(4, communication.progressCalledList.size)
        assertEquals(false, communication.progressCalledList[2])

        assertEquals(2, communication.stateCalledList.size)
        assertEquals(UiState.Error("No internet connection."), communication.stateCalledList[1])

        assertEquals(1, communication.timesShowList)

        viewModel.init(isFirstRun = false)
        assertEquals(4, communication.progressCalledList.size)
        assertEquals(2, communication.stateCalledList.size)
        assertEquals(1, communication.timesShowList)
    }

    @Test
    fun test_empty() {
        val interactor = FakeAnimalInteractor()
        val communication = FakeAnimalCommunication.Base()
        val viewModel = AnimalViewModel(
            FakeManageResources(),
            interactor,
            communication,
            AnimalResultMapper(communication, AnimalUiMapper())
        )
        viewModel.fetchAnimalData("")

        assertEquals(0, interactor.fetchAboutAnimalCalledList.size)

        assertEquals(0, communication.progressCalledList.size)
        assertEquals(1, communication.stateCalledList.size)
        assertEquals(UiState.Error("Entered animal is empty."), communication.stateCalledList[0])
        assertEquals(0, communication.timesShowList)

    }

    @Test
    fun test_fact_about_animal() {
        val interactor = FakeAnimalInteractor()
        val communication = FakeAnimalCommunication.Base()
        val viewModel = AnimalViewModel(
            FakeManageResources(),
            interactor,
            communication,
            AnimalResultMapper(communication, AnimalUiMapper())
        )
        interactor.changeExpectedResult(
            AnimalResult.Success(
                listOf(
                    AnimalFact(
                        "cat",
                        "fact about cat"
                    ),
                )
            )
        )
        viewModel.fetchAnimalData("cat")

        assertEquals(1, communication.progressCalledList.size)
        assertEquals(true, communication.progressCalledList[0])

        assertEquals(1, interactor.fetchAboutAnimalCalledList.size)
        assertEquals(AnimalFact("cat", "fact about cat"), interactor.fetchAboutAnimalCalledList[0])


        assertEquals(2, communication.progressCalledList.size)
        assertEquals(false, communication.progressCalledList[1])

        assertEquals(1, communication.stateCalledList.size)
        assertEquals(UiState.Success(), communication.stateCalledList[0])

        assertEquals(1, communication.timesShowList)
        assertEquals(AnimalUi("cat", "fact about cat"), communication.animalList[0])
    }

}

private class FakeAnimalInteractor() : AnimalInteractor {

    private var result: AnimalResult = AnimalResult.Success()

    val initCalledList = mutableListOf<AnimalResult>()
    val fetchAboutAnimalCalledList = mutableListOf<AnimalResult>()

    fun changeExpectedResult(newResult: AnimalResult) {
        result = newResult
    }

    override suspend fun init(): AnimalResult {
        initCalledList.add(result)
        return result
    }

    override suspend fun factAboutAnimal(animalList: String): AnimalResult {
        fetchAboutAnimalCalledList.add(result)
        return result
    }
}

private interface FakeAnimalCommunication : AnimalCommunication {
    class Base() : FakeAnimalCommunication {
        val progressCalledList = mutableListOf<Boolean>()
        val stateCalledList = mutableListOf<UiState>()
        val animalList = mutableListOf<AnimalUi>()
        var timesShowList = 0

        override fun showProgress(show: Boolean) {
            progressCalledList.add(show)
        }

        override fun showState(uiState: UiState) {
            stateCalledList.add(uiState)
        }

        override fun showList(list: List<AnimalUi>) {
            timesShowList++
            animalList.addAll(list)
        }

        override fun observeProgress(owner: LifecycleOwner, observe: Observer<Boolean>) = Unit
        override fun observeState(owner: LifecycleOwner, observe: Observer<UiState>) = Unit
        override fun observeList(owner: LifecycleOwner, observe: Observer<List<AnimalUi>>) = Unit

    }
}

class FakeManageResources: ManageResources {
    var text = ""

    override fun string(id: Int): String {
        return text
    }

}

