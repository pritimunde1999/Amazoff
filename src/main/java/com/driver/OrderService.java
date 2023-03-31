package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository = new OrderRepository();

    public void addOrder(Order order)
    {
        orderRepository.addOrder(order);
    }

    public void addPartner(String id)
    {
        orderRepository.addPartner(id);
    }

    public void addOrderPartnerPair(String partnerId, String orderId)
    {
        orderRepository.addOrderPartner(partnerId,orderId);
    }

    public Order getOrder(String id)
    {
        return orderRepository.getOrder(id);

    }

    public DeliveryPartner getPartner(String id)
    {
        return orderRepository.getPartner(id);
    }

    public int getCount(String id)
    {
        return orderRepository.orderPartnerDb.get(id).size();
    }

    public List<String> getOrderByPartner(String id)
    {
        if(orderRepository.orderPartnerDb.containsKey(id))
        {
            return orderRepository.orderPartnerDb.get(id);
        }
        return null;
    }

    public List<String> getAllOrders()
    {
        return orderRepository.getAllOrders();

    }

    public int getCountOfUnassignedOrders()
    {
        return orderRepository.unAssignedOrder.size();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId)
    {
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String id)
    {
        return orderRepository.getLastDeliveryTimeByPartnerId(id);
    }


    public void deletePartnerById(String id)
    {
        orderRepository.deletePartnerById(id);
    }

    public void deleteOrderById(String id)
    {
        orderRepository.deleteOrderById(id);
    }
}
