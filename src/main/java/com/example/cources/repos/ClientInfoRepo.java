package com.example.cources.repos;
import com.example.cources.domain.ClientInfo;
import com.example.cources.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface ClientInfoRepo extends CrudRepository<ClientInfo, Long> {
    ClientInfo findByUsername(String username);
    Iterable<ClientInfo> findByRole(Role role);
}
