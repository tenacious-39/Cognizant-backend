package com.vitacure.pharmacyservice.util;

import java.util.ArrayList;
import java.util.List;

import com.vitacure.pharmacyservice.model.Order;
import com.vitacure.pharmacyservice.model.OrderWrapper;

public class OrderUtil {
	
	/**
	 * Converts {@literal List<Order>} to {@literal List<OrderWrapper>}
	 * 
	 * @param list of type {@literal Order}
	 * @return list of type {@literal OrderWrapper}
	 */
	public static List<OrderWrapper> convertOrderListToOrderWrapperList(List<Order> list){
		List<OrderWrapper> orderInfo = new ArrayList<>();
		for(Order order : list) {
			orderInfo.add(new OrderWrapper(
					order.getOrderId(),
					order.getMedicineName(),
					order.getQuantity(),
					order.getDateOfOrder(),
					order.getPharmacy().getName(),
					order.getPharmacy().getEmail(),
					order.getPharmacy().getLocation()
					));
		}
		return orderInfo;
	}
}
