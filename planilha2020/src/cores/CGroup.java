package cores;

import java.util.LinkedList;

/**
 *
 * @author Makar Plakhotnyk
 */
public class CGroup {
    
    private final String name;
    private final LinkedList<Cor> cores;
    
    public CGroup(String name){
        this.name =name;
        this.cores =new LinkedList<>();
        }
    
    public void add(Cor cor){
        this.cores.add(cor);
        }
    
    public String getName(){
        return this.name;
        }
    
    @Override
    public String toString(){
        return this.name;
        }

    public LinkedList<Cor> getCores() {
        return this.cores;
    }

    int getInd(int rgb[]) {
        for(int i=0; i<this.cores.size(); i++){
            Cor cor =this.cores.get(i);
            if (cor.hasRGB(rgb)){
                return i; } }
        return -1;
    }
    
}
