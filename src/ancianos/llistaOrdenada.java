package ancianos;

import java.util.ArrayList;

public class llistaOrdenada<T>{
    private ArrayList<T> llista = new ArrayList<>();

    public void afegir(T item){
        llista.add(item);
    }

    public ArrayList<T> getLlista() {
        return llista;
    }

    public void setLLista(ArrayList<T> t) {
        this.llista = t;
    }

    @Override
    public String toString() {
        return llista.toString();
    }
}
