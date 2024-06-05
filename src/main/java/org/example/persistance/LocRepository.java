package org.example.persistance;

import org.example.domain.Administrator;
import org.example.domain.Loc;
import org.hibernate.Session;

public class LocRepository implements ILocRepository{

    @Override
    public void save(Loc loc) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(loc));
    }

    @Override
    public void delete(Integer id) {
        HibernateUtils.getSessionFactory().inTransaction(session -> {
            Loc loc = session.find(Loc.class, id);
            if (loc != null) {
                session.remove(loc);
            }
        });
    }

    @Override
    public Loc findOne(Integer id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from Loc where id=:idM ", Loc.class)
                    .setParameter("idM", id)
                    .getSingleResultOrNull();
        }
    }

    @Override
    public void update(Integer id, Loc newLoc) {
        HibernateUtils.getSessionFactory().inTransaction(session -> {
            Loc loc = session.find(Loc.class, id);
            if (loc != null) {
                loc.setPozitie(newLoc.getPozitie());
                loc.setNumar(newLoc.getNumar());
                loc.setPret(newLoc.getPret());
                loc.setStare(newLoc.getStare());
                loc.setNr_rezervare(newLoc.getNr_rezervare());
                session.merge(loc);
            }
        });
    }

    @Override
    public Iterable<Loc> getAll() {
        try( Session session=HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Loc ", Loc.class).getResultList();
        }
    }
}
