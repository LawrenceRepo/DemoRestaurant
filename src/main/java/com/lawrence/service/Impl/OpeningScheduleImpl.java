package com.lawrence.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lawrence.model.OpeningSchedule;
import com.lawrence.repository.OpeningDayRepo;
import com.lawrence.repository.OpeningScheduleRepo;
import com.lawrence.service.OpeningScheduleService;
import com.lawrence.web.dto.OpeningScheduleDto;

@Service
public class OpeningScheduleImpl implements OpeningScheduleService {
    @Autowired
    OpeningDayRepo openingDayRepo;
    @Autowired
    OpeningScheduleRepo openingScheduleRepo;
    @Override
    public void addOpeningSchedule(OpeningScheduleDto openingScheduleDto) {
       OpeningSchedule openingSchedule = new OpeningSchedule();
       openingSchedule.setOpeningDayS(openingDayRepo.findByIdIn(openingScheduleDto.getOpeningDays()));
       openingScheduleRepo.save(openingSchedule);
    }

}
