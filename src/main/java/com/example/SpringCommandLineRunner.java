package com.example;

import com.example.users.model.AppUser;
import com.example.users.repo.AppUserRepository;
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
    private final RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping;

    @PersistenceContext
    private EntityManager entityManager;

    public SpringCommandLineRunner(AppUserRepository appUserRepository, RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping) {
        this.appUserRepository = appUserRepository;
        this.requestMappingInfoHandlerMapping = requestMappingInfoHandlerMapping;
    }

    @Override
    public void run(String... args) {

        log.info("Entity Manager : {} ", entityManager);

        Map<RequestMappingInfo, HandlerMethod> handlersMethod = requestMappingInfoHandlerMapping.getHandlerMethods();

        handlersMethod.forEach((key, value) -> log.info(key.getPathPatternsCondition().getPatternValues() + " : " + key.getMethodsCondition().getMethods()));

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlersMethod.entrySet()) {
            System.out.println(entry.getKey());
        }

        List<AppUser> appUserList = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 97);
            AppUser appUser = new AppUser(0, c + "@gmail.com", c + "", c + "");
            appUserList.add(appUser);
        }
        List<AppUser> saveAppUserList = appUserRepository.saveAll(appUserList);
        for (AppUser u : saveAppUserList) {
            appUserRepository.save(u);
        }
    }

}
