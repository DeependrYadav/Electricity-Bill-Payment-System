package com.masai.utility;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.masai.entities.Consumer;
import com.masai.entities.Bills;
import com.masai.entities.Complain;

public class CheckFile {

	public static Map<String, Consumer> consumerFile() {
		Map<String, Consumer> cFile = null;
		File f = new File("consumerFile.ser");
		boolean ans = false;
		try {
			if (!f.exists()) {
				f.createNewFile();
				ans = true;
			}
			if (ans) {
				cFile = new LinkedHashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(cFile);
				return cFile;
			} else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				cFile = (Map<String, Consumer>) ois.readObject();
				return cFile;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cFile;
	}
	public static Map<Integer,Complain> complainFile(){
		
		Map<Integer,Complain> complainFile = null;
		
		File f = new File("complainFile.ser");
		boolean ans = false;
		try {
			if(!f.exists()) {
				f.createNewFile();
				ans = true;
			}if(ans) {
				complainFile = new HashMap<>();
				return complainFile;
			}else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				complainFile = (Map<Integer,Complain>)ois.readObject();
				ois.close();
				return complainFile;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		return complainFile;
	}

	
	public static List<Bills> bills(){
		List<Bills> lFile = null;
		File f = new File("billsFile.ser");
		boolean ans = false;
		try {
			if(!f.exists()) {
				f.createNewFile();
				ans = true;
			}
			if(ans) {
				lFile = new LinkedList<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(lFile);
				return lFile;
			}else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				lFile = (List<Bills>) ois.readObject();
				return lFile;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return lFile;
	}
	
	
}
