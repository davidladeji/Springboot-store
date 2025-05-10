package com.codewithdave.store.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.codewithdave.store.dtos.UserSummary;
import com.codewithdave.store.entities.Profile;
import java.util.List;


public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @EntityGraph(attributePaths = "user")
    @Query("select p from Profile p where p.loyaltyPoints > :loyaltyPoints order by p.user.email")
    List<UserSummary> findByLoyaltyPoints(@Param("loyaltyPoints") int loyaltyPoints);

    // Derived Query Equivalent
    // List<Profile> findByLoyaltyPointsGreaterThanOrderByUserEmail(int loyaltyPoints);
}
