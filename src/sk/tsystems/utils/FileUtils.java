package sk.tsystems.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0;
		
	}
}
