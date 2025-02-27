package bangun_datar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database.initDB();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Hitung dan Simpan Bangun Datar");
            System.out.println("2. Tampilkan Data Bangun Datar");
            System.out.println("3. Hapus Data Bangun Datar");
            System.out.println("4. Update Data Bangun Datar");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            String pilihan = scanner.next();

            if (pilihan.equals("1")) {
                System.out.println("\nPilih Bangun Datar:");
                System.out.println("1. Persegi");
                System.out.println("2. Persegi Panjang");
                System.out.println("3. Lingkaran");
                System.out.print("Pilih: ");
                String jenis = scanner.next();

                BangunDatar bangunDatar = null;
                double dimensi1 = 0, dimensi2 = 0;
                String namaBangun = "";

                try {
                    if (jenis.equals("1")) {
                        System.out.print("Masukkan sisi: ");
                        dimensi1 = scanner.nextDouble();
                        bangunDatar = new Persegi(dimensi1);
                        namaBangun = "Persegi";
                    } else if (jenis.equals("2")) {
                        System.out.print("Masukkan panjang: ");
                        dimensi1 = scanner.nextDouble();
                        System.out.print("Masukkan lebar: ");
                        dimensi2 = scanner.nextDouble();
                        bangunDatar = new PersegiPanjang(dimensi1, dimensi2);
                        namaBangun = "Persegi Panjang";
                    } else if (jenis.equals("3")) {
                        System.out.print("Masukkan radius: ");
                        dimensi1 = scanner.nextDouble();
                        bangunDatar = new Lingkaran(dimensi1);
                        namaBangun = "Lingkaran";
                    } else {
                        System.out.println("Pilihan tidak valid!");
                        continue;
                    }

                    System.out.printf("\n%s - Luas: %.2f, Keliling: %.2f\n", namaBangun, bangunDatar.hitungLuas(), bangunDatar.hitungKeliling());
                    Database.simpanKeDB(namaBangun, dimensi1, dimensi2, bangunDatar.hitungLuas(), bangunDatar.hitungKeliling());
                } catch (Exception e) {
                    System.out.println("Input tidak valid! Harap masukkan angka.");
                    scanner.nextLine();
                }
            } else if (pilihan.equals("2")) {
                Database.tampilkanData();
            } else if (pilihan.equals("3")) {
                System.out.print("Masukkan ID data yang akan dihapus: ");
                int id = scanner.nextInt();
                Database.hapusData(id);
            } else if (pilihan.equals("4")) {
                System.out.print("Masukkan ID data yang akan diperbarui: ");
                int id = scanner.nextInt();
                System.out.print("Masukkan nama baru: ");
                String namaBaru = scanner.next();
                System.out.print("Masukkan dimensi 1 baru: ");
                double dimensi1 = scanner.nextDouble();
                System.out.print("Masukkan dimensi 2 baru: ");
                double dimensi2 = scanner.nextDouble();
                System.out.print("Masukkan luas baru: ");
                double luas = scanner.nextDouble();
                System.out.print("Masukkan keliling baru: ");
                double keliling = scanner.nextDouble();
                Database.updateData(id, namaBaru, dimensi1, dimensi2, luas, keliling);
            } else if (pilihan.equals("5")) {
                System.out.println("Terima kasih telah menggunakan program ini!");
                break;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }
}
