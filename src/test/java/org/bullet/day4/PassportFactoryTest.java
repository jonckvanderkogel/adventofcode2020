package org.bullet.day4;

import io.vavr.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.bullet.day4.PassportFactory.createPassport;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassportFactoryTest {
    @Test
    public void factoryCanProduceValidPassport() {
        List<Tuple2<String, String>> tuples = List.of(
                new Tuple2<>("ecl", "gry"),
                new Tuple2<>("pid", "860033327"),
                new Tuple2<>("eyr", "2020"),
                new Tuple2<>("hcl", "#fffffd"),
                new Tuple2<>("byr", "1937"),
                new Tuple2<>("iyr", "2017"),
                new Tuple2<>("cid", "147"),
                new Tuple2<>("hgt", "183cm")
        );

        Passport passport = createPassport(tuples);
        PassportValidator validator = new PassportValidator();
        assertTrue(validator.isValidPassport(passport));
    }

    @Test
    public void incompleteListOfTuplesShouldResultInInvalidPassport() {
        List<Tuple2<String, String>> tuples = List.of(
                // missing ecl
                new Tuple2<>("pid", "860033327"),
                new Tuple2<>("eyr", "2020"),
                new Tuple2<>("hcl", "#fffffd"),
                new Tuple2<>("byr", "1937"),
                new Tuple2<>("iyr", "2017"),
                new Tuple2<>("cid", "147"),
                new Tuple2<>("hgt", "183cm")
        );

        Passport passport = createPassport(tuples);
        PassportValidator validator = new PassportValidator();
        assertFalse(validator.isValidPassport(passport));
    }
}
