import java.util.Scanner;

public class belanjaMain {

    // Mencari barang berdasarkan kode, hasilnya null kalau tidak ketemu
    static barangHarga cariBarang(barangHarga[] daftar, String kode) {
        for (int i = 0; i < daftar.length; i++) {
            if (daftar[i].getKode().equalsIgnoreCase(kode)) {
                return daftar[i];
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Instansiasi barang
        barangHarga chiki = new barangHarga("Chiki", "SN01", "Snack", 3000, 24);
        barangHarga gelang = new barangHarga("Gelang", "AR01", "Aksesoris", 2000, 10);
        barangHarga gtgkunci = new barangHarga("Gantungan Kunci", "AR02", "Aksesoris", 5000, 10);
        barangHarga tehpucuk = new barangHarga("Teh Pucuk", "MN01", "Minuman", 5000, 24);
        barangHarga[] daftar = { chiki, gelang, gtgkunci, tehpucuk };

        // Objek fitur (dibuat SATU kali)
        Diskon diskon = new Diskon(0.0, 0.0);
        stokBarang stok = new stokBarang();

        System.out.println("=============== SELAMAT DATANG ===============");

        // Ukuran keranjang = jumlah jenis barang di toko (tidak perlu dipilih lagi)
        Keranjang keranjang = new Keranjang(daftar.length, diskon);

        // Catatan isi keranjang di main (dipakai saat checkout untuk mengurangi stok)
        String[] kodeKeranjang = new String[daftar.length];
        int[] jmlKeranjang = new int[daftar.length];
        int jumlahItem = 0;

        int pilihan = -1;
        while (pilihan != 0) {
            System.out.println("\n=============== MENU ===============");
            System.out.println("1. Lihat barang dan harga");
            System.out.println("2. Cek stok barang");
            System.out.println("3. Masukkan barang ke keranjang");
            System.out.println("4. Info diskon");
            System.out.println("5. Checkout");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = sc.nextInt();
            sc.nextLine();

            switch (pilihan) {

                case 1:
                    System.out.println("=============== DAFTAR BARANG ===============");
                    for (int i = 0; i < daftar.length; i++) {
                        System.out.println(daftar[i].getKode() + " | " + daftar[i].getNama()
                                + " | " + daftar[i].getKategori()
                                + " | Rp" + daftar[i].getHarga());
                    }
                    break;

                case 2:
                    System.out.println("=============== STOK BARANG ===============");
                    stok.tampilkanStok();
                    break;

                case 3:
                    System.out.print("Kode barang : ");
                    String kode = sc.nextLine();
                    barangHarga b = cariBarang(daftar, kode);

                    if (b == null) {
                        System.out.println("Kode barang tidak ditemukan.");
                        break;
                    }

                    System.out.print("Jumlah beli : ");
                    int jml = sc.nextInt();
                    sc.nextLine();

                    // cari: barang ini sudah ada di catatan keranjang? (-1 = belum)
                    int posisi = -1;
                    for (int i = 0; i < jumlahItem; i++) {
                        if (kodeKeranjang[i].equals(b.getKode())) {
                            posisi = i;
                        }
                    }

                    int sudahAda = 0;
                    if (posisi != -1) {
                        sudahAda = jmlKeranjang[posisi];
                    }
                    int sisa = stok.cekStok(b.getKode()) - sudahAda;

                    if (jml <= 0) {
                        System.out.println("Jumlah harus lebih dari 0.");
                    } else if (jml > sisa) {
                        System.out.println("Stok " + b.getNama() + " tidak cukup. Sisa yang bisa dibeli: " + sisa);
                    } else {
                        keranjang.tambahBarang(b, jml);
                        if (posisi == -1) {
                            // barang baru, pakai kotak baru
                            kodeKeranjang[jumlahItem] = b.getKode();
                            jmlKeranjang[jumlahItem] = jml;
                            jumlahItem++;
                        } else {
                            // barang sudah ada, tambah jumlahnya saja
                            jmlKeranjang[posisi] += jml;
                        }
                    }
                    break;

                case 4:
                    diskon.tampilkanDiskon();
                    break;

                case 5:
                    if (jumlahItem == 0) {
                        System.out.println("Keranjang masih kosong.");
                        break;
                    }

                    double totalBelanja = keranjang.hitungTotal();
                    double totalBayar = diskon.hitungTotalBayar(totalBelanja);

                    // STOK berkurang saat checkout
                    for (int i = 0; i < jumlahItem; i++) {
                        stok.kurangiStok(kodeKeranjang[i], jmlKeranjang[i]);
                    }

                    System.out.println("=============== STRUK ===============");
                    keranjang.tampilkan();
                    diskon.tampilkanDiskon(totalBelanja);
                    System.out.println("Total bayar   : Rp" + totalBayar);
                    System.out.println("Checkout berhasil. Terima kasih!");

                    System.out.println("\nSisa stok:");
                    stok.tampilkanStok();

                    // Kosongkan keranjang untuk belanja berikutnya
                    keranjang = new Keranjang(daftar.length, diskon);
                    jumlahItem = 0;
                    break;

                case 0:
                    System.out.println("Terima kasih sudah berbelanja.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}