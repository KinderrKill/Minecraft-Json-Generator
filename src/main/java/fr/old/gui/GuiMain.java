package fr.old.gui;

import fr.old.OldJsonGenerator;
import fr.old.utils.Lang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiMain extends JFrame implements ActionListener {

    private OldJsonGenerator generator;

    private JComboBox templates;
    private JButton validateButton;

    private JTextField modelName, modelTexture;
    private JButton generateButton;

    public void launch() {
        generator = new OldJsonGenerator();
        generator.launch();

        setTitle("Minecraft JSON Generator");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getContentPane().add(getMainPanel());

        setVisible(true);
        setResizable(false);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        Image background = Toolkit.getDefaultToolkit().getImage("assets/background.jpg");
        g.drawImage(background, 0, 0, this);
    }

    private JPanel getMainPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(null);
        panel.setBackground(new Color(44, 47, 51));

        JLabel mainText = new JLabel("Minecraft JSON Generator");
        mainText.setBounds(60, 30, getWidth(), 30);
        mainText.setForeground(Color.LIGHT_GRAY);
        mainText.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(mainText);

        JLabel elementText = new JLabel("<HTML><U>" + Lang.list.SELECT_TEMPLATE.getName() + "</U></HTML>");
        elementText.setBounds(getWidth()/3, 140, 250, 30);
        elementText.setForeground(Color.WHITE);
        elementText.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));
        panel.add(elementText);

        Object[] elements = generator.getJsonHelper().getAvailableTemplates().toArray();
        templates = new JComboBox(elements);
        templates.setBounds(getWidth()/4, 180, 250, 30);
        panel.add(templates);

        validateButton = new JButton("Verified template");
        validateButton.setBounds(getWidth() / 2 - 125, getHeight() - 100, 250, 30);
        validateButton.addActionListener(this);
        panel.add(validateButton);

        JLabel copyrightPanel = new JLabel("Made by KinderrKill with <3");
        copyrightPanel.setBounds(getWidth()/4 + 40, getHeight() - 60, getWidth(), 30);
        copyrightPanel.setForeground(Color.LIGHT_GRAY);
        copyrightPanel.setFont(new Font("Arial", Font.TRUETYPE_FONT, 15));
        panel.add(copyrightPanel);

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

            if (response == 0) {
                GuiMain main = new GuiMain();
                main.launch();
                dispose();
            }

            if (response == 1) {
                dispose();
            }
        }
    }

    private JPanel getSecondPanel(String template) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(44, 47, 51));

        JLabel mainText = new JLabel("Minecraft JSON Generator");
        mainText.setBounds(60, 30, getWidth(), 30);
        mainText.setForeground(Color.LIGHT_GRAY);
        mainText.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(mainText);

        JLabel templateText = new JLabel("Selected template : " + template);
        templateText.setBounds(90, 80, getWidth(), 20);
        templateText.setForeground(Color.WHITE);
        templateText.setFont(new Font("Arial", 0, 20));
        panel.add(templateText);

        JLabel blockText = new JLabel("<HTML><U>" + Lang.list.MODEL_NAME.getName() + "</U></HTML>");
        blockText.setForeground(Color.WHITE);
        blockText.setBounds(getWidth()/4 + 22, 160, getWidth(), 30);
        blockText.setFont(new Font("Arial", 0, 20));
        panel.add(blockText);

        modelName = new JTextField("");
        modelName.setBounds(getWidth() / 2 - 125, 200, 250, 30);
        panel.add(modelName);

        JLabel textureText = new JLabel("<HTML><U>" + Lang.list.MODEL_TEXTURE.getName() + "</U></HTML>");
        textureText.setForeground(Color.WHITE);
        textureText.setBounds(getWidth() / 4 + 15, 270, getWidth(), 30);
        textureText.setFont(new Font("Arial", 0, 20));
        panel.add(textureText);

        modelTexture = new JTextField("");
        modelTexture.setBounds(getWidth() / 2 - 125, 310, 250, 30);
        panel.add(modelTexture);

        generateButton = new JButton("Generate JSON files");
        generateButton.setBounds(getWidth() / 2 - 125, getHeight() - 100, 250, 30);
        generateButton.addActionListener(this);
        panel.add(generateButton);

        JLabel copyrightPanel = new JLabel("Made by KinderrKill with <3");
        copyrightPanel.setBounds(getWidth()/4 + 40, getHeight() - 60, getWidth(), 30);
        copyrightPanel.setForeground(Color.LIGHT_GRAY);
        copyrightPanel.setFont(new Font("Arial", Font.TRUETYPE_FONT, 15));
        panel.add(copyrightPanel);

        return panel;
    }

    private int getStringWidth(JPanel panel, String str) {
        return getGraphics().getFontMetrics().stringWidth(str);
    }
}
