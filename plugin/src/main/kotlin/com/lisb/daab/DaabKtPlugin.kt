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

import org.gradle.api.Plugin
import org.gradle.api.Project

class DaabKtPlugin: Plugin<Project>, Function1<Project, Unit> {

    override fun apply(project: Project?) = if (project == null) Unit else this(project)

    override fun invoke(project: Project) = TODO("not implemented")
}
