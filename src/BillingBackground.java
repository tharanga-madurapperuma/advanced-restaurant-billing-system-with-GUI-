// Importing pre-build libraries
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BillingBackground implements ActionListener, dataBaseQuaries, AbstractMethods{
    // Instance variables
    int totalItemCount = 0;
    float sumOfTotal;
    int totalQtyCount;
    String cusName;
    float cGivenMoney;
    int cItemTotal;
    float cTotalPrice;
    String adminFirstName = "";
    String adminLastName = "";
    String adminEmail = "";

    // All JPanel objects
    JPanel leftPanelOuter = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0,0));
    JPanel userDetail = new JPanel();
    JPanel customerName = new JPanel();
    JPanel itemNumber = new JPanel();
    JPanel itemQty = new JPanel();
    JPanel allItems = new JPanel();
    JPanel itemsCounter = new JPanel();
    JPanel balance = new JPanel();
    JPanel buttons = new JPanel();
    JPanel buttonGrid2 = new JPanel();
    JPanel column1 = new JPanel();
    JPanel column2 = new JPanel();
    JPanel column3 = new JPanel();
    JPanel column4 = new JPanel();
    JPanel enteredItem = new JPanel();
    JPanel totalCount = new JPanel();
    JPanel givenMoneyPanel = new JPanel();

    // All JLabel objects
    JLabel greeting = new JLabel();
    JLabel date = new JLabel();
    JLabel cName = new JLabel();
    JLabel itmNumber = new JLabel();
    JLabel itmQty = new JLabel();
    JLabel blank1 = new JLabel();
    JLabel blank2 = new JLabel();
    JLabel blank3 = new JLabel();
    JLabel blank4 = new JLabel();
    JLabel blank5 = new JLabel();
    JLabel numOfItems = new JLabel();
    JLabel numOfItemCount = new JLabel();
    JLabel nameColumn = new JLabel("Item Name");
    JLabel qtyColumn = new JLabel("Quantity");
    JLabel priceColumn = new JLabel("Unit Price");
    JLabel totalColumn = new JLabel("Total");
    JLabel enteredItemName = new JLabel();
    JLabel rightEnteredName = new JLabel();
    JLabel totalCountAdding = new JLabel();
    JLabel rightCountAdding = new JLabel();
    JLabel finalTotal = new JLabel();
    JLabel rightFinalTotal = new JLabel();
    JLabel givenMoneyLabel = new JLabel();

    // All Textfeild onbjects
    JTextField cNameEnter = new JTextField();
    JTextField itmNumberEnter = new JTextField();
    JTextField itmQtyEnter = new JTextField();
    JTextField givenMoneyEnter = new JTextField();

    // JButton objects
    JButton backButton = new JButton();
    JButton okButton = new JButton();
    JButton itemEnter = new JButton();

    // Date & Time getting
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm    yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();

    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

    // JFrame object
    JFrame frame = new JFrame();

    // BIlling Background method
    @Override
    public void billingBackgroundInterface(String FirstName, String LastName, String email){
        this.adminFirstName = FirstName;
        this.adminLastName = LastName;
        this.adminEmail = email;

        // Outer frame creating
            frame.setVisible(true);
            frame.setSize(1480,840);
            frame.setTitle("Restaurant Billing System");
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setBackground(new Color(209, 235, 255));
        frame.add(leftPanelOuter);
        frame.add(userDetail);
        frame.add(customerName);
        frame.add(itemNumber);
        frame.add(itemQty);
        frame.add(allItems);
        frame.add(enteredItem);
        frame.add(itemsCounter);
        frame.add(totalCount);
        frame.add(balance);
        frame.add(buttons);
        frame.add(givenMoneyPanel);

        // Left panel designing - Outer small frame
        leftPanelOuter.setBounds(20,20,322,758);
        leftPanelOuter.setBackground(new Color(240,240,240));
        leftPanelOuter.add(leftPanel);


        // Left panel designing
        leftPanel.setBounds(0,0,292,753);
        leftPanel.setBackground(Color.white);

        // Welcoming the use - First & Last name of system user 
        greeting.setText("Hello, " + FirstName + " " + LastName);
        greeting.setFont(new Font("Calibri", Font.BOLD, 25));
        greeting.setForeground(new Color(18, 84, 136));
        greeting.setHorizontalAlignment(JLabel.LEFT);

        // Date styling - Date & time display top right corner
        date.setText("Time & Date:   " + dtf.format(now));
        date.setFont(new Font("Calibri", Font.PLAIN, 18));
        date.setForeground(new Color(100,100,100));
        date.setHorizontalAlignment(JLabel.RIGHT);

        // Username entering box, name and name-label
        userDetail.setBounds(360, 20, 1080,50);
        userDetail.setBackground(new Color(209, 235, 255));
        userDetail.setLayout(new GridLayout(1,2, 50,10));
        userDetail.add(greeting);
        userDetail.add(date);

        // Customer name entering feild label
        cName.setText("Enter Customer Name:");
        cName.setFont(new Font("Calibri", Font.PLAIN, 15));

        // Customer name entering feild
        cNameEnter.setBackground(Color.white);
        cNameEnter.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));
        cusName = cNameEnter.getText();

        // Panel for customer name textfeild and label
        customerName.setBounds(360,70, 380,70);
        customerName.setBackground(new Color(209, 235, 255));
        customerName.setLayout(new GridLayout(2,1 ));
        customerName.add(cName);
        customerName.add(cNameEnter);

        // Item number entering label
        itmNumber.setText("Enter the Item Number:");
        itmNumber.setFont(new Font("Calibri", Font.PLAIN, 15));

        // Item number entering textfeild
        itmNumberEnter.setBackground(Color.white);
        itmNumberEnter.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));

        // Panel for item entering label and textfield
        itemEnter.setText("Add Item");
        itemEnter.setFont(new Font("Calibri", Font.PLAIN, 17));
        itemEnter.setBackground(new Color(18, 84, 136));
        itemEnter.setFocusable(false);
        itemEnter.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        itemEnter.setForeground(Color.WHITE);
        itemEnter.addActionListener(this);

        itemNumber.setBounds(360, 140,380,70);
        itemNumber.setLayout(new GridLayout(2,1, 10,0));
        itemNumber.setBackground(new Color(209, 235, 255));
        itemNumber.add(itmNumber);
        itemNumber.add(itmNumberEnter);

        itmQty.setText("Enter the Quantity:");
        itmQty.setFont(new Font("Calibri", Font.PLAIN, 15));

        itmQtyEnter.setBackground(Color.white);
        itmQtyEnter.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));

        buttonGrid2.setLayout(new GridLayout(0,2));
        buttonGrid2.setBackground(new Color(209, 235, 255));
        buttonGrid2.add(itemEnter);

        itemQty.setBounds(820,140,700,70);
        itemQty.setBackground(new Color(209, 235, 255));
        itemQty.setLayout(new GridLayout(2,3, 10,0));
        itemQty.add(itmQty);
        itemQty.add(blank1);
        itemQty.add(itmQtyEnter);
        itemQty.add(buttonGrid2);

        givenMoneyPanel.setBounds(360,740,732,38);
        givenMoneyPanel.setLayout(new GridLayout(0,2));
        givenMoneyPanel.setBackground(new Color(209, 235, 255));
        givenMoneyPanel.add(givenMoneyLabel);
        givenMoneyPanel.add(givenMoneyEnter);

        givenMoneyLabel.setText("Enter the Customer Given Amount:");
        givenMoneyLabel.setForeground(new Color(18, 84, 136));
        givenMoneyLabel.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
        givenMoneyLabel.setFont(new Font("Calibri", Font.BOLD, 16));

        givenMoneyEnter.setBackground(new Color(150,200,255));
        givenMoneyEnter.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));
        givenMoneyEnter.setFont(new Font("Calibri", Font.PLAIN, 15));

        allItems.setBounds(360, 230,732,480);
        allItems.setLayout(new GridLayout(0,4));
        allItems.setBackground(Color.WHITE);
        allItems.add(column1);
        allItems.add(column2);
        allItems.add(column3);
        allItems.add(column4);

        column1.setBounds(360, 230, 193, 480);
        column1.setBackground(Color.WHITE);
        column1.setLayout(new GridLayout(14,0));
        column1.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
        column1.add(nameColumn);

        column2.setBounds(553, 230, 193, 480);
        column2.setBackground(new Color(240,240,240));
        column2.setLayout(new GridLayout(14,0));
        column2.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        column2.add(qtyColumn);

        column3.setBounds(746, 230, 193, 480);
        column3.setBackground(Color.WHITE);
        column3.setLayout(new GridLayout(14,0));
        column3.setBorder(BorderFactory.createEmptyBorder(0,0,0,15));
        column3.add(priceColumn);

        column4.setBounds(939, 230, 193, 480);
        column4.setBackground(new Color(240,240,240));
        column4.setLayout(new GridLayout(14,0));
        column4.setBorder(BorderFactory.createEmptyBorder(0,0,0,15));
        column4.add(totalColumn);

        nameColumn.setFont(new Font("Calibri", Font.BOLD, 16));
        nameColumn.setHorizontalAlignment(JLabel.CENTER);

        qtyColumn.setFont(new Font("Calibri", Font.BOLD, 16));
        qtyColumn.setHorizontalAlignment(JLabel.CENTER);

        priceColumn.setFont(new Font("Calibri", Font.BOLD, 16));
        priceColumn.setHorizontalAlignment(JLabel.CENTER);

        totalColumn.setFont(new Font("Calibri", Font.BOLD, 16));
        totalColumn.setHorizontalAlignment(JLabel.CENTER);

        enteredItem.setBounds(1140, 230, 330,125);
        enteredItem.setBackground(new Color(209, 235, 255));
        enteredItem.setLayout(new GridLayout(4,1));
        enteredItem.add(blank2);
        enteredItem.add(enteredItemName);

        itemsCounter.setBounds(1140, 355, 330, 125);
        itemsCounter.setBackground(new Color(209, 235, 255));
        itemsCounter.setLayout(new GridLayout(4,1));
        itemsCounter.add(blank3);
        itemsCounter.add(numOfItems);
        itemsCounter.add(numOfItemCount);

        totalCount.setBounds(1140,480,330,125);
        totalCount.setBackground(new Color(209, 235, 255));
        totalCount.setLayout(new GridLayout(4,1));
        totalCount.add(blank4);
        totalCount.add(totalCountAdding);

        balance.setBounds(1140, 605, 330, 125);
        balance.setBackground(new Color(209, 235, 255));
        balance.setLayout(new GridLayout(4,1));
        balance.add(blank5);
        balance.add(finalTotal);

        enteredItemName.setText("Entered Item:");
        enteredItemName.setFont(new Font("Calibri", Font.PLAIN, 16));
        rightEnteredName.setFont(new Font("Calibri", Font.PLAIN, 15));
        rightEnteredName.setForeground(new Color(18, 84, 136));
        rightEnteredName.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));

        numOfItems.setText("Total Item Count:");
        numOfItems.setFont(new Font("Calibri", Font.PLAIN, 16));
        numOfItemCount.setFont(new Font("Calibri", Font.PLAIN, 15));
        numOfItemCount.setForeground(new Color(18, 84, 136));
        numOfItemCount.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));

        totalCountAdding.setText("Total Quantity Count:");
        totalCountAdding.setFont(new Font("Calibri", Font.PLAIN, 16));
        rightCountAdding.setFont(new Font("Calibri", Font.PLAIN, 15));
        rightCountAdding.setForeground(new Color(18, 84, 136));
        rightCountAdding.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));

        finalTotal.setText("Total Price:");
        finalTotal.setFont(new Font("Calibri", Font.PLAIN, 16));
        rightFinalTotal.setFont(new Font("Calibri", Font.BOLD, 18));
        rightFinalTotal.setForeground(new Color(18, 84, 136));
        rightFinalTotal.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));

        buttons.setBounds(1175,740, 220,40);
        buttons.setLayout(new GridLayout(1,2, 35,0));
        buttons.setBackground(new Color(209, 235, 255));
        buttons.add(backButton);
        buttons.add(okButton);

        // BACK BUTTON
        backButton.setText("Back");
        backButton.setBackground(new Color(55, 202, 236));
        backButton.setForeground(new Color(0, 0, 0));
        backButton.setFocusable(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        backButton.setFont(new Font("Calibri", Font.BOLD, 18));
        backButton.addActionListener(this);

        // OK BUTTON
        okButton.setText("Ok");
        okButton.setBackground(new Color(55, 202, 236));
        okButton.setForeground(new Color(0, 0, 0));
        okButton.setFocusable(false);
        okButton.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        okButton.setFont(new Font("Calibri", Font.BOLD, 18));
        okButton.addActionListener(this);

        // DATA BASE QUERY FOR SELECT DATA
        String query = "SELECT * FROM itemMenu";
        String[] columnNames = {"Number", "Name", "Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, dbPassword);
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("number");
                String name = rs.getString("name");
                int age = rs.getInt("price");
                Object[] row = {id, name, age};
                model.addRow(row);
            }
            connection.close();
        }
        catch (Exception x){
            System.out.println(x);
        }


        TableColumnModel columnModel = table.getColumnModel();
        table.setShowGrid(false);
        table.setRowHeight(30);
        table.setBackground(Color.WHITE);
        table.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        columnModel.getColumn(0).setPreferredWidth(60);
        columnModel.getColumn(1).setPreferredWidth(160);
        columnModel.getColumn(2).setPreferredWidth(90);
        table.setBorder(BorderFactory.createEmptyBorder());


        JTableHeader header = table.getTableHeader();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        table.setFont(new Font("Calibri", Font.PLAIN, 14));

        table.getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 15));

        table.setAlignmentY(JTable.CENTER_ALIGNMENT);

        JScrollPane scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        leftPanel.add(scroll);
        scroll.setPreferredSize(new Dimension(scroll.getPreferredSize().height, 760));

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    }

    @Override
    public void backgroundUI(){}
    @Override
    public void loginUI(){}
    @Override
    public void createOneUI(){}

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == itemEnter){
            if(itmNumberEnter.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Enter Item Number..!", "Item Number Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                itemEntering();
            }
        }
        if(e.getSource() == backButton){
            frame.dispose();
            Login login = new Login();
            login.loginUI();
        }
        if(e.getSource() == okButton){
            if(givenMoneyEnter.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Enter the Given Amount..!", "Enter the Price", JOptionPane.ERROR_MESSAGE);
            }
            else{
                cGivenMoney = Float.parseFloat(givenMoneyEnter.getText());
                cusName = cNameEnter.getText();
                JOptionPane.showMessageDialog(frame, "Successfully Billed...", "Success", JOptionPane.INFORMATION_MESSAGE);

                Customer customer = new Customer();

                customer.saveData(cusName, cItemTotal, cTotalPrice, cGivenMoney, adminEmail);
                frame.dispose();
                customer.moveToFinal(adminFirstName, adminLastName);
            }
        }
    }

    // WHEN THE ITEM ENTERING BUTTON CLICKED
    void itemEntering(){
        if(itmQtyEnter.getText().isEmpty()){
            JOptionPane.showMessageDialog(frame, "Enter Item Quantity..!", "Item Quantity Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            Items items = new Items();
            items.getItems(itmNumberEnter.getText(), itmQtyEnter.getText());
            String getName = items.getItemName();
            String qtyTotal = itmQtyEnter.getText();
            float getPrice = items.getItemPrice();
            float totalPrice = Integer.parseInt(itmQtyEnter.getText()) * getPrice;

            JLabel boxItemName = new JLabel();
            JLabel boxItemQty = new JLabel();
            JLabel boxItemPrice = new JLabel();
            JLabel boxTotalPrice = new JLabel();

            boxItemName.setText(String.valueOf(getName));
            boxItemQty.setText(String.valueOf(itmQtyEnter.getText()));
            boxItemPrice.setText(String.valueOf(getPrice) + "0");
            boxTotalPrice.setText(String.valueOf(totalPrice) + "0");

            boxItemName.setHorizontalAlignment(JLabel.CENTER);
            boxItemName.setFont(new Font("Calibri", Font.PLAIN, 15));

            boxItemQty.setHorizontalAlignment(JLabel.CENTER);
            boxItemQty.setFont(new Font("Calibri", Font.PLAIN, 15));

            boxItemPrice.setHorizontalAlignment(JLabel.RIGHT);
            boxItemPrice.setFont(new Font("Calibri", Font.PLAIN, 15));

            boxTotalPrice.setHorizontalAlignment(JLabel.RIGHT);
            boxTotalPrice.setFont(new Font("Calibri", Font.PLAIN, 15));

            column1.add(boxItemName);
            column2.add(boxItemQty);
            column3.add(boxItemPrice);
            column4.add(boxTotalPrice);
            allItems.revalidate();
            allItems.repaint();

            itmQtyEnter.setText("");
            itmNumberEnter.setText("");
            itmNumberEnter.requestFocusInWindow();

            totalItemCount++;
            sumOfTotal = sumOfTotal + totalPrice;
            totalQtyCount += Integer.parseInt(qtyTotal);

            cItemTotal = totalItemCount;
            cTotalPrice = sumOfTotal;

            numOfItemCount.setText(String.valueOf(totalItemCount));
            rightEnteredName.setText("' " + String.valueOf(getName) + " '" + "  Added");
            enteredItem.add(rightEnteredName);
            rightCountAdding.setText(String.valueOf(totalQtyCount));
            totalCount.add(rightCountAdding);
            rightFinalTotal.setText(String.valueOf(sumOfTotal) + "0");
            balance.add(rightFinalTotal);
        }
    }
}
