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

import com.lisb.daab.Configurations.minus
import com.lisb.daab.Configurations.plus
import com.lisb.daab.Configurations.tuple
import org.gradle.api.Plugin
import org.gradle.api.Project

class DaabKtPlugin: Plugin<Project>, Function1<Project, Unit> {

    override fun apply(project: Project?) = if (project == null) Unit else this(project)

    override fun invoke(project: Project): Unit =
            (Configurations.applyPlugin(project) to Configurations.configureModel(project)) +
                    (Configurations::applyRepositories).tuple +
                    (Configurations::addDependency).tuple +
                    (Configurations::configureKotlinCompileOption).tuple +
                    (Configurations::configurePrepareDaabDirectory).tuple +
                    (Configurations::configureDaabInitTask).tuple +
                    (Configurations::configureWritePackageJsonTask).tuple +
                    (Configurations::configureReplacePackageJsonTask).tuple +
                    (Configurations::configurePackageJsonTask).tuple +
                    (Configurations::configureGenerateAppJsTask).tuple +
                    (Configurations::configureForeverIgnoreTask).tuple +
                    (Configurations::configureCompileKotlin2JsTask).tuple -
                    Unit
}
