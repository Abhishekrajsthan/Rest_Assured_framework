package common_method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class common_meyhod_utility {
	
	public static void evidencefilecreater(String filename, String request, String response) throws IOException
	{
		File Newtextfile = new File("C:\\Users\\Abhishek\\Desktop\\New folder\\Rest Assured\\RA\\"+filename+".txt");
		if (Newtextfile.createNewFile())
		{
			FileWriter datawriter = new FileWriter(Newtextfile);
			datawriter.write("requestbody is " +request+ "\n\n");
			datawriter.write("responsebody is " +response);
			datawriter.close();
			System.out.println("response and request data saved in " +Newtextfile.getName());
		}
		else {
			System.out.println(Newtextfile.getName() +"file already exits");
		}
	}

}
