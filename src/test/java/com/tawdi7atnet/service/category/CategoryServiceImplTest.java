package com.tawdi7atnet.service.category;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categorieService;

    private static Instant startAt=Instant.now();

    @BeforeAll
    static public void methodCallBeforeAllTest(){
        System.out.println("ce traitement s'execute avant tous les test unitaire start at : " + startAt);
    }

    @AfterAll
    static public void methodCallAfterAllTest(){
        Instant endAt = Instant.now();
        System.out.println("ce traitement s'execute après tous test unitaire => finished at : " + endAt);
        System.out.println("le nombre des ms a pris le traitement : " + Duration.between(startAt,endAt).toMillis());
    }

    @BeforeEach
    public void methodCallBeforeAnyTest(){
        System.out.println("ce traitement s'execute avant chaque test unitaire");
    }
    @AfterEach
    public void methodCallAfterAnyTest(){
        System.out.println("ce traitement s'execute après chaque test unitaire");
        categorieService = null;
    }

}
