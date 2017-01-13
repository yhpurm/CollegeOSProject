package Provider;

import java.io.*;
import java.net.*;
import java.util.*;

import clientSide.theAccounts;

public class server{
	ServerSocket mySocket;
	Socket mycon = null;

	ObjectOutputStream out;
	ObjectInputStream in;
	Scanner input;
	String message;
	String user_input;
	String name;
	String address;
	int bankAccNum;
	String userName;
	String password;
	int balance = 1000;
	int choice;
	int withdraw;
	
	ArrayList<theAccounts> acc = new ArrayList<theAccounts>();
	
	Scanner Scan = new Scanner(System.in);
	
	server()
	{
		input = new Scanner(System.in);
	}
	void listener()
	{
		try{
			mySocket = new ServerSocket(2017, 10);
			System.out.println("Waiting for connection");
			
			mycon = mySocket.accept();
			
			System.out.println("Connection received from " + mycon.getInetAddress().getHostName());
			out = new ObjectOutputStream(mycon.getOutputStream());
			out.flush();
			in = new ObjectInputStream(mycon.getInputStream());
			
			do{
				try{
					System.out.println("1 for register");
					System.out.println("2 for log in");
					System.out.println("3 for withdrawel");
					System.out.print("Please select option");
					choice = Scan.nextInt();
					
					if(choice == 1 ){
					
					sendMessage("Please Enter Name ");
					message = (String)in.readObject();
					name = new String(message);
					
					sendMessage("Please Enter Address");
					message = (String)in.readObject();
					address = new String(message);
					
					sendMessage("Please enter bank Account Number");
					message = (String)in.readObject();
					bankAccNum = new Integer(message);
					
					sendMessage("Please enter username");
					message = (String)in.readObject();
					userName = new String (message);
					
					sendMessage("Please enter password");
					message = (String)in.readObject();
					password = new String (message);
					
					sendMessage("you are registered");
					
					message=(String)in.readObject();
					}
					else if (choice == 2){
						sendMessage("Please Enter Username ");
						message = (String)in.readObject();
						name = new String(message);
						
						sendMessage("Please Enter password");
						message = (String)in.readObject();
						address = new String(message);
						
						
						sendMessage("you are logged in");
						
						message=(String)in.readObject();
						
						
					}
					else if(choice == 3){
						
						System.out.println("How much would you like to withdraw?");
						withdraw = Scan.nextInt();
						balance -= withdraw;
						
						sendMessage("your balance is " + balance);
						
						if (withdraw > balance){
							sendMessage("withdrawl too high you cannot do that");
							
						}
						
						message=(String)in.readObject();
					}
					
					
				}
				catch(ClassNotFoundException classnot){
					System.err.println("Data received in unknown format");
				}
			}while(!message.equals("Thank You!"));
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{

			try{
				in.close();
				out.close();
				mySocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		server server = new server();
		while(true)
		{
			server.listener();
		}
	}
}
