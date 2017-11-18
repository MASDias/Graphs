/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.LinkedList;
import java.util.Objects;

public class Conquista {

    private boolean sucesso;
    private LinkedList<Local> locaisAConquistar;
    private double forca;
    private Personagem aliado;

    public Conquista(boolean sucesso, LinkedList<Local> locaisAConquistar, double forca, Personagem aliado) {
        this.sucesso = sucesso;
        this.locaisAConquistar = locaisAConquistar;
        this.forca = forca;
        this.aliado = aliado;
    }

    public Conquista(boolean sucesso, LinkedList<Local> locaisAConquistar, double forca) {
        this.sucesso = sucesso;
        this.locaisAConquistar = locaisAConquistar;
        this.forca = forca;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public LinkedList<Local> getLocaisAConquistar() {
        return locaisAConquistar;
    }

    public double getForca() {
        return forca;
    }

    public Personagem getAliado() {
        return aliado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.sucesso ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.locaisAConquistar);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.forca) ^ (Double.doubleToLongBits(this.forca) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.aliado);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conquista other = (Conquista) obj;
        if (this.sucesso != other.sucesso) {
            return false;
        }
        if (!Objects.equals(this.locaisAConquistar, other.locaisAConquistar)) {
            return false;
        }
        if (Double.doubleToLongBits(this.forca) != Double.doubleToLongBits(other.forca)) {
            return false;
        }
        if (!Objects.equals(this.aliado, other.aliado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (aliado != null) {
            return sucesso + " " + locaisAConquistar + " " + forca + " " + aliado;
        }
        return sucesso + " " + locaisAConquistar + " " + forca;
    }

}
