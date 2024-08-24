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

    private val featureNameTextField: JTextField = JTextField()
    private val responseNameTextField: JTextField = JTextField()
    private val bodyNameTextField: JTextField = JTextField()

    private val javaRadioButton: JRadioButton = JRadioButton("Java")
    private val flutterRadioButton: JRadioButton = JRadioButton("Flutter")
    private val kotlinRadioButton: JRadioButton = JRadioButton("Kotlin", true)
    private val languageGroup: ButtonGroup = ButtonGroup()

    private val featureNameText: JLabel = JLabel()
    private val createClassButton = JButton("Create ")

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

        return panel
    }


}