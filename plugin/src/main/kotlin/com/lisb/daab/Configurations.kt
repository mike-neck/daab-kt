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

import com.lisb.daab.GradleFunUtil.create
import com.lisb.daab.GradleFunUtil.createDirIfNotExists
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.TaskValidationException
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile
import java.io.File


object Configurations {

    operator inline fun <T> T.plus(op: (T) -> Any): T = this.also { op(it) }

    @Suppress("unused")
    operator fun <A: Any> A.minus(unit: Unit): Unit = unit

    val <A, B, C> ((A,B) -> C).tuple: (Pair<A,B>) -> C get() = { p -> this(p.first, p.second) }

    fun applyPlugin(project: Project): Project =
            if (project.pluginManager.hasPlugin(Daab.kotlin2Js)) project
            else project.also { it.pluginManager.apply(Daab.kotlin2Js) }

    fun configureModel(project: Project): Daab = project.extensions.create<Daab>("daab")

    fun applyRepositories(project: Project, @Suppress("UNUSED_PARAMETER") daab: Daab): Unit =
            project.repositories.add(project.repositories.mavenCentral()).let{ Unit }

    fun addDependency(project: Project, @Suppress("UNUSED_PARAMETER") daab: Daab): Unit =
            project.dependencies.add(
                    "compile", mapOf(
                    "group" to Daab.jetbrainsKotlin,
                    "name" to Daab.kotlinStdLibJs,
                    "version" to Daab.kotlinVersion
            )).let { Unit }

    fun configureKotlinCompileOption(project: Project, daab: Daab): Unit = project.afterEvaluate {
        val options = it.tasks.getByName(Daab.compileKotlin2Js).k2Js().kotlinOptions
        options.outputFile = "${project.projectDir}/${daab.daabAppDir}/lib/${daab.appName}.js"
        options.moduleKind = "commonjs"
        options.sourceMap = true
    }

    private fun <T: Any> T.k2Js(): Kotlin2JsCompile =
            if (this is Kotlin2JsCompile) this 
            else throw TaskValidationException(
                    "Expected task compileKotlin2Js is [${Kotlin2JsCompile::class.qualifiedName}] type but " +
                            "it is [${this::class.qualifiedName}].", mutableListOf())

    fun configurePrepareDaabDirectory(project: Project, daab: Daab): Task =
            project.tasks.create(Daab.prepareDaabDirectory).doLast { 
                createDirIfNotExists(project.file(daab.daabAppDir))
            }.also { 
                it.outputs.upToDateWhen { project.file(daab.daabAppDir).exists() }
            }

    fun configureDaabInitTask(project: Project, daab: Daab): Task = project.tasks.create<Exec>(Daab.daabInit)
            .also { it.description = "init daab project directory" }
            .also { it.group = Daab.group }
            .also { it.dependsOn(Daab.prepareDaabDirectory) }
            .also { it.executable = daab.executable }
            .also { it.args("init") }
            .also { it.environment("PATH", appendPath(project.file(daab.executable))) }
            .also { it.workingDir(daab.daabAppDir) }
            .also { it.finalizedBy(Daab.packageJson) }

    fun appendPath(exec: File): String = "${System.getenv("PATH")}:${exec.absoluteFile.parent}"

    fun configureNpmKotlinVersionTask(project: Project, daab: Daab): Task =
            project.tasks.create<NpmKotlinVersion>(Daab.npmKotlinVersion)
                    .also { it.executable = daab.executable.replace("daab", "npm") }
                    .also { it.args("view", "kotlin") }
                    .also { it.environment("PATH", appendPath(project.file(daab.executable))) }
                    .also { it.standardOutput = it.outputStream }
                    .also { it.dependsOn(Daab.daabInit) }

    fun configureWritePackageJsonTask(project: Project, daab: Daab): Task =
            project.tasks.create<WritePackageJson>(Daab.writePackageJson)
                    .also { it.daab = daab }
                    .also { it.dependsOn(Daab.npmKotlinVersion) }

    fun configureReplacePackageJsonTask(project: Project, daab: Daab): Task =
            project.tasks.create(Daab.replacePackageJson).doLast {
                project.file(daab.packageJson(project)).delete()
            }.doLast {
                project.file(daab.newPackageJson(project))
                        .renameTo(project.file(daab.packageJson(project)))
            }.also { it.dependsOn(Daab.writePackageJson) }

    fun configurePackageJsonTask(project: Project, @Suppress("UNUSED_PARAMETER") daab: Daab): Task =
            project.tasks.create(Daab.packageJson).dependsOn(Daab.replacePackageJson)

    fun configureGenerateAppJsTask(project: Project, daab: Daab): Task =
            project.tasks.create<GenerateAppJsTask>(Daab.generateAppJs)
                    .also{ it.daab = daab }
                    .also { it.group = Daab.group }

    fun configureForeverIgnoreTask(project: Project, daab: Daab): Task =
            project.tasks.create<ForeverIgnoreTask>(Daab.foreverIgnore)
                    .also { it.daab = daab }

    fun configureCompileKotlin2JsTask(project: Project, @Suppress("UNUSED_PARAMETER") daab: Daab): Task =
            project.tasks.getByName(Daab.compileKotlin2Js).finalizedBy(Daab.foreverIgnore, Daab.generateAppJs)
}
