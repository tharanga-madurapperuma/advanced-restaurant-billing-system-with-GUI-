import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Items implements dataBaseQuaries{
    String itemName;
    int itemNumber;
    float itemPrice;
    int itemQty;

    public void getItems(String itemNumber, String itemQty){
        this.itemNumber = Integer.parseInt(itemNumber);
        this.itemQty = Integer.parseInt(itemQty);
    }

    public String getItemName(){
        String query = "SELECT * FROM itemMenu WHERE number = '"+itemNumber+"'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,dbPassword);
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();

            while(resultSet.next()){
                this.itemName = resultSet.getString("name");
                this.itemPrice = resultSet.getFloat("price");
            }

            connection.close();
            pst.close();
            resultSet.close();


        } catch(Exception e){
            System.out.println(e);
        }

        return (itemName);
    }

    public float getItemPrice(){
        return itemPrice;
    }
}
