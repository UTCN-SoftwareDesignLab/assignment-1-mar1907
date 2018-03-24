package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PayBillView extends View {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JButton btnDelete;
    private JLabel lblAccountId;
    private JTextField textField_2;

    public PayBillView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 526, 351);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel lblId = new JLabel("Amount");
        contentPane.add(lblId);

        textField = new JTextField();
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Text");
        contentPane.add(lblNewLabel);

        textField_1 = new JTextField();
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        lblAccountId = new JLabel("Account Id");
        contentPane.add(lblAccountId);

        textField_2 = new JTextField();
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        btnDelete = new JButton("Pay");
        contentPane.add(btnDelete);

    }

}
