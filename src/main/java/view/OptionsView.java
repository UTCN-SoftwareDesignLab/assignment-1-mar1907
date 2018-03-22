package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OptionsView extends View{

    //private List<Boolean> options;
    private List<JButton> buttons;
    private JPanel contentPane;


    public OptionsView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 241, 213);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    }

    public void setButtons(List<Boolean> options){
        buttons = new ArrayList<>();
        buttons.add(new JButton("Manage Clients"));
        buttons.add(new JButton("Manage Accounts"));
        buttons.add(new JButton("Transfer Money"));
        buttons.add(new JButton("Pay Bill"));
        buttons.add(new JButton("Manage Employees"));
        buttons.add(new JButton("Generate Activity Report"));

        for(int i = 0; i < options.size(); i++) {
            if (options.get(i))
                contentPane.add(buttons.get(i));
        }
    }

    public void setManageClientsButtonListener(ActionListener al){
        buttons.get(0).addActionListener(al);
    }

    public void setManageAccountsButtonListener(ActionListener al){
        buttons.get(1).addActionListener(al);
    }

    public void setTransferMoneyButtonListener(ActionListener al){
        buttons.get(2).addActionListener(al);
    }

    public void setPayBillButtonListener(ActionListener al){
        buttons.get(3).addActionListener(al);
    }

    public void setManageEmployeesButtonListener(ActionListener al){
        buttons.get(4).addActionListener(al);
    }

    public void setGenerateActivityReportButtonListener(ActionListener al){
        buttons.get(5).addActionListener(al);
    }
}
