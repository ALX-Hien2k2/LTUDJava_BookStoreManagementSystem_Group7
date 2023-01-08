package business;

import java.util.*;
import dataaccess.OrderDA;
import pojo.OrderPOJO;

public class OrderBU {
    public List<OrderPOJO> getAllOrder() {
        OrderDA da = new OrderDA();
        return da.getAllOrder();
    }
}
