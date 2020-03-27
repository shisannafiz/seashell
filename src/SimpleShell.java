
import java.io.*;
import java.util.*;

public class SimpleShell 
{
	
	public static void main(String[] args) throws java.io.IOException 
	{
		String[] commandLine;   
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in)); 
		ProcessBuilder pb = new ProcessBuilder();
		OSProcess process = new OSProcess();
		process.setHome(pb);
		
		List<String> history = new ArrayList<String>();

		while (true)   
		{       
			System.out.print("jsh>");     
			commandLine = console.readLine().split(" "); 
			
			List<String> command = new ArrayList<String>();
			
			for(int i = 0; i < commandLine.length; i++) 
			{
				command.add(commandLine[i]);
			} 
			history.addAll(command);
			
			
			try { 
				if(commandLine.length == 0) continue; 
				
				else if(command.get(0).equals("cd"))	
				{
					if(command.size() > 2)
					{
						System.out.println("Choose one directory");
					}
					else if(command.size() == 1)
					{
						process.setHome(pb);
						continue;
					} 
					else 
					{
						process.changeDir(pb, command);
						continue;
					} 
				}
				else if(command.get(0).equals("history")) 
				{
					process.printHistory(history);
					continue;
				}
				else if(command.get(0).equals("!!") && (command.size() == 1)) 
				{
					process.doPrevious(pb, history);
					continue;
				}
				else if(command.get(0).charAt(0) == '!') 
				{
					process.doCommand(pb, command, history);
					continue;
				}
				else
				{
					pb.command(command);
				}
			process.start(pb);
			}
			
			catch (IOException e){
				System.out.println("Error! Try again.");
			}    
		} 
	}
}

