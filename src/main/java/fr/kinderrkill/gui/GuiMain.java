package fr.kinderrkill.gui;

import fr.kinderrkill.JsonGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiMain extends JFrame implements ActionListener {

    private JsonGenerator generator;

    private JComboBox templates;
    private JButton validateButton;

    private JTextField modelName, modelTexture;
    private JButton generateButton;

    public void launch() {
        generator = new JsonGenerator();
        generator.launch();

        setTitle("Minecraft JSON Generator");
        setSize(720, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getContentPane().add(getMainPanel());

        setVisible(true);
    }

    private JPanel getMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);

        JLabel mainText = new JLabel("Minecraft JSON Generator");
        mainText.setBounds(getWidth() / 4 - 15, 20, getWidth(), 30);
        mainText.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(mainText);

        JLabel elementText = new JLabel("Select your template :");
        elementText.setBounds(getWidth() / 4 - 40, 98, getWidth(), 30);
        elementText.setFont(new Font("Arial", 0, 20));
        panel.add(elementText);

        Object[] elements = generator.getJsonHelper().getAvailableTemplates().toArray();
        templates = new JComboBox(elements);
        templates.setBounds(getWidth() / 2 - 20, 100, 200, 25);
        panel.add(templates);

        validateButton = new JButton("Verified template");
        validateButton.setBounds(getWidth() / 2 - 125, 150, 250, 30);
        validateButton.addActionListener(this);
        panel.add(validateButton);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == validateButton) {
            String key = templates.getSelectedItem().toString();
            generator.getJsonHelper().initTemplateObject(key);
            setContentPane(getSecondPanel(key));
            revalidate();
        }

        if (source == generateButton) {
            generator.generateJson(modelName.getText(), modelTexture.getText());

            String[] options = new String[2];
            options[0] = new String("Generate again");
            options[1] = new String("Close");

            int response = JOptionPane.showOptionDialog(null, "JSON Files generated with success !", "Minecraft JSON Generator", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);

            if(response == 0) {
                GuiMain main = new GuiMain();
                main.launch();
                dispose();
            }

            if(response == 1) {
                dispose();
            }
        }
    }

    private JPanel getSecondPanel(String template) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);

        JLabel mainText = new JLabel("Minecraft JSON Generator");
        mainText.setBounds(getWidth() / 4 - 15, 20, getWidth(), 30);
        mainText.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(mainText);

        JLabel templateText = new JLabel("Selected template : " + template);
        templateText.setBounds(getWidth() / 4 + 20, 60, getWidth(), 30);
        templateText.setFont(new Font("Arial", 0, 20));
        panel.add(templateText);

        JLabel blockText = new JLabel("Define your block name : ");
        blockText.setBounds(getWidth() / 4 - 50, 150, getWidth(), 30);
        blockText.setFont(new Font("Arial", 0, 20));
        panel.add(blockText);

        modelName = new JTextField("");
        modelName.setBounds(getWidth() / 2 + 10, 150, 200, 30);
        panel.add(modelName);

        JLabel textureText = new JLabel("Define your texture name : ");
        textureText.setBounds(getWidth() / 4 - 50, 200, getWidth(), 30);
        textureText.setFont(new Font("Arial", 0, 20));
        panel.add(textureText);

        modelTexture = new JTextField("");
        modelTexture.setBounds(getWidth() / 2 + 10, 200, 200, 30);
        panel.add(modelTexture);

        generateButton = new JButton("Generate JSON files");
        generateButton.setBounds(getWidth() / 2 - 125, 300, 250, 30);
        generateButton.addActionListener(this);
        panel.add(generateButton);

        return panel;
    }
}
