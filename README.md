# Amazoff
Amazoff is the new and coming delivery service started by Beff Jezos. At the end of each day, the delivery schedule for the next day is decided.

There are some orders that need to be delivered and some delivery partners who will deliver the orders. Following points will help you understand the working of Amazoff:

Orders:
Each order has a unique orderId and a delivery time (in the 24-hour HH:MM format). This means that the order with the given Id needs to be delivered exactly at HH:MM on the next day.
Each order is assigned to at most one delivery partner. This means that either the order is assigned to exactly one delivery partner or it is left unassigned.
Delivery Partner:
Each delivery partner has a unique partnerId.
Any (possibly zero) number of orders can be assigned to a delivery partner.
A simple spring boot application can support all of the given CRUD operations. You need to make an MVC architecture with Order and DeliveryPartner as your only model classes, which will have some fields as their properties, constructors, and getters-setters. Then, make a controller, service, and repository class with appropriate annotations. To keep it simple, avoid using any database. (Hint: Think using 4 HashMap(s))

You need to implement the logic for following functionalities:

1.Add an Order: POST /orders/add-order  Pass the Order object as request body  Return success message wrapped in a ResponseEntity object  Controller Name - addOrder

2.Add a Delivery Partner: POST /orders/add-partner/{partnerId}  Pass the partnerId string as path variable  Return success message wrapped in a ResponseEntity object  Controller Name - addPartner

3.Assign an order to a partner: PUT /orders/add-order-partner-pair  Pass orderId and partnerId strings as request parameters  Return success message wrapped in a ResponseEntity object  Controller Name - addOrderPartnerPair

4.Get Order by orderId: GET /orders/get-order-by-id/{orderId}  Pass orderId string as path variable  Return Order object wrapped in a ResponseEntity object  Controller Name - getOrderById

5.Get Partner by partnerId: GET /orders/get-partner-by-id/{partnerId}  Pass partnerId string as path variable  Return DeliveryPartner object wrapped in a ResponseEntity object  Controller Name - getPartnerById

6.Get number of orders assigned to given partnerId: GET /orders/get-order-count-by-partner-id/{partnerId}  Pass partnerId as path variable  Return Integer wrapped in a ResponseEntity object  Controller Name - getOrderCountByPartnerId

7.Get List of all orders assigned to given partnerId: GET /orders/get-orders-by-partner-id/{partnerId}  Pass partnerId as path variable  Return List of Order object wrapped in a ResponseEntity object  Controller Name - getOrdersByPartnerId

8.Get List of all orders in the system: GET /orders/get-all-orders  No params or body required Return List of Order object wrapped in a ResponseEntity object  Controller Name - getAllOrders

9.Get count of orders which are not assigned to any partner: GET /orders/get-count-of-unassigned-orders  No params or body required  Return Integer wrapped in a ResponseEntity object  Controller Name - getCountOfUnassignedOrders

10.Get count of orders which are left undelivered by partnerId after given time: GET /orders/get-count-of-orders-left-after-given-time  Pass time string (in HH:MM format) and partnerId string as path variable  Return Integer wrapped in a ResponseEntity object  Controller Name - getOrdersLeftAfterGivenTimeByPartnerId

11.Get the time at which the last delivery is made by given partner: GET /orders/get-last-delivery-time/{partnerId}  Pass partnerId string as path variable  Return String with format HH:MM wrapped in a ResponseEntity object  Controller Name - getLastDeliveryTimeByPartnerId

12.Delete a partner and the corresponding orders should be unassigned: DELETE /orders/delete-partner-by-id/{partnerId}  Pass partnerId as path variable  Return success message wrapped in a ResponseEntity object  Controller Name - deletePartnerById

13.Delete an order and the corresponding partner should be unassigned: DELETE /orders/delete-order-by-id/{orderId}  Pass orderId as path variable  Return success message wrapped in a ResponseEntity object  Controller Name - deleteOrderById

