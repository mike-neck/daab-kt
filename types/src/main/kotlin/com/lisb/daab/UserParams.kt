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

external interface OnReadHandler {
    @JsName("onread") val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)?
}

external interface AfterMessageHandler<in E, in D>: OnReadHandler {
    @JsName("onsend") val onSend: ((MessageSent<E, D>) -> Unit)?
}

external interface MessageSent<out E, out D> {
    val context: E
    val listeners: dynamic
    val talk: Talk
    val message: MessageDetail<D>
}

external interface MessageDetail<out E> {
    val type: Int
    val content: E
    val createdAt: LongValue
    val id: String
    @JsName("id_i64") val idI64: LongValue
    val user: User
    val userId: String
    val talk: TalkDetail
    val talkId: String
}

external interface TalkDetail: Talk {
    @JsName("id_i64") val idI64: LongValue
    val topic: String?
    val users: Array<User>
    val domain: Domain
    @JsName("domainId_i64") val domainIdI64: LongValue
}

external interface CloseQuestionResult {
    @JsName("in_reply_to") val inReplyTo: LongValue
    val responses: Array<QuestionResult>
    @JsName("last_response") val lastResponse: Int?
    val question: String
    val listing: Boolean
}

external interface QuestionResult {
    val content: String
    val count: Int?
}

external interface CloseTaskResult {
    @JsName("in_reply_to") val inReplyTo: LongValue
    val responses: Array<QuestionResult>
    @JsName("last_response") val lastResponse: Int?
    val title: String
    @JsName("closing_type") val closingType: Int
}
