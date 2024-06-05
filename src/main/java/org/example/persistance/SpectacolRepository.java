package org.example.persistance;

import org.example.domain.Spectacol;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SpectacolRepository implements ISpectacolRepository{
    @Override
    public void save(Spectacol spectacol) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(spectacol));
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Spectacol findOne(Integer id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from Spectacol where id=:idM ", Spectacol.class)
                    .setParameter("idM", id)
                    .getSingleResultOrNull();
        }
    }


    @Override
    public void update(Integer integer, Spectacol spectacol) {

    }

    @Override
    public Iterable<Spectacol> getAll() {
        try( Session session=HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Spectacol ", Spectacol.class).getResultList();
        }
    }

    // Metodă pentru a converti un șir de caractere în LocalDateTime
    private LocalDateTime parseStringToLocalDateTime(String dateString) {
        // Definim formatul în care este stocată data și timpul în baza de date SQLite
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Parsăm șirul de caractere într-un obiect LocalDateTime
        return LocalDateTime.parse(dateString, formatter);
    }
}
