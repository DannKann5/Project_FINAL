public class barangHarga {
    private String nama;
    private String kode;
    private String kategori;
    private double harga;
    private int[] stok;

    public barangHarga(String namaBarang, String kodeBarang, String kategoriBarang, double hargaBarang) {
        nama = namaBarang;
        kode = kodeBarang;
        kategori = kategoriBarang;
        harga = hargaBarang;
    }

    public int[] getStok() {
        return stok;
    }

    public String getNama() {
        return nama;
    }

    public String getKode() {
        return kode;
    }

    public String getKategori() {
        return kategori;
    }

    public double getHarga() {
        return harga;
    }

}
