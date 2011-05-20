
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Zipper {

    
    public static void main(String[] args) {
         try {
             ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("w:\\test.zip", false));
            
             String name = "";
             for( int i = 0; i < 1000; i++) {
                 name += "a";
             }
             ZipEntry ze = new ZipEntry(name);
            
             zos.putNextEntry( ze);
             zos.write("bbbb".getBytes());
             
             
             zos.close();
             
             
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
