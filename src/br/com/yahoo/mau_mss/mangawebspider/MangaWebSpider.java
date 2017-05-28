package br.com.yahoo.mau_mss.mangawebspider;

import br.com.yahoo.mau_mss.mangawebspider.service.NozokiThread;
import br.com.yahoo.mau_mss.mangawebspider.service.XxxHolicThread;

/**
 * <p>Título: MangaWebSpider</p>
 * <p>Descrição: Programa para buscar os arquivos protegidos do site mangahere </p>
 * <p>Data: Jan 16, 2012, 3:05:07 PM</p>
 * @author Mauricio Soares da Silva (mauricio.soares)
 */
public class MangaWebSpider {

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      //new NozokiThread(72);//68
      new XxxHolicThread(213);
   }
}
