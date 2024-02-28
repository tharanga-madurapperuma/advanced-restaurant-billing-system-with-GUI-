import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalBackground implements ActionListener {
    static String name;
    static int numOfItems;
    static float cusTotal;
    static float givenMoney;
    static float balance;
    static String date;
    static String time;
    static String adminFirstName;
    static String adminLastName;
    static String adminEmail;


    JPanel middlePanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();

    JLabel blank1 = new JLabel();
    JLabel blank2 = new JLabel();
    JLabel blank3 = new JLabel();
    JLabel successAdded = new JLabel();
    JLabel successAdded1 = new JLabel();
    JLabel finalCusName = new JLabel();
    JLabel finalItems = new JLabel();
    JLabel finalCusTotal = new JLabel();
    JLabel finalBalance = new JLabel();
    JLabel finalDate = new JLabel();

    JButton addCustomerBtn = new JButton();
    JButton exitBtn = new JButton();

    JFrame frame = new JFrame();

    public void finalFrame(String fName, int fNumOfItems, float fCusTotal, float fGivenMoney, float fBalance, String fDate, String fTime, String adminFName, String adminLName){
        name = fName;
        numOfItems = fNumOfItems;
        cusTotal = fCusTotal;
        givenMoney = fGivenMoney;
        balance = fBalance;
        date = fDate;
        time = fTime;
        adminFirstName = adminFName;
        adminLastName = adminLName;

        new FinalBackground().background();
    }

    public void background(){
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(1080, 720);
        frame.setTitle("Restaurant Billing System");
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(209, 235, 255));
        frame.add(middlePanel);

        middlePanel.setBounds(340, 75, 400, 520);
        middlePanel.setLayout(new GridLayout(12,1));
        middlePanel.setBackground(Color.WHITE);
        middlePanel.add(blank1);
        middlePanel.add(successAdded);
        middlePanel.add(successAdded1);
        middlePanel.add(blank2);
        middlePanel.add(finalCusName);
        middlePanel.add(finalCusTotal);
        middlePanel.add(finalBalance);
        middlePanel.add(finalItems);
        middlePanel.add(finalDate);
        middlePanel.add(blank3);
        middlePanel.add(bottomPanel);

        topPanel.setBounds(340,75,400,200);
        topPanel.setBackground(Color.BLUE);

        successAdded.setText("SUCCESSFULLY");
        successAdded.setHorizontalAlignment(JLabel.CENTER);
        successAdded.setForeground(new Color(3,201,136));
        successAdded.setFont(new Font("Calibri", Font.BOLD, 30));

        successAdded1.setText("BILLED");
        successAdded1.setHorizontalAlignment(JLabel.CENTER);
        successAdded1.setForeground(new Color(3,201,136));
        successAdded1.setFont(new Font("Calibri", Font.BOLD, 30));

        finalCusName.setText("Customer Name:   " + String.valueOf(name));
        finalCusName.setHorizontalAlignment(JLabel.CENTER);
        finalCusName.setForeground(new Color(18, 84, 136));
        finalCusName.setFont(new Font("Calibri", Font.PLAIN, 15));

        finalItems.setText("Total Item Count:   " + String.valueOf(numOfItems));
        finalItems.setHorizontalAlignment(JLabel.CENTER);
        finalItems.setForeground(new Color(18, 84, 136));
        finalItems.setFont(new Font("Calibri", Font.PLAIN, 15));

        finalCusTotal.setText("Sub Total:   " + String.valueOf(cusTotal) + "0");
        finalCusTotal.setHorizontalAlignment(JLabel.CENTER);
        finalCusTotal.setForeground(new Color(18, 84, 136));
        finalCusTotal.setFont(new Font("Calibri", Font.PLAIN, 15));

        finalBalance.setText("Balance:   " + String.valueOf(balance) + "0");
        finalBalance.setHorizontalAlignment(JLabel.CENTER);
        finalBalance.setForeground(new Color(18, 84, 136));
        finalBalance.setFont(new Font("Calibri", Font.BOLD, 18));

        finalDate.setText("Billed Details:   " + String.valueOf(date) + " - " + String.valueOf(time));
        finalDate.setHorizontalAlignment(JLabel.CENTER);
        finalDate.setForeground(new Color(18, 84, 136));
        finalDate.setFont(new Font("Calibri", Font.PLAIN, 15));

        bottomPanel.setBounds(0,0,300,50);
        bottomPanel.setLayout(new GridLayout(0,2,20,0));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,50,0,50));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(addCustomerBtn);
        bottomPanel.add(exitBtn);

        addCustomerBtn.setText("Add Customer");
        addCustomerBtn.setFont(new Font("Calibri", Font.BOLD, 16));
        addCustomerBtn.setBackground(new Color(55, 202, 236));
        addCustomerBtn.setFocusable(false);
        addCustomerBtn.setBorder(BorderFactory.createEmptyBorder(0,3,0,0));
        addCustomerBtn.addActionListener(this);

        exitBtn.setText("Exit");
        exitBtn.setFont(new Font("Calibri", Font.BOLD, 16));
        exitBtn.setBackground(new Color(55, 202, 236));
        exitBtn.setFocusable(false);
        exitBtn.setBorder(BorderFactory.createEmptyBorder(0,7,0,0));
        exitBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCustomerBtn) {
            frame.dispose();
            BillingBackground billingBackground = new BillingBackground();
            billingBackground.billingBackgroundInterface(adminFirstName, adminLastName, adminEmail);
        }

        if (e.getSource() == exitBtn) {
            frame.dispose();
        }
    }
}
