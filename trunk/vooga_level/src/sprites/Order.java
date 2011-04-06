package sprites;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private ArrayList orderItems;
    private String orderNumber;
    
    public ArrayList getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(ArrayList orderItems) {
        this.orderItems = orderItems;
    }
    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}