package com.github.dekola.androidcleanarchitecturetemplateplugin

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.util.ui.JBUI
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
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.JTextField


class CleanArchitectureTemplateName(private val event: AnActionEvent) : DialogWrapper(true) {

    private lateinit var panel: JPanel

    private val inputTextArea = JTextArea(5, 50)
    private val resultTextArea = JTextArea(3, 50)
    private val submitButton = JButton("Submit")

    private val classNameText: JLabel = JLabel()
    private val classNameTextField: JTextField = JTextField()

    private val methodNameText: JLabel = JLabel()
    private val methodNameTextField: JTextField = JTextField()

    private val javaRadioButton: JRadioButton = JRadioButton("Java")
    private val kotlinRadioButton: JRadioButton = JRadioButton("Kotlin", true)
    private val languageGroup: ButtonGroup = ButtonGroup()

    private val copyButton = JButton("Copy to Clipboard")
    private val addToExistingButton = JButton("Add to existing class")
    private val createClassButton = JButton("Create new class")

    init {
        init()
        title = "Create Clean Architecture Name"
        isResizable()
//        isResizable = true
    }

    override fun createCenterPanel(): JComponent {
        panel = JPanel(GridBagLayout())
        val constraints = GridBagConstraints()

        inputTextArea.text = ""

        constraints.fill = GridBagConstraints.HORIZONTAL
        constraints.gridx = 0
        constraints.weightx = 1.0
        constraints.insets = JBUI.insets(4)
        constraints.anchor = GridBagConstraints.NORTH


        constraints.gridy += 1
        constraints.weighty = 0.0
        panel.add(JScrollPane(inputTextArea), constraints)

        methodNameText.text = "Method Name"
        constraints.gridy += 1
        panel.add(methodNameText, constraints)

        methodNameTextField.text = "performRequest"
        methodNameTextField.preferredSize = Dimension(250, 30)
        constraints.gridy += 1
        panel.add(methodNameTextField, constraints)

        constraints.gridy += 1
        panel.add(submitButton, constraints)

        constraints.gridy += 1

        resultTextArea.isEditable = false
        panel.add(JScrollPane(resultTextArea), constraints)

        classNameText.text = "Class Name"
        constraints.gridy += 1
        panel.add(classNameText, constraints)

        classNameTextField.text = "ApiService"
        classNameTextField.preferredSize = Dimension(250, 30)
        constraints.gridy += 1
        panel.add(classNameTextField, constraints)

        languageGroup.add(kotlinRadioButton);
        languageGroup.add(javaRadioButton);

        constraints.gridy += 1
        panel.add(javaRadioButton, constraints)
        constraints.gridy += 1
        panel.add(kotlinRadioButton, constraints)


        val buttonPanel = JPanel(FlowLayout(FlowLayout.RIGHT))
        buttonPanel.add(copyButton)
        buttonPanel.add(addToExistingButton)
        buttonPanel.add(createClassButton)
        constraints.gridy += 1
        constraints.weighty = 0.0
        panel.add(buttonPanel, constraints)

        constraints.gridy += 1
        constraints.weighty = 1.0
        panel.add(Box.createVerticalGlue(), constraints)

        return panel
    }


}