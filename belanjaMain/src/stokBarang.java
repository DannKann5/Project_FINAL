public class stokBarang {
    private String[] kode = { "SN01", "AR01", "AR02", "MN02" };
    private static int[] stok = { 10, 8, 5, 2 };

    private int cariIndex(String kodeBarang) {
        for (int i = 0; i < kode.length; i++) {
            if (kode[i].equals(kodeBarang)) {
                return i;
            }
        }
        return -1;
    }

    public static int[] getStok() {
        return stok;
    }

    public int cekStok(String kodeBarang) {
        int i = cariIndex(kodeBarang);
        if (i == -1) {
            return 0;
        }
        return stok[i];
    }

    public boolean kurangiStok(String kodeBarang, int jumlahBeli) {
        int i = cariIndex(kodeBarang);
        if (i == -1 || stok[i] < jumlahBeli) {
            return false;
        }
        stok[i] = stok[i] - jumlahBeli;
        return true;
    }

    public void tampilkanStok() {
        for (int i = 0; i < kode.length; i++) {
            if (stok[i] == 0) {
                System.out.println(kode[i] + " : Habis");
            } else {
                System.out.println(kode[i] + " : " + stok[i]);
            }
        }
    }
}