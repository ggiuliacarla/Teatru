package org.example.persistance;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.domain.Administrator;
import org.hibernate.Session;

public class AdministratorRepository implements IAdministratorRepository{
    @Override
    public Administrator logIn(String numeuser, String parola) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Administrator> criteria = builder.createQuery(Administrator.class);
            Root<Administrator> root = criteria.from(Administrator.class);
            criteria.select(root);
            criteria.where(
                    builder.and(
                            builder.equal(root.get("numeuser"), numeuser),
                            builder.equal(root.get("parola"), parola)
                    )
            );
            return session.createQuery(criteria).uniqueResult();
        }
    }

    @Override
    public void save(Administrator administrator) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Administrator findOne(Integer id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from Administrator where id=:idM ", Administrator.class)
                    .setParameter("idM", id)
                    .getSingleResultOrNull();
        }
    }

    @Override
    public void update(Integer integer, Administrator administrator) {

    }

    @Override
    public Iterable<Administrator> getAll() {
        try( Session session=HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Administrator ", Administrator.class).getResultList();
        }
    }
}
