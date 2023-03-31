package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {

    public HashMap<String,Order> orderDb;
    public HashMap<String,DeliveryPartner> deliveryPartnerDb;
    public HashMap<String,List<String>> orderPartnerDb;
    public Set<String> unAssignedOrder;




    public void addOrder(Order order) {
        //add in orderdb to get all orders
        String key = order.getId();
        orderDb.put(key, order);

        //add in unassigned db to get orders which are not assigned
        unAssignedOrder.add(key);
    }
    public void addPartner(String id)
    {

        deliveryPartnerDb.put(id,new DeliveryPartner(id));
    }

    public void addOrderPartner(String partnerId, String orderId)
    {
        //setting the number of orders in existing partner id
        if(deliveryPartnerDb.containsKey(partnerId))
        {
            deliveryPartnerDb.get(partnerId).setNumberOfOrders(deliveryPartnerDb.get(partnerId).getNumberOfOrders()+1);
        }

        //remove unassign orderid as its will be assign to a partner
        if(unAssignedOrder.contains(orderId))
        {
            unAssignedOrder.remove(orderId);
        }


        //list of oerder ids with their respective partner
        if(orderPartnerDb.containsKey(partnerId))
        {
            orderPartnerDb.get(partnerId).add(orderId);
        }
        else
        {
            List<String> list = new ArrayList<>();
            list.add(orderId);
            orderPartnerDb.put(partnerId,list);
        }

    }

    public Order getOrder(String id)
    {
        if(orderDb.containsKey(id))
        {
            return orderDb.get(id);
        }
        return null;
    }

    public DeliveryPartner getPartner(String id)
    {
        if(deliveryPartnerDb.containsKey(id))
        {
            return deliveryPartnerDb.get(id);
        }
        return null;
    }

    public List<String> getAllOrders()
    {
        List<String> list = new ArrayList<>();
        for(String id:orderDb.keySet())
        {
            list.add(id);
        }

        return list;
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId)
    {
        int HH = Integer.parseInt(time.substring(0,2));
        int MM = Integer.parseInt(time.substring(3));

        int time1 = HH*60 + MM;

        List<String> list = new ArrayList<>();

        if(orderPartnerDb.containsKey(partnerId))
        {
            list = orderPartnerDb.get(partnerId);
        }

        for(String orderId : list)
        {
            //get expected time delivered order
            int t = orderDb.get(orderId).getDeliveryTime();
            if(t <= time1)
            {
                orderDb.remove(orderId);
                orderPartnerDb.get(partnerId).remove(orderId);
                deliveryPartnerDb.get(partnerId).setNumberOfOrders(deliveryPartnerDb.get(partnerId).getNumberOfOrders()-1);
            }
        }

        return deliveryPartnerDb.get(partnerId).getNumberOfOrders();
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId)
    {
        List<String> list = new ArrayList<>();

        if(orderPartnerDb.containsKey(partnerId))
        {
            list = orderPartnerDb.get(partnerId);
        }

        int max = Integer.MIN_VALUE;

        for(String orderId : list)
        {
            int time = orderDb.get(orderId).getDeliveryTime();
            max = Math.max(max, time);
        }

        String time = "";

        int hh = max/60;
        int mm = max%60;

        String hour = String.valueOf(hh);
        String min = String.valueOf(mm);

        if(hour.length()==1) hour = "0"+hour;
        if(min.length()==1) min ="0"+min;

        time = hour +":"+ min;

        return time;

    }

    public void deletePartnerById(String id)
    {
        List<String> list = orderPartnerDb.get(id);
        for(String orderId : list)
        {
            unAssignedOrder.add(orderId);
        }

        orderPartnerDb.remove(id);
        deliveryPartnerDb.remove(id);
    }

    public void deleteOrderById(String id) {
        if (unAssignedOrder.contains(id)) {
            unAssignedOrder.remove(id);
        } else {
            for (List<String> list : orderPartnerDb.values()) {
                list.remove(id);
            }
        }

        orderDb.remove(id);
    }

    }
