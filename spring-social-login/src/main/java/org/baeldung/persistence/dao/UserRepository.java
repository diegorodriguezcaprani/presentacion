package org.baeldung.persistence.dao;

import org.baeldung.persistence.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(final String username);

}
