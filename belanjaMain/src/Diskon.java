public class Diskon {
    private double persentase;      
    private double minimalBelanja;  

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

}