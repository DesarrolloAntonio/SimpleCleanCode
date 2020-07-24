package com.desarrollodroide.simplecleancode.ui

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.desarrollodroide.simplecleancode.domain.DummyObjectRepository
import com.desarrollodroide.simplecleancode.model.DummyObject
import com.desarrollodroide.simplecleancode.model.Resource
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(Log::class)
@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)
    private val repository = mock<DummyObjectRepository>()
    private val viewModel  by lazy { MainViewModel(repository) }
    private val observerDummyObjects: Observer<Resource<List<DummyObject>?>> = mock()

    @Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        PowerMockito.mockStatic(Log::class.java)
        Dispatchers.setMain(testDispatcher)
        viewModel.dummyObjects.observeForever(observerDummyObjects)
    }

    @Test
    fun `should success when fetchFromServer returns proper data`() = testCoroutineScope.runBlockingTest {
        whenever(repository.getDummyObjects()).thenReturn(Resource.Success(listOf(DummyObject(1, 1, "Title", "Body"))))
        viewModel.getData()
        advanceTimeBy(500)
        verify(repository).getDummyObjects()
        // Changes two times , loading and success
        verify(observerDummyObjects, times(2)).onChanged(any())

        verify(observerDummyObjects, times(1))
            .onChanged(ArgumentMatchers.any(Resource.Loading::class.java) as Resource<List<DummyObject>?>?)
        verify(observerDummyObjects, times(1))
            .onChanged(ArgumentMatchers.any(Resource.Success::class.java) as Resource<List<DummyObject>?>?)
        verify(observerDummyObjects, never())
            .onChanged(ArgumentMatchers.any(Resource.Error::class.java) as Resource<List<DummyObject>?>?)
    }

    @Test
    fun `should fail when fetchFromServer throws exception`()  = testCoroutineScope.runBlockingTest {
        val error = Error()
        whenever(repository.getDummyObjects()).thenThrow(error)

        // act
        viewModel.getData()
        advanceTimeBy(500)

        // assert - Verify if this method is called
        verify(repository).getDummyObjects()

        // Changes two times , loading and error
        verify(observerDummyObjects, times(2)).onChanged(any())

        verify(observerDummyObjects, times(1))
            .onChanged(ArgumentMatchers.any(Resource.Loading::class.java) as Resource<List<DummyObject>?>?)
        verify(observerDummyObjects, times(1))
            .onChanged(ArgumentMatchers.any(Resource.Error::class.java) as Resource<List<DummyObject>?>?)
        verify(observerDummyObjects, never())
            .onChanged(ArgumentMatchers.any(Resource.Success::class.java) as Resource<List<DummyObject>?>?)
    }
}
