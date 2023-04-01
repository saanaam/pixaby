package com.sanam.yavarpour.image_search.rule

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MainDispatcherRule @OptIn(ExperimentalCoroutinesApi::class) constructor(private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()) :
    TestWatcher() {

    override fun starting(description: Description?) {
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }

}