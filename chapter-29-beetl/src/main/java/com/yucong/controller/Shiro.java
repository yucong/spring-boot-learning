package com.yucong.controller;

import java.util.HashSet;
import java.util.Set;

public class Shiro {

	private Set<String> urls = new HashSet<>();
	
	
	public Shiro () {
		urls.add("a");
		urls.add("b");
	}
	
	public boolean hasPermission(String url) {
		return urls.contains(url);
	}
	
	public static void main(String[] args) {
		
		Shiro shiro = new Shiro();
		System.out.println( shiro.hasPermission("a") );
		System.out.println( shiro.hasPermission("b") );
		System.out.println( shiro.hasPermission("c") );
		
	}
	
	
}
