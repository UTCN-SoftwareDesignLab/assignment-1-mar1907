package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class GenerateReportView extends View {

    private JTextArea textArea;
    private JLabel lblResult;
    private JTextField textField_3;
    private JLabel lblClientId;
    private JPanel contentPane;
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

        lblClientId = new JLabel("client id");
        contentPane.add(lblClientId);

        textField_3 = new JTextField();
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        lblResult = new JLabel("result");
        contentPane.add(lblResult);

        textArea = new JTextArea();
        contentPane.add(textArea);

        btnDelete = new JButton("Generate report");
        contentPane.add(btnDelete);
    }

    public void addGenereateReportActionListener(ActionListener al){
        btnDelete.addActionListener(al);
    }

    public String getFrom(){
        return textField_1.getText();
    }

    public String getTo(){
        return textField_2.getText();
    }

    public String getCId(){
        return textField_3.getText();
    }

    public void sendData(String data){
        textArea.setText(data);
    }
}
