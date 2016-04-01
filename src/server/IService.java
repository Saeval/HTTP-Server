package server;

import java.io.IOException;
import java.net.Socket;

/**
 * Generico Servizio
 * 
 * TODO: rivedere javadoc
 * 
 * @author A. Martinelli
 */
public interface IService {

	/**
	 * TODO : fissare il 'filename', sostituirlo con il 
	 * componente 'RichiestaHttp'
	 * 
	 * @param clientSocket
	 * @param uri
	 * @throws IOException
	 */
	public void sendHTTP(final Socket clientSocket,
			HttpRequest request) throws IOException;
}
