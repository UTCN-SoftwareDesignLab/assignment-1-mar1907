package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ManageClientsView extends View {
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JButton btnNew;
    private JButton btnUpdate;

    /**
     * Create the frame.
     */
    public ManageClientsView() {
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

        JButton btnViewAll = new JButton("View all");
        contentPane.add(btnViewAll);

        btnNew = new JButton("New");
        contentPane.add(btnNew);

        btnUpdate = new JButton("Update");
        contentPane.add(btnUpdate);
    }
}
