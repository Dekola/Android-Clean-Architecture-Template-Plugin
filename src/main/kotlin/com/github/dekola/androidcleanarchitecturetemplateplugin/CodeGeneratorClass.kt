package com.github.dekola.androidcleanarchitecturetemplateplugin

import com.intellij.psi.PsiDirectory

object CodeGeneratorClass {

    fun getViewModelCode(
        selectedLanguage: LanguageSelection,
        folder: PsiDirectory?,
        featureName: String?,
        packageName: String
    ): String {

        val featureNameLowerCase = featureName!!.replaceFirstChar { it.lowercase() }
        val featureNameUpperCase = featureName.replaceFirstChar { it.uppercase() }


        return when (selectedLanguage) {
            LanguageSelection.JAVA -> {
                return """package ${packageName}.${featureNameLowerCase}.ui
                    

import ${packageName}.${featureNameLowerCase}.data.repository.${featureName}Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ${featureNameUpperCase}ViewModel @Inject constructor(private val repository: ${featureNameUpperCase}Repository) {

}
            """
            }

            LanguageSelection.KOTLIN -> {

                return """package ${packageName}.${featureNameLowerCase}.ui
                    

import ${packageName}.${featureNameLowerCase}.data.repository.${featureName}Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ${featureNameUpperCase}ViewModel @Inject constructor(private val repository: ${featureNameUpperCase}Repository) {

}
            """

            }

            LanguageSelection.FLUTTER -> {
                return """package ${packageName}.${featureNameLowerCase}.ui
                    

import ${packageName}.${featureNameLowerCase}.data.repository.${featureName}Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ${featureNameUpperCase}ViewModel @Inject constructor(private val repository: ${featureNameUpperCase}Repository) : {

}
            """
            }
        }
    }

    fun getRepositoryCode(
        selectedLanguage: LanguageSelection,
        folder: PsiDirectory?,
        featureName: String?,
        packageName: String
    ): String {
        val featureNameLowerCase = featureName!!.replaceFirstChar { it.lowercase() }
        val featureNameUpperCase = featureName.replaceFirstChar { it.uppercase() }


        return """package ${packageName}.${featureNameLowerCase}.data.repository

import ${packageName}.${featureNameLowerCase}.data.remoteDataSource.${featureName}RemoteDataSource
import javax.inject.Inject

class ${featureNameUpperCase}Repository @Inject constructor(
    private val remoteDataSource: ${featureNameUpperCase}RemoteDataSource,
) {

}"""
    }

    fun getRemoteDataSourceCode(
        selectedLanguage: LanguageSelection,
        folder: PsiDirectory?,
        featureName: String?,
        packageName: String

    ): String {
        val featureNameLowerCase = featureName!!.replaceFirstChar { it.lowercase() }
        val featureNameUpperCase = featureName.replaceFirstChar { it.uppercase() }


        return """package ${packageName}.${featureNameLowerCase}.data.remoteDataSource

import ${packageName}.${featureNameLowerCase}.data.api.${featureName}ApiService
import javax.inject.Inject

class ${featureNameUpperCase}RemoteDataSource @Inject constructor(
    private val apiService: ${featureNameUpperCase}ApiService
) {

}
"""

    }

    fun getApiInterfaceCode(
        selectedLanguage: LanguageSelection,
        folder: PsiDirectory?,
        featureName: String?,
        packageName: String
    ): String {
        val featureNameLowerCase = featureName!!.replaceFirstChar { it.lowercase() }
        val featureNameUpperCase = featureName.replaceFirstChar { it.uppercase() }


        return """package ${packageName}.${featureNameLowerCase}.data.api

interface ${featureNameUpperCase}ApiService {

}"""

    }

    fun getDiCode(
        selectedLanguage: LanguageSelection,
        baseDirectory: PsiDirectory?,
        featureName: String,
        packageName: String
    ): String {
        val featureNameLowerCase = featureName.replaceFirstChar { it.lowercase() }
        val featureNameUpperCase = featureName.replaceFirstChar { it.uppercase() }

        return """package ${packageName}.${featureNameLowerCase}.di
            
import ${packageName}.${featureNameLowerCase}.data.api.${featureNameUpperCase}ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ${featureNameUpperCase}Module {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ${featureNameUpperCase}ApiService {
        return retrofit.create(${featureNameUpperCase}ApiService::class.java)
    }
    
}
"""

    }
}