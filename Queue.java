
public class Queue {
	
	
	/*This is not a heap priority queue. Its a linked list with a sort method called reorder that acts as a heap queue.*/
	/*Reorder is manual, which means after you insert a node into the queue you must manually call the method reorder
	 * to reorder your linked list (queue).
	 * 
	 * The reorder method sorts the linked list from minimum to maximum frequency.*/
	
	
	
	//_____Class Variables_____//
	public Node head, tail;
	public  int size;
	//_____Class Variables_____//
	
	
	//Constructor.
	public Queue()
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	
	
	//==================Insert Method==================//
	public void insert(Node node)
	{
		
		//The very first node.
		if (this.tail == null || this.head == null)
		{
			this.head = node;
			this.tail = node;
			
			this.size++; //Increase the size.
		}
		
		
		//Append the queue.
		else
		{
			this.tail.setNext(node); //Append the queue.
			 
			this.tail = node; //Update the tail.
			
			this.size++; //Increase the size.
		}
	}
	//==================Insert Method==================//
	
	
	
	
	
	//===================Pop Method====================//
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
	//===================Pop Method====================//
	
	
	
	
	
	//==================Reorder Method=================//
	public void reorder()
	{
		Node currNode  = this.head;
		Node checkNode;
		
		if (currNode != null)
			checkNode = currNode.getNext();
		
		else
			return;
		
		
		
		//Bubble Sort Algorithm For Linked List.
		for (int i = 0; i < this.size; i++)
		{
			
			//Get next node.
			if (i > 0)
			{
				currNode = currNode.getNext();
				checkNode = currNode.getNext();
			}
			
			
			for (int j = 0; j < this.size-(i+1); j++)
			{
				
				//Get next node.
				if (j > 0)
					checkNode = checkNode.getNext();
				
				
				//Swap content.
				if (checkNode.getFreq() < currNode.getFreq())
					this.swap(checkNode, currNode);
			}
		}
	}
	//==================Reorder Method=================//
	
	
	
	
	
	//----------------------Swap Method----------------------//
	private void swap(Node node1, Node node2)
	{
		
		/*This method swaps all the variables from two nodes.*/
		
		//Temp variables.
		char letter = node1.getLetter();
		int freq    = node1.getFreq();
		Node left   = node1.getLeft();
		Node right  = node1.getRight();
		String L    = node1.getL();
		String R    = node1.getR();
		
		
		//Copy content from node 2 to node 1.
		node1.setLetter( node2.getLetter() );
		node1.setFreq( node2.getFreq() );
		node1.setLeft( node2.getLeft() );
		node1.setRight( node2.getRight() );
		node1.setL( node2.getL() );
		node1.setR( node2.getR() );
		
		//Copy content from node 1 to node 2.
		node2.setLetter( letter );
		node2.setFreq( freq );
		node2.setLeft( left );
		node2.setRight( right );
		node2.setL( L );
		node2.setR( R );
	}
	//----------------------Swap Method----------------------//

}
