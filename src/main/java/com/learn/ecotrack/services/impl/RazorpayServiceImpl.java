package com.learn.ecotrack.services.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.services.RazorpayService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

@Service
public class RazorpayServiceImpl implements RazorpayService {
	
	@Value("${Razorpay.key}")
	private String razorpayKey;
	
	@Value("${Razorpay.secret}")
	private String razorpaySecret;

	@Override
	public Order createOrder(double amount) throws RazorpayException {
		
		RazorpayClient client = new RazorpayClient(razorpayKey, razorpaySecret);
		
		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", amount);
		orderRequest.put("currency", "INR");
		orderRequest.put("receipt", "receipt_"+System.currentTimeMillis());
		
		return client.orders.create(orderRequest);
	}

	@Override
	public boolean verifyPaymentSignature(String orderId, String paymentId, String signature) {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("razorpay_order_id", orderId);
		jsonObject.put("razorpay_payment_id", paymentId);
		jsonObject.put("razorpay_signature",signature);
		
		try {
			Utils.verifyPaymentSignature(jsonObject, razorpaySecret);
			System.out.println("Payment Success");
			return true;
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Payment Failed");
			return false;
		}
		
		
	}

}
