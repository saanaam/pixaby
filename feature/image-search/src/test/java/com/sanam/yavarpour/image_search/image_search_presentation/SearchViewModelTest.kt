package com.sanam.yavarpour.image_search.image_search_presentation

import androidx.paging.*
import com.google.common.truth.Truth
import com.sanam.yavarpour.image_search.domain.usecase.SearchImageUseCase
import com.sanam.yavarpour.image_search.image_search_presentation.TestData.sampleTestQuery1
import com.sanam.yavarpour.image_search.image_search_presentation.TestData.sampleTestQuery2
import com.sanam.yavarpour.image_search.image_search_presentation.TestData.sampleTestQuery3
import com.sanam.yavarpour.image_search.presentation.SearchEvent
import com.sanam.yavarpour.image_search.presentation.SearchViewModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import com.sanam.yavarpour.image_search.rule.MainDispatcherRule

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get: Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var searchViewModel: SearchViewModel
    
    private lateinit var searchImageUseCase: SearchImageUseCase

    @Before
    fun setup() {
        searchImageUseCase = mockk(relaxed = true)
        searchViewModel = SearchViewModel(searchImageUseCase)
    }

    @Test
    fun `searchImage less than 3 chars do nothing`() = runTest {
        searchViewModel.searchImage(sampleTestQuery3)
        coVerify(inverse = true) { searchImageUseCase.invoke("") }
    }

    @Test
    fun `searchImage more than 3 chars set value successfully`() = runTest {
        val pagingData = PagingData.from(
            TestData.hitList
        )
        coEvery { searchImageUseCase.invoke("") } returns flowOf(pagingData)

        searchViewModel.searchImage(sampleTestQuery1)

        searchViewModel.hitListPagingData.first()

        searchViewModel.hitListPagingData.value.map {
            Truth.assertThat(it.user).isEqualTo(TestData.hitList.first().user)
        }
    }


    @Test
    fun `onEvent calls with onTextChange event changes the state value calls searchImage`() = runTest {
        val v = SearchEvent.OnTextChange(sampleTestQuery2)
        searchViewModel.onEvent(v)
        val result = searchViewModel.state.first()
        verify { searchViewModel.searchImage() }
        Truth.assertThat(sampleTestQuery2).isEqualTo(result.searchedValue)
    }

    @Test
    fun `onEvent calls with OnSearchClick event changes the state value calls searchImage`() = runTest {
        val v = SearchEvent.OnSearchClick(sampleTestQuery2)
        searchViewModel.onEvent(v)
        val result = searchViewModel.state.first()
        verify { searchViewModel.searchImage() }
        Truth.assertThat(sampleTestQuery2).isEqualTo(result.searchedValue)
    }

    @Test
    fun `onEvent calls with OnCloseClick event changes the state value to null`() = runTest {
        val v = SearchEvent.OnCloseClick
        searchViewModel.onEvent(v)
        val result = searchViewModel.state.first()
        Truth.assertThat(result.searchedValue).isEqualTo("All")
    }

}
