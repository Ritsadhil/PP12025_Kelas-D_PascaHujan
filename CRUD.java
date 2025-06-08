import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CRUD {
 public Produk[] listProduk;
    private Node HEAD;
    private static final char EOF = '.';
    private static String[] dump;
    private static final String FILE_NAME = "Produk.txt";
    private static StringBuilder sb = new StringBuilder();
    private Scanner sc = new Scanner(System.in);
    static BufferedInputStream bf = null;
    static FileInputStream fis = null;
    int maxIndex = 0;

    public void menu() {
        System.out.println("Menu yang tersedia : ");
        System.out.println("1. Tambah data ");
        System.out.println("2. Tampil data ");
        System.out.println("3. Hapus data ");
        System.out.println("4. Ubah data ");
        System.out.println("5. Cari data ");
        System.out.println("99. Keluar ");
    }

     public void tulisFile() {
        try {
            FileWriter fw = new FileWriter("Produk.txt", false);
            Node currNode = HEAD;
            while (currNode != null) {
                Produk p = currNode.getData();
                fw.write(p.getKode() + "-" + p.getNama() + "-" + 
                        p.getHarga() + "-" + p.getKategori() + " ");
                        currNode = currNode.getNext();
            }
            fw.write(".");
            fw.close();
        } catch (IOException io) {
            System.out.println("Error IO");
        }
    }

    public void hitungJmlData() {
        int data;
        char ch;
        maxIndex = 0;

        try {
            fis = new FileInputStream(FILE_NAME);
            bf = new BufferedInputStream(fis);

            while ((data = bf.read()) != -1 && (ch = (char) data) != EOF) {
                if (ch == ' ') {
                    maxIndex++;
                }
            }

            dump = new String[maxIndex];
        } catch (IOException e) {
            System.out.println("Error membaca file: " + e.getMessage());
        } finally {
            try {
                if (bf != null) bf.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                System.out.println("Gagal menutup file: " + e.getMessage());
            }
        }
    }
    public static void bacaFile() {
        int data;
        char ch;
        int index = 0;
        sb = new StringBuilder();

        try {
            fis = new FileInputStream(FILE_NAME);
            bf = new BufferedInputStream(fis);

            while ((data = bf.read()) != -1 && (ch = (char) data) != EOF) {
                if (ch != ' ') {
                    sb.append(ch);
                } else {
                    dump[index++] = sb.toString();
                    sb = new StringBuilder();
                }
            }
        } catch (IOException e) {
            System.out.println("Error membaca file: " + e.getMessage());
        } finally {
            try {
                if (bf != null) bf.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                System.out.println("Gagal menutup file: " + e.getMessage());
            }
        }
    }
    public void ambilData() {
        hitungJmlData();
        bacaFile();
        HEAD = null;

        System.out.println("=============== Ambil Data Produk ===============");
        for (String item : dump) {
            if (item != null && item.contains("-")) {
                String[] temp = item.split("-");
                if (temp.length == 4) {
                    Produk p = new Produk();
                    p.setKode(temp[0].trim());
                    p.setNama(temp[1].trim());
                    p.setHarga(Integer.parseInt(temp[2].trim()));
                    p.setKategori(temp[3].trim());
                    addTail(p);
                }
            }
        }
        System.out.println("=============== Data Berhasil Diambil ===============");
    }

 

   

}
