public class cScorePoints implements Comparable<cScorePoints>{

    private String nomUser;
    private int puntuacion;

    public cScorePoints(String nomUser, int puntuacion) {
        this.nomUser = nomUser;
        this.puntuacion = puntuacion;
    }

    //Metode per comparar les puntuacions
    @Override
    public int compareTo(cScorePoints o) {
        return this.nomUser.compareTo(o.nomUser);
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    //Com es mostrara la informacio en el jugador
    @Override
    public String toString() {
        return  nomUser + " -- " + puntuacion;
    }


}
