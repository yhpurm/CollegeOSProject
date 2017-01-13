package clientSide;

import java.io.*;
import java.net.*;
import java.util.Scanner;
public class clientConnection{
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
 	Scanner user_input;
	clientConnection()
	{
		user_input = new Scanner(System.in);
	}
	void run()
	{
		try{

			
			requestSocket = new Socket("127.0.0.1", 2017);
			System.out.println("Connected to localhost in port 2017");

			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());

			do{
				try{
					message = (String)in.readObject();
					System.out.println(message);
					message = user_input.next();
					sendMessage(message);
					
					message = (String)in.readObject();
					System.out.println(message);
					message = user_input.next();
					sendMessage(message);
					
					message = (String)in.readObject();
					System.out.println(message);
					message = user_input.next();
					sendMessage(message);
					
					message = (String)in.readObject();
					System.out.println(message);
					message = user_input.next();
					sendMessage(message);
					
					message = (String)in.readObject();
					System.out.println(message);
					message = user_input.next();
					sendMessage(message);
					
					
					message = user_input.next();
					if(message.compareTo("No")==0)
					{
						sendMessage("Thank You!");
					
						message = (String)in.readObject();
					
					}
					
					else
					{
						sendMessage("Continue");
					}
					
					
				}
				catch(ClassNotFoundException classNot){
					System.err.println("data received in unknown format");
				}
			}while(!message.equals("Thank You!"));
		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{

			try{
				in.close();
				out.close();
				requestSocket.close();
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
			System.out.println("client>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		clientConnection client = new clientConnection();
		client.run();
	}
}