package com.celi.cii.base.utils;

import cn.hutool.core.util.IdUtil;

public class IDUtils {

	private static IdWorker mySQLId = new IdWorker(1);

	public static String randomUUID() {
		return IdUtil.simpleUUID();
	}

	public static Long getId() {
		try {
			return mySQLId.nextId();
		} catch (Exception e) {
		}
		return 0L;
	}

	public static String getStringId() {
		return String.valueOf(IDUtils.getId());
	}

	public static String getShortId(){
		Long n = getId();
		String str = "ABCDEFGHIJKLNMOPQRSTUVWXYZ";
		String s = "";
		if(n==0L)
		{
			return str.charAt(0)+"";
		}
		while (n > 0){
			long m = n % 26;
			s = str.charAt((int)m) + s;
			n = (n - m) / 26;
		}
		return s;
	}

	public static String long2Short(Long n){
		String str = "0123456789abcdefghijklnmopqrstuvwxyzABCDEFGHIJKLNMOPQRSTUVWXYZ";
		String s = "";
		if(n==0L)
		{
			return str.charAt(0)+"";
		}
		while (n > 0){
			long m = n % 62;
			s = str.charAt((int)m) + s;
			n = (n - m) / 62;
		}
		return s;
	}

	public static Long short2Long(String data){
		String str = "0123456789abcdefghijklnmopqrstuvwxyzABCDEFGHIJKLNMOPQRSTUVWXYZ";
		int scale = str.length(); //转化目标进制
		String s = "";
		if("0".equalsIgnoreCase(data)) {
			return new Long(0);
		}
		Long value = 0L;
		for (int i= data.length()-1; i>=0;i--){
			int m = str.indexOf( data.charAt(i) );
			value += new Double( m * Math.pow(62, data.length()-1-i) ).longValue() ;
		}

		return value;
	}

	public static void main(String[] args) {
//		for (int i = 0; i < 100; i++ ){
//			System.out.println(IDUtils.getStringId());
//		}

		System.out.println(IDUtils.randomUUID());
		System.out.println(IDUtils.randomUUID().length());
	}

}
