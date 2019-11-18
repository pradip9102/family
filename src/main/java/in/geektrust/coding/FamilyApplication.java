package in.geektrust.coding;

import in.geektrust.coding.domain.Lengaburu;
import in.geektrust.coding.domain.family.Gender;
import in.geektrust.coding.domain.family.Person;
import in.geektrust.coding.domain.relationship.Relationship;
import in.geektrust.coding.exceptions.MotherNotFoundException;
import in.geektrust.coding.exceptions.ResultCodes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FamilyApplication {

    private static Lengaburu lengaburu;

    public static void main(String[] args) {
        try {
            String fileName = (args.length > 0) ? args[0] : "./src/main/resources/sample/TheShanFamilyTree.txt";
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(" ");
                if (tokens.length > 0) {
                    if (runCommand(tokens) != 0) {
                        System.out.println(String.format(ResultCodes.INVALID_COMMAND, line));
                        printHelp();
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printHelp() {
        System.out.println();
    }

    private static int runCommand(String[] tokens) {
        switch (tokens[0]) {
            case "LENGABURU":
                if (tokens.length != 3) return -1;
                lengaburu = new Lengaburu(new Person(tokens[1], Gender.MALE), new Person(tokens[2], Gender.FEMALE));
                System.out.println(ResultCodes.PLANET_CREATED);
                return 0;
            case "ADD_CHILD":
                if (tokens.length != 4 || lengaburu == null) return -1;
                try {
                    lengaburu.addPerson(tokens[1], tokens[2], Gender.valueOf(tokens[3].toUpperCase()));
                    System.out.println(ResultCodes.CHILD_ADDITION_SUCCEEDED);
                } catch (MotherNotFoundException e) {
                    System.out.println(ResultCodes.PERSON_NOT_FOUND);
                } catch (Exception e) {
                    System.out.println(ResultCodes.CHILD_ADDITION_FAILED);
                }
                return 0;
            case "MARRIAGE":
                if (tokens.length != 3 || lengaburu == null) return -1;
                Optional<Person> person1 = lengaburu.find(tokens[1]);
                Optional<Person> person2 = lengaburu.find(tokens[2]);
                if (person1.isPresent() && person2.isPresent()) {
                    person1.get().marry(person2.get());
                    System.out.println(ResultCodes.MARRIED);
                } else {
                    System.out.println(ResultCodes.PERSON_NOT_FOUND);
                }
                return 0;
            case "GET_RELATIONSHIP":
                if (tokens.length != 3 || lengaburu == null) return -1;
                Optional<Person> person = lengaburu.find(tokens[1]);
                Relationship relationship = Relationship.valueOf(tokens[2].replace('-', '_').toUpperCase());
                if (person.isPresent()) {
                    Set<Person> searchResult = person.get().search(relationship.getRelation());
                    if (searchResult.isEmpty()) {
                        System.out.println(ResultCodes.NONE);
                    } else {
                        String result = searchResult.stream().map(Person::getName).collect(Collectors.joining(" "));
                        System.out.println(result);
                    }
                } else {
                    System.out.println(ResultCodes.PERSON_NOT_FOUND);
                }
                return 0;
            default:
                return -1;
        }
    }
}
