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
@file:JsQualifier("com.lisb.daab")

package com.lisb.daab

import com.lisb.daab.message.*
import kotlin.js.*

external interface Robot {
    val adapterName: String

    fun listen(regex: Regex, callback: (Response) -> Unit): Unit
    fun listen(regex: Regex, options: ListenerOption, callback: (Response) -> Unit): Unit

    fun hear(regex: Regex, callback: (Response) -> Unit): Unit
    fun hear(regex: Regex, options: ListenerOption, callback: (Response) -> Unit): Unit
}

external interface Response {
    val robot: Robot
    val message: TextMessage
    val match: RegExpMatch
    val envelope: Envelope

    fun send(vararg messages: String): Unit
    fun send(destination: MessageDestination, vararg messages: String): Unit
    fun send(vararg messages: SendingMessage): Unit
    fun send(destination: MessageDestination, vararg messages: SendingMessage): Unit

    fun send(stamp: StampWithHandler): Unit

    fun send(question: QuestionWithHandler): Unit
    fun send(closeQuestion: CloseQuestionWithHandler): Unit

    fun send(selectStamp: SelectStampWithHandler): Unit
    fun send(closeSelect: CloseSelectWithHandler): Unit

    fun send(taskStamp: TaskStampWithHandler): Unit
    fun send(closeTask: CloseTaskWithHandler): Unit

    fun send(sendFile: SendFileWithHandler): Unit
    fun send(sendFiles: SendFilesWithHandler): Unit
}
