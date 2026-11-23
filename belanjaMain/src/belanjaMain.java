import java.util.Scanner;

public class belanjaMain {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Diskon diskon = new Diskon(0.0, 0.0);
        double persentase = diskon.getPersentase(totalBelanja);

        //Instansiasi beberapa barang
        barangHarga chiki = new barangHarga("Chiki", "SN01", "Snack", 3000, 24);
        barangHarga gelang = new barangHarga("Gelang", "AR01", "Aksesoris", 2000, 10);
        barangHarga gtgkunci = new barangHarga("Gantungan Kunci", "AR02", "Aksesoris", 5000, 10);
        barangHarga tehpucuk = new barangHarga("Teh Pucuk", "MN02", "Minuman", 5000, 24);

        //Opening + Ukuran Keranjang
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

        //Set Array
        Keranjang[] keranjang = new Keranjang[kapasitas];

        //STTOK BARANG
        stokBarang stok = new stokBarang();

        System.out.println(chiki.getNama() + " stok : " + stok.cekStok(chiki.getKode()));

        if (stok.kurangiStok(chiki.getKode(), 3)) {
            System.out.println("Checkout berhasil");
        } else {
            System.out.println("Stok barang tidak cukup");
        }

        stok.tampilkanStok();
    }
}
