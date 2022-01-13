package com.pdb.loadbalancing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class RouterTest {
    private Router router;

    private static Stream<Arguments> loadBalancingDataProvider() {
        return Stream.of(
                Arguments.of(asList("10.10.10.1", "10.10.10.2", "10.10.10.3"), asList("10.10.10.1", "10.10.10.2", "10.10.10.3")),
                Arguments.of(
                        asList("10.10.10.1", "10.10.10.2", "10.10.10.3", "10.10.10.4",
                                "10.10.10.5", "10.10.10.6", "10.10.10.7", "10.10.10.8",
                                "10.10.10.9", "10.10.10.10"),
                        asList("10.10.10.1", "10.10.10.2", "10.10.10.3", "10.10.10.4",
                                "10.10.10.5", "10.10.10.6", "10.10.10.7", "10.10.10.8",
                                "10.10.10.9", "10.10.10.10")
                ),
                Arguments.of(
                        asList("10.10.10.1", "10.10.10.2", "10.10.10.3", "10.10.10.4",
                                "10.10.10.5", "10.10.10.6", "10.10.10.7", "10.10.10.8",
                                "10.10.10.9", "10.10.10.10", "10.10.10.10", "10.10.10.10"),
                        asList("10.10.10.1", "10.10.10.2", "10.10.10.3", "10.10.10.4",
                                "10.10.10.5", "10.10.10.6", "10.10.10.7", "10.10.10.8",
                                "10.10.10.9", "10.10.10.10")
                )
        );
    }

    @BeforeEach
    void setUp() {
        router = new Router();
    }

    @ParameterizedTest
    @MethodSource("loadBalancingDataProvider")
    void startupTest(List<String> input, List<String> expectedAdresses) {
        //given
        //ArrayList<String> adresses = (ArrayList<String>) asList("10.10.10.1", "10.10.10.2", "10.10.10.3");

        //when
        router.startup(input);

        //then
        List<String> actualRouterAdresses = router.getAdresses();
        assertEquals(actualRouterAdresses, expectedAdresses);
    }

    @Test
    void maxInstancesAchievedTest() {
        //given
        List<String> tooManyAdresses = asList("10.10.10.1", "10.10.10.2", "10.10.10.3", "10.10.10.4",
                "10.10.10.5", "10.10.10.6", "10.10.10.7", "10.10.10.8",
                "10.10.10.9", "10.10.10.10", "10.10.10.11");

        //when
        assertThrows(IllegalArgumentException.class, () -> router.startup(tooManyAdresses));
    }
}