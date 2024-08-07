package com.architectureclean.recipes.rule

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockDispatcher: Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when(request.requestUrl?.pathSegments?.get(0)) {
            "random.php" -> MockResponse().fromJson("mock_recipes.json")
            else -> MockResponse().setResponseCode(404)
        }
        }
    }
