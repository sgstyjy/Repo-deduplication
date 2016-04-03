package deduplication;

public class Constant {
	public static String base_path="/home/zf/VMDs/OSBOXES/VDI/";
	public static int image_num = 0;
	public static int image_ID = 0;

	public static int PRIME = 49999;
	public static Node[] finallist = new Node[Constant.PRIME];
	public static int[]  nodenumlist = new int[Constant.PRIME]; 
	
	public static int blocksize=1*1024;
	public static int COLUMNS = 10000;
	public static int HASH_METHOD = 0;
	
	public static int totalblocks=0;
	public static int storedblocks=0;
	      //one big prime number, it is used for deciding the node list length
	//public static int[]  nodenumlistbkdr = new int[Constant.PRIME];     //the node number of each position in the list for the BKDR table
	//public static int[]  nodenumlistap = new int[Constant.PRIME];       //the node number of each position in the list for the AP table
	
	public static int similar = 0;  //the similar block number
}
