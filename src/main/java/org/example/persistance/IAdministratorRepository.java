package org.example.persistance;

import org.example.domain.Administrator;

public interface IAdministratorRepository extends ICrudRepository<Integer, Administrator> {
    public Administrator logIn(String username,String parola);
}
