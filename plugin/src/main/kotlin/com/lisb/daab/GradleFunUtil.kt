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

import org.gradle.api.Task
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.tasks.TaskContainer
import kotlin.reflect.KClass

object GradleFunUtil {

    inline fun <reified A: Any> ExtensionContainer.create(extensionName: String, kc: KClass<A> = A::class): A =
            this.create(extensionName, kc.java)

    inline fun <reified A: Task> TaskContainer.create(
            name: String,
            kc: KClass<A> = A::class,
            crossinline f: (A) -> Unit): A = this.create(name, kc.java) { f(it) }
}
