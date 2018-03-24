package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ManageAccountsView extends View {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JButton btnNew;
    private JButton btnUpdate;
    private JCheckBox chckbxSavingAccount;
    private JButton btnDelete;

    public ManageAccountsView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 526, 351);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel lblId = new JLabel("id");
        contentPane.add(lblId);

        textField = new JTextField();
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("amount");
        contentPane.add(lblNewLabel);

        textField_1 = new JTextField();
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        chckbxSavingAccount = new JCheckBox("Saving account");
        contentPane.add(chckbxSavingAccount);

        JButton btnViewAll = new JButton("View all");
        contentPane.add(btnViewAll);

        btnNew = new JButton("New");
        contentPane.add(btnNew);

        btnUpdate = new JButton("Update");
        contentPane.add(btnUpdate);

        btnDelete = new JButton("Delete");
        contentPane.add(btnDelete);
    }
}
