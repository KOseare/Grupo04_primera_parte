package negocio;

public class Domicilio {
    private String calle;
    private String numero;
    private String codPostal;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Domicilio)) {
            return false;
        }
        final Domicilio other = (Domicilio) object;
        if (!(calle == null ? other.calle == null : calle.equals(other.calle))) {
            return false;
        }
        if (!(numero == null ? other.numero == null : numero.equals(other.numero))) {
            return false;
        }
        if (!(codPostal == null ? other.codPostal == null : codPostal.equals(other.codPostal))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((calle == null) ? 0 : calle.hashCode());
        result = PRIME * result + ((numero == null) ? 0 : numero.hashCode());
        result = PRIME * result + ((codPostal == null) ? 0 : codPostal.hashCode());
        return result;
    }

}