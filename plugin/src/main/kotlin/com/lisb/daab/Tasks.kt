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

import com.lisb.daab.GradleFunUtil.invoke
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.AbstractExecTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.ByteArrayOutputStream
import java.io.File

open class ForeverIgnoreTask: DefaultTask() {

    lateinit var daab: Daab

    open val targetDir: File get() = project.file("${project.projectDir}/${daab.daabAppDir}/scripts")

    @get:OutputFile
    open val ignoreFile: File get() = targetDir.resolve(".foreverignore")

    @TaskAction
    open fun writeIgnoreFile(): Unit = ignoreFile.writeText("")
}

open class GenerateAppJsTask: DefaultTask() {

    lateinit var daab: Daab

    open val appDir: File get() = project.file("${project.projectDir}/${daab.daabAppDir}/scripts")

    @get:OutputFile
    open val jsFile: File get() = appDir.resolve("app.js")

    @TaskAction
    open fun writeJsFile(): Unit = """
const daab = require("./lib/${daab.appName}");

module.exports = daab.${if(daab.mainPackage.isEmpty()) "" else "${daab.mainPackage}."}${daab.appName};
""".let { jsFile.writeText(it) }
}

open class WritePackageJson: DefaultTask() {

    lateinit var daab: Daab

    @get:OutputFile
    open val newPackageJson: File get() = project.file(daab.newPackageJson(project))

    @get:InputFile
    open val packageJson: File get() = project.file(daab.packageJson(project))

    open fun appendNextLine(current: String): String =
            if (current.contains("\"dependencies\": {"))
                """
    "kotlin": "^${project.properties["kotlinVersion"]}",
"""
            else
                "\n"

    @TaskAction
    open fun appendJsonFile(): Unit = StringBuilder()
            .also { sb: StringBuilder -> packageJson.forEachLine { sb(it)(appendNextLine(it)) } }
            .let { sb: StringBuilder -> newPackageJson.writeText("$sb") }
}
