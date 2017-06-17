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

import org.gradle.api.Project
import java.util.*

data class Daab(
        var executable: String = "/usr/local/bin/daab",
        var mainPackage: String =  "",
        var daabAppDir: String = "daab",
        var appName: String = "app"
) {

    fun executable(path: String): Unit = Unit.apply { this@Daab.executable = path }

    fun mainPackage(packageName: String): Unit = Unit.apply { this@Daab.mainPackage = packageName }

    fun daabAppDir(appDir: String): Unit = Unit.apply { this@Daab.daabAppDir = appDir }

    fun appName(name: String): Unit = Unit.apply { this@Daab.appName = name }

    fun newPackageJson(project: Project): String = "${project.projectDir}/${daabAppDir}/new-package.json"

    fun packageJson(project: Project): String = "${project.projectDir}/${daabAppDir}/package.json"

    companion object: TaskNamer {

        val group: String = "daab"

        override val daabInit: String = "daabInit"
        override val daabRun: String = "daabRun"
        override val daabStart: String = "daabStart"

        override val foreverIgnore: String = "foreverIgnore"
        override val generateAppJs: String = "generateAppJs"
        override val npmKotlinVersion: String = "npmKotlinVersion"
        override val writePackageJson: String = "writePackageJson"
        override val replacePackageJson: String = "replacePackageJson"
        override val packageJson: String = "packageJson"

        val kotlin2Js: String = "kotlin2js"
        val jetbrainsKotlin = "org.jetbrains.kotlin"
        val kotlinStdLibJs = "kotlin-stdlib-js"

        val compileKotlin2Js: String = "compileKotlin2Js"

        lateinit var kotlinVersion: String
        
        init {
            this::class.java.classLoader.getResourceAsStream("kotlinVersion.properties").use { 
                val properties = Properties()
                properties.load(it)
                this.kotlinVersion =
                        properties.getProperty("kotlinVersion")?:
                                throw IllegalStateException("no kotlin version found.")
            }
        }
    }
}

interface TaskNamer {
    val daabInit: String
    val daabRun: String
    val daabStart: String

    val foreverIgnore: String
    val generateAppJs: String
    val npmKotlinVersion: String
    val writePackageJson: String
    val replacePackageJson: String
    val packageJson: String
}
