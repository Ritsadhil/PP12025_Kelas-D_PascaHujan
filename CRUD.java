import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class CRUD {
 public Produk[] listProduk;
     int indexArray = 0;
	    static StringBuilder sb = new StringBuilder();
	    static String[] dump;
	    Scanner sc = new Scanner(System.in);
	    static BufferedInputStream bf = null;
	    static FileInputStream fis = null;
	    static char eof = '.';
	    int maxIndex = 0;

 public void menu() {
		    System.out.println("Menu yang tersedia : ");
		    System.out.println("1. Tampil data ");
		    System.out.println("2. Tambah data ");
		    System.out.println("3. Hapus data ");
		    System.out.println("4. Ubah data ");
		    System.out.println("5. Cari data ");
		    System.out.println("99. Keluar ");
		}
	    
	    public void hitungJmlData() {
	        int data = -1;
	        char ch;
	        maxIndex = 0;
	        try {
	            fis = new FileInputStream("Produk.txt"); // Buka file
	            bf = new BufferedInputStream(fis);

	            if (bf != null) {
	                data = bf.read();
	                ch = (char) data;
	                while (data != -1 && ch != eof) { // Baca hingga akhir file
	                    if (ch == ' ') {
	                        maxIndex++;
	                    }
	                    data = bf.read();
	                    ch = (char) data;
	                }
	            }
	            dump = new String[maxIndex];
	            Produk p = new Produk();
	            bf.close();
	        } catch (IOException e) {
	            System.out.println("Error I/O");
	        }
	    }

	    public static void bacaFile() {
	        int data = -1;
	        char ch;
	        int index = 0;
	        try {
	            fis = new FileInputStream("Produk.txt");
	            bf = new BufferedInputStream(fis);
	            if (bf != null) {
	                data = bf.read();
	                ch = (char) data;
	                while (data != -1 && ch != eof) {
	                    if (ch != ' ') {
	                        sb.append(ch);
	                    } else {
	                        dump[index] = sb.toString();
	                        index++;
	                        sb = new StringBuilder();
	                    }
	                    data = bf.read();
	                    ch = (char) data;
	                }
	            }
	            bf.close();
	        } catch (IOException e) {
	            System.out.println("Error I/O");
	        }
	    }

	    public void ambilData() {
	        hitungJmlData();
	        bacaFile();
	        System.out.println("=============== Ambil Data Mahasiswa ===============");
	        String[] temp;

	        for (int a = 0; a < dump.length; a++) {
	            if (dump[a] != null && dump[a].contains("-")) { 
	                temp = dump[a].split("-"); 
	                if (temp.length == 4) { 
	                    listProduk[a] = new Produk();
	                    listProduk[a].setKode(temp[0].trim()); 
	                    listProduk[a].setNama(temp[1].trim());                
	                    listProduk[a].setKategori(temp[2].trim());         
	                    listProduk[a].setHarga(Integer.parseInt(temp[3].trim()));           
	                } else {
	                    System.out.println("Format data tidak valid pada indeks " + a + ": " + dump[a]);
	                }
	            } else {
	                System.out.println("Data kosong atau tidak sesuai format pada indeks " + a);
	            }
	        }
	        System.out.println("=============== Data Berhasil Diambil ===============");
	    }

}
