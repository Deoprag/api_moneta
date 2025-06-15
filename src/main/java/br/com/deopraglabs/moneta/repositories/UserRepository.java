package br.com.deopraglabs.moneta.repositories;

import br.com.deopraglabs.moneta.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("""
            SELECT COUNT(u) > 0 \
            FROM User u \
            WHERE u.id = :id \
            AND u.enabled = true""")
    boolean isDeleted(UUID id);

    @Modifying
    @Query("""
            UPDATE User u \
            SET u.enabled = false \
            WHERE u.id = :id""")
    void softDelete(UUID id);

    Optional<User> findByUsername(String username);
}
