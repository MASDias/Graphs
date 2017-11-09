package Entidades;

import java.util.Objects;

public class Estrada {

    private float dificuldade;

    public Estrada(float dificuldade) {
        this.dificuldade = dificuldade;
    }

    public float getDificuldade() {
        return dificuldade;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Estrada(dificuldade);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Float.floatToIntBits(this.dificuldade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Estrada other = (Estrada) obj;
        if (Float.floatToIntBits(this.dificuldade) != Float.floatToIntBits(other.dificuldade)) {
            return false;
        }
        return true;
    }    

}
