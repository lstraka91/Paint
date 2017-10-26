package sk.tsystems.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileUtils {

	public int savePainting( ArrayList list, File fout) {
		
		if(fout!=null) {
			try {
				FileOutputStream fos=new FileOutputStream(fout);
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				oos.writeObject(list);
				oos.flush();
				oos.close();
				fos.close();
			
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
