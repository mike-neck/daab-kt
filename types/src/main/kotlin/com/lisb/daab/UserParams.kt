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

class SendingMessage(
        val text: String,
        @JsName("onsend") val onSend: ((Any) -> Unit)?) 

class MessageDestination(
        val room: String,
        @JsName("onsend") val onSend: ((Any) -> Unit)?)

class Stamp(
        @JsName("stamp_set") val stampSet: String,
        @JsName("stamp_index") val stampIndex: String,
        val text: String?,
        @JsName("onsend") val onSend: ((Any) -> Unit)?)

class Question(
        val question: String,
        @JsName("onsend") val onSend: ((Any) -> Unit)?)

class CloseQuestion(
        @JsName("close_yesno") val closeYesNo: LongValue,
        @JsName("onsend") val onSend: ((Any) -> Unit)?) 

class SelectStamp(
        val question: String, val options: List<String>,
        @JsName("onsend") val onSend: ((Any) -> Unit)?)

class CloseSelect(
        @JsName("close_select") val closeSelect: LongValue,
        @JsName("onsend") val onSend: ((Any) -> Unit)?) 

class TaskStamp(
        val title: String, @JsName("closing_type") val closingType: Int,
        @JsName("onsend") val onSend: ((Any) -> Unit)?) 

class CloseTask(
        @JsName("close_task") val closeTask: LongValue,
        @JsName("onsend") val onSend: ((Any) -> Unit)?)

class SendFile(
        val path: String,
        val name: String?,
        val type: String?,
        val text: String?,
        @JsName("onsend") val onSend: ((Any) -> Unit)?) 

class SendFiles(
        val path: List<String>,
        val name: List<String>?,
        val type: List<String>?,
        val text: List<String>?,
        @JsName("onsend") val onSend: ((Any) -> Unit)?) 
