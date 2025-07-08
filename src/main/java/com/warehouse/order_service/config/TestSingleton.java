package com.warehouse.order_service.config;

public class TestSingleton {

	private static volatile TestSingleton instance;

	private TestSingleton() {
		
	}

	public static TestSingleton getTestSingleton() {
		if (instance == null)
			synchronized (TestSingleton.class) {
				if (instance == null)
					instance = new TestSingleton();
			}
		return instance;
	}
}
