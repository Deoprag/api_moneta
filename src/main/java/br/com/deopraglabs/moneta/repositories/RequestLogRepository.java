package br.com.deopraglabs.moneta.repositories;

import br.com.deopraglabs.moneta.domain.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
}
