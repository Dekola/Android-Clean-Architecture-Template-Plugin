package com.github.dekola.androidcleanarchitecturetemplateplugin

import com.intellij.psi.PsiDirectory

object CodeGeneratorClass {

    fun getViewModelCode(
        selectedLanguage: LanguageSelection,
        folder: PsiDirectory?,
        featureName: String?,
        packageName: String
    ): String {

        return when (selectedLanguage) {
            LanguageSelection.JAVA -> {
                return """
                
                package ${packageName}.ui.${featureName}
    
                import com.cookpad.hiring.android.data.entities.Collection
                import com.cookpad.hiring.android.data.repository.CollectionListRepository
                import dagger.hilt.android.lifecycle.HiltViewModel
                import javax.inject.Inject
                
                @HiltViewModel
                class ${featureName}ViewModel @Inject constructor(private val repository: ${featureName}Repository) :
                
                }
    
            """
            }

            LanguageSelection.KOTLIN -> {

                return """package ${packageName}.ui.${featureName}

import com.cookpad.hiring.android.data.repository.CollectionListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ${featureName}ViewModel @Inject constructor(private val repository: ${featureName}Repository) :

}
            """

            }

            LanguageSelection.FLUTTER -> {
                return """
                
                package ${folder}.ui.${featureName}
    
                import com.cookpad.hiring.android.data.entities.Collection
                import com.cookpad.hiring.android.data.repository.CollectionListRepository
                import dagger.hilt.android.lifecycle.HiltViewModel
                import javax.inject.Inject
                
                @HiltViewModel
                class ${featureName}ViewModel @Inject constructor(private val repository: ${featureName}Repository) :
                
                }
    
            """
            }
        }
    }

    fun getRepositoryCode(
        selectedLanguage: LanguageSelection,
        folder: PsiDirectory?,
        featureName: String?
    ): String {
        return ""
    }

    fun getRemoteDataSourceCode(
        selectedLanguage: LanguageSelection,
        folder: PsiDirectory?,
        featureName: String?

    ): String {
        return ""

    }

    fun getApiInterfaceCode(
        selectedLanguage: LanguageSelection,
        folder: PsiDirectory?,
        featureName: String?
    ): String {
        return ""

    }
}