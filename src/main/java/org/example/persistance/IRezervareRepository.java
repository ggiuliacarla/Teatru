package org.example.persistance;

import org.example.domain.Rezervare;

public interface IRezervareRepository extends ICrudRepository<Integer, Rezervare> {
    public Rezervare findByTelefon(String s);
}
