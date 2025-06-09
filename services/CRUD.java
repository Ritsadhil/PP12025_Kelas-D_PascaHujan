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
	 public void addHead(Produk produk) {
        Node newNode = new Node(produk);
	if (head == null) {
		head = newNode;
	} else {
		newNode.setNext(head);
		head = newNode;
	}
    tulisFile();
    }

	// Menambahkan di akhir (tail)
	public void addTail(Produk p) {
		Node newNode = new Node(p);
		if (head == null) {
			head = newNode;
		} else {
			Node currNode = head;
			while (currNode.getNext() != null) {
				currNode = currNode.getNext();
			}
			currNode.setNext(newNode);
		}
		tulisFile();
	}

	// Menambahkan di tengah (berdasarkan nama secara alfabet)
 public void addMid(Produk data, int position) {
	Node posNode=null, currNode=null;
	int i;
	Node newNode = new Node(data);
	if (head == null) {
		head = newNode;
	} else {
		currNode = head;
		if(position == 1) {
			newNode.setNext(currNode);
			head = newNode;
		} else {
			i = 1;
					while(currNode != null && i < position) {
						posNode = currNode;
						currNode = currNode.getNext();
						i++;
					}
					posNode.setNext(newNode);
					newNode.setNext(currNode);
		}
	}
    tulisFile();
}

	public void tulisFile() {
		try {
			FileWriter fw = new FileWriter("Produk.txt", false); // overwrite
			Node currNode = head;

			while (currNode != null) {
				Produk p = currNode.getData();
				fw.write(p.getKode() + "-" + p.getNama() + "-" + p.getHarga() + "-" + p.getKategori() + " ");
				currNode = currNode.getNext();
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

					addTail(p);
				}
			}
		}
		System.out.println("=============== Data Berhasil Diambil ===============");
	}

	public void tambahData() {
		System.out.println("\n=== Tambah Data Produk ===");
		Produk produk = new Produk();

		System.out.print("Kode Produk  : ");
		produk.setKode(sc.nextLine().trim());

		System.out.print("Nama Produk  : ");
		produk.setNama(sc.nextLine().trim());

		System.out.print("Harga        : Rp");
		try {
			produk.setHarga(Integer.parseInt(sc.nextLine().trim()));
		} catch (NumberFormatException e) {
			System.out.println("Harga harus berupa angka!");
			return;
		}

		System.out.print("Kategori     : ");
		produk.setKategori(sc.nextLine().trim());

		 System.out.print("Tambahkan di awal list(1), Akhir list(2), pilih di urutan yang kamu mau(3)");
    int pilihan = sc.nextInt();
    sc.nextLine();
    
    if (pilihan == 1) {
        addHead(produk);  
    } if (pilihan == 2) {
        addTail(produk); 
    } else {
		addMid(sb);
	}
		System.out.println("Data berhasil ditambahkan!");
	}


	public void display() {
		System.out.println("\n=============== Daftar Produk ===============");
		if (head == null) {
			System.out.println("Data kosong.");
			return;
		}

		Node currNode = head;
		int i = 1;
		while (currNode != null) {
			Produk p = currNode.getData();
			System.out.println(i + ". Kode    : " + p.getKode());
			System.out.println("   Nama    : " + p.getNama());
			System.out.println("   Kategori: " + p.getKategori());
			System.out.println("   Harga   : Rp" + p.getHarga());
			System.out.println("--------------------------------------------");
			currNode = currNode.getNext();
			i++;
		}
	}

	public void hapusData() {
		System.out.println("\n=== Hapus Data Produk ===");
		System.out.print("Masukkan kode produk yang ingin dihapus: ");
		String kode = sc.nextLine().trim();

		if (head == null) {
			System.out.println("Data kosong.");
			return;
		}

		Node currNode = head;
		Node prevNode = null;

		while (currNode != null && !currNode.getData().getKode().equalsIgnoreCase(kode)) {
			prevNode = currNode;
			currNode = currNode.getNext();
		}

		if (currNode == null) {
			System.out.println("Data dengan kode '" + kode + "' tidak ditemukan.");
			return;
		}

		if (prevNode == null) {
			head = currNode.getNext(); // Hapus head
		} else {
			prevNode.setNext(currNode.getNext()); // Hapus node tengah atau tail
		}

		System.out.println("Data dengan kode '" + kode + "' berhasil dihapus.");
		tulisFile();
	}








}