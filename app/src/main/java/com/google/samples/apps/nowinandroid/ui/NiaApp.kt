/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.nowinandroid.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.samples.apps.nowinandroid.feature.interests.InterestsRoute
import com.google.samples.apps.nowinandroid.feature.topic.TopicRoute

@Composable
fun NiaApp() {
    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            var selectedTopicId: String? by remember { mutableStateOf(null) }
            var isTopicListVisible: Boolean by remember { mutableStateOf(false) }

            InterestsRoute(
                onTopicClick = { topicId ->
                    selectedTopicId = topicId
                    isTopicListVisible = true
                },
            )

            AnimatedVisibility(
                visible = isTopicListVisible,
                enter = slideInHorizontally(),
                exit = slideOutHorizontally()
            ) {
                selectedTopicId?.let { id ->
                    Surface {
                        TopicRoute(
                            topicId = id,
                            onBackClick = {
                                isTopicListVisible = false
                            },
                            onTopicClick = { topicId ->
                                selectedTopicId = topicId
                            }
                        )
                    }
                }
            }
        }
    }
}

