package com.BankApi.ConnectInstallService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
class PropertyServiceTest {

    PropertyService propertyService=new PropertyService();

    @Test
    void TestPropertyPathNotEmpty(){
        assertFalse(propertyService.getPropertyPath().isEmpty());
    }
    @Test
    void TestPropertyNotEmpty(){
        assertFalse(propertyService.getProperties().isEmpty());
    }


}