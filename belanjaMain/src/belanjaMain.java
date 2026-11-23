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

        // Opening + ukuran keranjang
        System.out.println("=============== SELAMAT DATANG ===============");
        int kapasitas = 0;
        while (kapasitas == 0) {
            System.out.print("Pilih ukuran keranjang (besar/sedang/kecil): ");
            String ukuran = sc.nextLine();
            kapasitas = Keranjang.ukuranKeranjang(ukuran);
            if (kapasitas == 0) {
                System.out.println("Ukuran invalid! harap masukkan ukuran dengan benar (besar/sedang/kecil)");
            }
        }

        Keranjang keranjang = new Keranjang(kapasitas, diskon);

        // Catatan isi keranjang di main (dipakai saat checkout untuk mengurangi stok)
        String[] kodeKeranjang = new String[kapasitas];
        int[] jmlKeranjang = new int[kapasitas];
        int jumlahItem = 0;

        int pilihan = -1;
        while (pilihan != 0) {
            System.out.println("\n=============== MENU ===============");
            System.out.println("1. Lihat barang dan harga");
            System.out.println("2. Cek stok barang");
            System.out.println("3. Masukkan barang ke keranjang");
            System.out.println("4. Lihat keranjang");
            System.out.println("5. Info diskon");
            System.out.println("6. Checkout");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = sc.nextInt();
            sc.nextLine();

            switch (pilihan) {

                case 1:
                    // LIHAT BARANG DAN HARGA
                    System.out.println("=============== DAFTAR BARANG ===============");
                    for (int i = 0; i < daftar.length; i++) {
                        System.out.println(daftar[i].getKode() + " | " + daftar[i].getNama()
                                + " | " + daftar[i].getKategori()
                                + " | Rp" + daftar[i].getHarga());
                    }
                    break;

                case 2:
                    // CEK STOK
                    System.out.println("=============== STOK BARANG ===============");
                    stok.tampilkanStok();
                    break;

                case 3:
                    // MASUKKAN KE KERANJANG
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

                    if (jml <= 0) {
                        System.out.println("Jumlah harus lebih dari 0.");
                    } else if (jumlahItem >= kapasitas) {
                        System.out.println("Keranjang penuh.");
                    } else if (stok.cekStok(b.getKode()) < jml) {
                        System.out.println("Stok " + b.getNama() + " tidak cukup. Sisa stok: "
                                + stok.cekStok(b.getKode()));
                    } else {
                        keranjang.tambahBarang(b, jml);
                        kodeKeranjang[jumlahItem] = b.getKode();
                        jmlKeranjang[jumlahItem] = jml;
                        jumlahItem++;
                    }
                    break;

                case 4:
                    // LIHAT KERANJANG
                    keranjang.tampilkan();
                    break;

                case 5:
                    // INFO DISKON
                    diskon.tampilkanDiskon();
                    break;

                case 6:
                    // CHECKOUT
                    if (jumlahItem == 0) {
                        System.out.println("Keranjang masih kosong.");
                        break;
                    }

                    double totalBelanja = keranjang.hitungTotal();

                    // DISKON dihitung di sini, setelah total belanja diketahui
                    double persentase = diskon.getPersentase(totalBelanja);
                    double potongan = diskon.hitungDiskon(totalBelanja);
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
                    keranjang = new Keranjang(kapasitas, diskon);
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