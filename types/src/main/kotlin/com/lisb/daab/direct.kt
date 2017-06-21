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

external interface User {
    val id: String
    val status: String
    val displayName: String
    val canonicalDisplayName: String
    val phoneticDisplayName: String
    val canonicalPhoneticDisplayName: String
    val profileImageUrl: String
    val updatedAt: LongValue
    val domainId: LongValue
    @JsName("id_i64")
    val idI64: LongValue
    val profileContact: Array<ProfileItem>
    val email: String
    val name: String
    @JsName("profile_url")
    val profileUrl: String
}

external interface UserDetail: User {
    val room: String
    val rooms: Map<String, Room>
}

external class LongValue {
    val high: Int
    val low: Int
}

external interface ProfileItem {
    val profileItemId: Int
    val value: String
    val canonicalValue: String
}

external interface Room {
    val id: String
    val domainId: String
    val type: Int
    val name: String?
    val iconUrl: String?
    val userIds: Array<LongValue>
    val guestIds: Array<LongValue>?
    val updatedAt: LongValue
    val leftUsers: Any?
    @JsName("id_i64")
    val idI64: LongValue
    val topic: String?
    val users: Array<User>
    val domain: Domain
}

external interface Domain {
    val id: String
    val updatedAt: LongValue
    val domainInfo: DomainInfo
    val contract: Contract
    val profileDefinition: ProfileDefinition
    val setting: Setting
    val role: Role
    val closed: Boolean
    @JsName("id_i64")
    val idI64: LongValue
}

external interface DomainInfo {
    val name: String
    val logoUrl: String?
    val frozen: Any?
}

external interface Contract {
    val id: LongValue
    val quota: LongValue
    val plan: LongValue
    val solutionIds: Any?
}

external interface ProfileDefinition {
    val domainId: LongValue
    val itemDefinitions: Array<Any>
}

external interface Setting {
    val allowAttachmentType: Any
    val allowSaveAttachmentsToDevice: Boolean
}

external interface Role {
    val type: LongValue
    val allowGuests: Boolean
    val allowCreateAttachments: Boolean
    val allowReadAttachments: Boolean
    val allowCreateAnnouncement: Boolean
    val allowReadAnnouncements: Boolean
    val allowListUsers: Boolean
}

external interface Envelope {
    val room: String
    val user: UserDetail
    val message: Message
}

external interface Talk {
    val id: LongValue
    val domainId: LongValue
    val type: TalkType
    val name: String?
    val iconUrl: String?
    val userIds: Array<LongValue>
    val guestIds: Array<LongValue>?
    val updatedAt: LongValue
    val leftUsers: Array<LongValue>?
}

external interface TalkType {
    operator fun get(i: Int): Any
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

// message -> message
// stamp -> another stamp type
// question -> question
// select_stamp -> select_stamp
// task_stamp -> task_stamp

// close question -> another close question type
external interface CloseQuestionResult {
    @JsName("in_reply_to") val inReplyTo: LongValue
    val responses: Array<QuestionResult>
    @JsName("last_response") val lastResponse: Int
    val question: String
    val listing: Boolean
}

external interface QuestionResult {
    val content: String
    val count: Int?
}
