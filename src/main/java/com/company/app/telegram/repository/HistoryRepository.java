package com.company.app.telegram.repository;

import com.company.app.telegram.entity.History;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository<History, Long> {
}
