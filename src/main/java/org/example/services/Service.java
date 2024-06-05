package org.example.services;

import org.example.domain.*;
import org.example.persistance.IAdministratorRepository;
import org.example.persistance.ILocRepository;
import org.example.persistance.IRezervareRepository;
import org.example.persistance.ISpectacolRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Service {
    private IAdministratorRepository administratorRepository;
    private ISpectacolRepository spectacolRepository;

    private ILocRepository locRepository;

    private IRezervareRepository rezervareRepository;
    public Service (IAdministratorRepository administratorRepository,ISpectacolRepository spectacolRepository,ILocRepository locRepository,IRezervareRepository rezervareRepository){
        this.administratorRepository=administratorRepository;
        this.spectacolRepository=spectacolRepository;
        this.locRepository=locRepository;
        this.rezervareRepository=rezervareRepository;
    }

    public Administrator findUser(String nume, String pass){
        try {
            return administratorRepository.logIn(nume,pass);
        }
        catch (Exception ex){
            return null;
        }
    }

    public Spectacol addSpectacol(Spectacol spectacol){
        try {
            spectacolRepository.save(spectacol);
        }
        catch (Exception ex){
            return null;
        }
        return spectacol;
    }

    public Spectacol findSpectacolAzi() {
        Iterable<Spectacol> toateSpectacolele = spectacolRepository.getAll();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Spectacol s : toateSpectacolele) {
            String dataString = s.getData();  // assuming getData() returns the date as a string
            LocalDate spectacolDate = LocalDate.parse(dataString.substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (spectacolDate.equals(today)) {
                return s;
            }
        }
        return null;
    }

    public String getSeatDetailsById(int seatId) {
        Loc loc=locRepository.findOne(seatId);
        return loc.toString();
    }

    public boolean isReservationExistsForPhoneNumber(String telefon){
        // Obțineți toate rezervările
        Iterable<Rezervare> rezervariIterable = rezervareRepository.getAll();

        // Parcurgeți iterabilul de rezervări și verificați dacă există o rezervare cu numărul de telefon specificat
        for (Rezervare rezervare : rezervariIterable) {
            if (rezervare.getTelefon().equals(telefon)) {
                return true; // Se găsește o rezervare cu numărul de telefon specificat
            }
        }
        return false; // Nu se găsește nicio rezervare cu numărul de telefon specificatt
    }

    public Rezervare addRezervare(String Nume,String Prenume,String Telefon){
        Rezervare rezervare=new Rezervare(Nume,Prenume,Telefon);
        rezervareRepository.save(rezervare);
        return rezervareRepository.findByTelefon(Telefon);
    }

    public void makeReservation(Integer seatId,Integer rezervareId){
        Loc locVechi=locRepository.findOne(seatId);
        Loc loc=new Loc(locVechi.getPozitie(),locVechi.getNumar(),rezervareId,locVechi.getPret(), Stare.REZERVAT);
        locRepository.update(seatId,loc);
    }

    public boolean deleteReservationByTelefon(String telefon) {
        Rezervare rezervare = rezervareRepository.findByTelefon(telefon);
        Iterable<Loc>locuri=locRepository.getAll();
        for(Loc loc:locuri){
            if (loc.getNr_rezervare()!=null && loc.getNr_rezervare().equals(rezervare.getId())){
                Loc locNou=new Loc(loc.getPozitie(),loc.getNumar(),null,loc.getPret(), Stare.LIBER);
                locRepository.update(loc.getId(),locNou);
            }
        }
        if (rezervare != null) {
            rezervareRepository.delete(rezervare.getId());
            return true;
        }
        return false;
    }


}
