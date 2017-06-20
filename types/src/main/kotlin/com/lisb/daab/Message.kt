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

import kotlin.js.RegExpMatch

external open class Message(user: User, done: Boolean): MessageProperty {
    override val user: User = definedExternally
    override val done: Boolean = definedExternally
    override val room: String = definedExternally
}

external open class TextMessage: Message {
    val text: String
    val id: String
    val rooms: Map<String, Room>
    val roomType: Int
    val roomTopic: String?
    val roomUsers: Array<User>

    fun match(regex: Regex): RegExpMatch?
    override fun toString(): String
}

external class EnterMessage: Message
external class LeaveMessage: Message
external class TopicMessage: TextMessage
external class CatchAllMessage: Message
