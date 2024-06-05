package org.example.persistance;

import org.example.domain.Administrator;
import org.example.domain.Loc;
import org.example.domain.Rezervare;
import org.example.domain.Spectacol;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if ((sessionFactory==null)||(sessionFactory.isClosed()))
            sessionFactory=createNewSessionFactory();
        return sessionFactory;
    }

    private static  SessionFactory createNewSessionFactory(){
        sessionFactory = new Configuration()
                .addAnnotatedClass(Administrator.class)
                .addAnnotatedClass(Spectacol.class)// Adăugăm clasa Spectacol
                .addAnnotatedClass(Rezervare.class)
                .addAnnotatedClass(Loc.class)
                .buildSessionFactory();
        return sessionFactory;
    }

    public static  void closeSessionFactory(){
        if (sessionFactory!=null)
            sessionFactory.close();
    }
}
