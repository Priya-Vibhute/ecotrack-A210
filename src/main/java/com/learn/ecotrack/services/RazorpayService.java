package com.learn.ecotrack.services;

import com.razorpay.Order;
import com.razorpay.RazorpayException;

public interface RazorpayService {
	
  Order  createOrder(double amount) throws RazorpayException;
  boolean verifyPaymentSignature(String orderId,String paymentId,
		                                             String signature);
}
