package org.launchcode.clique.models.data;

import org.launchcode.clique.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Jaurron on 7/11/2017.
 */

@Repository
@Transactional
public interface UserDao extends CrudRepository<Users, Integer> {
}
