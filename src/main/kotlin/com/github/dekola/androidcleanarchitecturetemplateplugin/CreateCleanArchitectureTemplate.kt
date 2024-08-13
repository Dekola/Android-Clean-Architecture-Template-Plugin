package com.github.dekola.androidcleanarchitecturetemplateplugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class CreateCleanArchitectureTemplate : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        CleanArchitectureTemplateName(event).show()
    }

}