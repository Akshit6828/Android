/*
 * Copyright (c) 2021 DuckDuckGo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duckduckgo.privacy.config.store

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RealPrivacyFeatureTogglesRepositoryTest {

    lateinit var testee: RealPrivacyFeatureTogglesRepository

    private val mockDatabase: PrivacyConfigDatabase = mock()
    private val mockPrivacyFeatureTogglesDao: PrivacyFeatureTogglesDao = mock()

    @Before
    fun before() {
        whenever(mockDatabase.privacyFeatureTogglesDao()).thenReturn(mockPrivacyFeatureTogglesDao)
        testee = RealPrivacyFeatureTogglesRepository(mockDatabase)
    }

    @Test
    fun whenDeleteAllThenDeleteAllCalled() {
        testee.deleteAll()

        verify(mockPrivacyFeatureTogglesDao).deleteAll()
    }

    @Test
    fun whenGetThenGetCalled() {
        testee.get("test")

        verify(mockPrivacyFeatureTogglesDao).get("test")
    }

    @Test
    fun whenInsertThenInsertCalled() {
        val privacyFeatureToggle = PrivacyFeatureToggles("feature", true)
        testee.insert(privacyFeatureToggle)

        verify(mockPrivacyFeatureTogglesDao).insert(privacyFeatureToggle)
    }
}
