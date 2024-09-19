package com.github.dekola.androidcleanarchitecturetemplateplugin

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory

object ClassCreator {

    fun createTemplateStructure(
        project: Project,
        baseDirectory: PsiDirectory?,
        featureName: String,
        selectedLanguage: LanguageSelection,
        packageName: String
    ) {

//        val directory = PsiManager.getInstance(project)
//            baseDirectory.findDirectory(project.baseDir)

        val baseFeatureDirectory = createDirectory(project, baseDirectory, featureName.replaceFirstChar { it.lowercase() })

        val viewModelDirectory = createDirectory(project, baseFeatureDirectory, "ui")
        val dataDirectory = createDirectory(project, baseFeatureDirectory, "data")
        val repositoryDirectory = createDirectory(project, dataDirectory, "repository")
        val remoteDataSourceDirectory = createDirectory(project, dataDirectory, "remoteDataSource")
        val apiDirectory = createDirectory(project, dataDirectory, "api")

        val viewModelCode:String = CodeGeneratorClass.getViewModelCode(selectedLanguage, baseDirectory, featureName, packageName)
        val repositoryCode:String = CodeGeneratorClass.getRepositoryCode(selectedLanguage, baseDirectory, featureName, packageName)
        val remoteDataSourceCode:String = CodeGeneratorClass.getRemoteDataSourceCode(selectedLanguage, baseDirectory, featureName, packageName)
        val apiInterfaceCode:String = CodeGeneratorClass.getApiInterfaceCode(selectedLanguage, baseDirectory, featureName, packageName)

        createClass(project, viewModelDirectory, selectedLanguage, featureName, "ViewModel", viewModelCode)
        createClass(project, repositoryDirectory, selectedLanguage, featureName, "Repository", repositoryCode)
        createClass(project, remoteDataSourceDirectory, selectedLanguage, featureName, "RemoteDataSource", remoteDataSourceCode)
        createClass(project, apiDirectory, selectedLanguage, featureName, "ApiInterface", apiInterfaceCode)

    }

    fun createClass(
        project: Project,
        viewModelDirectory: PsiDirectory?,
        selectedLanguage: LanguageSelection,
        featureName: String,
        classType: String,
        viewModelCode: String
    ) {
        val suffix = when (selectedLanguage) {
            LanguageSelection.JAVA -> {
                ".java"
            }
            LanguageSelection.KOTLIN -> ".kt"
            else -> ".dart"
        }
        val extension = when (selectedLanguage) {
            LanguageSelection.JAVA -> {
                "java"
            }
            LanguageSelection.KOTLIN -> "kt"
            else -> "dart"
        }
        WriteCommandAction.runWriteCommandAction(project) {
            val fileType = FileTypeManager.getInstance().getFileTypeByExtension(extension)
            val psiFile = PsiFileFactory.getInstance(project)
                .createFileFromText("${featureName}$classType$suffix", fileType, viewModelCode)
            viewModelDirectory?.add(psiFile)
        }
    }

    fun createDirectory(project: Project, baseDirectory: PsiDirectory?, directoryName: String): PsiDirectory? {
        var newSubdirectory: PsiDirectory? = null

        WriteCommandAction.runWriteCommandAction(project) {
            try {
                newSubdirectory = baseDirectory?.createSubdirectory(directoryName)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return newSubdirectory
    }




}
