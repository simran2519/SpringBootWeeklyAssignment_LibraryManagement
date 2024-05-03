package com.library.management.Repositories;
import com.library.management.Entities.LibraryMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryMemberRepository extends JpaRepository<LibraryMember,Long> {
     Optional<LibraryMember> findByUsername(String username);
}
