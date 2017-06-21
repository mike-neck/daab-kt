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

interface WithHandler<in E, in D> {
    @JsName("onsend") val onSend: (MessageSent<E, D>) -> Unit
    @JsName("onread") val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
}

interface MessageSent<out E, out D> {
    val context: E
    val listeners: dynamic
    val talk: Talk
    val message: MessageDetail<D>
} 

open class SendingMessage(val text: String) 

class SendingMessageWithHandler(
        text: String,
        override val onSend: (MessageSent<SendingMessage, SendingMessage>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): SendingMessage(text), WithHandler<SendingMessage, SendingMessage>


// TODO now type is not defined.
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
        override val onSend: (MessageSent<Question, Question>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): Question(question, listing), WithHandler<Question, Question>

open class CloseQuestion(@JsName("close_yesno") val closeYesNo: String) 

class CloseQuestionWithHandler(
        closeYesNo: String,
        override val onSend: (MessageSent<CloseQuestion, CloseQuestionResult>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): CloseQuestion(closeYesNo), WithHandler<CloseQuestion, CloseQuestionResult>



open class SelectStamp(val question: String, val options: Array<String>, val listing: Boolean?)

class SelectStampWithHandler(
        question: String,
        options: Array<String>,
        listing: Boolean?,
        override val onSend: (MessageSent<SelectStamp, SelectStamp>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): SelectStamp(question, options, listing), WithHandler<SelectStamp, SelectStamp>

open class CloseSelect(@JsName("close_select") val closeSelect: LongValue) 


open class TaskStamp(val title: String, @JsName("closing_type") val closingType: Int) 

class TaskStampWithHandler(
        title: String,
        closingType: Int,
        override val onSend: (MessageSent<TaskStamp, TaskStamp>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): TaskStamp(title, closingType), WithHandler<TaskStamp, TaskStamp>

open class CloseTask(@JsName("close_task") val closeTask: LongValue)

open class SendFile(
        val path: String,
        val name: String?,
        val type: String?,
        val text: String?) 

// TODO it's inspecting now
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

// TODO it's inspecting now
class SendFilesWithHandler(
        path: Array<String>,
        name: Array<String>?,
        type: Array<String>?,
        text: String?,
        override val onSend: (MessageSent<SendFiles>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): SendFiles(path, name, type, text), WithHandler<SendFiles>
