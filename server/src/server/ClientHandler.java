package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private String name;
	final DataInputStream in;
	final DataOutputStream out;
	Socket s;
	boolean isLogged;
	
	
	
	public ClientHandler(Socket s, String name, DataInputStream in, DataOutputStream out) {
		this.in = in;
		this.out = out;
		this.name = name;
		
	}


	@Override
	public void run(){
		// TODO Auto-generated method stub
		String received = null;
		while(true) {
			try {
				received = in.readUTF();
				System.out.println(received);
				if( received != null ) s.close();
			}catch(Throwable e) {
				//e.printStackTrace();
				break;
			}
		}
	}
	}
