import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExtraItems implements ActionListener {
    JFrame frame = new JFrame();
    JLabel manager = new JLabel();
    JLabel managerName = new JLabel();
    JLabel downloaded = new JLabel();
    JLabel downloaded2 = new JLabel();
    JButton backButton = new JButton();

    String firstName = "";
    String lastName = "";
    String Email = "";

    // ADD ITEMS UI
    public void addItemsUI(String fName, String lName, String email){
        firstName = fName;
        lastName = lName;
        Email = email;

        // BACKGROUND FRAME
        frame.setVisible(true);
        frame.setSize(1480,840);
        frame.setTitle("Restaurant Billing System");
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(209, 235, 255));
        frame.add(manager);
        frame.add(managerName);
        frame.add(downloaded);
        frame.add(downloaded2);
        frame.add(backButton);

        manager.setText("Account: " + fName + " " + lName + " (Manager)");
        manager.setFont(new Font("Calibri", Font.PLAIN, 16));
        manager.setForeground(new Color(100,100,100));
        manager.setBounds(20,20,400,30);

        managerName.setText("Email: " + email);
        managerName.setFont(new Font("Calibri", Font.PLAIN, 16));
        managerName.setForeground(new Color(100,100,100));
        managerName.setBounds(20,40,400,30);

        downloaded.setText("--- FUTURE MODIFICATION ---");
        downloaded.setFont(new Font("Calibri", Font.BOLD, 35));
        downloaded.setForeground(new Color(18, 84, 136));
        downloaded.setBounds(500, 350, 800,50);

        downloaded2.setText("- Coming Soon -");
        downloaded2.setFont(new Font("Calibri", Font.PLAIN, 25));
        downloaded2.setForeground(new Color(18, 84, 136));
        downloaded2.setBounds(640, 400, 500,50);

        backButton.setText("Back");
        backButton.setBackground(new Color(55, 202, 236));
        backButton.setForeground(new Color(0, 0, 0));
        backButton.setFocusable(false);
        backButton.setBounds(630, 740, 180, 40);
        backButton.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        backButton.setFont(new Font("Calibri", Font.BOLD, 18));
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton){
            frame.dispose();
            Manager manager = new Manager(firstName, lastName, Email);
            manager.managerServices();
        }
    }
}
