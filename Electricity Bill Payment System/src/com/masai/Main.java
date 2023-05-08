package com.masai;

import java.io.FileOutputStream;

import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Consumer;
import com.masai.entities.Bills;
import com.masai.entities.Complain;
import com.masai.utility.CheckFile;
import com.masai.exceptions.*;
import com.masai.service.ConsumerService;
import com.masai.service.ConsumerServiceImpl;
import com.masai.service.BillsService;
import com.masai.service.BillsServiceImpl;
import com.masai.service.ComplainService;
import com.masai.service.ComplainServiceImpl;

public class Main {
	private static void adminFunctionality(Scanner sc, Map<String, Consumer> consumer, List<Bills> bills,
			Map<Integer, Complain> complain) throws InvalidCredentialsException {

		adminLogin(sc);

		ConsumerService conServices = new ConsumerServiceImpl();
		BillsService billsServices = new BillsServiceImpl();
		ComplainService complainServices = new ComplainServiceImpl();
		int choice = 0;
		do {
		try {
			do {
				System.out.print("\n");
				System.out.println("Press 0 for log out");
				System.out.println("Press 1 for register new Consumer");
				System.out.println("Press 2 view all Consumer");
				System.out.println("Press 3 to view all Bills");
				System.out.println("Press 4 to create bill of Consumer");
				System.out.println("Press 5 to view all complains");
				System.out.println("Press 6 to change status of complain");
				System.out.println("Press 7 for delete Consumer");
				choice = sc.nextInt();
				switch (choice) {
				case 0: {
					System.out.println("Admin has successfully logout");
					break;
				}
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
					createBill(sc, consumer, bills);
					break;
				}
				case 5: {
					complainServices.viewAllComplain(complain);
					break;
				}
				case 6: {
					updateStatusOfComplain(sc, complain);
					break;
				}
				case 7: {
					deleteConsumer(sc, consumer, bills);
					break;
				}
				default:
					throw new InvalidCredentialsException("Please choose correct option");
				}
			} while (choice != 0);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		} while (choice != 0);

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

		Bills conBill = new Bills(email, 765, dateTime, "New connection charge");

		bills.add(conBill);
//		System.out.println(bills);

		Consumer cons = new Consumer(name, pass, address, email, conBill);

		conServices.signUp(cons, consumer);

		System.out.println("Successfully register");
	}

	public static void deleteConsumer(Scanner sc, Map<String, Consumer> consumer, List<Bills> bills)
			throws InvalidCredentialsException {

		System.out.println("Enter Consumer email ID");

		String email = sc.next();

		if (consumer.containsKey(email)) {

			consumer.remove(email);

			for (int i = 0; i < bills.size(); i++) {
				if (bills.get(i).getEmail().equals(email)) {
					bills.remove(i);
				}
			}

			System.out.println("Account deleted successfully");

		} else {
			throw new InvalidCredentialsException("Please write valid email");
		}

	}

	public static void createBill(Scanner sc, Map<String, Consumer> consumer, List<Bills> bills) throws BillsException {

		System.out.println("Enter the email of consumer");
		String email = sc.next();
		if (!consumer.containsKey(email)) {
			throw new BillsException("Please put the correct email");
		}
		System.out.println("Enter the bill amount");
		int amt = sc.nextInt();
		LocalDateTime dateTime = LocalDateTime.now();
//		LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now(), DateTimeFormatter.ofPattern("dd-MMM-yyyy HH-mm-ss"));
//		dateTime = DateTimeFormatter.ofPattern();
		String billType = "";
		System.out.println("Choose bill Type");
		System.out.println("Press 1 for Monthly bill charge");
		System.out.println("Press 2 for yearly mantinance charge");
		System.out.println("Press 3 for Service charge");

		int chooseType = sc.nextInt();
		if (chooseType == 1)
			billType = "Monthly bill charge";
		else if (chooseType == 2)
			billType = "Yearly mantinance charge";
		else if (chooseType == 3)
			billType = "Service charge";
		else
			throw new IllegalArgumentException("Please choose correct option");

		Bills conBill = new Bills(email, amt, dateTime, billType);

		bills.add(conBill);

		System.out.println("Bill created successfully");
	}

	public static void updateStatusOfComplain(Scanner sc, Map<Integer, Complain> complain)
			throws InvalidCredentialsException {
		System.out.println("Write the complain ID");

		int complainId = sc.nextInt();

		System.out.println("Write the current status");
		sc.nextLine();
		String newStatus = sc.nextLine();

		ComplainService complainServices = new ComplainServiceImpl();

		complainServices.updateStatusOfComplain(complainId, newStatus, complain);

		System.out.println("Status updated successfully");
	}

	private static void consumerFunctionality(Scanner sc, Map<String, Consumer> consumer, List<Bills> bills,
			Map<Integer, Complain> complain) throws InvalidCredentialsException {

		ConsumerService conServices = new ConsumerServiceImpl();
		BillsService billsServices = new BillsServiceImpl();

		System.out.println("please enter the following details to login");
		System.out.println("please enter the email");
		String email = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();

		consumerLogin(email, pass, conServices, consumer);
		int choice1 = 0;
		do {
		try {
			do {
				System.out.print("\n");
				System.out.println("press 1 for Pay Bill");
				System.out.println("press 2 for see all bills");
				System.out.println("Press 3 for unpaid bills");
				System.out.println("Press 4 for raise a complain");
				System.out.println("Press 5 for view all complains");
				System.out.println("press 0 for exit from the app");

				choice1 = sc.nextInt();
				switch (choice1) {
				case 0: {
					System.out.println("Successfully exited from the system");
					break;
				}
				case 1: {
					payConsumerBill(sc, email, bills);
					break;
				}
				case 2: {
					billsServices.viewConsumerBills(email, bills);
					break;
				}
				case 3: {
					billsServices.viewConsumerUnpaidBills(email, bills);
					break;
				}
				case 4: {
					raiseComplain(sc, email, consumer, complain);
					break;
				}
				case 5: {
					complainService(sc, complain);
					break;
				}
				default:
					throw new IllegalArgumentException("Please choose correct option from consumer");
				}

			} while (choice1 != 0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}while (choice1 != 0);
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

	public static void raiseComplain(Scanner sc, String email, Map<String, Consumer> consumer,
			Map<Integer, Complain> complain) {

		ComplainService complainService = new ComplainServiceImpl();

		sc.nextLine();
		System.out.println("Write what is your issue");
		String complainIssue = sc.nextLine();

		int id = (int) (Math.random() * 1000000);
		String name = consumer.get(email).getUsername();
		String status = "Pending";

		Complain comp = new Complain(id, name, email, complainIssue, status);

		complainService.raiseComplain(comp, complain);
		System.out.println("Your Complain Id : " + id);
		System.out.println("Issue raised successfully");
	}

	public static void complainService(Scanner sc, Map<Integer, Complain> complain) throws InvalidCredentialsException {
		System.out.println("Write complain ID");
		int complainId = sc.nextInt();
		ComplainService complainService = new ComplainServiceImpl();

		complainService.viewComplain(complainId, complain);
	}

	public static void main(String[] args) {

		Map<String, Consumer> consumer = CheckFile.consumerFile();
		List<Bills> bills = CheckFile.bills();
		Map<Integer, Complain> complain = CheckFile.complainFile();

//		System.out.println(consumer);
//		System.out.println(bills);
//		System.out.println(complain);

		Scanner sc = new Scanner(System.in);

		System.out.println("Hii Welcome,to Electricity Bill Payment System");
		int choice = 0;
		do {
			try {
				do {
					System.out.print("\n");
					System.out.println(
							"Enter your choice" + "\n1 --> Admin Login \n2 --> Consumer Login\n" + "0 --> For exit");
					choice = sc.nextInt();
					switch (choice) {
					case 1: {
						adminFunctionality(sc, consumer, bills, complain);
						break;
					}
					case 2: {
						consumerFunctionality(sc, consumer, bills, complain);
						break;
					}
					case 0: {
						System.out.println("Successfully exited from the app");
						break;
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
					coos.flush();
					coos.close();
//				System.out.println(consumer);
					ObjectOutputStream boos = new ObjectOutputStream(new FileOutputStream("billsFile.ser"));
					boos.writeObject(bills);
					boos.flush();
					boos.close();

					ObjectOutputStream complainOutput = new ObjectOutputStream(
							new FileOutputStream("complainFile.ser"));
					complainOutput.writeObject(complain);
					complainOutput.flush();
					complainOutput.close();

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		} while (choice != 0);
	}
}
