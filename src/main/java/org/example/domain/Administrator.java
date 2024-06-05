package org.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name="administrator")
public class Administrator implements Entity<Integer>{

    private Integer id;
    private String numeuser,parola;

    public Administrator(){
        id=0;
        numeuser=parola="default";
    }

    public Administrator(String numeuser,String parola){
        this.numeuser=numeuser;
        this.parola=parola;
    }

    @Column(name = "numeuser")
    public String getNumeuser(){
        return numeuser;
    }

    public void setNumeuser(String numeuser) {
        this.numeuser = numeuser;
    }

    @Column(name = "parola")
    public String getParola(){
        return this.parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
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
        return "Administrator{" +
                "id=" + id +
                ", numeuser='" + numeuser + '\'' +
                ", parola='" + parola + '\'' +
                +
                '}';
    }
}
