package view;

import javax.swing.*;
import java.awt.*;

public class EmployeeView extends JFrame {

    private JPanel container;
    private JPanel pnl_top;
    private JButton button1;
    private JTabbedPane tabbedPane1;
    private JTable tbl_otel;
    private JScrollPane scrl_otel;
    private JPanel pnl_otel;

    public EmployeeView() {
        //this.userManager = new UserManager();
        this.add(container);
        this.setSize(750,450);
        this.setTitle("Employee View");
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);
    }


}
