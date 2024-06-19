package allancristiano.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import allancristiano.api.domain.anddress.Anddress;

public interface AnddressRepository extends JpaRepository<Anddress, UUID>{
    
}
