
public class Tree {
	
	//_____Class Variables_____//
	public Node root;
	//_____Class Variables_____//
	
	
	
	
	//Constructor.
	public Tree(Node root)
	{
		this.root = root;
	}
	
	
	
	//====================Get Node Method====================//
	public Node getNode(char path, Node start_node)
	{
		
		/*This method returns the next node which the path shows.*/
		
		
		//Return the left node.
		if (path == '0')
			return start_node.getLeft();
		
		
		//Return the right node.
		else
			return start_node.getRight();
	}
	//====================Get Node Method====================//
	
	
	
	
	
	
	//====================Get Code Method====================//
	public String getCode(char c)
	{
		Node currNode = this.root; //Current node.
		String s      = ""+c; //Char to string.
		String code   = "";   //Char sequence.
		
		
		
		
		//Loop until you find a leaf.
		while ( !currNode.isLeaf() )
		{

			
			//Go left.
			if ( currNode.getL().contains(s) )
			{
				currNode = currNode.getLeft();
				code += "0"; //Add a 0 in the sequence.
			}
			
			
			//Go right.
			else if ( currNode.getR().contains(s) )
			{
				currNode = currNode.getRight();
				code += "1"; //Add a 1 in the sequence.
			}
			
			
			//Error occurred.
			else
			{
				System.out.println("Coding Failed:\nThe character: \""+c+"\" , could not been identified!");
				return null;
			}
		}
		
		

		
		
		//Return the code.
		return code;
		
	}
	//====================Get Code Method====================//
	
	

}
