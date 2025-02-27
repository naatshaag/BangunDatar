package bangun_datar;

import java.sql.*;
import java.util.Scanner;

// Interface untuk Standarisasi Perhitungan
interface Hitung {
    double hitungLuas();
    double hitungKeliling();
}

// Kelas abstrak untuk Bangun Datar
abstract class BangunDatar implements Hitung {
    public abstract String getNama();
}

// Kelas Persegi
class Persegi extends BangunDatar {
    private double sisi;

    public Persegi(double sisi) {
        this.sisi = sisi;
    }

    @Override
    public double hitungLuas() {
        return sisi * sisi;
    }

    @Override
    public double hitungKeliling() {
        return 4 * sisi;
    }

    @Override
    public String getNama() {
        return "Persegi";
    }

    public double getSisi() {
        return sisi;
    }
}

// Kelas Persegi Panjang
class PersegiPanjang extends BangunDatar {
    private double panjang, lebar;

    public PersegiPanjang(double panjang, double lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
    }

    @Override
    public double hitungLuas() {
        return panjang * lebar;
    }

    @Override
    public double hitungKeliling() {
        return 2 * (panjang + lebar);
    }

    @Override
    public String getNama() {
        return "Persegi Panjang";
    }
}

// Kelas Lingkaran
class Lingkaran extends BangunDatar {
    private double radius;

    public Lingkaran(double radius) {
        this.radius = radius;
    }

    @Override
    public double hitungLuas() {
        return Math.PI * radius * radius;
    }

    @Override
    public double hitungKeliling() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String getNama() {
        return "Lingkaran";
    }
}
