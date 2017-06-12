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
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Exec
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile
import org.jetbrains.kotlin.utils.addToStdlib.cast

data class Daab(
        var executable: String = "/usr/local/bin/daab",
        var mainPackage: String =  "",
        var daabAppDir: String = "daab",
        var appName: String = "app"
) {

    fun executable(path: String): Unit = Unit.apply { this@Daab.executable = path }

    fun mainPackage(packageName: String): Unit = Unit.apply { this@Daab.mainPackage = packageName }

    fun daabApp(appDir: String): Unit = Unit.apply { this@Daab.daabAppDir = appDir }
    
}

object DaabConfigure {

    val group: String = "daab"

    val definedTask: TaskNamer = object: TaskNamer {
        override val daabInit: String = "daabInit"
        override val daabRun: String = "daabRun"
        override val daabStart: String = "daabStart"
    }

    fun configureModel(project: Project): Daab = project.extensions.create<Daab>("daab")

    fun configureDaabInit(project: Project, daab: Daab): Task = project.tasks.create<Exec>(definedTask.daabInit) {
        it.description = "init daab project directory"
        it.executable = daab.executable
        it.workingDir(daab.daabAppDir)
    }

    fun configureKotlinCompileOption(project: Project): Unit = project.afterEvaluate {
        val daab = it.extensions.getByName("daab").cast<Daab>()
        val options = it.extensions.getByName("compileKotlin2Js").cast<Kotlin2JsCompile>().kotlinOptions
        options.outputFile = "${project.projectDir}/${daab.daabAppDir}/${daab.appName}.js"
        options.moduleKind = "commonjs"
        options.sourceMap = true
        
    }
}

interface TaskNamer {
    val daabInit: String
    val daabRun: String
    val daabStart: String
}



