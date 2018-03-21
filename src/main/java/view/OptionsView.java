package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;

public class OptionsView extends JFrame{

    private List<Boolean> options;
    private JPanel contentPane;


    public OptionsView(List<Boolean> options) {
        this.options = options;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 241, 213);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        List<JButton> buttons = new ArrayList<>();
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

        setVisible(true);

    }
}
