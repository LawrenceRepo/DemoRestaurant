package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.OpeningSchedule;

public interface OpeningScheduleRepo extends JpaRepository<OpeningSchedule,Long> {
}
