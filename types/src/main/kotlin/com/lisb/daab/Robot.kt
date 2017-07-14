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
import com.lisb.daab.receive.Receive
import kotlin.js.*

external interface Robot {
    val adapterName: String

    fun listen(condition: (Message) -> Boolean, callback: (Response<TextMessage>) -> Unit): Unit
    fun listen(condition: (Message) -> Boolean, options: ListenerOption, callback: (Response<TextMessage>) -> Unit): Unit

    /**
     * detects a change of a topic of a room, and calls a given callback.
     */
    fun topic(callback: (Response<TopicMessage>) -> Unit): Unit

    /**
     * detects a entrance of a new user into a room, and calls a given callback.
     */
    fun enter(callback: (Response<EnterMessage>) -> Unit): Unit

    /**
     * detects a user leaving from a room, and calls a given callback.
     */
    fun leave(callback: (Response<LeaveMessage>) -> Unit): Unit

    /**
     * calls a given callback on being invited to a room.
     */
    fun join(callback: (Response<JoinMessage>) -> Unit): Unit

    // hear : reaction to group talk.

    fun hear(regex: RegExp, callback: (Response<TextMessage>) -> Unit): Unit
    fun hear(regex: RegExp, options: ListenerOption, callback: (Response<TextMessage>) -> Unit): Unit

    fun hear(stamp: Receive.Stamp, action: (ActionResponse<Stamp>) -> Unit): Unit

    fun hear(question: Receive.YesNo, action: (ActionResponse<ReceivedQuestion>) -> Unit): Unit

    fun hear(select: Receive.Select, action: (ActionResponse<ReceivedSelect>) -> Unit)

    fun hear(task: Receive.Task, action: (ActionResponse<ReceivedTask>) -> Unit): Unit

    fun hear(file: Receive.File, action: (ActionResponse<ReceivedFile>) -> Unit): Unit
    fun hear(files: Receive.FileWithMessage, action: (ActionResponse<ReceivedFilesWithMessage>) -> Unit): Unit
    fun hear(files: Receive.Files, action: (ActionResponse<ReceivedFiles>) -> Unit): Unit

    // respond : reaction to pair talk.

    fun respond(regex: RegExp, callback: (Response<TextMessage>) -> Unit): Unit

    fun respond(stamp: Receive.Stamp, action: (ActionResponse<Stamp>) -> Unit): Unit
    // TODO Received Question does not have last_response property but when user closes a question last_response
    // TODO property will be added.
    // TODO yesnot を登録していると、セレクトスタンプの終了も引っかかるのは、バグだと思われる
    fun respond(question: Receive.YesNo, action: (ActionResponse<ReceivedQuestion>) -> Unit): Unit

    fun respond(select: Receive.Select, action: (ActionResponse<ReceivedSelect>) -> Unit)

    fun respond(task: Receive.Task, action: (ActionResponse<ReceivedTask>) -> Unit): Unit

    fun respond(file: Receive.File, action: (ActionResponse<ReceivedFile>) -> Unit): Unit
    fun respond(files: Receive.FileWithMessage, action: (ActionResponse<ReceivedFilesWithMessage>) -> Unit): Unit
    fun respond(files: Receive.Files, action: (ActionResponse<ReceivedFiles>) -> Unit): Unit
}

