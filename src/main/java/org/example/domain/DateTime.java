package org.example.domain;

public class DateTime {
    private int min;
    private int ora;
    private int zi;
    private int luna;
    private int an;

    // Constructor
    public DateTime(int min, int ora, int zi, int luna, int an) {
        this.min = min;
        this.ora = ora;
        this.zi = zi;
        this.luna = luna;
        this.an = an;
    }

    // Metode pentru setarea și accesarea valorilor
    public void setMin(int min) {
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public int getOra() {
        return ora;
    }

    public void setZi(int zi) {
        this.zi = zi;
    }

    public int getZi() {
        return zi;
    }

    public void setLuna(int luna) {
        this.luna = luna;
    }

    public int getLuna() {
        return luna;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public int getAn() {
        return an;
    }
}
