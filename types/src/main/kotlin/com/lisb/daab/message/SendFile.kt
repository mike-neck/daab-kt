package com.lisb.daab.message

import com.lisb.daab.MessageSent
import com.lisb.daab.User
import com.lisb.daab.WithHandler

open class SendFile(
        val path: String,
        val name: String?,
        val type: String?,
        val text: String?)

// TODO it's inspecting now
class SendFileWithHandler(
        path: String,
        name: String?,
        type: String?,
        text: String?,
        override val onSend: (MessageSent<SendFile>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): SendFile(path, name, type, text), WithHandler<SendFile>

open class SendFiles(
        val path: Array<String>,
        val name: Array<String>?,
        val type: Array<String>?,
        val text: String?)

// TODO it's inspecting now
class SendFilesWithHandler(
        path: Array<String>,
        name: Array<String>?,
        type: Array<String>?,
        text: String?,
        override val onSend: (MessageSent<SendFiles>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): SendFiles(path, name, type, text), WithHandler<SendFiles>