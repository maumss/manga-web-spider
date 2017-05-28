package br.com.yahoo.mau_mss.mangawebspider.service;

import br.com.yahoo.mau_mss.mangawebspider.util.CopyURL;

/**
 * <p>Título: XxxHolicThread</p>
 * <p>Descrição:  </p>
 * <p>Data: Jan 22, 2012, 1:51:30 PM</p>
 * @author Mauricio Soares da Silva (Mau)
 * @see http://www.manga123.com/read/XxxHolic
 */
public class XxxHolicThread implements Runnable {
   public static final String URL_PREFIX = "http://www.citymanga.com/files/images/xxxholic/"; // "http://cdn.images.manga123.com/32/";
   private Thread runner;
   private static final int QTDE_CAP = 213;
   private static final int QTDE_PAG = 50; //TODO: mudar a qtde. de págs. quando tiver testado
   private int capitulo;
   
   /**
     * Cria uma nova instância de <code>XxxHolicThread</code> sem argumentos.
     */
    public XxxHolicThread() {
      runner = new Thread(this, "XxxHolic"); // (1) Create a new thread.
      System.out.println(runner.getName());
      runner.start();
    }
  
   public XxxHolicThread(int capitulo) {
      this.capitulo = capitulo;
      runner = new Thread(this, "XxxHolic"); // (1) Create a new thread.
	   System.out.println(runner.getName());
	   runner.start();
   }
  
   private void criaDiretorio(String folder) {
      java.io.File diretorio = new java.io.File(folder);
      if (! diretorio.exists()) {
         // cria diretório e seu caminho
         try {
             diretorio.mkdirs();
         } catch (Exception e) {
            System.err.println(String.format("Erro ao criar diretório [%s]", folder));
         } // end catch
      } // end if
   }
   
   private String defineDirDestino(String pasta) {
      String userDir = System.getProperty("user.dir");
      String separador = System.getProperty("file.separator");
      String dirDados = userDir + separador + "dados" + separador +
                        "32" + separador + pasta + separador;
      this.criaDiretorio(dirDados);
      return dirDados;
   }
    
   @Override
   public void run() {
      int count = 0, i = 208;
      while (i <= XxxHolicThread.QTDE_CAP) {
         if (capitulo > 0)
           i = capitulo;
         String origem = XxxHolicThread.URL_PREFIX + i + "/"; // + "13.jpg";
         String sufixo = ".jpg";
         String origemFinal, ultimoReal = null;
         
         System.out.println("Buscando capítulo " + i);
         String numeroReal = null;
         int tolerancia = 0;
         for (int j = 1; j < XxxHolicThread.QTDE_PAG; j++) {
            String nomeArq =  j + sufixo;
            origemFinal = origem + nomeArq;
            //System.out.println("Copiando " + origemFinal);
            if (j == 0)
              System.out.println("Destino " + defineDirDestino(String.valueOf(i)) + nomeArq);
            if (CopyURL.copy(origemFinal, defineDirDestino(String.valueOf(i)) + nomeArq)) {
              ultimoReal = numeroReal;
              count++;
            } else {
              tolerancia++;
              if (tolerancia > 1)
                break;
            }
         }
         if (capitulo > 0)
           break;
         i++;
      }
      System.out.println(count + " arquivos copiados.");
   }


}
