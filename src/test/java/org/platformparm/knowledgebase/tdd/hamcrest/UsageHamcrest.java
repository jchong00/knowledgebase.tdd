package org.platformparm.knowledgebase.tdd.hamcrest;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class UsageHamcrest {

    @Test
    public void testStringStartsWith() {

        String s = "Hamcrest is a framework for writing matcher objects allowing ‘match’ rules to be defined declaratively.";
        assertThat(s, startsWith("Hamcrest is"));
        assertThat(s, MyStringStartsWithMatcher.myStringStartsWith("Hamcrest is"));
    }


    @Test
    public void hasSizeOf3() {
        List<Integer> list = Arrays.asList(5, 2, 4);

        assertThat(list, hasSize(3));
    }

    @Test
    public void everyItemGreaterThan1() {
        List<Integer> list = Arrays.asList(5, 2, 4);
        assertThat(list, everyItem(greaterThan(1)));
    }

    @Test
    public void containsNumbersInAnyOrder() {
        List<Integer> list = Arrays.asList(5, 2, 4);
        assertThat(list, containsInAnyOrder(2, 4, 5));
    }

}
