import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CreateOne extends JFrame implements ActionListener, MouseListener, dataBaseQuaries, AbstractMethods{
    String FirstName;
    String LastName;
    String Email;
    // CREATED OBJECTS
    JPanel createOneBackground = new JPanel();
    JPanel dataBackground = new JPanel();
    JPanel btnResize = new JPanel();
    JLabel topic = new JLabel();
    JLabel firstName = new JLabel();
    JLabel lastName = new JLabel();
    JLabel email = new JLabel();
    JLabel password1 = new JLabel();
    JLabel blank1 = new JLabel();
    JLabel blank2 = new JLabel();
    JLabel blank3 = new JLabel();
    JLabel blank4 = new JLabel();
    JLabel moveToLogin = new JLabel();
    JTextField firstNameEnter = new JTextField();
    JTextField lastNameEnter = new JTextField();
    JTextField emailEnter = new JTextField();
    JPasswordField pwEnter = new JPasswordField();
    JButton createOneBtn = new JButton();
    JButton goBack = new JButton();

    // MAIN RUNNING METHOD
    @Override
    public void createOneUI(){
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(1080, 720);
        this.setTitle("Restaurant Billing System");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(209, 235, 255));
        this.add(goBack);
        this.add(dataBackground);
        this.add(createOneBackground);

        // BACK TO MENU PAGE
        goBack.setText("<--  Back");
        goBack.setBounds(20,30,110,20);
        goBack.setFont(new Font("Calibri", Font.PLAIN, 16));
        goBack.setForeground(new Color(18, 84, 136));
        goBack.setBackground(new Color(209, 235, 255));
        goBack.setFocusable(false);
        goBack.setBorder(BorderFactory.createEmptyBorder());
        goBack.addActionListener(this);

        createOneBackground.setBounds(320,60, 400, 550);
        createOneBackground.setBackground(Color.white);

        // OUTER PANEL
        dataBackground.setBounds(350,90, 340, 510);
        dataBackground.setBackground(Color.white);
        dataBackground.setLayout(new GridLayout(14,1, 10,2));
        dataBackground.add(topic);
        dataBackground.add(blank1);
        dataBackground.add(firstName);
        dataBackground.add(firstNameEnter);
        dataBackground.add(lastName);
        dataBackground.add(lastNameEnter);
        dataBackground.add(email);
        dataBackground.add(emailEnter);
        dataBackground.add(password1);
        dataBackground.add(pwEnter);
        dataBackground.add(blank2);
        dataBackground.add(btnResize);
        dataBackground.add(blank3);
        dataBackground.add(moveToLogin);

        topic.setText("<html>Create Account</html>");
        topic.setHorizontalAlignment(JLabel.CENTER);
        topic.setForeground(new Color(18, 84, 136));
        topic.setFont(new Font("Poppins", Font.BOLD, 22));

        firstName.setText("First Name :");
        firstName.setFont(new Font("Calibri", Font.PLAIN, 16));
        firstName.setForeground(new Color(150,150,150));

        firstNameEnter.setBackground(new Color(235,235,235));
        firstNameEnter.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));

        lastName.setText("Last Name :");
        lastName.setFont(new Font("Calibri", Font.PLAIN, 16));
        lastName.setForeground(new Color(150,150,150));

        lastNameEnter.setBackground(new Color(235,235,235));
        lastNameEnter.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));

        emailEnter.setBackground(new Color(235,235,235));
        emailEnter.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));

        email.setText("Email :");
        email.setFont(new Font("Calibri", Font.PLAIN, 16));
        email.setForeground(new Color(150,150,150));

        password1.setText("Password :");
        password1.setFont(new Font("Calibri", Font.PLAIN, 16));
        password1.setForeground(new Color(150,150,150));

        pwEnter.setBackground(new Color(235,235,235));
        pwEnter.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));

        blank1.setText("");
        blank2.setText("");
        blank3.setText("");
        blank4.setText("");

        // CREATE ONE BUTTON
        createOneBtn.setText("Create");
        createOneBtn.setFont(new Font("Calibri", Font.BOLD, 17));
        createOneBtn.setBackground(new Color(55, 202, 236));
        createOneBtn.setFocusable(false);
        createOneBtn.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        createOneBtn.addActionListener(this);

        moveToLogin.setText("Already have an account! Login");
        moveToLogin.setFont(new Font("Calibri", Font.PLAIN, 15));
        moveToLogin.setForeground(new Color(55,202,236));
        moveToLogin.setHorizontalAlignment(JLabel.CENTER);
        moveToLogin.addMouseListener(this);

        btnResize.add(blank4);
        btnResize.add(createOneBtn);
        btnResize.setBackground(Color.white);
        btnResize.setLayout(new GridLayout(0,3, 0 ,0));

    }
    @Override
    public void loginUI(){}
    @Override
    public void backgroundUI(){}
    @Override
    public void billingBackgroundInterface(String FName, String LName, String email){}

    // BUTTON ACTIONS
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == goBack){
            dispose();
            Background background = new Background();
            background.backgroundUI();
        }
        if(e.getSource() == createOneBtn){
            createOneButtonClicked();
        }
    }

    // CREATE ONE BUTTON CLICKED THIS FUNCTION EXECUTE
    void createOneButtonClicked(){
        String fNameSave = firstNameEnter.getText();
        String lNameSave = lastNameEnter.getText();
        String emailSave = emailEnter.getText();
        String pwSave = pwEnter.getText();

        // DATA BASE QUERY TO INSERT INFORMATION
        String query = "INSERT INTO `admin`(`firstName`, `lastName`, `email`, `password`) VALUES (?, ?, ?, ?)";

        if(fNameSave.isEmpty() || lNameSave.isEmpty() || emailSave.isEmpty() || pwSave.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill all fields..!", "Filling Error..", JOptionPane.ERROR_MESSAGE);
            firstNameEnter.setText("");
            lastNameEnter.setText("");
            emailEnter.setText("");
            pwEnter.setText("");
        }else{
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url,user,dbPassword);
                PreparedStatement pst = connection.prepareStatement(query);

                // DATA SAVING
                FirstName = firstNameEnter.getText();
                LastName = lastNameEnter.getText();
                Email = emailEnter.getText();
                pst.setString(1, fNameSave);
                pst.setString(2, lNameSave);
                pst.setString(3, emailSave);
                pst.setString(4, pwSave);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Account Creation Success..", "Account creation",JOptionPane.INFORMATION_MESSAGE);

                // CALLING TO METHOD FOR NEXT STEP
                BillingBackground billingBackground = new BillingBackground();
                billingBackground.billingBackgroundInterface(FirstName,LastName,Email);

                dispose();
                connection.close();

            }catch (Exception e2){
                System.out.println(e2);
            }
        }
    }

    // MOUSE ACTIONS
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == moveToLogin){
            labelClicked();
        }
    }

    void labelClicked(){
        dispose();
        Login login = new Login();
        login.loginUI();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        moveToLogin.setForeground(new Color(18, 84, 136));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        moveToLogin.setForeground(new Color(55,202,236));
    }
}
