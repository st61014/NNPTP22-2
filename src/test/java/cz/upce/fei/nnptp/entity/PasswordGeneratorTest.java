package cz.upce.fei.nnptp.entity;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {
    @Test
    void generatePasswordWithAllOptionsTest() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        //long password to ensure it contains at least one character from each option
        String generatedPassword = passwordGenerator.generatePassword(100,
                new PasswordGeneratorConfiguration(true, true, true));
        assertTrue(generatedPassword.length() == 100);
        assertTrue(generatedPassword.chars().anyMatch(Character::isUpperCase));
        assertTrue((generatedPassword.chars().anyMatch(Character::isDigit)));
        assertTrue(Pattern.compile("[+?!<>*_#@%]").matcher(generatedPassword).find());
    }
    @Test
    void generatePasswordWithTooShortLength() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String generatedPassword = passwordGenerator.generatePassword(2,
                new PasswordGeneratorConfiguration(true, true, true));
        assertTrue(generatedPassword.length() == 5);
    }
    @Test
    void generatePasswordWithTooSLongLength() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String generatedPassword = passwordGenerator.generatePassword(1000,
                new PasswordGeneratorConfiguration(true, true, true));
        assertTrue(generatedPassword.length() == 255);
        assertTrue(generatedPassword.chars().anyMatch(Character::isUpperCase));
    }
}