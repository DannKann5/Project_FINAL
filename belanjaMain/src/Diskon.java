public class Diskon {
    private double persentase;      
    private double minimalBelanja;  

    public Diskon(double persentase, double minimalBelanja) {
        this.persentase = persentase;
        this.minimalBelanja = minimalBelanja;
    }
    public double getPersentase(double totalBelanja) {
        if(totalBelanja >= 300000) {
            return 20;
        } else if(totalBelanja >= 150000) {
            return 15;
        } else if(totalBelanja >= 100000) {
            return 10;
        } else if( totalBelanja >= 50000) {
            return 5;
        } else {
            return 0;
        }
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