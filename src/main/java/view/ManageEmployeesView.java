package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class ManageEmployeesView extends View {
    private JPanel contentPane;
    private JLabel lblId;
    private JTextField textField;
    private JTextArea textArea;
    private JLabel lblUsername;
    private JLabel lblPass;
    private JTextField textField_1;
    private JTextField textField_2;
    private JCheckBox chckbxAdministrator;
    private JButton btnViewAll;
    private JButton btnCreateNew;
    private JButton btnUpdate;
    private JButton btnDelete;

    public ManageEmployeesView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 526, 351);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("data");
        contentPane.add(label);

        textArea = new JTextArea();
        contentPane.add(textArea);

        lblId = new JLabel("id");
        contentPane.add(lblId);

        textField = new JTextField();
        contentPane.add(textField);
        textField.setColumns(10);

        lblUsername = new JLabel("username");
        contentPane.add(lblUsername);

        textField_1 = new JTextField();
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        lblPass = new JLabel("password");
        contentPane.add(lblPass);

        textField_2 = new JTextField();
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        chckbxAdministrator = new JCheckBox("Administrator");
        contentPane.add(chckbxAdministrator);

        btnViewAll = new JButton("View all");
        contentPane.add(btnViewAll);

        btnCreateNew = new JButton("Create new");
        contentPane.add(btnCreateNew);

        btnUpdate = new JButton("Update");
        contentPane.add(btnUpdate);

        btnDelete = new JButton("Delete");
        contentPane.add(btnDelete);
    }

    public void addViewAllActionListene(ActionListener al){
        btnViewAll.addActionListener(al);
    }

    public void sendData(String text){
        textArea.setText(text);
    }

    public void addCreateNewActionListener(ActionListener al){
        btnCreateNew.addActionListener(al);
    }

    public String getEName(){
        return textField_1.getText();
    }

    public String getEPass(){
        return textField_2.getText();
    }

    public boolean isAdmin(){
        return chckbxAdministrator.isSelected();
    }

    public void addUpdateActionListener(ActionListener al){
        btnUpdate.addActionListener(al);
    }

    public String getID(){
        return textField.getText();
    }

    public void addDeleteActionListener(ActionListener al){
        btnDelete.addActionListener(al);
    }
}
