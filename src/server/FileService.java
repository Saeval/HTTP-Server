package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import server.HttpMessage.ContentType;

public class FileService implements IService{

	public void sendHTTP(final Socket clientSocket,
			HttpRequest request) throws IOException,
			FileNotFoundException {
		
		String filename = checkURI(request.getUri());
		
		HttpMessage message = new HttpMessage();
		
		if(filename.endsWith("xml")){
			message.setContentType(ContentType.XML);
		}
		
		message.openHttpAnswer(clientSocket);
		copyFile(filename, message.getOut());
//		copyFile(filename, clientSocket.getOutputStream());
		message.closeHttpAnswer();
	}

	private void copyFile(String filename,
			OutputStreamWriter out)
			throws FileNotFoundException, IOException {
		BufferedReader fileReader=new BufferedReader(
			new FileReader(filename)
		);
		String fileLine=fileReader.readLine();
		while(fileLine!=null){
			out.write(fileLine+"\n");
			fileLine=fileReader.readLine();
		}
	}
	
//	private void copyFile(String filename,
//			OutputStream stream){
//		FileInputStream inputStream = new FileInputStream(file);
		
		// TODO: Copiare il contenuto del file nell'inputStream e poi nell'outputStream del clientSocket
//	}
	


	private String checkURI(String uri) {
		String filename="web"+uri;
//			File webFolder=new File("web");
//			File[] files=webFolder.listFiles();
//			boolean found=false;
//			for (int i = 0; i < files.length; i++) {
//				if(files[i].getName().endsWith(filename)){
//					found=true;
//				}
//			}
//			if(!found){
//				filename="web/error.html";
//			}
		File file=new File(filename);
		if(!file.exists()){
			filename="web/error.html";
		}
		return filename;
	}

}
