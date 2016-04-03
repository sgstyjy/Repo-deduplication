package deduplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class PrintResult {
	
	public void printNodelistinfo() throws IOException{
		
		FileOutputStream fs = new FileOutputStream(new File("finallist.txt"));
		PrintStream writer = new PrintStream(fs);
		
		for(int i=0; i<Constant.PRIME; i++)
		{
			//print out the total node at this location
			writer.print("("+i+ ") T: "+Constant.nodenumlist[i]+" ");
			
			Node tempNode = Constant.finallist[i];
			while(tempNode != null)
			{
				//print out the block number of this node
				writer.print("S:|"+ tempNode.block_sum +"|  ");
				//print out the detail info of these blocks
				BlockNode tempBlock = tempNode.blocklist;
				for(int j=0; j<tempNode.block_sum; j++)
				{
					writer.print("id: "+tempBlock.image_id+"; bnum: "+ tempBlock.image_blockNum+" ");
					tempBlock = tempBlock.next;
				}
				tempNode = tempNode.next;
			}
			writer.println();
		}
		fs.close();
	}

}
