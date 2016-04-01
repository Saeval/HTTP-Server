package server;


public class ServerLauncher {

	public static final int PORT = 8080;
	
	public static void main(String[] args) {
		
		Server server=new Server(PORT);
		
//		server.addService("/books", new BooksService());
		server.addService("File", new FileService());
		
		server.launch();
	}
}
