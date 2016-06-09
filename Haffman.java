import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


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
		
		File             file   = new File(this.codedFilePath);
		
		file.createNewFile();
		
		BufferedReader   reader = new BufferedReader( new FileReader( new File(this.filepath) ) );
		BufferedWriter   writer = new BufferedWriter( new FileWriter( file ) );
		
		int c;
		
		
		
		//Create the file.
		while ( (c = reader.read()) != -1)
		{
			
			//Get the code of the current character.
			String code = this.tree.getCode( (char)c );
			
			
			//Getting the chars.
			char[] chars = code.toCharArray();
			
			
			//Write them.
			writer.write(chars);
		}
		
		
		reader.close();
		writer.close();
	}
	//--------------------------------Create the file tree--------------------------------//
	
	
	
	
	
	
	//-------------------------------------Print Method-----------------------------------//
	private void print() throws IOException
	{
		
		//File.
		File file   = new File(this.codedFilePath);
		
		
		
		//Reader.
		BufferedReader reader = new BufferedReader( new FileReader( file ) );
		
		
		int c;
		Node node = this.tree.root;
		
		
		//Go though all characters of the file.
		while ( (c = reader.read()) != -1 )
		{
			
			//Get the next node from the haff man tree based the bit char.
			node = this.tree.getNode((char)c, node);
			
			
			//Reset and show the letter.
			if ( node.isLeaf() ){
				System.out.print(node.getLetter());
				node = this.tree.root;
			}
			
			
		}
		
		reader.close();
		
	}
	//-------------------------------------Print Method-----------------------------------//

}
