package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.Person;

import java.util.Set;
import java.util.function.Function;

public abstract class Relation {

    public abstract  String name();

    public abstract String description();

    public abstract Function<Person, Set<Person>> searchStrategy();

    public static Relation son() {
        return SonRelation.getInstance();
    }

    public static Relation daughter() {
        return DaughterRelation.getInstance();
    }

    public static Relation sibling() {
        return SiblingRelation.getInstance();
    }

    public static Relation brother() {
        return BrotherRelation.getInstance();
    }

    public static Relation sister() {
        return SisterRelation.getInstance();
    }

    public static Relation brotherInLaw() {
        return BrotherInLawRelation.getInstance();
    }

    public static Relation sisterInLaw() {
        return SisterInLawRelation.getInstance();
    }

    public static Relation paternalUncle() {
        return PaternalUncleRelation.getInstance();
    }

    public static Relation maternalUncle() {
        return MaternalUncleRelation.getInstance();
    }

    public static Relation paternalAunt() {
        return PaternalAuntRelation.getInstance();
    }

    public static Relation maternalAunt() {
        return MaternalAuntRelation.getInstance();
    }
}
