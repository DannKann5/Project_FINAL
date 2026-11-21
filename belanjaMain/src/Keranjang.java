

public class Keranjang {
    private barangHarga barang;
    private int jumlah;
    private String ukuran;

    public Keranjang(barangHarga barang, int jumlah) {
        this.barang = barang;
        this.jumlah = jumlah;
    }

    public String getNama() {
        return barang.getNama();
    }

    public barangHarga getBarang() {
        return barang;
    }
    
    public int getJumlah() {
        return jumlah;
    }

    public double subTotal() {
        return barang.getHarga() * jumlah;
    }

    public static int ukuranKeranjang(String ukuran) {
        if (ukuran.equalsIgnoreCase("Besar")) {
            return 10;
        } else if (ukuran.equalsIgnoreCase("Sedang")) {
            return 8;
        } else if (ukuran.equalsIgnoreCase("Kecil")){
            return 5;
        } else {
            return 0;
        }
    }

}
