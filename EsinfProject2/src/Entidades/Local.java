package Entidades;

import java.util.Objects;

public class Local {

    String nome;
    int dificuldade;
    Personagem dono;

    public Local(String nome, int dificuldade) {
        this.nome = nome;
        this.dificuldade = dificuldade;
        this.dono = null;
    }

    @Override
    public String toString() {
        return nome + " " + dificuldade + " " + String.format("%s", dono != null ? dono : "");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.nome);
        hash = 17 * hash + this.dificuldade;
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
        final Local other = (Local) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

}
