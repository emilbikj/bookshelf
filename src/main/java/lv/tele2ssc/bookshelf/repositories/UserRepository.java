package lv.tele2ssc.bookshelf.repositories;

import java.util.List;
import lv.tele2ssc.bookshelf.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
        
    @Query("SELECT b FROM User b WHERE lower(b.email) like %?1%")
    User findByEmail(String email);
    
    List<User> findAll();
        
}
