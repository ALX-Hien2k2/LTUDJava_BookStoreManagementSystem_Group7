package business;

import java.util.*;
import dataaccess.OrderDA;
import pojo.OrderPOJO;
import pojo.DetailOrderPOJO;

public class OrderBU {
    public List<OrderPOJO> getAllOrder() {
        OrderDA da = new OrderDA();
        return da.getAllOrder();
    }

    public List<DetailOrderPOJO> getDetailOrder(int id) {
        OrderDA da = new OrderDA();
        return da.getDetailOrder(id);
    }
}
