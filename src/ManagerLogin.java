import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ManagerLogin extends JFrame implements ActionListener, dataBaseQuaries{
    String FirstName;
    String LastName;
    // CREATED OBJECTS
    JPanel loginBackground = new JPanel();
    JPanel dataBackground = new JPanel();
    JPanel btnResize = new JPanel();
    JLabel topic = new JLabel();
    JLabel email = new JLabel();
    JLabel password1 = new JLabel();
    JLabel blank1 = new JLabel();
    JLabel blank2 = new JLabel();
    JLabel blank3 = new JLabel();
    JLabel blank4 = new JLabel();
    JLabel moveToSignUp = new JLabel();
    JTextField emailEnter = new JTextField();
    JPasswordField pwEnter = new JPasswordField();
    JButton loginBtn = new JButton();
    JButton goBack = new JButton();
    BillingBackground billingBackground = new BillingBackground();

    void ManagerLoginUI(){
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
        this.add(loginBackground);

        // GO BACK TO MAIN MENU
        goBack.setText("<--  Back");
        goBack.setBounds(20,30,110,20);
        goBack.setFont(new Font("Calibri", Font.PLAIN, 16));
        goBack.setForeground(new Color(18, 84, 136));
        goBack.setBackground(null);
        goBack.setFocusable(false);
        goBack.setBorder(BorderFactory.createEmptyBorder());
        goBack.addActionListener(this);

        // OUTER PANEL
        loginBackground.setBounds(320,60, 400, 550);
        loginBackground.setBackground(Color.white);

        // WHITE BACKGROUND PANEL
        dataBackground.setBounds(350,90, 340, 510);
        dataBackground.setBackground(Color.white);
        dataBackground.setLayout(new GridLayout(10,1, 10,10));
        dataBackground.add(topic);
        dataBackground.add(blank1);
        dataBackground.add(email);
        dataBackground.add(emailEnter);
        dataBackground.add(password1);
        dataBackground.add(pwEnter);
        dataBackground.add(blank2);
        dataBackground.add(btnResize);
        dataBackground.add(blank3);
        dataBackground.add(moveToSignUp);

        topic.setText("<html>Login Account</html>");
        topic.setHorizontalAlignment(JLabel.CENTER);
        topic.setForeground(new Color(18, 84, 136));
        topic.setFont(new Font("Poppins", Font.BOLD, 22));

        // EMAIL
        emailEnter.setBackground(new Color(235,235,235));
        emailEnter.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));

        email.setText("Email :");
        email.setFont(new Font("Calibri", Font.PLAIN, 16));
        email.setForeground(new Color(150,150,150));

        // PASSWORD
        password1.setText("Password :");
        password1.setFont(new Font("Calibri", Font.PLAIN, 16));
        password1.setForeground(new Color(150,150,150));

        pwEnter.setBackground(new Color(235,235,235));
        pwEnter.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));

        blank1.setText("");
        blank2.setText("");
        blank3.setText("");
        blank4.setText("");

        // FOOTER BUTTON
        loginBtn.setText("Login");
        loginBtn.setFont(new Font("Calibri", Font.BOLD, 19));
        loginBtn.setBackground(new Color(55, 202, 236));
        loginBtn.setFocusable(false);
        loginBtn.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        loginBtn.addActionListener(this);

        btnResize.add(blank4);
        btnResize.add(loginBtn);
        btnResize.setBackground(Color.white);
        btnResize.setLayout(new GridLayout(0,3, 0 ,0));
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == goBack){
            Background background = new Background();
            background.backgroundUI();
        }

        if(e.getSource() == loginBtn){
            loginButtonClicked();
        }
    }

    void loginButtonClicked(){
        String getEmail = emailEnter.getText();
        String getPassword = pwEnter.getText();

        String query = "Select * from manager where email='"+getEmail+"' and password = '"+getPassword+"'";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()){
                dispose();
                FirstName = resultSet.getString(1);
                LastName = resultSet.getString(2);
                Manager manager = new Manager(FirstName, LastName,resultSet.getString(3));
                manager.managerServices();
            }
            else{
                JOptionPane.showMessageDialog(this,"Invalid email or Password !!!", "Login Error !!!",JOptionPane.ERROR_MESSAGE);
                emailEnter.setText("");
                pwEnter.setText("");
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception x){
            System.out.println(x);
        }
    }
}
