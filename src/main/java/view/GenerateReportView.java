package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GenerateReportView extends View {

    private JPanel contentPane;
    private JLabel lblId;
    private JTextField textField;
    private JLabel lblUsername;
    private JTextField textField_1;
    private JButton btnDelete;
    private JLabel lblEndDate;
    private JTextField textField_2;

    public GenerateReportView() {
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

        lblUsername = new JLabel("start date");
        contentPane.add(lblUsername);

        textField_1 = new JTextField();
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        lblEndDate = new JLabel("end date");
        contentPane.add(lblEndDate);

        textField_2 = new JTextField();
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        btnDelete = new JButton("Generate report");
        contentPane.add(btnDelete);
    }
}
