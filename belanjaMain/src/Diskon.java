public class Diskon {
    private double persentase;
    private double minimalBelanja;

    public Diskon(double persentase, double minimalBelanja) {
        this.persentase = persentase;
        this.minimalBelanja = minimalBelanja;
    }

    public double getPersentase(double totalBelanja) {
        if (totalBelanja >= 300000) {
            return 20;
        } else if (totalBelanja >= 150000) {
            return 15;
        } else if (totalBelanja >= 100000) {
            return 10;
        } else if (totalBelanja >= 50000) {
            return 5;
        } else {
            return 0;
        }
    }

    // Hitung potongan harga (sekarang memakai persentase dari getPersentase)
    public double hitungDiskon(double totalBelanja) {
        double persen = getPersentase(totalBelanja);
        return totalBelanja * persen / 100;
    }

    // Hitung total yang harus dibayar
    public double hitungTotalBayar(double totalBelanja) {
        return totalBelanja - hitungDiskon(totalBelanja);
    }

    // versi 1: tanpa parameter (dipakai menu 5)
    public void tampilkanDiskon() {
        System.out.println("======================INFO DISKON======================");
        System.out.println("Belanja lebih dari 300.000 mendapatkan diskon 20%");
        System.out.println("Belanja lebih dari 150.000 mendapatkan diskon 15%");
        System.out.println("Belanja lebih dari 100.000 mendapatkan diskon 10%");
        System.out.println("Belanja lebih dari 50.000 mendapatkan diskon 5%");
    }

    // versi 2: pakai total belanja (dipakai saat checkout)
    public void tampilkanDiskon(double totalBelanja) {
        double persen = getPersentase(totalBelanja);

        System.out.println("======================INFO DISKON======================");
        if (persen == 0) {
            System.out.println("Total belanja belum mencapai Rp50.000, tidak mendapatkan diskon.");
        } else {
            System.out.println("Selamat! Kamu mendapatkan diskon " + persen + "%");
            System.out.println("Potongan : Rp" + hitungDiskon(totalBelanja));
        }
    }
}