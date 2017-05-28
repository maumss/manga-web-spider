package br.com.yahoo.mau_mss.mangawebspider.service;

import br.com.yahoo.mau_mss.mangawebspider.util.CopyURL;

/**
 * <p>Título: NozokiThread</p>
 * <p>Descrição: Simula a lógica de pastas e nomes dos arquivos para o Manga Ana Nozoki </p>
 * <p>Data: Jan 16, 2012, 3:05:07 PM</p>
 * @author Mauricio Soares da Silva (mauricio.soares)
 * @see http://www.mangahere.com/manga/nozoki_ana/
 */
public class NozokiThread implements Runnable {
   public static final String URL_PREFIX = "http://m.mhcdn.net/store/manga/8291/";
   private String capitulos[] = {
            "01-001.0", "01-002.0", "01-003.0", "01-004.0", 
            "01-005.0", "01-006.0", "01-007.0", "01-008.0", 
            "01-009.0", "01-010.0", "01-011.0", "01-012.0", 
            "01-013.0", "01-014.0", "01-015.0", "02-016.0", 
            "02-017.0", "02-018.0", "03-019.0", "03-020.0", 
            "03-021.0", "03-022.0", "03-023.0", "03-024.0", 
            "03-025.0", "03-026.0", "03-027.0", "03-027.5", 
            "03-028.0", "03-029.0", "03-030.0", "03-031.0", 
            "03-032.0", "03-033.0", "03-034.0", "04-035.0", 
            "04-036.0", "04-036.5", "05-037.0", "05-038.0", 
            "05-039.0", "05-040.0", "05-041.0", "05-042.0", 
            "05-043.0", "05-044.0", "05-045.0", "06-046.0", 
            "06-047.0", "06-048.0", "06-049.0", "06-050.0", 
            "06-051.0", "06-052.0", "06-053.0", "06-054.0", 
            "06-054.5", "07-055.0", "07-056.0", "07-057.0", 
            "07-058.0", "07-059.0", "07-060.0", "07-061.0", 
            "07-062.0", "07-063.0", "07-063.5", "08-064.0", 
            "08-065.0", "08-066.0", "08-067.0", "08-068.0", 
            "08-069.0", "08-070.0", "08-071.0"};
   private String prefixoJPG[] = 
           {"unozoki_1_", "jnozoki_1_", "knozoki_1_", "m004.", 
            "l005.", "m006.", "h007.", "j008.", 
            "g009.", "j010.", "i011.", "c012.", 
            "d013.", "u014.", "hnozoki_2_", "jnozoki_2_", 
            "qnozoki_2_", "dnozoki_2_", "nnozoki_3_", "bnozoki_3_", 
            "onozoki_3_", "lnozoki_3_", "cnozoki_3_", "qnozoki_3_", 
            "enozoki_3_", "knozoki_3_", "pnozoki_3_", "enozoki_3_", 
            "cnozoki_4_", "inozoki_4_", "unozoki_4_", "enozoki_4_", 
            "lnozoki_4_", "dnozoki_4_", "inozoki_4_", "hnozoki_4_", 
            "pnozoki_4_", "lnozoki_4_", "qnozoki_5_", "enozoki_5_", 
            "cnozoki_5_", "onozoki_5_", "tnozoki_5_", "hnozoki_5_", 
            "gnozoki_5_", "lnozoki_5_", "unozoki_5_", "mnzk_06_p", 
            "dnzk_06_p", "bnzk_06_p", "dnzk_06_p", "bnozoki_ana_50.nzk_06_p", 
            "inzk_06_p", "enzk_06_p", "mnzk_06_p", "onzk_06_p", 
            "mnzk_06_p", "knzk_07_p", "snzk_07_p", "dnzk_07_p", 
            "pnzk_07_p", "gnzk_07_p", "jnzk_07_p", "mnzk_07_p", 
            "lnzk_07_p", "enzk_07_p169_", "knzk_07_p", "n", 
            "mnozoki_8_", "hnozoki_8_", "nnozoki_8_", "fnozoki_8_", 
            "inozoki_8_", "enozoki_8_", "jnozoki_8_"};
   private String numeroJPG[] =
           {"001", "024", "044", "01", 
            "01", "01", "01", "01", 
            "01", "01", "01", "01", 
            "01", "01", "106", "125", 
            "145", "165", "001", "026",
            "046", "066", "086", "106", 
            "126", "146", "166", "186", 
            "007", "026", "046", "066", 
            "086", "106", "126", "146", 
            "167", "186", "001", "026", 
            "046", "066", "086", "106", 
            "126", "146", "166", "001", 
            "026", "048", "068", "088", 
            "108", "128", "148", "168", 
            "007", "005", "026", "048", 
            "068", "088", "108", "128", 
            "148", "170", "188", "02", 
            "028", "048", "068", "088", 
            "108", "128", "148"};
   private String sufixoJPG[] = new String[75];
   private static final String CREDITO = "credit";
   private static final String NOTE1 = "tlnote";
   private static final String NOTE2 = "tlnote2";
   private static final String NOTE3 = "tlnote3";
   private Thread runner;
   private static final int QTDE_PAG = 50; //TODO: mudar a qtde. de págs. quando tiver testado
   private int capitulo;

  /**
   * Cria uma nova instância de <code>NozokiThread</code> sem argumentos.
   */
  public NozokiThread() {
     runner = new Thread(this, "AnaNozoki"); // (1) Create a new thread.
	  System.out.println(runner.getName());
     this.montaCapitulos();
	  runner.start();
  }
  
   public NozokiThread(int capitulo) {
      this.capitulo = capitulo;
      runner = new Thread(this, "AnaNozoki"); // (1) Create a new thread.
	   System.out.println(runner.getName());
      this.montaCapitulos();
	   runner.start();
   }
  
  private void montaCapitulos() {
      sufixoJPG[19] = "_copy";
      sufixoJPG[62] = "_copy";
      sufixoJPG[63] = "_copy";
      sufixoJPG[64] = "_copy";
      sufixoJPG[68] = "_copy";
   }
   
   private String getPalavraFormatada(int numero, int tamanho) {
     String temp = Long.toString(numero);
     int tamanhoTemp = temp.length();
     if (tamanhoTemp < tamanho) {
       for (int i = tamanhoTemp; i < tamanho; ++i) {
         temp = "0" + temp;
       } // end for
     } else if (tamanhoTemp > tamanho) {
       temp = temp.substring(0, tamanho);
     }
     return temp;
   }
   
   private int qtdeDigitosAEsquerda(String value) {
      int result = 0;
      if (value != null && value.startsWith("0") && value.length() > 1) {
         result = value.length();
         //for (int i = 0; i<value.length() - 1; i++) {
         //   if (value.charAt(i) == '0')
         //     result++;
         //}
      }
      return result;
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
                        "8291" + separador + pasta + separador;
      this.criaDiretorio(dirDados);
      return dirDados;
   }
    
   @Override
   public void run() {
      int count = 0, i = 0;
      while (i < capitulos.length) {
         if (capitulo > 0)
           i = capitulo - 1;
         String origem = NozokiThread.URL_PREFIX + capitulos[i] + "/compressed/"; // + "013.jpg";
         String sufixo;
         if (sufixoJPG[i] == null)
            sufixo = ".jpg";
         else
            sufixo = sufixoJPG[i] + ".jpg";
         String origemFinal, ultimoReal = null;
         String nomeArq = prefixoJPG[i] + numeroJPG[i] + sufixo;
         int numero = 0;
         try {
            numero = Integer.parseInt(numeroJPG[i]);
         }catch(NumberFormatException num) {
            System.err.println("Erro ao converter número " + numeroJPG[i]);
            continue;
         }
         System.out.println("Buscando capítulo " + capitulos[i]);
         String numeroReal = null;
         int tolerancia = 0;
         for (int j = 0; j < NozokiThread.QTDE_PAG; j++) {
            if (j > 0) {
              numero++;
              if (qtdeDigitosAEsquerda(numeroJPG[i]) > 0)
                numeroReal = this.getPalavraFormatada(numero, this.qtdeDigitosAEsquerda(numeroJPG[i]));
              else
                numeroReal = String.valueOf(numero);
              nomeArq =  prefixoJPG[i] + numeroReal + sufixo;
            }
            origemFinal = origem + nomeArq;
            //System.out.println("Copiando " + origemFinal);
            if (j == 0)
              System.out.println("Destino " + defineDirDestino(capitulos[i]) + nomeArq);
            if (CopyURL.copy(origemFinal, defineDirDestino(capitulos[i]) + nomeArq)) {
              ultimoReal = numeroReal;
              count++;
            } else {
              tolerancia++;
              if (tolerancia > 1)
                break;
            }
         }
         if (numeroReal == null)
           numeroReal = numeroJPG[i];
         // busca créditos e notas com o último número de página
         nomeArq = prefixoJPG[i] + numeroReal + NozokiThread.CREDITO + sufixo;
         origemFinal = origem + nomeArq;
         if (CopyURL.copy(origemFinal, defineDirDestino(capitulos[i]) + nomeArq))
           count++;
         nomeArq = prefixoJPG[i] + numeroReal + NozokiThread.NOTE1 + sufixo;
         origemFinal = origem + nomeArq;
         if (CopyURL.copy(origemFinal, defineDirDestino(capitulos[i]) + nomeArq))
           count++;
         nomeArq = prefixoJPG[i] + numeroReal + NozokiThread.NOTE2 + sufixo;
         origemFinal = origem + nomeArq;
         if (CopyURL.copy(origemFinal, defineDirDestino(capitulos[i]) + nomeArq))
           count++;
         nomeArq = prefixoJPG[i] + numeroReal + NozokiThread.NOTE3 + sufixo;
         origemFinal = origem + nomeArq;
         if (CopyURL.copy(origemFinal, defineDirDestino(capitulos[i]) + nomeArq))
           count++;
         // busca créditos e notas com o número 999
         nomeArq = prefixoJPG[i] + "999" + NozokiThread.CREDITO + sufixo;
         origemFinal = origem + nomeArq;
         if (CopyURL.copy(origemFinal, defineDirDestino(capitulos[i]) + nomeArq))
           count++;
         nomeArq = prefixoJPG[i] + NozokiThread.NOTE1 + sufixo;
         origemFinal = origem + nomeArq;
         if (CopyURL.copy(origemFinal, defineDirDestino(capitulos[i]) + nomeArq))
           count++;
         nomeArq = prefixoJPG[i] + NozokiThread.NOTE2 + sufixo;
         origemFinal = origem + nomeArq;
         if (CopyURL.copy(origemFinal, defineDirDestino(capitulos[i]) + nomeArq))
           count++;
         nomeArq = prefixoJPG[i] + NozokiThread.NOTE3 + sufixo;
         origemFinal = origem + nomeArq;
         if (CopyURL.copy(origemFinal, defineDirDestino(capitulos[i]) + nomeArq))
           count++;
         
         //TODO: retirar este break quando estiver testado
         //if (1 == 1)
         //  break;
         if (capitulo > 0)
           break;
         i++;
      }
      System.out.println(count + " arquivos copiados.");
   }

}
