package deduplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jxl.write.Label;


public class Deduplication {
	
	public void deduplicate (String imagename) throws IOException{
	
		File file_in = new File(imagename);
		InputStream reader = new FileInputStream(file_in);
		
		BKDR bkdrhasher = new BKDR();
		
		Node[] tempnodelist = Constant.finallist;
		
		int blocknum = 0;
		int newblocknum = 0;
		int position = 0;   //the pointer of file reader
		int location = 0;   //the location of each hash value in the final Node list
		int size = reader.available();     //the total image size in byte
		byte[] bb = new byte[Constant.blocksize];
		String temp = null;
		long bkdrabs = 0;
		
		while(position<size){
			//read block data and generate hash value
			reader.read(bb);
			temp = new String (bb);
			bkdrabs = bkdrhasher.bkdrhash(temp);    	
			
			//calculate the position of this hash value in the node list
			location =(int) (Math.abs(bkdrabs)%Constant.PRIME);
			
			//this position still does not have any block 
			if (Constant.nodenumlist[location]==0)
			{
				Node newnode = new Node();
				newnode.hashvalue = bkdrabs;
				newnode.block_sum = 1;
				newnode.blocklist.image_id = Constant.image_ID;
				newnode.blocklist.image_blockNum = blocknum;
				newnode.blocklist.next = null;
				Constant.finallist[location] = newnode;
				
				Constant.nodenumlist[location]++;
				newblocknum++;
			}
			//this position has blocks, compare hash value to decide add a new block or not
			else
			{
				Node tempNode = Constant.finallist[location];
				for(int i=0; (i<Constant.nodenumlist[location]) && (tempNode.getNext() != null); i++)
				{
					//this hash value already exists
					if(tempNode.hashvalue == bkdrabs)
					{						
						break;
					}
					
					tempNode = tempNode.getNext();
				}
				//this hash value already exist, change the block list info of this Node
				if((tempNode != null) && (tempNode.hashvalue == bkdrabs))
				{
					tempNode.block_sum++;
					tempNode.addBlock(Constant.image_ID, blocknum);
				}
				//this hash value does not exist, construct and add new node to the end of node list.
				else
				{
					Node newnode = new Node();
					newnode.hashvalue = bkdrabs;
					newnode.block_sum++;
					newnode.blocklist.image_id = Constant.image_ID;
					newnode.blocklist.image_blockNum = blocknum;
					newnode.next = null;
					tempNode.setNext(newnode);
					
					Constant.nodenumlist[location]++;
					newblocknum++;
				}
			}
			blocknum++;
			//read next block data
			position += Constant.blocksize;
		}
		System.out.println("The total blocks of image "+ Constant.image_ID + "is: "+ blocknum);
		System.out.println("The new blocks of image "+ Constant.image_ID + "is: "+ newblocknum);
		reader.close();
	}
	
}
