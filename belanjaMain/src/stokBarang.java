import java.util.ArrayList;
public class stokBarang {
    private ArrayList<String> kode = new ArrayList<>();
    private ArrayList<Integer> stok = new ArrayList<>();

    public stokBarang() {
        tambahBarang("SN01", 30);
        tambahBarang("AR01", 20);
        tambahBarang("AR02", 15);
        tambahBarang("MN01", 25);
    }

    public void tambahBarang(String kodeBarang, int jumlah) {
        kode.add(kodeBarang);
        stok.add(jumlah);
    }
    
    private int cariIndex(String kodeBarang) {
        for (int i = 0; i < kode.size(); i++) {
            if (kode.get(i).equals(kodeBarang)) {
                return i;
            }
        }
        return -1;
    }

    public int cekStok(String kodeBarang) {
        int i = cariIndex(kodeBarang);
        if (i == -1) {
            return 0;
        }
        return stok.get(i);
    }

    public boolean kurangiStok(String kodeBarang, int jumlahBeli) {
        int i = cariIndex(kodeBarang);
        if (i == -1 || stok.get(i) < jumlahBeli) {
            return false;
        }
        stok.set(i, stok.get(i) - jumlahBeli);
        return true;
    }

    public void tampilkanStok() {
        for (int i = 0; i < kode.size(); i++) {
            if (stok.get(i) == 0) {
                System.out.println(kode.get(i) + " : Habis");
            } else {
                System.out.println(kode.get(i) + " : " + stok.get(i));
            }
        }
    }
}