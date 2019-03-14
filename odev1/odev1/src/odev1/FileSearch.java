/**  
 *  
 * @author Ali Tüzün ali.tuzun11@gmail.com  
 * @author Yasin Ayvaz
 * @since 10.03.2018
 * <p>  
 *  Hocam dosya uzantısını verirken hata yapmış olabiliriz. 
 * </p>  
 */
package odev1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Ali
 */
public class FileSearch {

    public void parseFile(String fileName,String[] liste) throws FileNotFoundException{
        String[] parametre={"int","boolean","char","String","double","float"};
        String[] dturu={"int","boolean","void","String ","char"};
        String str=new String("{");
        String str1=new String(" ");
        String karakter=new String(";");
        int alteleman=0,fonksiyonlar=0,parametres=0;
        String ara,ara1,ara2,ara3;
        int par,par1=0;
        String parca,parca1;
        Scanner scan = new Scanner(new File(fileName));
        while(scan.hasNext()){
            String line = scan.nextLine();
            for (String liste1 : liste) {
                if (line.contains(liste1)) {
                    if(line.indexOf("(")==-1){// "(" katarı yoksa kesinlikle classdır diyip yazdırıyoruz
                    ara=line.replace(str,str1);
                    ara1=ara.replace(liste1,str1);
                    if(line.indexOf(";")!=-1){ //";" varsa alt elemandır.
                        for(String parametre1:parametre){
                        if(line.contains(parametre1)){
                        ara=line.replace(karakter,str1);
                        ara1=ara.replace(liste1,str1);
                        ara2=ara1.replace(parametre1, str1);
                        System.out.println(ara2+"- "+parametre1);
                        }
                        }
                    parametres++;    
                    break;
                    }
                    System.out.println("Sınıf Adı:"+ara1);
                    }
                    else // "(" varsa fonksiyondur.
                    {
                        System.out.println("------------------------------");
                        ara=line.replace(str,str1);
                        ara1=ara.replace(liste1,str1);
                        par=ara1.indexOf('(');
                        par1=ara1.indexOf(')');
                        parca=ara1.substring(par+1, par1);// "()" içerisindeki ifadeyi aldık o bizim parametremiz
                        ara2=ara1.substring(0,par);//satır başında "(" ifadeye kadar olan yeri aldık böylece parametre kısmından kurtulduk
                        
                        for(String dturu1:dturu){
                            if(ara2.indexOf(dturu1)==-1){
                            System.out.println(ara2);
                            
                            }
                            break;
                        }
                        for(String dturu1:dturu){//dönüş türünü yazdırdık.
                          if(ara2.contains(dturu1)){
                            ara3=ara2.replace(dturu1,str1);
                            System.out.println(ara3);
                            System.out.println("  Dönüş Türü:"+dturu1);
                            break;
                            }
                        }
                        for(String parametre1:parametre){//parametreyi yazdırdık.
                        alteleman++;
                        if(line.contains(parametre1)){
                            parca1=parca.replace(parametre1,str1);
                            System.out.println("  Aldığı parametre:"+alteleman);
                            System.out.println(parca1+"-"+parametre1);
                        }
                        else{
                            System.out.println("  Aldığı parametre:"+"0");
                        }
                        alteleman=0;
                        break;
                        }
                        fonksiyonlar++;
                    }
                    
                    break;
                }
            }
           
        }
             System.out.println("---------------------");
             System.out.println("üye fonksiyonlar:"+fonksiyonlar+"adet");
             System.out.println("alt elemanlar:"+parametres+"adet");
    }
  
    public static void main(String[] args) throws FileNotFoundException{
        FileSearch fileSearch = new FileSearch();
        String filePath = "..\\odev1\\Program.java";//dosya uzantısı
        String[] liste = {"public class","private","public"};
        fileSearch.parseFile(filePath,liste);
    }
    
}