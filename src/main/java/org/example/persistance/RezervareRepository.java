package org.example.persistance;

import org.example.domain.Administrator;
import org.example.domain.Loc;
import org.example.domain.Rezervare;
import org.hibernate.Session;

public class RezervareRepository implements IRezervareRepository{

    @Override
    public void save(Rezervare rezervare) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(rezervare));
    }

    @Override
    public void delete(Integer id) {
        HibernateUtils.getSessionFactory().inTransaction(session -> {
            Rezervare rezervare = session.find(Rezervare.class, id);
            if (rezervare != null) {
                session.remove(rezervare);
            }
        });
    }

    @Override
    public Rezervare findOne(Integer id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from Rezervare where id=:idM ", Rezervare.class)
                    .setParameter("idM", id)
                    .getSingleResultOrNull();
        }
    }

    @Override
    public void update(Integer id, Rezervare newRezervare) {
        HibernateUtils.getSessionFactory().inTransaction(session -> {
            Rezervare rezervare = session.find(Rezervare.class, id);
            if (rezervare != null) {
                rezervare.setNume(newRezervare.getNume());
                rezervare.setPrenume(newRezervare.getPrenume());
                rezervare.setTelefon(newRezervare.getTelefon());
                session.merge(rezervare);
            }
        });
    }

    @Override
    public Iterable<Rezervare> getAll() {
        try( Session session=HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Rezervare ", Rezervare.class).getResultList();
        }
    }

    @Override
    public Rezervare findByTelefon(String telefon) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Rezervare where telefon = :telefon", Rezervare.class)
                    .setParameter("telefon", telefon)
                    .uniqueResult();
        }
    }
}
