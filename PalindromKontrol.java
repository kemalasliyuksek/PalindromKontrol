import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PalindromKontrol {

    private static class MyStack {

        // stack'teki karakterleri tutan bir karakter dizisi oluşturur
        char[] charArray;

        // tersine çevrilmiş karakterleri tutan bir karakter dizisi oluşturur
        char[] tersArray;

        // şu anda bulunan karakter sayısı
        private int size;

        public MyStack(String satir) {
            // array'ı oluşturur
            this.charArray = new char[satir.length()];
            this.size = 0;
        }

        // stack'e bir karakter ekler (`charArray'in sonuna yerleştirir)
        public void push(char c) {
            charArray[size++] = c;
        }

        public void tersineCevir() {
            // array'ı tersine çevirir
            tersArray = new char[size];
            for (int i = 0, j = size - 1; i < size; i++, j--) {
                tersArray[i] = charArray[j];
            }
        }

        public String toString(char[] array) {
            // array'ı stringe dönüştürür
            return new String(array, 0, size);
        }
    }

    // text.txt den çekip String List oluşturur
    public static List<String> TxtCekArray(String txtName){
        try {
            // dosyayı açar
            FileInputStream dosya= new FileInputStream(txtName);
            // dosyayı satır satır okumak için BufferedReader nesnesi oluşturur
            BufferedReader fileRead = new BufferedReader(new InputStreamReader(dosya));
            // okunan her satırı saklamak için bir değişken
            String value="";
            // satırları saklamak için bir String listesi
            List<String> txtArray = new ArrayList<>();

            // dosya sonuna kadar satırları okur ve listeye ekler
            while((value = fileRead.readLine()) != null){
                txtArray.add(value);
            }
            // dosyayı kapatır
            fileRead.close();
            return txtArray;
        } catch (Exception e) {
            // bir hata oluşursa hata mesajı yazdırır
            e.printStackTrace();
        }
        // bata durumunda null döndürür
        return null;
    }
    public static  String replaceStr(String satir){
        // okunmaması gereken noktalama işaretleri ve boşluğu kaldırır; I harfini değiştirir
        satir = satir.replace(" ","")
                .replace("!","")
                .replace(".","")
                .replace("'","")
                .replace(",","")
                .replace("-","")
                .replace("”","")
                .replace("?","")
                .replace("I","i");

        return  satir;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // text.txt 'den çekip String List oluşturur
        List<String> satirlar = TxtCekArray("text.txt");

        // satırları tek tek işlemek için for döngüsü
        for (int i=0; i < satirlar.size(); i++){
            // for döngüsünde satirlari listden tek tek çeker
            String satir = satirlar.get(i);

            // tüm harfleri küçük harfe çevirir
            String yeniSatir = replaceStr(satir).toLowerCase();

            // MyStack class'ını çağırır
            MyStack stack = new MyStack(yeniSatir);

            // yeniSatir String'ini karakterlere böler
            for (char c : yeniSatir.toCharArray()) {
                // stack class'ındaki charArray'a yeniSatir karakterlerini push eder
                stack.push(c);
            }

            // charArray'ı tersine çevirir ve tersArray üretir
            stack.tersineCevir();

            // tersArray'ı String'e çevirir
            String tersSatir = stack.toString(stack.tersArray);

            // equals ile yeniSatir = tersSatir kontrol eder eğer eşitse true döndürür
            if (yeniSatir.equals(tersSatir)) {
                System.out.println("\"" + satir + "\" bir polindromdur.");
            } else {
                System.out.println("\"" + satir + "\" bir polindrom değildir!");
            }
        }
    }
}
