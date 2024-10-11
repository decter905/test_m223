package com.multiuserexam.codebase.junit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ApplicationContextTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testControllerExists() {
        assertNotNull(context.getBean("applicationController"), "Controller should be loaded in the context");
    }

    @Test
    public void testServiceExists() {
        assertNotNull(context.getBean("applicationService"), "Service should be loaded in the context");
    }

    @Test
    public void testRepositoryExists() {
        assertNotNull(context.getBean("bookRepository"), "Repository should be loaded in the context");
    }
}
