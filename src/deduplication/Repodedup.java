package deduplication;

import java.io.IOException;

public class Repodedup {

	public static void main(String[] args) throws IOException {
		/*
		 * @para image numbers, the total input image files;
		 * @para images[],  the image file names, base_path="/home/zf/VMDs/OSBOXES/VDI";
		 * 
		 * @output the deduplicated block table
		 * @output the total blocks and the final block number
		 */
		Constant.image_num = Integer.parseInt(args[0]);
		
		//initialize the final list
		for(int i=0; i< Constant.PRIME; i++)
		{
			Constant.nodenumlist[i] = 0;
		}
		
		//get the input image file name
		String[] images = new String [Constant.image_num];
		Deduplication deduplicator = new Deduplication();
		Long starttime = System.currentTimeMillis();
		for(int i = 0; i<Constant.image_num; i++)
		{
			images[i] = Constant.base_path + args[i+1]; 
			System.out.println("The input file name is: "+ images[i]);
			System.out.println("The image id is: "+ Constant.image_ID);
			deduplicator.deduplicate(images[i]);
			Constant.image_ID++;
		}
		Long endtime = System.currentTimeMillis();
		Long duration = endtime - starttime;
		System.out.println("The deduplication time is: "+ duration);
		
		PrintResult printer = new PrintResult();
		printer.printNodelistinfo();
	}

}
