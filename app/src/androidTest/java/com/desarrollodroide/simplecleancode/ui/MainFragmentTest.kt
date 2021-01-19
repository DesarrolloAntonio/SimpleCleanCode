package com.desarrollodroide.simplecleancode.ui

import android.os.SystemClock
import org.junit.Test
import org.junit.runner.RunWith
import com.desarrollodroide.simplecleancode.R
import com.desarrollodroide.simplecleancode.domain.DummyObjectRepository
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.desarrollodroide.simplecleancode.model.DummyObject
import com.desarrollodroide.simplecleancode.model.Resource
import com.desarrollodroide.simplecleancode.util.RecyclerViewMatchers.hasHolderItemAtPosition
import com.desarrollodroide.simplecleancode.util.RecyclerViewMatchers.hasItemCount
import com.desarrollodroide.simplecleancode.util.RecyclerViewMatchers.withTextViewText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class MainFragmentTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)

    @Mock
    lateinit var repository: DummyObjectRepository

    private val json = this::class.java.getResource("/sample_response.json")?.readText()?:""
    private val response = Gson().fromJson<List<DummyObject>>(json, object : TypeToken<List<DummyObject>>() {}.type)

    private val viewModel by lazy {
        MainFragmentViewModel(repository)
    }

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun test_views() = testCoroutineScope.runBlockingTest {
        launchFragmentInContainer(null, R.style.AppTheme) {
            MainFragment(viewModel)
        }

        `when`(repository.getDummyObjects()).thenReturn(Resource.Success(response))
        onView(withId(R.id.btnGetData)).perform(click())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).check(matches(hasItemCount(response.size)))
        onView(withId(R.id.recyclerView)).check(matches(hasHolderItemAtPosition(0,
            withTextViewText(R.id.tv_title, response[0].title))))
        onView(withId(R.id.recyclerView)).check(matches(hasHolderItemAtPosition(0,
            withTextViewText(R.id.tv_subTitle, response[0].body))))
    }
}