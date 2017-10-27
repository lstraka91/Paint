package sk.tsystems.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	public static int savePainting( List list, File fout) {
		
		if(fout!=null) {
			try {
				FileOutputStream fos=new FileOutputStream(fout);
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				oos.writeObject(list);
				oos.flush();
				oos.close();
				fos.close();
			System.out.println("Succesfulty saved "+list.size()+" drawing objects into "+fout.getName());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 1;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 2;
			}
		}
		
		return 0;
		
	}
	
	public static int savePainting( ArrayList list, File fout) {
		
		if(fout!=null) {
			try {
				FileOutputStream fos=new FileOutputStream(fout);
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				oos.writeObject(list);
				oos.flush();
				oos.close();
				fos.close();
			System.out.println("Succesfulty saved "+list.size()+" drawing objects into "+fout.getName());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0;		
	}
	
	public static ArrayList loadPainting(File fin) {
		ArrayList list=null;
		if(fin!=null) {
			try {
				FileInputStream fis=new FileInputStream(fin);
				ObjectInputStream ois=new ObjectInputStream(fis);
				list=(ArrayList) ois.readObject();
				ois.close();				
				fis.close();
			System.out.println("Succesfulty loaded "+list.size()+" drawing objects from "+fin.getName());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		return list;
		
	}
}
