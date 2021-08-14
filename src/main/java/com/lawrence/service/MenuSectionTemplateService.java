package com.lawrence.service;

import com.lawrence.model.MenuSectionTemplate;
import com.lawrence.web.dto.MenuSectionTemplateDto;

import java.io.IOException;
import java.util.List;

public interface MenuSectionTemplateService {
void addTemplate(MenuSectionTemplateDto menuSectionTemplateDto) throws IOException;
List<MenuSectionTemplate> viewTemplates();
}
