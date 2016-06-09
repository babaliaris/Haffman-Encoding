import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Scan {
	
	//_____Class Variables_____//
	private BufferedReader reader;
	private List 		   list;
	private char[] 		   buffer;
	public String filepath;
	//_____Class Variables_____//
	
	
	
	//Constructor.
	public Scan(String filepath) throws FileNotFoundException
	{
		
		//Create a new reader.
		this.reader = new BufferedReader( new FileReader( new File(filepath) ) );
		
		//Create a new character buffer.
		this.buffer = new char[128];
		
		this.filepath = filepath;
	}
	
	
	
	
	
	//=================================Scan Method=================================//
	public List scan() throws IOException
	{
		
		/*This method is scanning the file and storing the characters and their frequency into a list.
		 * When its done the list will be returned.*/
		
		
		//Max size of this list is the size of the alphabet.
		this.list = new List(); //Create a new list.
		
		boolean flag = true; //A flag for the while loop.
		
		
		//Create the list of characters.
		while (flag)
		{
			
			//Read next buffer.
			this.nextBuffer();
			
			
			//Go through the buffer.
			for (char c : this.buffer)
			{
				
				//Terminate.
				if (c == '\0')
				{
					flag = false;
					break;
				}
				
				
				//Append the character into the list.
				//If the character already in the list it will increase the frequency.
				this.list.append(c);
			}
		}
		
		this.reader.close();
		
		return this.list;
	}
	//=================================Scan Method=================================//
	
	
	
	
	
	//------------------------------Next Buffer Method------------------------------//
	private void nextBuffer()
	{
		
		/*This method reads the next 128 characters and stores them into an array.*/
		
		//Read next 128 characters.
		for (int i = 0; i < this.buffer.length; i++)
		{
			
			
			//Try to read.
			try 
			{
				int code = this.reader.read();
				
				
				//End of stream.
				if (code == -1)
				{
					this.buffer[i] = '\0';
					break;
				}
				
				
				//Append the character into the buffer.
				this.buffer[i] = (char) code;
			} 
			
			
			
			//Something went wrong.
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
		}
	}
	//------------------------------Next Buffer Method------------------------------//
	

}
