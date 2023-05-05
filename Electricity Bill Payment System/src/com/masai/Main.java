package com.masai;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Consumer;
import com.masai.entities.Bills;
import com.masai.utility.CheckFile;
import com.masai.exceptions.*;
import com.masai.service.ConsumerService;
import com.masai.service.ConsumerServiceImpl;
import com.masai.service.BillsService;
import com.masai.service.BillsServiceImpl;

public class Main {
	private static void adminFunctionality(Scanner sc, Map<String, Consumer> consumer, List<Bills> bills)
			throws InvalidCredentialsException {

		adminLogin(sc);

		ConsumerService conServices = new ConsumerServiceImpl();
		BillsService billsServices = new BillsServiceImpl();
		try {
			int choice = 0;
			do {
				System.out.println("Press 1 for register new Consumer");
				System.out.println("Press 2 view all Consumer");
				System.out.println("Press 3 to view all Bills");
				System.out.println("Press 4 for delete Consumer");
				System.out.println("Press 5 for log out");
				choice = sc.nextInt();
				switch (choice) {
				case 1: {
					consumerRegister(sc, consumer, bills);
					break;
				}
				case 2: {
					List<Consumer> consumerList = conServices.viewAllConsumer(consumer);
					for (Consumer cons : consumerList) {
						System.out.println(cons);
					}
					break;
				}
				case 3: {
					List<Bills> billsList = billsServices.viewAllBills(bills);
					for (Bills br : billsList) {
						System.out.println(br);
					}
					break;
				}
				case 4: {
					deleteConsumer(sc, consumer,bills);
					break;
				}
				case 5: {
					System.out.println("Admin has successfully logout");
					break;
				}
				default:
					throw new InvalidCredentialsException("Please choose correct option");
				}
			} while (choice != 5);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void adminLogin(Scanner sc) throws InvalidCredentialsException {

		System.out.println("please enter the following details to login");
		System.out.println("Enter the user name");
		String userName = sc.next();
		System.out.println("Enter the password");
		String password = sc.next();

		if (userName.equals("Dee") && password.equals("987")) {

			System.out.println("Login Succesfully");

		} else {

			throw new InvalidCredentialsException("Invalid Credentials, Please put correct Id and Password");

		}

	}

	public static void consumerRegister(Scanner sc, Map<String, Consumer> consumer, List<Bills> bills)
			throws DuplicateCredentialsException {

		System.out.println("please enter the following details to Register");
		System.out.println("please enter the user name");
		String name = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		System.out.println("Enter the address");
		String address = sc.next();
		System.out.println("Enter the email id");
		String email = sc.next();

		ConsumerService conServices = new ConsumerServiceImpl();

		LocalDateTime dateTime = LocalDateTime.now();

		Bills conBill = new Bills(email, (int)( Math.random() * 1000) / 1.0, dateTime);

		bills.add(conBill);
		System.out.println(bills);

		Consumer cons = new Consumer(name, pass, address, email, conBill);

		conServices.signUp(cons, consumer);

		System.out.println("Successfully register");
	}

	public static void deleteConsumer(Scanner sc, Map<String, Consumer> consumer, List<Bills> bills) throws InvalidCredentialsException {

		System.out.println("Enter Consumer email ID");

		String email = sc.next();

		if (consumer.containsKey(email)) {

			consumer.remove(email);
			
			for(int i = 0; i < bills.size(); i++) {
				if(bills.get(i).getEmail().equals(email)) {
					bills.remove(i);
				}
			}
			
			System.out.println("Account deleted successfully");

		} else {
			throw new InvalidCredentialsException("Please write valid email");
		}

	}

	private static void consumerFunctionality(Scanner sc, Map<String, Consumer> consumer, List<Bills> bills)
			throws InvalidCredentialsException {

		ConsumerService conServices = new ConsumerServiceImpl();
		BillsService billsServices = new BillsServiceImpl();

		System.out.println("please enter the following details to login");
		System.out.println("please enter the email");
		String email = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();

		consumerLogin(email, pass, conServices, consumer);
		try {
			int choice1 = 0;
			do {
				System.out.println("press 1 for Pay Bill");
				System.out.println("press 2 for see all bills");
				System.out.println("Press 3 for unpaid bills");
				System.out.println("press 4 for exit from the app");

				choice1 = sc.nextInt();
				switch (choice1) {
				case 1: {
					payConsumerBill(sc, email, bills);
					break;
				}
				case 2: {
					billsServices.viewConsumerBills(email, bills);
					break;
				}
				case 3:{
					billsServices.viewConsumerUnpaidBills(email, bills);
				}
				case 4: {
					System.out.println("Successfully exited from the system");
					break;
				}
				default:
					throw new IllegalArgumentException("Please choose correct option from consumer");
				}

			} while (choice1 != 4);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void consumerLogin(String email, String pass, ConsumerService conServices,
			Map<String, Consumer> consumer) throws InvalidCredentialsException {
		conServices.login(email, pass, consumer);
		System.out.println("Login successfull");
	}

	public static void payConsumerBill(Scanner sc, String email, List<Bills> billList) throws BillsException {

		BillsService billsServices = new BillsServiceImpl();

		List<Bills> bil = billsServices.payConsumerBills(email, billList);

		System.out.println("Your bills are");
		for (Bills br : bil) {
			System.out.println(br);
		}
		System.out.println("Enter amount to pay");
		double amt = sc.nextDouble();

		billsServices.payBill(email, billList, amt);
	}

	public static void main(String[] args) {

		Map<String, Consumer> consumer = CheckFile.consumerFile();
		List<Bills> bills = CheckFile.bills();

		System.out.println(consumer);
		System.out.println(bills);

		Scanner sc = new Scanner(System.in);

		System.out.println("Hii Welcome,to Electricity Bill Payment System");

		try {
			int choice = 0;
			do {
				System.out.println(
						"Enter your choice" + "\n1 --> Admin Login \n2 --> Consumer Login\n" + "0 --> For exit");
				choice = sc.nextInt();
				switch (choice) {
				case 1: {
					adminFunctionality(sc, consumer, bills);
					break;
				}
				case 2: {
					consumerFunctionality(sc, consumer, bills);
				}
				case 0: {
					System.out.println("Successfully exited from the app");
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice != 0);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("consumerFile.ser"));
				coos.writeObject(consumer);
				coos.close();
//				System.out.println(consumer);
				ObjectOutputStream boos = new ObjectOutputStream(new FileOutputStream("billsFile.ser"));
				boos.writeObject(bills);
				boos.close();
				

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
