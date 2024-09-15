package com.github.dekola.androidcleanarchitecturetemplateplugin

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiManager
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.Box
import javax.swing.ButtonGroup
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JRadioButton
import javax.swing.JTextField


class CleanArchitectureTemplateName(
    private val event: AnActionEvent,
    private val selectedFile: VirtualFile
) : DialogWrapper(true) {

    private lateinit var panel: JPanel

    private val featureNameTextField: JTextField = JTextField()
    private val responseNameTextField: JTextField = JTextField()
    private val bodyNameTextField: JTextField = JTextField()

    private val javaRadioButton: JRadioButton = JRadioButton("Java")
    private val flutterRadioButton: JRadioButton = JRadioButton("Flutter")
    private val kotlinRadioButton: JRadioButton = JRadioButton("Kotlin", true)
    private val languageGroup: ButtonGroup = ButtonGroup()

    private val featureNameText: JLabel = JLabel()
    private val createClassButton = JButton("Create")

    init {
        init()
        title = "Create Clean Architecture Name"
        isResizable()
//        isResizable = true
    }

    override fun createCenterPanel(): JComponent {
        panel = JPanel(GridBagLayout())
        val constraints = GridBagConstraints()

        featureNameText.text = "Class Name"
        constraints.gridy += 1
        panel.add(featureNameText, constraints)

        featureNameTextField.text = "ApiService"
        featureNameTextField.preferredSize = Dimension(250, 30)
        constraints.gridy += 1
        panel.add(featureNameTextField, constraints)

        languageGroup.add(kotlinRadioButton);
        languageGroup.add(javaRadioButton);
        languageGroup.add(flutterRadioButton);

        constraints.gridy += 1
        panel.add(javaRadioButton, constraints)
        constraints.gridy += 1
        panel.add(kotlinRadioButton, constraints)


        val buttonPanel = JPanel(FlowLayout(FlowLayout.RIGHT))
        buttonPanel.add(createClassButton)
        constraints.gridy += 1
        constraints.weighty = 0.0
        panel.add(buttonPanel, constraints)

        constraints.gridy += 1
        constraints.weighty = 1.0
        panel.add(Box.createVerticalGlue(), constraints)

        createClassButton.addActionListener {
            try {
                createTemplate()
            } catch (e: Exception) {
                showErrorDialog(e.message, title = "Error Creating Classes")
            }
        }
        return panel
    }

    private fun createTemplate() {
        val selectedLanguage = getSelectedLanguage();
        event.project?.let { projectEvent ->
//            chooseFolder(projectEvent)?.let { selectedFolder ->

            val folder: PsiDirectory? =
                PsiManager.getInstance(projectEvent).findDirectory(selectedFile)
            ClassCreator.createTemplateStructure(
                projectEvent,
                folder,
                featureNameTextField.text,
                selectedLanguage,
                getPackageName(selectedFile!!)
            )

        }
//        when (selectedLanguage) {
//            LanguageSelection.JAVA -> {
//
//            }
//
//            LanguageSelection.KOTLIN -> {
//
//            }
//
//            LanguageSelection.FLUTTER -> {
//
//            }
//        }

    }

    private fun getSelectedLanguage(): LanguageSelection {
        return if (javaRadioButton.isSelected) {
            LanguageSelection.JAVA
        } else {
            LanguageSelection.KOTLIN
        }
    }

    private fun showErrorDialog(errorMessage: String?, title: String = "Validation Result") {
        Messages.showMessageDialog(
            panel,
            errorMessage,
            title,
            Messages.getInformationIcon(),
        )
    }


    fun getPackageName(file: VirtualFile): String {
        val filePath = file.path
        val index = filePath.indexOf("/java/")

        return if (index != -1) {
            val packagePath = filePath.substring(index + 6) // +6 to skip over "/java/"
            packagePath.replace('/', '.')
        } else {
            ""
        }
    }

}