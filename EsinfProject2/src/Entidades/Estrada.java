package Entidades;

import java.util.Objects;

public class Estrada {

    private String nome;
    private double dificuldade;

    public Estrada(String nome, double dificuldade) {
        this.nome = nome;
        this.dificuldade = dificuldade;
    }

    public String getNome() {
        return nome;
    }

    public double getDificuldade() {
        return dificuldade;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Estrada(nome, dificuldade);
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.nome);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.dificuldade) ^ (Double.doubleToLongBits(this.dificuldade) >>> 32));
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
        if (Double.doubleToLongBits(this.dificuldade) != Double.doubleToLongBits(other.dificuldade)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

}
