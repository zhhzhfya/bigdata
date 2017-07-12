package com.kdps.common;

public class ReflectionHelper {

	public Object getInstance(String claName)  {
		Class<?> classT;
		Object newInstance = null;
			try {
				classT = Class.forName(claName);
				newInstance = classT.newInstance(); 
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return newInstance;
			
	}
}
