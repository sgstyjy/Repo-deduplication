package deduplication;

public class Node {
	public long hashvalue =0;   //the hash value of this block
	public int block_sum = 0;   //the total blocks with this hash value;
	public BlockNode blocklist = new BlockNode();
    public Node next = null;
  
    public void setHashvalue(long hv){
 	        this.hashvalue = hv;
    }
    public long getHashvalue(){
	        return this.hashvalue;
    }
    
    public void setBlcok_sum(int bs){
 	        this.block_sum = bs;
    }
    public int getBlock_sum(){
 	         return this.block_sum;
    }
    
    public BlockNode getBlocklist(){
	        return this.blocklist;
    }
    
    public void addBlock(int imageid, int blocknum){
    	
    	if(this.block_sum == 0)
    	{
    		this.blocklist.image_id = imageid;
    		this.blocklist.image_blockNum = blocknum;
    		this.block_sum++;
    	}
    	else
    	{
    		BlockNode blk = new BlockNode();
        	blk.setImageid(imageid);
    		blk.setImageblocknum(blocknum);
    		
    		//add new block to the list head
    		blk.next = this.blocklist;
    		this.blocklist = blk;
 
    		this.block_sum++;
    	}	
    }
    
    public void setNext(Node newnext){
    	this.next = newnext;
    }
    
    public Node getNext(){
 	        return this.next;
    }
 
}
