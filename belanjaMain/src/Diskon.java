public class Diskon {

    // Atribut
    private double persentase;      // contoh: 10 artinya 10%
    private double minimalBelanja;  // syarat minimal belanja

    // Constructor
    public Diskon(double persentase, double minimalBelanja) {
        this.persentase = persentase;
        this.minimalBelanja = minimalBelanja;
    }

    // Hitung potongan harga
    public double hitungDiskon(double totalBelanja) {
        if (totalBelanja >= minimalBelanja) {
            return totalBelanja * persentase / 100;
        } else {
            return 0;
        }
    }

    // Hitung total yang harus dibayar
    public double hitungTotalBayar(double totalBelanja) {
        return totalBelanja - hitungDiskon(totalBelanja);
    }

    // Contoh uji coba (hapus saat digabung ke project)
    public static void main(String[] args) {
        Diskon diskon = new Diskon(10, 100000);

        double totalBelanja = 350000;

        System.out.println("Total belanja : Rp" + totalBelanja);
        System.out.println("Diskon        : Rp" + diskon.hitungDiskon(totalBelanja));
        System.out.println("Total bayar   : Rp" + diskon.hitungTotalBayar(totalBelanja));
    }
}