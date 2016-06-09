
public class Node {
	
	/*This class is been used for both creating a linked list and a binary tree.
	 * I did this to escape confusion between nodes when i will combine them to 
	 * create the haffman tree*/
	
	//_____Class Variables_____//
	private char     letter;
	private int      frequency;
	private Node     left, right;
	private Node     next;
	private String 	 R;
	private String 	 L;
	//_____Class Variables_____//
	
	
	
	//Constructor.
	public Node(char c)
	{
		this.letter    = c;
		this.frequency = 1;
		this.left      = null;
		this.right     = null;
		this.next      = null;
		this.R         = "";
		this.L         = "";
	}
	
	
	
	//-----------------------Set Methods-----------------------//
	
	//Set Letter.
	public void setLetter(char c){ this.letter = c; }
	
	//Set frequency.
	public void setFreq(int freq){ this.frequency = freq; }
	
	//Set next.
	public void setNext(Node new_node){ this.next = new_node; }
	
	//Set left.
	public void setLeft(Node new_node){ this.left = new_node; }
	
	//Set right.
	public void setRight(Node new_node){ this.right = new_node; }
	
	//Set R.
	public void setR(String s){ this.R = s; }
	
	//Set L.
	public void setL(String s){ this.L = s; }
	
	//-----------------------Set Methods-----------------------//
	
	
	
	
	//-----------------------Get Methods-----------------------//
	//Get letter.
	public char getLetter(){ return this.letter; }
	
	//Get frequency.
	public int getFreq(){ return this.frequency; }
	
	//Get next.
	public Node getNext(){ return this.next; }
	
	//Get left.
	public Node getLeft(){ return this.left; }
	
	//Get right.
	public Node getRight(){ return this.right; }
	
	//Get R.
	public String getR(){ return this.R; }
	
	//Get L.
	public String getL(){ return this.L; }
	//-----------------------Get Methods-----------------------//
	
	
	//Increase frequency.
	public void increaseFreq() { this.frequency++; }
	
	
	
	//IsLeaf.
	public boolean isLeaf(){ return (this.left == null) && (this.right == null); }

}
