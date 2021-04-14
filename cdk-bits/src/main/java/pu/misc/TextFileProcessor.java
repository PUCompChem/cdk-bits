package pu.misc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class TextFileProcessor 
{
	String inputFileName = null;
	boolean FlagOmitEmptyLine = true;
	List<String> errors = new ArrayList<String>();
	
	public TextFileProcessor() {
	}
	
	public TextFileProcessor(String inputFileName) {
		this.inputFileName = inputFileName;
	}
	
	public String getInputFileName() {
		return inputFileName;
	}

	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public boolean isFlagOmitEmptyLine() {
		return FlagOmitEmptyLine;
	}

	public void setFlagOmitEmptyLine(boolean flagOmitEmptyLine) {
		FlagOmitEmptyLine = flagOmitEmptyLine;
	}
		
	public List<String> getErrors() {
		return errors;
	}
	
	public String getAllErrorsAsString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < errors.size(); i++)
			sb.append(errors.get(i) + "\n");
		return sb.toString();
	}
	
	public void clearErrors() {
		errors.clear();
	}

	public void iterateFile() 
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFileName));
		}
		catch (FileNotFoundException e) {
			errors.add(e.getMessage());
		}		
		
		try {
			int nLine = 0;
			String line0;
			
			while ( (line0 = br.readLine()) != null ) 
			{
				nLine++;
				String line = line0.trim();
				
				if (line.isEmpty())
				{
					if (FlagOmitEmptyLine)
						continue;
					else
					{
						errors.add("Line #" + nLine + " is empty!");
						break;
					}
				}
				
				processLine(line, nLine);
			}	
			
			br.close();
		}
		catch (IOException e) {
			errors.add(e.getMessage());
		}
		
	}
	
	public abstract void processLine(String line, int lineNumber);
	
}
