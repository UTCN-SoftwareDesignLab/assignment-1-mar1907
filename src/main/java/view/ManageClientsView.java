package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManageClientsView extends View {
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextArea clientDataField;
    private JButton btnViewAll;
    private JButton btnNew;
    private JButton btnUpdate;

    public ManageClientsView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 526, 351);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel lblData = new JLabel("client data");
        contentPane.add(lblData);

        clientDataField = new JTextArea();
        clientDataField.setColumns(10);
        contentPane.add(clientDataField);

        JLabel lblId = new JLabel("id");
        contentPane.add(lblId);

        textField = new JTextField();
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("name");
        contentPane.add(lblNewLabel);

        textField_1 = new JTextField();
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("idCard");
        contentPane.add(lblNewLabel_1);

        textField_2 = new JTextField();
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblCnp = new JLabel("cnp");
        contentPane.add(lblCnp);

        textField_3 = new JTextField();
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        btnViewAll = new JButton("View all");
        contentPane.add(btnViewAll);

        btnNew = new JButton("New");
        contentPane.add(btnNew);

        btnUpdate = new JButton("Update");
        contentPane.add(btnUpdate);
    }

    public void addViewClientsActionListener(ActionListener al){
        btnViewAll.addActionListener(al);
    }

    public void sendClientsData(String clientsData){
        clientDataField.setText(clientsData);
    }

    public void addNewClientActionListener(ActionListener al){
        btnNew.addActionListener(al);
    }

    public String getCName(){
        return textField_1.getText();
    }

    public String getIdCard(){
        return textField_2.getText();
    }

    public String getCNP(){
        return textField_3.getText();
    }

    public void addUpdateClientActionListener(ActionListener al){
        btnUpdate.addActionListener(al);
    }

    public String getCId(){
        return textField.getText();
    }
}
