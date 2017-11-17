package Entidades;

import java.util.Objects;

public class Personagem {

    String nome;
    int forca;

    public Personagem(String nome, int forca) {
        this.nome = nome;
        this.forca = forca;
    }

    public String getNome() {
        return nome;
    }

    public int getForca() {
        return forca;
    }

    @Override
    public String toString() {
        return nome + " " + forca;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.nome);
        hash = 37 * hash + this.forca;
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
        final Personagem other = (Personagem) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

}
