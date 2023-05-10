package com.example.airport.repos;
import com.example.airport.domain.ClientInfo;
import com.example.airport.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface ClientInfoRepo extends CrudRepository<ClientInfo, Long> {
    ClientInfo findByUsername(String username);
    Iterable<ClientInfo> findByRole(Role role);
}
