/*
 * Copyright 2017 Shinya Mochida
 * 
 * Licensed under the Apache License,Version2.0(the"License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,software
 * Distributed under the License is distributed on an"AS IS"BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lisb.daab

data class ListenerOption(val id: String)

data class SendingMessage(val text: String)

data class MessageDestination(val room: String)

data class Stamp(
        @JsName("stamp_set") val stampSet: String,
        @JsName("stamp_index") val stampIndex: String,
        val text: String?)

data class Question(val question: String)

data class CloseQuestion(@JsName("close_yesno") val closeYesNo: LongValue)

data class SelectStamp(val question: String, val options: List<String>)

data class CloseSelect(@JsName("close_select") val closeSelect: LongValue)

data class TaskStamp(val title: String, @JsName("closing_type") val closingType: Int)

data class CloseTask(@JsName("close_task") val closeTask: LongValue)

data class SendFile(val path: String, val name: String?, val type: String?, val text: String?)

data class SendFiles(val path: List<String>, val name: List<String>?, val type: List<String>?, val text: List<String>?)
