
import java.io.*;
import java.util.*;

public class OSProcess {
	
	public OSProcess() {};
	
	public void start(ProcessBuilder pb) throws IOException 
	{
		Process pro = pb.start(); 
		InputStream is = pro.getInputStream();     
		InputStreamReader isr = new InputStreamReader(is);     
		BufferedReader br = new BufferedReader(isr); 
		 
		String line;     
		while ( (line = br.readLine()) != null)      
			System.out.println(line); 
		br.close(); 
	}
	
	public void setHome(ProcessBuilder pb) 
	{
		File home = new File(System.getProperty("user.dir"));
		pb.directory(home);
	}
	
	public void changeDir(ProcessBuilder pb, List<String> list) 
	{
		String nextDir = list.get(1);
		File currDir = pb.directory();
		
		File path = new File(currDir + "\\" + nextDir);
		if(path.exists())
		{
			pb.directory(path);
		} 
		else 
		{
			System.out.println("Please input a valid directory!");
		}
	}
	
	public void printHistory(List<String> hist) 
	{
		for(int i = 0; i < hist.size()-1; i++) 
		{
			System.out.println(i + " " + hist.get(i));
		}
	}
	
	public void doPrevious(ProcessBuilder pb, List<String> hist) 
	{
		if(hist.size() == 1) {
			System.out.println("No previous command!");
		}
		else
		{
			List<String> list = new ArrayList<String>();
			list.add(hist.get(hist.size()-2));
			pb.command(list);
			System.out.println(list);
		}
	}
	
	public void doCommand(ProcessBuilder pb, List<String> cmd, List<String> hist) 
	{
		int num = Character.getNumericValue(cmd.get(0).charAt(1));
		List<String> list = new ArrayList<String>();
		list.add(hist.get(num));
		pb.command(list);
		System.out.println(hist.get(num));
	}
}

