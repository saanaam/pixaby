package com.sanam.yavarpour.commontest

import com.sanam.yavarpour.core.AppDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher

private val testScheduler = TestCoroutineScheduler()

@OptIn(ExperimentalCoroutinesApi::class)
val testDispatchers = AppDispatchers(
    main = UnconfinedTestDispatcher(testScheduler, "main-test-dispatcher"),
    io = UnconfinedTestDispatcher(testScheduler, "iO-test-dispatcher"),
    default = UnconfinedTestDispatcher(testScheduler, "default-test-dispatcher")
)