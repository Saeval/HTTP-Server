package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
	
	private HashMap<String, IService> services = 
		new HashMap<String, IService>();
	
	private int port;

	public Server(int port) {
		this.port=port;
	}
	
	public void addService(String name,IService service){
		services.put(name, service);
	}
	
	public void launch(){

		try {
			ServerSocket socket=new ServerSocket(port);
			
			while(true){

				final Socket clientSocket = socket.accept();
			
				Runnable runnable=new Runnable() {
					@Override
					public void run() {

						try {
							HttpRequest request = new HttpRequest(clientSocket);
							
							//String uri = loadHTTPRequest(clientSocket);
							
							//String filename = checkURI(uri);
							
							System.err.println(request.getUri());
							
							IService service = services.get(request.getUri());
							if(service==null){
								service=new FileService();
							}
							
							service.sendHTTP(clientSocket, request);
							
//							if(filename.equals("web/books02.html")){
//								(new BooksService()).sendHTTP(clientSocket, filename);
//							}else {
//								(new FileService()).sendHTTP(clientSocket, filename);
//							}
							
							clientSocket.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				};
				Thread thread=new Thread(runnable);
				thread.start();

			}
			
			//socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
