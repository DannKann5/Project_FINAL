public class belanjaMain {
    public static void main(String[] args) throws Exception {
        barangHarga chiki = new barangHarga("Chiki", "SN01", "Snack", 3000);
        barangHarga gelang = new barangHarga("Gelang", "AR01", "Aksesoris", 2000);
        barangHarga gtgkunci = new barangHarga("Gantungan Kunci", "AR02", "Aksesoris", 5000);
        barangHarga tehpucuk = new barangHarga("Teh Pucuk", "MN02", "Minuman", 5000);

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
