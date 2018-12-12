package agenda3;

import java.util.Scanner;
import com.zubiri.agenda.*;

public class Interface {
	public static void main(String[] args) {
		boolean repeat = true;
		Agenda x = new Agenda();
		while (repeat) {
			System.out.println("*MENU*");
			System.out.println("[1] Create a new contact");
			System.out.println("[2] Created contacts");
			System.out.println("[3] View an existing contact");
			System.out.println("[4] Modify an existing contact");
			System.out.println("[5] Delete an existing contact");
			System.out.println("[0] Exit");
			Scanner sc = new Scanner(System.in);
			if (sc.hasNextInt()) {
				int option = sc.nextInt();
				switch (option) {
				case 0:/* Stop the program */
					System.out.println("The program has been stopped");
					repeat = false;
					break;
				case 1:/* Create a new contact */
					System.out.println("*CREATE A NEW CONTACT*");
					// ask for Person's class information
					System.out.println("Name:");
					Person info = new Person(sc.next());
					System.out.println("Age:");
					boolean flag = true;
					while (flag) {
						if (sc.hasNextInt()) {
							info.setAge(sc.nextInt());
							flag = false;
						} else
							System.out.println("It has to be a number");
					}
					flag = true;
					System.out.println("Weight:");
					while (flag) {
						if (sc.hasNextInt()) {
							info.setWeight(sc.nextInt());
							flag = false;
						} else
							System.out.println("It has to be a number");
					}
					flag = true;
					System.out.println("DNI:");
					flag = true;
					while (flag) {

						String dni = sc.next();
						if (dni.length() != 9)
							System.out.println("The DNI has to be of 9 digits. Try again:");
						else {
							info.setDni(dni);
							flag = false;
						}

					}

					// add this information to the Contact:
					System.out.println("Mobile number:");
					flag = true;
					int tfn = 0;
					while (flag) {
						if (sc.hasNextInt()) {
							tfn = sc.nextInt();
							if (Integer.toString(tfn).length() != 9)
								System.out.println("The mobile number has to be of 9 digits. Try again:");
							else {
								flag = false;
							}
						}
					}
					System.out.println("Address:");
					sc.nextLine();
					String addrs = sc.nextLine();
					Contact newContact = new Contact(info, tfn, addrs); //

					// add this Contact to the ArrayList of class Agenda
					x.addContact(newContact);
					break;
				case 2:/* Show a name list of the already created contacts */
					for (int i = 0; i < x.getKontaktuak().size(); i++) {
						System.out.println(x.getKontaktuak().get(i).getPerson().getName());
					}
					System.out.println();
					break;
				case 3:/* View the information of a contact */
					System.out.println("*VIEW A CONTACT*");
					System.out.println("Whose information do you want? (Enter hers/his name)");
					String findName = sc.next();
					boolean flag2 = false;
					for (int i = 0; i < x.getKontaktuak().size(); i++) {
						if (findName.compareTo(x.getKontaktuak().get(i).getPerson().getName()) == 0) {
							System.out.println(x.readContact(findName));
							flag2 = true;
							break;
						}
					}
					if (flag2 == false)
						System.out.println("There's no person with that name created yet");
					System.out.println();
					break;
				case 4:/* Change the information of a contact */
					System.out.println("*MODIFY A CONTACT*");
					System.out.println("Whose information do you want to change? (Enter hers/his name)");
					boolean flag1 = true;
					findName = sc.next();
					flag2 = false;
					int position = -1;
					for (int i = 0; i < x.getKontaktuak().size(); i++) {
						if (findName.compareTo(x.getKontaktuak().get(i).getPerson().getName()) == 0) {
							position = i;
							flag2 = true;
							break;
						}
					}
					if (flag2 == false)
						System.out.println("There's no person with that name created yet");
					if (position >= 0) {
						newContact = x.getKontaktuak().get(position);
						while (flag1) {
							System.out.println("What do you want to change?");
							System.out.println(" - 1. Name");
							System.out.println(" - 2. Age");
							System.out.println(" - 3. Weight");
							System.out.println(" - 4. DNI");
							System.out.println(" - 5. Telephone number");
							System.out.println(" - 6. Address");
							System.out.println("--------------------------------");
							System.out.println(" - 0. Go back to menu");
							System.out.println();
							if (sc.hasNextInt()) {
								int change = sc.nextInt();
								switch (change) {
								case 0:
									flag1 = false;
									break;
								case 1: /* Change name */
									System.out.println("Your actual name is "
											+ x.getKontaktuak().get(position).getPerson().getName());
									System.out.println("What's the name you want?");
									x.modifyContact(x.getKontaktuak().get(position).getPerson().getName(), "name", sc.next());
									System.out.println("Your name has been changed");
									break;
								case 2: /* Change age */
									System.out.println(
											"Your actual age is " + x.getKontaktuak().get(position).getPerson().getAge());
									System.out.println("What's the age you want?");
									boolean loop = true;
									while (loop) {
										if (sc.hasNextInt()) {
											x.modifyContact(x.getKontaktuak().get(position).getPerson().getName(), "age", Integer.toString(sc.nextInt()));
											loop = false;
										} else
											System.out.println("It has to be a number");
									}
									System.out.println("Your age has been changed");
									break;
								case 3: /* Change weight */
									System.out.println("Your actual weight is "
											+ x.getKontaktuak().get(position).getPerson().getWeight());
									System.out.println("What's the weight you want?");
									loop = true;
									while (loop) {
										if (sc.hasNextInt()) {
											x.modifyContact(x.getKontaktuak().get(position).getPerson().getName(),"weight", Integer.toString(sc.nextInt()));
											loop = false;
										} else
											System.out.println("It has to be a number");
									}
									System.out.println("Your weight has been changed");
									break;
								case 4: /* Change DNI */
									System.out.println(
											"Your actual DNI is " + x.getKontaktuak().get(position).getPerson().getDni());
									System.out.println("What's the DNI you want?");
									x.modifyContact(x.getKontaktuak().get(position).getPerson().getName(),"dni", sc.next());
									System.out.println("Your DNI has been changed");
									break;
								case 5:/* Change Telephone Number */
									System.out.println("Your actual telephone number is "
											+ x.getKontaktuak().get(position).getNumber());
									System.out.println("What mobile number do you want?");
									loop = true;
									while (loop) {
										if (sc.hasNextInt()) {
											tfn = sc.nextInt();
											if (Integer.toString(tfn).length() == 9) {
												x.modifyContact(x.getKontaktuak().get(position).getPerson().getName(),"number", Integer.toString(tfn));
												loop = false;
											} else
												System.out.println("The number has to be 9 digits long");
										} else
											System.out.println("It has to be a number");
									}
									System.out.println("Your mobile number has been changed");
									break;
								case 6:/* Change address */
									System.out.println(
											"Your actual address is: " + x.getKontaktuak().get(position).getAddress());
									System.out.println("What's the address you want me to save?");
									x.modifyContact(x.getKontaktuak().get(position).getPerson().getName(), "address", sc.nextLine());
									break;
								default:/* if the user doesn't enter an option we can use */
									System.out.println("Enter one of the options bellow:");
									System.out.println();
								}
								System.out.println();
							} else {
								System.out.println("You have to enter the number of the option you want:");
								System.out.println();
							}
						}
					} else {
						System.out.println("The name you entered is not created yet");
					}
					break;
				case 5:/* Delete the information of a contact */
					System.out.println("*DELETE A CONTACT*");
					System.out.println("Who do you want to delete?");
					x.deleteContact(sc.next());
					break;
				default:/* if the user doesn't enter an option we can use */
					System.out.println("Enter one of the options bellow:");
					System.out.println();
				}
			} else {
				System.out.println("Enter a valid number");
			}
		}
	}

}
