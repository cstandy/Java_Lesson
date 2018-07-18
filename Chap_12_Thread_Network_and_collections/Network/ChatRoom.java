import java.io.*;
import java.net.*;
public class ChatRoom {
	public static void main(String[] args) {
		try {
			ServerSocket serverSock = new ServerSocket(8000);
			System.out.print("Server started...");
			while (true) {
				Socket cSock = serverSock.accept();
				Chat chat = new Chat(cSock);
				Thread chatthread = new Thread(chat);
				chatthread.start();
			}
		} catch (IOException e) { System.out.println("disconnected..."); }
	}
}
class Chat implements Runnable {
	private Socket socket;
	public Chat(Socket socket) { this.socket = socket; }
	public void run() {
		try {
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true){
				String clientText = clientInput.readLine();
				System.out.println("From Client: " + clientText);
				if(clientText.equals("bye")) break;
			}
			clientInput.close();
			socket.close();
		} catch (Exception e) { e.printStackTrace(); }
	}
}
