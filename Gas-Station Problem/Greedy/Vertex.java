package AlgoritmaAnalizi;

import java.util.Comparator;

public class Vertex  implements Comparator<Vertex>{
    Integer label;
    Double fiyat;
    
    public Vertex(Integer label,Double fiyat) {
        this.label = label;
        this.fiyat=fiyat;
    }
    public boolean equals(Object o) {
        
        return this.label.equals(((Vertex) o).label);
    }
    public int hashCode() {
        return label.hashCode();
    }
	@Override
	public int compare(Vertex o1, Vertex o2) {
		// TODO Auto-generated method stub
		if(o1.fiyat<o2.fiyat)
			return -1;
		else if(o1.fiyat>o2.fiyat)
			return 1;
		return 0;
	}
    
}
