package Entidades;

import java.util.LinkedList;
import java.util.Objects;

public class AliancaMaisForte implements Cloneable{

    private LinkedList<Personagem> aliancaMaisForte;
    private double forcaAlianca;

    public AliancaMaisForte(LinkedList<Personagem> aliancaMaisForte, double forcaAlianca) {
        this.aliancaMaisForte = aliancaMaisForte;
        this.forcaAlianca = forcaAlianca;
    }

    public LinkedList<Personagem> getAliancaMaisForte() {
        return aliancaMaisForte;
    }

    public double getForcaAlianca() {
        return forcaAlianca;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.aliancaMaisForte);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.forcaAlianca) ^ (Double.doubleToLongBits(this.forcaAlianca) >>> 32));
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
        final AliancaMaisForte other = (AliancaMaisForte) obj;
        if (Double.doubleToLongBits(this.forcaAlianca) != Double.doubleToLongBits(other.forcaAlianca)) {
            return false;
        }
        if (!Objects.equals(this.aliancaMaisForte, other.aliancaMaisForte)) {
            return false;
        }
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new AliancaMaisForte(aliancaMaisForte, forcaAlianca);
    }

    @Override
    public String toString() {
        return aliancaMaisForte + " Força Alianca:" + forcaAlianca;
    }
    
}
