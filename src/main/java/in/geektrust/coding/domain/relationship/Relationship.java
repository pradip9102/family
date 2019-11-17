package in.geektrust.coding.domain.relationship;

public enum Relationship {

    /**
     * Son
     */
    SON(Relation.son()),

    /**
     * Daughter
     */
    DAUGHTER(Relation.daughter()),

    /**
     * Siblings
     */
    SIBLINGS(Relation.sibling()),

    /**
     * Brother
     */
    BROTHER(Relation.brother()),

    /**
     * Sister
     */
    SISTER(Relation.sister()),

    /**
     * Spouse's brothers, Husbands of siblings
     */
    BROTHER_IN_LAW(Relation.brotherInLaw()),

    /**
     * Spouse's sisters, Wives of siblings
     */
    SISTER_IN_LAW(Relation.sisterInLaw()),

    /**
     * Father's brothers
     */
    PATERNAL_UNCLE(Relation.paternalUncle()),

    /**
     * Mother's brothers
     */
    MATERNAL_UNCLE(Relation.maternalUncle()),

    /**
     * Father's sisters
     */
    PATERNAL_AUNT(Relation.paternalAunt()),

    /**
     * Mother's sisters
     */
    MATERNAL_AUNT(Relation.maternalAunt());

    private final Relation relation;

    Relationship(Relation relation) {
        this.relation = relation;
    }

    public Relation getRelation() {
        return relation;
    }
}
