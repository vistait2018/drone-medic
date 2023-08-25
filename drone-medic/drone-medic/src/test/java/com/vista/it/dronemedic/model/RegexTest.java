package com.vista.it.dronemedic.model;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegexTest {

    @Test
    public void testNameRegularExpression(){
        boolean test = true;
        Pattern p = Pattern.compile("^[A-Za-z0-9-_]+$");
        Matcher m = p.matcher("Hello-_33");
        assertEquals(test,m.matches() );
    }


    @Test
    public void testCodeRegularExpression(){
        boolean test = true;
        Pattern p = Pattern.compile("^[A-Z]+_[0-9]+$");
        Matcher m = p.matcher("AB_33");
        assertEquals(test,m.matches() );
    }

}
