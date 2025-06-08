import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CRUD {
	private Node head = null;
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

	// Menambahkan di awal (head)
	public void addHead(Produk p) {
		Node newNode = new Node(p);
		newNode.setNext(head);
		head = newNode;
	}

	// Menambahkan di akhir (tail)
	public void addTail(Produk p) {
		Node newNode = new Node(p);
		if (head == null) {
			head = newNode;
		} else {
			Node current = head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(newNode);
		}
		tulisFile();
	}

	// Menambahkan di tengah (berdasarkan nama secara alfabet)
	public void addMid(Produk p) {
		Node newNode = new Node(p);
		if (head == null || p.getNama().compareToIgnoreCase(head.getData().getNama()) < 0) {
			addHead(p);
			return;
		}

		Node current = head;
		while (current.getNext() != null &&
				p.getNama().compareToIgnoreCase(current.getNext().getData().getNama()) > 0) {
			current = current.getNext();
		}

		newNode.setNext(current.getNext());
		current.setNext(newNode);
	}

	public void tulisFile() {
		try {
			FileWriter fw = new FileWriter("Produk.txt", false); // overwrite
			Node current = head; // pakai linked list, bukan array

			while (current != null) {
				Produk p = current.getData();
				fw.write(p.getKode() + "-" + p.getNama() + "-" + p.getHarga() + "-" + p.getKategori() + " ");
				current = current.getNext();
			}

			fw.write("."); // EOF marker
			fw.flush();
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
				if (bf != null)
					bf.close();
				if (fis != null)
					fis.close();
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
				if (bf != null)
					bf.close();
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				System.out.println("Gagal menutup file: " + e.getMessage());
			}
		}
	}

	public void ambilData() {
		hitungJmlData();
		bacaFile();
		head = null;

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

					// Tambahkan produk ke linked list
					addTail(p); // <= aktifkan ini
				}
			}
		}
		System.out.println("=============== Data Berhasil Diambil ===============");
	}

	public void display() {
		System.out.println("\n=============== Daftar Produk ===============");
		if (head == null) {
			System.out.println("Data kosong.");
			return;
		}

		Node current = head;
		int i = 1;
		while (current != null) {
			Produk p = current.getData();
			System.out.println(i + ". Kode    : " + p.getKode());
			System.out.println("   Nama    : " + p.getNama());
			System.out.println("   Kategori: " + p.getKategori());
			System.out.println("   Harga   : Rp" + p.getHarga());
			System.out.println("--------------------------------------------");
			current = current.getNext();
			i++;
		}
	}

}
