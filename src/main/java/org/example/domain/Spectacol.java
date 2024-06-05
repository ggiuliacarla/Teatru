package org.example.domain;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@jakarta.persistence.Entity
@Table(name = "spectacol")
public class Spectacol implements Entity<Integer>{

    private Integer id;
    private String nume,gen;
    private Integer pret;
    private Float durata;
    private String data;

    public Spectacol(){
        id=0;
        nume=gen="default";
        pret=0;
        durata=0F;
        data="default";
    }

    public Spectacol(String nume,String gen,Integer pret,Float durata,String data){
        this.durata=durata;
        this.pret=pret;
        this.nume=nume;
        this.gen=gen;
        this.data=data;
    }

    @Column(name="nume")
    public String getNume(){
        return this.nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Column(name = "gen")
    public String getGen(){
        return this.gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    @Column(name="pret")
    public Integer getPret(){
        return this.pret;
    }

    public void setPret(Integer pret) {
        this.pret = pret;
    }

    @Column(name="durata")
    public Float getDurata(){
        return this.durata;
    }

    public void setDurata(Float durata) {
        this.durata = durata;
    }

    @Column(name = "data")
    public String getData(){
       return this.data;
    }

    public void setData(String data) {
        this.data = data;
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
        return "Spectacol{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", gen='" + gen + '\'' +
                ", pret" +pret+'\''+
                ", durata"+durata+'\''+
                ", data"+data+'\''+
                        '}';
    }
}
