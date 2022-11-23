package com.example.androidfeed

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.androidfeed.data.model.Address
import com.example.androidfeed.data.model.Company
import com.example.androidfeed.data.model.Geo
import com.example.androidfeed.data.model.User
import com.example.androidfeed.domain.repository.UsersRepository
import com.example.androidfeed.ui.viewmodel.UsersViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UserUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var context: Application

    private lateinit var viewModel: UsersViewModel
    private lateinit var repository: UsersRepository

    private lateinit var userEmptyList: List<User>
    private lateinit var userList: List<User>

    private lateinit var isViewLoadingObserver: Observer<Boolean>
    private lateinit var onMessageErrorObserver: Observer<Any>
    private lateinit var emptyListObserver: Observer<Boolean>
    private lateinit var onRenderUserObserver: Observer<Any?>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        `when`(context.applicationContext).thenReturn(context)
        repository = UsersRepository()
        viewModel = UsersViewModel(context)

        mockData()
        setupObservers()
    }

    private fun setupObservers() {
        isViewLoadingObserver = mock(Observer::class.java) as Observer<Boolean>
        onMessageErrorObserver = mock(Observer::class.java) as Observer<Any>
        emptyListObserver = mock(Observer::class.java) as Observer<Boolean>
        onRenderUserObserver = mock(Observer::class.java) as Observer<Any?>
    }

    @Test
    fun `retrieve users with ViewModel and Repository returns full data`() {
        with(viewModel) {
            fetchUserByUsername("Bret")
            userData?.observeForever(onRenderUserObserver)
        }
        viewModel.userData =repository.getUserByUsername("Bret")
        Assert.assertTrue(viewModel.userData?.value?.size == 1)
    }
    private fun mockData() {
        userEmptyList = emptyList()
        val mockList: MutableList<User> = mutableListOf()
        var mockListAddress: Address
        var mockListCompany: Company
        var mockListGeo: Geo

        mockListCompany = Company(
            "comapanyAnme",
            "Test Catch",
            "bs"
        )
        mockListGeo = Geo(
                2.0,
                44.67
            )

        mockListAddress=   Address(
                "Street",
                "Suite ",
                "Chennai",
                "445667",
                 mockListGeo
            )

        mockList.add(
            User(
                0,
                "Museo Nacional de Arqueología, Antropología e Historia del Perú",
                "test",
                "test@gmail.com",
                mockListAddress,
                "1234567890",
                "www.test.com",
                mockListCompany
            )
        )

        userList = mockList.toList()
    }
}

