
public class List {
	
	
	
	/*This structure is been used specifically to store letters with their frequency.*/
	
	
	
	//_____Class Variables_____//
	private Node head, tail;
	public  int size;
	//_____Class Variables_____//
	

	
	
	//Constructor.
	public List()
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	
	
	
	
	
	//=============================Append Method=============================//
	public void append(char c)
	{
		
		//This is the very first node.
		if (this.tail == null)
		{
			
			//Create a new node.
			Node new_node = new Node(c);
			
			this.head = new_node; //Link it to the head.
			this.tail = new_node; //Link it to the tail.
			
			this.size++; //Increase the size.
		}
		
		
		
		//Append or increase frequency.
		else
		{
			
			//Search for a node with the letter c.
			Node searchedNode = this.search(c);
			
			//If the letter already exists.
			if (searchedNode != null)
				searchedNode.increaseFreq(); //Increase the frequency of the letter.
			
			
			//Else append a new node with c on the list.
			else
			{
				
				//Create a new node with letter c.
				Node new_node = new Node(c);
				
				this.tail.setNext(new_node); //Append the new node into the list.
				
				this.tail = new_node; //Update the tail pointer.
				
				this.size++; //Increase the size.
			}
		}
	}
	//=============================Append Method=============================//
	
	
	
	
	
	//=============================Search Method=============================//
	public Node search(char c)
	{
		
		/*This method searches for a character in the list. If the letter exists the method
		 * will return the node without deleting it, else returns the null pointer.*/
		
		//Current Node.
		Node currNode = this.head;
		
		
		//Search until the end of the list.
		while (currNode != null)
		{
			
			//If found, return the node.
			if (currNode.getLetter() == c)
				return currNode;
			
			
			//Move on.
			currNode = currNode.getNext();
		}
		
		
		//Letter did not found.
		return null;
	}
	//=============================Search Method=============================//
	
	
	
	
	//==============================Pop Method===============================//
	public Node pop()
	{
		
		//Get the head as the popped node.
		Node poppedNode = this.head;
		
		//Update the head.
		this.head = this.head.getNext();
		
		
		//If size is greater than 0.
		if (this.size > 0)
			this.size--; //Decrease the size.
		
		
		//Return the popped node.
		poppedNode.setNext(null);
		return poppedNode;
	}
	//==============================Pop Method===============================//

}
