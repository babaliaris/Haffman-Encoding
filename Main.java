import java.io.IOException;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		//File path to read.
		String fileToRead  = "/home/babaliaris/Documents/Ahri.txt";
		
		
		//File path to write.
		String fileToWrite = "/home/babaliaris/code.hmc"; //hmc = haff man code.
		
		
		
		
		
		//Create a new scan object.
		Scan s = new Scan(fileToRead);
		
		
		//Create a new Haffman object.
		Haffman h = new Haffman( s.scan(), s.filepath, fileToWrite);
		
		
		//Create haffman.
		h.create();

	}

}
