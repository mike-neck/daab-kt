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
import kotlin.js.RegExpMatch

external interface Response<out M: Message> {
    val robot: Robot
    val message: M
    val match: RegExpMatch
    val envelope: Envelope

    fun send(vararg messages: String): Unit
    fun send(destination: MessageDestination, vararg messages: String): Unit

    fun send(vararg messages: AfterMessageHandler<Any, Any>): Unit
    fun send(destination: MessageDestination, vararg messages: AfterMessageHandler<Any, Any>): Unit

    fun send(text: TextAfterMessageHandler): Unit

    fun send(stamp: StampAfterMessageHandler): Unit

    fun send(question: QuestionAfterMessageHandler): Unit
    fun send(closeQuestion: CloseQuestionAfterMessageHandler): Unit

    fun send(answerQuestion: AnswerQuestionAfterMessageHandler): Unit

    fun send(selectStamp: SelectStampAfterMessageHandler): Unit
    fun send(closeSelect: CloseSelectAfterMessageHandler): Unit

    fun send(answerSelect: AnswerSelectAfterMessageHandler): Unit

    fun send(taskStamp: TaskStampAfterMessageHandler): Unit
    fun send(closeTask: CloseTaskAfterMessageHandler): Unit

    fun send(answerTask: AnswerTaskAfterMessageHandler): Unit

    fun send(sendFile: SendFileAfterMessageHandler): Unit
    fun send(sendFileWithMessage: SendFileAfterMessageMessageAndHandler): Unit
    fun send(sendFiles: SendFilesAfterMessageHandler): Unit

    fun download(file: ReceivedFile, callback: (String) -> Unit): Unit
}

external interface ActionResponse<out E>: Response<TextMessage> {
    val json: E
}
