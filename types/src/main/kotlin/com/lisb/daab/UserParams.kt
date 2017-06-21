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

class MessageDestination(val room: String)

interface WithHandler<in E> {
    @JsName("onsend") val onSend: (MessageSent<E>) -> Unit
    @JsName("onread") val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
}

interface MessageSent<out E> {
    val context: E
    val listeners: dynamic
    val talk: Talk
} 

open class SendingMessage(val text: String) 

class SendingMessageWithHandler(
        text: String,
        override val onSend: (MessageSent<SendingMessage>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): SendingMessage(text), WithHandler<SendingMessage>



open class Stamp(
        @JsName("stamp_set") val stampSet: Int,
        @JsName("stamp_index") val stampIndex: LongValue,
        val text: String?)

class StampWithHandler(
        stampSet: Int,
        stampIndex: LongValue,
        text: String?,
        override val onSend: (MessageSent<Stamp>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): Stamp(stampSet, stampIndex, text), WithHandler<Stamp>



open class Question(val question: String, val listing: Boolean?)

class QuestionWithHandler(
        question: String,
        listing: Boolean?,
        override val onSend: (MessageSent<Question>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): Question(question, listing), WithHandler<Question>

open class CloseQuestion(@JsName("close_yesno") val closeYesNo: LongValue) 



open class SelectStamp(val question: String, val options: Array<String>, val listing: Boolean?)

class SelectStampWithHandler(
        question: String,
        options: Array<String>,
        listing: Boolean?,
        override val onSend: (MessageSent<SelectStamp>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): SelectStamp(question, options, listing), WithHandler<SelectStamp>

open class CloseSelect(@JsName("close_select") val closeSelect: LongValue) 


open class TaskStamp(val title: String, @JsName("closing_type") val closingType: Int) 

class TaskStampWithHandler(
        title: String,
        closingType: Int,
        override val onSend: (MessageSent<TaskStamp>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): TaskStamp(title, closingType), WithHandler<TaskStamp>

open class CloseTask(@JsName("close_task") val closeTask: LongValue)

open class SendFile(
        val path: String,
        val name: String?,
        val type: String?,
        val text: String?) 

class SendFileWithHandler(
        path: String,
        name: String?,
        type: String?,
        text: String?,
        override val onSend: (MessageSent<SendFile>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): SendFile(path, name, type, text), WithHandler<SendFile>

open class SendFiles(
        val path: Array<String>,
        val name: Array<String>?,
        val type: Array<String>?,
        val text: String?) 

class SendFilesWithHandler(
        path: Array<String>,
        name: Array<String>?,
        type: Array<String>?,
        text: String?,
        override val onSend: (MessageSent<SendFiles>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): SendFiles(path, name, type, text), WithHandler<SendFiles>
