import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Haffman {
	
	
	//_____Class Variables_____//
	private Queue queue;
	private Tree  tree;
	private String filepath;
	private String codedFilePath;
	//_____Class Variables_____//
	
	
	
	
	
	//Constructor.
	public Haffman(List l, String filepath, String destination)
	
	{
		
		//Create a new queue.
		this.queue = new Queue();
		
		
		//Initialize the queue.
		while (l.size > 0)
		{
			this.queue.insert( l.pop() );
		}
		
		
		//Reorder the queue.
		this.queue.reorder();
		
		
		//Set the filepath.
		this.filepath = filepath;
		
		
		//Coded file path.
		this.codedFilePath = destination;
	}
	
	
	
	
	
	//===============================Create the haffman tree===============================//
	public void create() throws IOException
	{
		
		
		while (this.queue.size > 1)
		{
			
			//---Popping two nodes from queue---//
			Node node1 = this.queue.pop();
			Node node2 = this.queue.pop();
			//---Popping two nodes from queue---//
			
			
			//Creating a new node.
			Node new_node = new Node('\0');
			
			
			new_node.setLeft(node1);  //Linking node 1 to the left.
			new_node.setRight(node2); //Linking node 2 to the right.
			
			
			//Set as frequency the sum of the two others.
			new_node.setFreq( node1.getFreq() + node2.getFreq() );
			
			
			//------------------Set the L string------------------//
			
			//Node 1 is a leaf.
			if ( node1.isLeaf() )
				new_node.setL( ""+node1.getLetter()  );
			
			//else
			else
				new_node.setL( node1.getL() + node1.getR() );
			//------------------Set the L string------------------//
			
			
			
			//------------------Set the R string------------------//
			
			//Node 2 is a leaf.
			if ( node2.isLeaf() )
				new_node.setR( ""+node2.getLetter()  );

			
			//else
			else
				new_node.setR( node2.getL() + node2.getR() );
			//------------------Set the R string------------------//
			
			
			
			
			this.queue.insert(new_node); //Insert the new node to the queue.
			
			this.queue.reorder(); //Reorder the queue.
			
		}
		
		
		
		
		
		//Create the haffman tree.
		this.tree = new Tree( this.queue.pop() );
		
		
		//Create the haffman file (Encode).
		this.write_file();
		
		
		//Decode the file and print the content.
		this.print();
		

	}
	//===============================Create the haffman tree===============================//
	
	
	
	
	
	
	//--------------------------------Create the file tree--------------------------------//
	private void write_file() throws IOException
	{
		
		File             f      = new File(this.codedFilePath);
		
		f.createNewFile();
		
		
		RandomAccessFile file   = new RandomAccessFile(f, "rw");
		BufferedReader   reader = new BufferedReader( new FileReader( new File(this.filepath) ) );
		
		int c, bits[] = new int[8], Byte = 0, header = 1;
		
		String code = "", help = "";
		
		char[] chars;
		
		
	
		//This is the header of the file. I will replace this byte later.
		file.writeByte(0);
		
		
		//Scan the read file (get characters one by one)//
		while ( (c = reader.read()) != -1 )
		{
			
			//Get the code of the current character.
			code += tree.getCode( (char)c );
			
			
			//If the code string become more than 8 characters.
			if (code.length() >= 8)
			{
				
				//Create a char array from this string.
				chars = code.toCharArray();
				
				
				//loop through all the characters.
				for (int i = 0; i < chars.length; i++)
				{
					
					//Pick up the first 8 characters and convert them to integers.
					if (i < 8)
						
						//Convert current char to integer and store it into the array.
						bits[i] = Integer.parseInt(""+chars[i]);
					
					
					//Save the rest characters for later.
					else
						
						help += chars[i];
				}
				
				
				//Create a byte from the bits.
				for (int i = 0; i < 8; i++)
				{
					Byte = Byte | ( bits[i] << i );
				}
				
				//Update the code string.
				code = help;
				
				//Reset the help string.
				help = "";
				
				//Write the byte to the file.
				file.writeByte(Byte);
				
				//Reset the byte variable.
				Byte = 0;
			}
		}
		
		
		//Get the last characters.
		chars = code.toCharArray();
		
		int i = 0;
		
		for (i = 0; i < chars.length; i++)
		{
			
			int i_adder = i;
			
			//If the remaining are more than 8 bits.
			if (chars.length - i > 8)
			{
				
				//Pick up the first 8 characters and convert them to integers.
				for (int j = 0; j < 8; j++)
				{
					
					//Convert current char to integer and store it into the array.
					bits[j] = Integer.parseInt(""+chars[i_adder]);
					
					
					//Increase i.
					i_adder++;
				}
				
				
				//Create a byte from the bits.
				for (int j = 0; j < 8; j++)
				{
					Byte = Byte | ( bits[j] << j );
				}
				
				//Write the byte to the file.
				file.writeByte(Byte);
				
			}
			
			
			//This is the last byte, so create the header.
			else
			{
				
				//Pick up the first 8 characters and convert them to integers.
				for (int j = 0; j < chars.length - i; j++)
				{
					
					//Convert current char to integer and store it into the array.
					bits[j] = Integer.parseInt(""+chars[i_adder]);
					
					
					//Increase i.
					i_adder++;
				}
				
				
				//Create a byte from the bits.
				for (int j = 0; j < chars.length - i; j++)
				{
					Byte   = Byte | ( bits[j] << j );
					header = 1 << j;
					
				}
				
				
				
				
				//Write the byte to the file.
				file.writeByte(Byte);
			}
			
			
			//Reset the byte.
			Byte = 0;
			
			
			//Update i.
			i = i_adder;
		}
		
		
		//Go to the start of the file.
		file.seek(0);
		
		//Write the header.
		file.writeByte(header);
		
		//Close the streams.
		reader.close();
		file.close();
	}
	//--------------------------------Create the file tree--------------------------------//
	
	
	
	
	
	
	
	
	//-------------------------------------Print Method-----------------------------------//
	private void print() throws IOException
	{

		
		DataInputStream file = new DataInputStream( new FileInputStream(this.codedFilePath) );
		
		int c = 0, mask = 1, bit, remaining_bits = 1;
		
		
		Node node = this.tree.root;
		
		//Get the header.
		int header = file.readByte();
		
		
		
		//Go through all the file.
		while ( file.available() > 0 )
		{
	
			//Read next byte.
			c = file.readByte();
			
			
			//If there are more bytes after this byte.
			if ( file.available() > 0 )
			{
				//Go through all the bits of this byte and take them one by one.
				for (int i = 0; i < 8; i++)
				{
					
					
					//Get the current bit//
					if ( ( c & (mask << i) ) == (mask << i) )
						bit = 1;
					
					else
						bit = 0;
					//Get the current bit//
					
					
					
					//Convert it to a character.
					char[] ch = Integer.toString( bit ).toCharArray();
					
					
					//Get the node using the char as path.
					node = this.tree.getNode( ch[0], node);
					
					
					//If the node is a leaf.
					if ( node.isLeaf() )
					{
						System.out.print( node.getLetter() ); //Print the character.
						
						node = this.tree.root; //Reset the node.
					}
					
				}
				
				
				//Reset the mask.
				mask = 1;
			}
		
		}
		
		
		//Find how many bits must been taken from the last byte.
		for (int i = 0; i < 8; i++)
		{
			if (header == (mask << i) )
				break;
			
			remaining_bits++;
			
		}
		
		
		//Reset the mask.
		mask = 1;
		
		
		//Last bits.
		for (int i = 0; i < remaining_bits; i++)
		{
			
			
			//Get the current bit//
			if ( ( c & (mask << i) ) == (mask << i) )
				bit = 1;
			
			else
				bit = 0;
			//Get the current bit//
			
			
			
			//Convert it to a character.
			char[] ch = Integer.toString( bit ).toCharArray();
			
			
			//Get the node using the char as path.
			node = this.tree.getNode( ch[0], node);
			
			
			//If the node is a leaf.
			if ( node.isLeaf() )
			{
				System.out.print( node.getLetter() ); //Print the character.
				
				node = this.tree.root; //Reset the node.
			}
			
		}
		
		
		
		
		
		//Close the stream.
		file.close();
	}
	//-------------------------------------Print Method-----------------------------------//

}
