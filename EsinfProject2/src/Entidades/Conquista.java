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

    public Conquista(boolean sucesso, LinkedList<Local> locaisAConquistar, double forca) {
        this.sucesso = sucesso;
        this.locaisAConquistar = locaisAConquistar;
        this.forca = forca;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Conquista(sucesso, locaisAConquistar, forca);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.sucesso ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.locaisAConquistar);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.forca) ^ (Double.doubleToLongBits(this.forca) >>> 32));
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
        return true;
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

    @Override
    public String toString() {
        return "Conquista{" + "sucesso=" + sucesso + ", locaisAConquistar=" + locaisAConquistar + ", forca=" + forca + '}';
    }
    
    
            
}
