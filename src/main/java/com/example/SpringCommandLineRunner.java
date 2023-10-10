package com.example;

import com.example.model.AppUser;
import com.example.repository.AppUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SpringCommandLineRunner implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final List<RequestMappingInfoHandlerMapping> requestMappingInfoHandlerMapping;

    @PersistenceContext
    private EntityManager entityManager;

    public SpringCommandLineRunner(AppUserRepository appUserRepository, List<RequestMappingInfoHandlerMapping> requestMappingInfoHandlerMapping) {
        this.appUserRepository = appUserRepository;
        this.requestMappingInfoHandlerMapping = requestMappingInfoHandlerMapping;
    }

    @Override
    public void run(String... args) {

        log.info("Entity Manager : {} ", entityManager);

        Map<RequestMappingInfo, HandlerMethod> handlersMethod = requestMappingInfoHandlerMapping.get(3).getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlersMethod.entrySet()) {
            log.info(entry.getKey() + " : " + entry.getValue());
        }

        List<AppUser> appUserList = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 97);
            AppUser appUser = new AppUser(0, c + "@gmail.com", c + "", c + "", "ee");
            appUserList.add(appUser);
        }
        List<AppUser> saveAppUserList = appUserRepository.saveAll(appUserList);
        for (AppUser u : saveAppUserList) {
            appUserRepository.save(u);
        }
    }

}
