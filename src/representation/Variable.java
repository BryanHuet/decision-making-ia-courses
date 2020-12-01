package representation;

import java.util.Set;

public class Variable {
    protected String name;
    private Set<Object> domaine;

    public Variable(String name, Set<Object> domaine) {
        this.name = name;
        this.domaine = domaine;
    }

    public String getName() {
        return this.name;
    }

    public Set<Object> getDomain() {
        return domaine;
    }

    public void setDomain(Set<Object> domaine) {
        this.domaine = domaine;
    }

    public String toString() {
        /*
        return "Name: " + this.name +
                " Domaine: " + this.domaine;
        */
        return "Name: " + this.name;
    }

    @Override
    public boolean equals(Object other) {
        if ((this.name != null) && (other instanceof Variable)) {
            Variable cast_other = (Variable) other;
            return cast_other.getName().equals(this.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

}
