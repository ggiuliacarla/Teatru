package org.example.domain;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;

@jakarta.persistence.Entity
@Table(name="loc")
public class Loc implements Entity<Integer> {

    private Integer id,pozitie,numar,nr_rezervare;
    private Float pret;
    private Stare stare;

    public Loc(){
        id=pozitie=numar=nr_rezervare=0;
        pret=0F;
        stare=Stare.LIBER;
    };

    public Loc(Integer pozitie,Integer numar,Integer nr_rezervare,Float pret,Stare stare){
        this.stare=stare;
        this.pret=pret;
        this.nr_rezervare=nr_rezervare;
        this.pozitie=pozitie;
        this.numar=numar;
    }

    @Column(name = "numar")
    public Integer getNumar() {
        return numar;
    }

    public void setNumar(Integer numar) {
        this.numar = numar;
    }

    @Column(name = "pozitie")
    public Integer getPozitie(){
        return pozitie;
    }

    public void setPozitie(Integer pozitie) {
        this.pozitie = pozitie;
    }

    @Column(name = "pret")
    public Float getPret(){
        return pret;
    }

    public void setPret(Float pret) {
        this.pret = pret;
    }

    @Column(name = "stare")
    public Stare getStare(){
        return stare;
    }

    public void setStare(Stare stare) {
        this.stare = stare;
    }

    @Column(name = "nr_rezervare")
    public Integer getNr_rezervare(){
        return nr_rezervare;
    }

    public void setNr_rezervare(Integer nr_rezervare) {
        this.nr_rezervare = nr_rezervare;
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
    public String toString() {
        return "Loc{" +
                "id=" + id +
                ", numar=" + numar +
                ", pozitie=" + pozitie +
                ", pret=" + pret +
                ", stare='" + stare + '\'' +
                ", nr_rezervare=" + nr_rezervare +
                '}';
    }

}
