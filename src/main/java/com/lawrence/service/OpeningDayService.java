package com.lawrence.service;

import com.lawrence.model.OpeningDay;
import com.lawrence.web.dto.OpeningDayDto;

import java.util.Optional;

public interface OpeningDayService {
    void addOpeningDay(OpeningDayDto openingDayDto);
    Optional<OpeningDay> findById(long id);

}
