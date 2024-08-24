package com.github.dekola.androidcleanarchitecturetemplateplugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

//class CreateCleanArchitectureTemplate : AnAction() {
//    override fun actionPerformed(event: AnActionEvent) {
//        CleanArchitectureTemplateName(event).show()
//    }
//
//}

class CreateCleanArchitectureTemplate : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        // For demonstration purposes, just show a dialog
        Messages.showMessageDialog(
            "Clean Architecture Template Created!",
            "Information",
            Messages.getInformationIcon()
        )
    }
}