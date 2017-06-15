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

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.ByteArrayOutputStream
import java.io.File

abstract class ForeverIgnoreTask: DefaultTask() {

    lateinit var daab: Daab

    val targetDir: File get() = project.file("${project.projectDir}/${daab.daabAppDir}/scripts")

    @get:OutputFile
    val ignoreFile: File get() = targetDir.resolve(".foreverignore")

    @TaskAction
    fun writeIgnoreFile(): Unit = ignoreFile.writeText("")
}

abstract class GenerateAppJsTask: DefaultTask() {

    lateinit var daab: Daab

    val appDir: File get() = project.file("${project.projectDir}/${daab.daabAppDir}/scripts")

    @get:OutputFile
    val jsFile: File get() = appDir.resolve("app.js")

    @TaskAction
    fun writeJsFile(): Unit = """
const daab = require("./lib/${daab.appName}");

module.exports = daab.${daab.mainPackage}.${daab.appName};
""".let { jsFile.writeText(it) }
}

abstract class NpmKotlinVersion: Exec() {

    val outputStream: ByteArrayOutputStream = ByteArrayOutputStream(65536)

    fun lazyConfigure(daab: Daab): Unit =
            Unit.apply { this@NpmKotlinVersion.executable = daab.executable.replace("daab", "npm") }
                    .apply { this@NpmKotlinVersion.args("view", "kotlin") }
                    .apply { this@NpmKotlinVersion.standardOutput = outputStream }

    fun getOutputAsString(): String = if (this.state.skipped) throw IllegalStateException("the task did nothing.")
            else outputStream.toString("UTF-8").lineSequence()
            .find { it.contains("latest:") }
            ?.substringAfter("latest:")
            ?.replace("},", "")
            ?.replace("'", "")
            ?.trim() ?: "1.1.0"
}
