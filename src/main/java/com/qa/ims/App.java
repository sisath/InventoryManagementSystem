package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;


public class App {

	public static final Logger LOGGER = LogManager.getLogger();

	public static void main(String[] args) {

		Connection con =  Login.getConnection();

		if(con != null){
			System.out.println("Connected!\n");
			Menu menu = new Menu();
			menu.menuSystem();
			LOGGER.info("¯\\_(ツ)_/¯ " + "Okay then...Goodbye! ");
		} else {
			System.out.println("Not Connected!\n");
		}
	}
}

