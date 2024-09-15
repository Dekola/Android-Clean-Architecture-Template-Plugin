package com.github.dekola.androidcleanarchitecturetemplateplugin

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory

object ClassCreator {

    fun createTemplateStructure(
        project: Project,
        folder: PsiDirectory?,
        featureName: String?,
        selectedLanguage: LanguageSelection,
        packageName: String
    ) {

//        val directory = PsiManager.getInstance(project)
//            .findDirectory(project.baseDir)

        val suffix = when (selectedLanguage) {
            LanguageSelection.JAVA -> {
                ".java"
            }
            LanguageSelection.KOTLIN -> ".kt"
            else -> ".dart"
        }

        val viewModelCode:String = CodeGeneratorClass.getViewModelCode(selectedLanguage, folder, featureName, packageName)
        val repositoryCode:String = CodeGeneratorClass.getRepositoryCode(selectedLanguage, folder, featureName)
        val remoteDataSourceCode:String = CodeGeneratorClass.getRemoteDataSourceCode(selectedLanguage, folder, featureName)
        val apiInterfaceCode:String = CodeGeneratorClass.getApiInterfaceCode(selectedLanguage, folder, featureName)


        WriteCommandAction.runWriteCommandAction(project) {
            val fileType = FileTypeManager.getInstance().getFileTypeByExtension("kt")
            val psiFile = PsiFileFactory.getInstance(project)
                .createFileFromText("${featureName}ViewModel$suffix", fileType, viewModelCode)
            folder?.add(psiFile)

        }

    }





}
