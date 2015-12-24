package view.file;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import model.entity.Type;

public class Window extends JFrame {
    public String inputLine;
    public model.entity.Type type;

    public JTextField textField1;
    public JTextField textField2;

    public String TYPE_BINARY  = "BINARY";
    public String TYPE_DECIMAL = "DECIMAL";
    public String TYPE_HEXDECIMAL = "HEXDECIMAL";

    private FileView fileView;

    public Window(){
        super("FileWindow");
        fileView = new FileView();
        type = model.entity.Type.DECIMAL;
        init();
        createGUI();
    }

    private void init(){
        this.setSize(700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.setLocation(300,50);
        this.setVisible(true);
    }

    private void createMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");

        JMenuItem itemOpen = new JMenuItem("Open");
        JMenuItem itemSave = new JMenuItem("Save");
        JMenuItem itemExit = new JMenuItem("Exit");

        ActionListener itemListener = new ItemActionListener();

        itemOpen.addActionListener(itemListener);
        itemSave.addActionListener(itemListener);
        itemExit.addActionListener(itemListener);

        menuFile.add(itemOpen);
        menuFile.add(itemSave);
        menuFile.add(itemExit);

        menuBar.add(menuFile);
        this.setJMenuBar(menuBar);
    }

    private void createGUI() {
        createMenu();

        textField1 = new JTextField();
        textField2 = new JTextField();

        JButton button = new JButton("Calculate");

        ActionListener buttonListener = new ButtonActionListener();
        button.addActionListener(buttonListener);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        jPanel.add(new JLabel("Select the type of data:"));
        jPanel.add(createCheckPanel());
        jPanel.add(new JLabel(""));

        jPanel.add(new JLabel("Arithmetic expression: "));
        jPanel.add(textField1);

        jPanel.add(new JLabel("Result: "));
        jPanel.add(textField2);
        jPanel.add(button);
        jPanel.add(new JLabel(""));

        this.getContentPane().add(jPanel);
    }

    private JPanel createCheckPanel(){
        JPanel boxesPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        JCheckBox checkBox2 = new JCheckBox(TYPE_BINARY);
        JCheckBox checkBox3 = new JCheckBox(TYPE_DECIMAL);
        JCheckBox checkBox4 = new JCheckBox(TYPE_HEXDECIMAL);

        checkBox3.setSelected(true);

        ActionListener checkBoxListener = new CheckBoxListener();

        checkBox2.addActionListener(checkBoxListener);
        checkBox3.addActionListener(checkBoxListener);
        checkBox4.addActionListener(checkBoxListener);

        group.add(checkBox2);
        group.add(checkBox3);
        group.add(checkBox4);

        boxesPanel.add(checkBox2);
        boxesPanel.add(checkBox3);
        boxesPanel.add(checkBox4);

        boxesPanel.setLayout(new BoxLayout(boxesPanel, BoxLayout.X_AXIS));

        return boxesPanel;
    }

    class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textField2.setText(fileView.getResLine());
        }
    }

    class CheckBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()){
                case "BINARY":
                    type = model.entity.Type.BINARY;
                    break;
                case "DECIMAL":
                    type = model.entity.Type.DECIMAL;
                    break;
                case "HEXDECIMAL":
                    type = model.entity.Type.HEXDECIMAL;
                    break;
            }
        }
    }

    class ItemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "Open":
                    JFileChooser dialog = new JFileChooser();
                    dialog.setApproveButtonText("Open");
                    dialog.setDialogTitle("Open file");
                    dialog.setDialogType(JFileChooser.OPEN_DIALOG);
                    dialog.setMultiSelectionEnabled(false);
                    dialog.showOpenDialog(null);

                    File file = null;
                    file = dialog.getSelectedFile();

                    if(!file.equals(null)){
                        fileView.openFile(file,type);
                            inputLine = fileView.getLineElement().getInputLine();
                            if(!inputLine.equals(null)){
                                textField1.setText(inputLine);
                                textField2.setText("");
                            }
                    }
                    break;
                case "Save":
                    JFileChooser saveDialog = new JFileChooser();
                    saveDialog.setApproveButtonText("Save");
                    saveDialog.setDialogTitle("Save file");
                    saveDialog.setDialogType(JFileChooser.SAVE_DIALOG);
                    saveDialog.setMultiSelectionEnabled(false);
                    saveDialog.showOpenDialog(null);

                    java.io.File saveFile = saveDialog.getSelectedFile();

                    fileView.saveFile(saveFile);

                    textField1.setText("");
                    textField2.setText("");
                    break;
                case "Exit":
                    System.exit(0);
                    break;
            }
        }
    }
}
