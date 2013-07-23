/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speaqs.hilmanshini.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author webdevelop
 */
public class IOUtils {
    public static byte[] read(InputStream is){
        List<Byte> al = new ArrayList<Byte>();
        int i   = 0;
        
        while(i != -1){
            try {
                i = is.read();
            } catch (IOException ex) {
                Logger.getLogger(IOUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
            al.add((byte)i);
        }
        byte[] q = new byte[al.size()];
        for( i = 0 ; i < al.size();i++ ){
            q[i] = al.get(i);
        }
        return q;
        
    }
}
