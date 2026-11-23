public class Keranjang {
    private barangHarga[] barang;
    private int[] jumlah;
    private int jumlahItem = 0;
    private Diskon diskon;

    // jenisBarang = banyaknya jenis barang yang dijual toko
    public Keranjang(int jenisBarang, Diskon diskon) {
        barang = new barangHarga[jenisBarang];
        jumlah = new int[jenisBarang];
        this.diskon = diskon;
    }

    public void tambahBarang(barangHarga b, int jml) {
        if (jml <= 0) {
            System.out.println("Jumlah harus lebih dari 0.");
            return;
        }

        // kalau barang sudah ada di keranjang, cukup tambah jumlahnya
        for (int i = 0; i < jumlahItem; i++) {
            if (barang[i] == b) {
                jumlah[i] += jml;
                System.out.println("Jumlah " + b.getNama() + " di keranjang jadi " + jumlah[i]);
                return;
            }
        }

        // barang belum ada, pakai kotak baru
        barang[jumlahItem] = b;
        jumlah[jumlahItem] = jml;
        jumlahItem++;
        System.out.println(b.getNama() + " masuk ke keranjang.");
    }

    public double hitungTotal() {
        double total = 0;
        for (int i = 0; i < jumlahItem; i++) {
            total += barang[i].getHarga() * jumlah[i];
        }
        return total;
    }

    public void tampilkan() {
        if (jumlahItem == 0) {
            System.out.println("Keranjang masih kosong.");
            return;
        }
        double total = hitungTotal();
        System.out.println("=============== ISI KERANJANG ===============");
        for (int i = 0; i < jumlahItem; i++) {
            System.out.println((i + 1) + ". " + barang[i].getNama() + " | " + jumlah[i] + " x "
                    + barang[i].getHarga() + " = " + (barang[i].getHarga() * jumlah[i]));
        }
        System.out.println("Total          : " + total);
        System.out.println("Diskon         : " + diskon.hitungDiskon(total));
        System.out.println("Total dibayar  : " + diskon.hitungTotalBayar(total));
    }
}