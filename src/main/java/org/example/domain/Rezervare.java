package org.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name="rezervare")
public class Rezervare implements Entity<Integer> {
    private Integer id;
    private String nume,prenume,telefon;

    public Rezervare(){
        id=0;
        nume=prenume=telefon="default";}


    public Rezervare(String nume,String prenume,String telefon){
        this.nume=nume;
        this.telefon=telefon;
        this.prenume=prenume;
    }

    @Column(name = "nume")
    public String getNume(){
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Column(name = "prenume")
    public String getPrenume(){
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    @Column(name = "telefon")
    public String getTelefon(){
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public void setId(Integer id) {
        this.id=id;
    }

    @Override
    @Id
    @GeneratedValue(generator = "increment")
    public Integer getId() {
        return id;
    }

    @Override
    public String
    toString() {
        return "Rezervare{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", telefon" +telefon+'\''+
                '}';
    }
}
