import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Manager extends Employee implements ActionListener, MouseListener {
    Manager(String fName, String lName, String email){
        this.firstName = fName;
        this.lastName = lName;
        this.setEmail(email);
    }

    // CREATING OBJECTS
    JFrame frame = new JFrame();
    JPanel middlePanel = new JPanel();
    JPanel topic = new JPanel();

    JLabel topic1 = new JLabel();
    JLabel topic2 = new JLabel();
    JLabel blank1 = new JLabel(" ");
    JLabel blank2 = new JLabel(" ");
    JLabel blank3 = new JLabel(" ");
    JLabel blank4 = new JLabel(" ");
    JLabel blank5 = new JLabel(" ");
    JLabel blank6 = new JLabel(" ");
    JLabel moveBack = new JLabel("Back");
    JLabel bottomEmail = new JLabel();

    JButton checkBills = new JButton();
    JButton addItems = new JButton();
    JButton editPrices = new JButton();
    JButton getSystemUsers = new JButton();


    void managerServices(){
        frame.setVisible(true);
        frame.setSize(1480,840);
        frame.setTitle("Restaurant Billing System");
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(209, 235, 255));
        frame.add(middlePanel);
        frame.add(bottomEmail);

        // MIDDLE BACKGROUND STYLING
        middlePanel.setBounds(515,50,450,700);
        middlePanel.setLayout(new GridLayout(12,1, 0,5));
        middlePanel.setBackground(Color.WHITE);
        middlePanel.setBorder(new EmptyBorder(10,50,0,50));
        middlePanel.add(blank1);
        middlePanel.add(topic);
        middlePanel.add(blank2);
        middlePanel.add(checkBills);
        middlePanel.add(blank3);
        middlePanel.add(addItems);
        middlePanel.add(blank4);
        middlePanel.add(editPrices);
        middlePanel.add(blank5);
        middlePanel.add(getSystemUsers);
        middlePanel.add(blank6);
        middlePanel.add(moveBack);

        // TOPIC, MANAGER NAME AND GREETING
        topic.setLayout(new GridLayout(2,0));
        topic.setBackground(Color.WHITE);
        topic.add(topic1);
        topic.add(topic2);

        topic1.setText("Hi, " + this.firstName);
        topic1.setHorizontalAlignment(JLabel.CENTER);
        topic1.setForeground(new Color(18, 84, 136));
        topic1.setFont(new Font("Calibri", Font.BOLD, 22));

        topic2.setText(this.lastName);
        topic2.setHorizontalAlignment(JLabel.CENTER);
        topic2.setForeground(new Color(18, 84, 136));
        topic2.setFont(new Font("Calibri", Font.BOLD, 22));

        // CHECK BILLS BUTTON
        checkBills.setText("Check Old Bills");
        checkBills.setBackground(new Color(55, 202, 236));
        checkBills.setForeground(new Color(0, 0, 0));
        checkBills.setFocusable(false);
        checkBills.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        checkBills.setFont(new Font("Calibri", Font.BOLD, 18));
        checkBills.addActionListener(this);

        // ADD ITEMS BUTTON
        addItems.setText("Add New Items");
        addItems.setBackground(new Color(55, 202, 236));
        addItems.setForeground(new Color(0, 0, 0));
        addItems.setFocusable(false);
        addItems.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        addItems.setFont(new Font("Calibri", Font.BOLD, 18));
        addItems.addActionListener(this);

        // EDIT PRICES BUTTON
        editPrices.setText("Edit Pricing");
        editPrices.setBackground(new Color(55, 202, 236));
        editPrices.setForeground(new Color(0, 0, 0));
        editPrices.setFocusable(false);
        editPrices.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        editPrices.setFont(new Font("Calibri", Font.BOLD, 18));
        editPrices.addActionListener(this);

        // GET SYSTEM USER DETAILS BUTTON
        getSystemUsers.setText("All System Users");
        getSystemUsers.setBackground(new Color(55, 202, 236));
        getSystemUsers.setForeground(new Color(0, 0, 0));
        getSystemUsers.setFocusable(false);
        getSystemUsers.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        getSystemUsers.setFont(new Font("Calibri", Font.BOLD, 18));
        getSystemUsers.addActionListener(this);

        // BACK TO DASHBOARD
        moveBack.setFont(new Font("Calibri", Font.PLAIN, 18));
        moveBack.setForeground(new Color(55, 202, 236));
        moveBack.setHorizontalAlignment(JLabel.CENTER);
        moveBack.addMouseListener(this);

        bottomEmail.setText("Manager Email: " + this.getEmail());
        bottomEmail.setFont(new Font("Calibri", Font.PLAIN, 15));
        bottomEmail.setForeground(new Color(100,100,100));
        bottomEmail.setBounds(20,770,400,20);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == checkBills){
            checkBillsClicked();
        }

        if(e.getSource() == getSystemUsers){
            systemUserDetails();
        }

        if(e.getSource() == addItems){
            AddExtraItems addExtraItems = new AddExtraItems();
            addExtraItems.addItemsUI(this.firstName, this.lastName,this.getEmail());
            frame.dispose();
        }

        if(e.getSource() == editPrices){
            EditPrices editPrices = new EditPrices();
            editPrices.editPricesUI(this.firstName, this.lastName, this.getEmail());
            frame.dispose();
        }
    }

    void systemUserDetails(){
        SystemUser systemUser = new SystemUser();
        systemUser.getSystemUserDetails();
        frame.dispose();
        systemUser.SystemUserUI(this.firstName, this.lastName, this.getEmail());
    }

    void checkBillsClicked(){
        CheckBills checkBills = new CheckBills();
        checkBills.billExcelDownload();
        frame.dispose();
        checkBills.checkBillsUI(this.firstName, this.lastName, this.getEmail());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == moveBack){
            backLabelClicked();
        }
    }

    void backLabelClicked(){
        frame.dispose();
        Background background = new Background();
        background.backgroundUI();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        moveBack.setForeground(new Color(18, 84, 136));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        moveBack.setForeground(new Color(55,202,236));
    }
}
