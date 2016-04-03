package deduplication;

public class BlockNode {
	public int image_id;
	public int image_blockNum;
	public BlockNode next = null;
	
    public void setImageid(int iid){
	        this.image_id = iid;
    }
    public long getHashvalue(){
        return this.image_id;
    }
    
    public void setImageblocknum(int ibn){
	        this.image_blockNum = ibn;
    }
    public long getsetImageblocknum(){
        return this.image_blockNum;
    }
    
    public BlockNode getNext(){
        return this.next;
    }
    
    public void setNext(BlockNode newblock)
    {
    	this.next = newblock;
    }
}
