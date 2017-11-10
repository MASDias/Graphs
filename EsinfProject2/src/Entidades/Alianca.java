package Entidades;

public class Alianca implements Cloneable {

    private boolean publica;
    private double compatibilade;

    public Alianca(boolean publica, double compatibilade) {
        this.publica = publica;
        this.compatibilade = compatibilade;
    }

    public Alianca(boolean publica) {
        this.publica = publica;
        this.compatibilade = (double) Math.random();
    }

    @Override
    public String toString() {
        return publica + " " + compatibilade;
    }

    
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Alianca(publica, compatibilade);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.publica ? 1 : 0);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.compatibilade) ^ (Double.doubleToLongBits(this.compatibilade) >>> 32));
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
        final Alianca other = (Alianca) obj;
        if (this.publica != other.publica) {
            return false;
        }
        if (Double.doubleToLongBits(this.compatibilade) != Double.doubleToLongBits(other.compatibilade)) {
            return false;
        }
        return true;
    }

    

}
