import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CheckBills implements dataBaseQuaries, ActionListener {
    JFrame frame = new JFrame();
    JLabel manager = new JLabel();
    JLabel managerName = new JLabel();
    JLabel downloaded = new JLabel();
    JLabel downloaded2 = new JLabel();
    JButton backButton = new JButton();

    String firstName = "";
    String lastName = "";
    String Email = "";

    // DOWNLOADING FILES TO EXCEL
    public void billExcelDownload(){
        Workbook wb = new Workbook();

        //Get the first worksheet
        Worksheet sheet = wb.getWorksheets().get(0);

        //Create a DataTable object
        DataTable dataTable = new DataTable();

        //Connect to database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                Connection conn = DriverManager.getConnection(url,user,dbPassword);
                Statement sta = conn.createStatement();

                //Select table from the database
                ResultSet resultSet = sta.executeQuery("select * from customers");
                JdbcAdapter jdbcAdapter = new JdbcAdapter();

                //Export data from database to datatable
                jdbcAdapter.fillDataTable(dataTable, resultSet);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Write datatable to the worksheet
        sheet.insertDataTable(dataTable, true, 1, 1);

        // Auto-fit column width
        sheet.getAllocatedRange().autoFitColumns();

        //Save to an Excel file
        wb.saveToFile("C:/Users/thara/Downloads/OldBills.xlsx", ExcelVersion.Version2016);
    }

    // STYLING CHECK BILLS WINDOW
    public void checkBillsUI(String fName, String lName, String email){
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

        downloaded.setText("--- Old Bills Details File Downloaded ---");
        downloaded.setFont(new Font("Calibri", Font.BOLD, 35));
        downloaded.setForeground(new Color(18, 84, 136));
        downloaded.setBounds(430, 350, 800,50);

        downloaded2.setText("- Check Your Download File -");
        downloaded2.setFont(new Font("Calibri", Font.PLAIN, 25));
        downloaded2.setForeground(new Color(18, 84, 136));
        downloaded2.setBounds(570, 400, 500,50);

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
