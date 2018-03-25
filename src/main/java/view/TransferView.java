package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TransferView extends View {
    private JPanel contentPane;
    private JTextField textField;
    private JButton btnDelete;
    private JLabel lblAccountId;
    private JTextField textField_1;
    private JLabel lblAccountId_1;
    private JTextField textField_2;


    public TransferView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 526, 351);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        lblAccountId = new JLabel("Account 1 id");
        contentPane.add(lblAccountId);

        textField_1 = new JTextField();
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        lblAccountId_1 = new JLabel("Account 2 id");
        contentPane.add(lblAccountId_1);

        textField_2 = new JTextField();
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblId = new JLabel("Amount");
        contentPane.add(lblId);

        textField = new JTextField();
        contentPane.add(textField);
        textField.setColumns(10);

        btnDelete = new JButton("Transfer");
        contentPane.add(btnDelete);
    }
}
