package com.github.dekola.androidcleanarchitecturetemplateplugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.vfs.VirtualFile

class CreateCleanArchitectureTemplate : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val selectedFile: VirtualFile? = event.getData(CommonDataKeys.VIRTUAL_FILE)

        if (selectedFile != null && selectedFile.isDirectory) {
            // Your logic for handling the selected directory
            println("Selected directory: " + selectedFile.path)
            println("Selected directory: ${selectedFile.url}")
            println("Selected directory: ${selectedFile.toNioPath()}")
            println("Selected directory: ${selectedFile.presentableUrl}")
            println("Selected directory: $selectedFile")
            CleanArchitectureTemplateName(event, selectedFile).show()
            // Implement the logic you want to perform on the selected directory
        } else {
            // Handle cases where the selection is not a directory
            println("Please select a directory.")
        }
    }

}

//class CreateCleanArchitectureTemplate : AnAction() {
//    override fun actionPerformed(event: AnActionEvent) {
//        // For demonstration purposes, just show a dialog
//        Messages.showMessageDialog(
//            "Clean Architecture Template Created!",
//            "Information",
//            Messages.getInformationIcon()
//        )
//    }
//}