package br.com.yahoo.mau_mss.mangawebspider.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Título: CopyURL</p>
 * <p>Descrição: Realiza um GET de uma URL e grava no destino </p>
 * <p>Data: Jan 16, 2012, 3:04:07 PM</p>
 * @author Mauricio Soares da Silva (mauricio.soares)
 */
public class CopyURL {

  /**
   * Cria uma nova instância de <code>CopyURL</code> sem argumentos.
   */
  public CopyURL() {
  }
  
  public static boolean copy(String origem, String destino) {
     boolean result = false;
     try {
         //formato da origem: "http://m.mhcdn.net/store/manga/8291/01-001.0/compressed/unozoki_1_013.jpg"
         URL url  = new URL(origem);
         System.out.println(String.format("Opening connection to %s...", origem));
         URLConnection urlC = url.openConnection();
         // Copy resource to local file, use remote file
         // if no local file name specified
         InputStream is = url.openStream();
         // Print info about resource
         Date date = new Date(urlC.getLastModified());
         DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         System.out.println(String.format("..copying resource (type: %s, modified on: %s)...", 
                 urlC.getContentType(), df.format(date)));
         System.out.flush();
         //formato do destino = dirDados + "unozoki_1_013.jpg";
         FileOutputStream fos = new FileOutputStream(destino);
         byte[] buf = new byte[4096];
         int bytes, size = 0;
         while ((bytes = is.read(buf)) > 0) {
            fos.write(buf, 0, bytes);
            size = bytes;
         } 
         is.close();
         fos.close();
         result = true;
     } catch (MalformedURLException e) { 
         System.err.println(e.toString()); 
     } catch (IOException e) { 
         System.err.println(e.toString()); 
     }
     return result;
  }

}
