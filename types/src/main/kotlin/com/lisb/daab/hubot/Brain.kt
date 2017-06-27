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
package com.lisb.daab.hubot

import com.lisb.daab.Domain
import com.lisb.daab.User

external interface Brain {
    val adapterPath: String
    val name: String
    val alias: Boolean
    val commands: Array<String>
    val version: String

    fun users(): Map<String, User>
    fun domains(): Map<String, Domain>

    fun userForId(userId: String): User
    fun userForName(userName: String): User
}
