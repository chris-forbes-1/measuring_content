package com.cs408.forbes.chris.analysis.interfaces;


import java.util.*;

public interface SWDL{
	public Map<String, List<String>> getSWDLData();
	public String Search(String Line);
	public boolean writeLemitizedFile();
}
