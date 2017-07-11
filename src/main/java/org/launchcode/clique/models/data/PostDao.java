package org.launchcode.clique.models.data;

import org.launchcode.clique.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Jaurron on 7/10/2017.
 */
@Repository
@Transactional
public interface PostDao extends CrudRepository<Post, Integer> {
}
