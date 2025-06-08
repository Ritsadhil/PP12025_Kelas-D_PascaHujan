public class Produk {
	private int harga;
	private String kode, nama, kategori;

    public Produk() {
        this.harga = harga;
        this.kategori = kategori;
        this.kode = kode;
        this.nama = nama;
    }

	public int getHarga() {
		return harga;
	}
	public void setHarga(int harga) {
		this.harga = harga;
	}
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	 
}
