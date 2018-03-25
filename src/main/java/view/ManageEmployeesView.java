package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ManageEmployeesView extends View {
    private JPanel contentPane;
    private JLabel lblId;
    private JTextField textField;
    private JLabel lblUsername;
    private JTextField textField_1;
    private JCheckBox chckbxEmployee;
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

        chckbxEmployee = new JCheckBox("Employee");
        contentPane.add(chckbxEmployee);

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
}
