package com.lawrence.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lawrence.model.OpeningDay;
import com.lawrence.repository.OpeningDayRepo;
import com.lawrence.service.OpeningDayService;
import com.lawrence.web.dto.OpeningDayDto;

@Service
public class OpeningDayServiceImpl implements OpeningDayService {

    @Autowired
    OpeningDayRepo openingDayRepo;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
    @Override
    public void addOpeningDay(OpeningDayDto openingDayDto) {
        OpeningDay openingDay = new OpeningDay();
        openingDay.setOpeningTime(LocalDateTime.parse(openingDayDto.getOpeningTime(),dateTimeFormatter));
        openingDay.setClosingTime(LocalDateTime.parse(openingDayDto.getClosingTime(),dateTimeFormatter));
        openingDayRepo.save(openingDay);

        openingDayRepo.save(openingDay);
    }

    @Override
    public Optional<OpeningDay> findById(long id) {
      return openingDayRepo.findById(id);
    }


}
