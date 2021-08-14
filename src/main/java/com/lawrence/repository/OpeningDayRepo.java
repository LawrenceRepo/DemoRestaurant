package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.OpeningDay;
import java.util.List;
import java.util.Set;

public interface OpeningDayRepo extends JpaRepository<OpeningDay,Long> {
    Set<OpeningDay> findByIdIn(List<Long> ids);
}
