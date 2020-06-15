package AlgoritmaAnalizi;
import java.util.*;

public class Greedy {
	Map<Vertex,Set<Vertex>> adj;
	double []pList;
	double[]mList;
	Integer [] parent;
	public Greedy(double []pList,double[]mList,double k, double f)
	{
		long start = System.currentTimeMillis();
		this.adj=new HashMap<Vertex,Set<Vertex>>();
		this.pList=pList.clone();
		this.mList=mList.clone();
		for(int i=0;i<mList.length;++i)
			addVertex(i);
		for(int i=0;i<mList.length;++i)
		{
			for(int j=i+1;j<mList.length;++j)
			{
				if((this.mList[j]-this.mList[i])>=k  && (this.mList[j]-this.mList[i])<=f)
					addEdge(new Vertex(i,this.pList[i]),new Vertex(j,pList[j]));
			}
		}
		queue(new Vertex(0,pList[0]));
		UCS(new Vertex(mList.length-1,pList[mList.length-1]));
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		double second=timeElapsed*0.001;
		System.out.println("Calisma Suresi "+second+" saniyedir");
			
	}
	public void addVertex(Integer i)
	{
		Vertex yeni=new Vertex(i,pList[i]);
		Set<Vertex>vset=new HashSet<>();
		adj.put(yeni, vset);
		
	}
	public Set<Vertex> getNeighbors(Integer i)
	{
		Set<Vertex> komsular=adj.get(new Vertex(i,pList[i]));
		return komsular;
	}
	public Set<Vertex> getNeighbors(Vertex v)
	{
		Set<Vertex> komsular=adj.get(v);
		return komsular;
	}
	public void addEdge(Vertex u, Vertex v) {
        Set<Vertex> uset = getNeighbors(u);
        uset.add(v);
        adj.put(u, uset);
	}
	 public void addEdge(Integer u, Integer v) {
	        Set<Vertex> uset = getNeighbors(u);
	        uset.add(new Vertex(v,pList[v]));
	        adj.put(new Vertex(u,pList[v]), uset);
	 }
	Comparator<Vertex> newVertexComp=new Comparator<Vertex>() {
		public int compare(Vertex i,Vertex j)
		{
			if(i.fiyat>j.fiyat)
				return 1;
			else if(i.fiyat<j.fiyat)
				return -1;
			return 0;
		}
	};
	
	PriorityQueue<Vertex> que=new PriorityQueue<Vertex>(newVertexComp);
	public void queue(Vertex e)
	{
		this.parent=new Integer[this.mList.length];
		Arrays.fill(parent,-1);
		que.add(e);
	}
	public void UCS(Vertex end)
	{
		if(!(que.isEmpty()))
		{
			Vertex b=que.peek();
				for(Vertex a:adj.get(que.remove()))
				{
					parent[a.label]=b.label;
					Vertex kontrol=new Vertex(a.label,pList[a.label]);
					a.fiyat=kontrol.fiyat;
					a.fiyat+=b.fiyat;
					if(a.equals(end))
					{
						System.out.println("Bulundu"+a.fiyat);
						return;
					}
					que.add(a);
				}
			UCS(end);
		}
		
	}
	public static void main(String [] args)
	{
		//double mList[]={0.0, 61.5,  120.0,147.5,  161.0,179.5,  219.0,278.5,  297.0,321.5,  336.0,393.5,  426.0,442.5,  490.0,548.5,  607.0,661.5,  718.0,764.5,  799.0 };
		//double pList[]={ 0.0, 15.4, 19.4,  5.4, 10.8,  6.6, 11.4,  7.4, 18.5,  9.2, 17.5, 14.2,  6.6, 13.2, 15.2, 17.8, 15.9, 15.3,  5.7, 19.6, 0.0,}; 
		/*double mList[]= {0.0,4.0,6.0,10.0,12.0,14.0,18.0,21.0,26.0,32.0,36.0,42.0,47.0,51.0,55.0,59.0,62.0,66.0,70.0,72.0,78.0};
		double pList[]= {0.0,3.0,5.0,5.0,1.0,3.0,1.0,1.0,4.0,4.0,2.0,4.0,3.0,5.0,2.0,1.0,1.0,2.0,1.0,1.0,0.0};*/
		//double mList[]={0.0, 61.0,  120.0,147.0,  161.0,179.0,  219.0,278.0,  297.0,321.0,  336.0,393.0,  426.0,442.0,  490.0,548.0,  607.0,661.0,  718.0,764.0,  799.0,822.0,  867.0,915.0,  958.0,1000.0 };
		//double pList[]={ 0.0, 15.0, 19.0,  5.0, 11.0,  7.0,11.0,  7.0, 18.0,  9.0, 17.0, 14.0,  7.0, 13.0, 15.0, 18.0, 16.0, 15.0,  6.0, 20.0, 20.0,  5.0, 11.0, 12.0, 12.0,0.0};
		/*double mList[] = { 0.0,  38.0,   70.0 , 87.0 ,120.0,150.0,169.5,200.5,210.1};
	    double pList[] = { 0.0,  14.0 , 15.0 , 9.0, 10.0,9.0,4.0,7.0,0.0};		*/
		//double mList[] = {0.0, 26.5,   48.0, 78.5,  124.0,160.5,  219.0,263.5,  296.0,312.5,  366.0,390.5,  447.0,479.5,  503.0,564.5,  611.0,645.5,  699.0,759.5,  794.0,849.5,  898.0,922.5,  940.0,963.5,  990.0,1006.5, 1059.0,1098.5, 1127.0,1180.5, 1205.0,1262.5, 1320.0,1337.5, 1371.0,1425.5, 1469.0,1501.5, 1536.0,1548.5, 1600.0,1634.5, 1670.0,1715.5, 1757.0,1803.5, 1846.0,1875.5,1900.5};
		//double pList[] = {0.0, 13.5,   16.0, 17.3,  9.5,   12.0, 13.2, 16.4,  5.3, 16.4, 18.5, 19.9, 17.8, 10.1,  7.7, 10.5, 17.5, 12.1,  8.4,   16.0,  5.1,    7.0,  5.7,   15.0,  5.1, 11.8, 18.3, 10.5,  7.5, 12.9, 15.7, 19.8, 13.8, 19.7, 19.3, 16.2, 13.2,  7.4, 13.3,  6.3, 10.9,  6.9, 17.5, 15.9,  6.3,  5.8, 10.2, 16.2, 13.5,  9.5,0.0};
		/*double mList[]= {0.0,10.4,18.3,28.9,39.41,55.10,57.25,64.50,68.8,70.5};
		double pList[]= {0.0,3.7,4.1,3.6,4.8,3.17,3.20,3.21,3.33,0.0};
	    double f= 30.0;
		double k= 10.0;	*/
		/*double mList[]={0.0,  38.0,    74.0 , 87.0  , 106.0 ,155.0 ,  197.0, 238.0,268.0};
	    double pList[]={0.0,  14.0 , 11.0 , 15.0,   7.0,  20.0,    10.0,   8.0,0.0};*/
		/*double mList[]={0.0, 61.5,  120.0,147.5,  161.0,179.5,  219.0,278.5,  297.0,321.5,  336.0,393.5,  426.0,442.5,  490.0,548.5,  607.0,661.5,  718.0,764.5,  799.0 };
		double pList[]={ 0.0, 15.4, 19.4,  5.4, 10.8,  6.6, 11.4,  7.4, 18.5,  9.2, 17.5, 14.2,  6.6, 13.2, 15.2, 17.8, 15.9, 15.3,  5.7, 19.6, 0.0,};*/
		/*double mList[]= {0.0,10.4,18.3,28.9,39.41,55.10,57.25,64.50,68.8,70.5};
		double pList[]= {0.0,3.7,4.1,3.6,4.8,3.17,3.20,3.21,3.33,0.0};*/
		/*double mList[]= {0,8,16,24.14,30,35,39,41,43,49,50,51,58,65,71,75,80,84,89,95,100};
		double pList[]= {0.0,1.2,1.3,2.15,1.9,2.8,1.54,2.0,1.13,2.0,1.88,1.74,1.56,1.44,1.1,1.25,1.37,1.36,1.39,1.50,0.0};
		double f= 15.0;
		double k= 5.0;	*/
		double f=40.0;
		double k=20.0;
		double mList[]= {0,8,16,24,30,35,39,41,43,49,50,51,58,65,71,75,80,84,89,95,100,105,109,110,115,119,121,130,135,136,138,140,147,155,160,165,171,178,184,190,192,198,200};
	     double pList[]={0,6,8,7,3,4,10,12,11,14,10,13,14,17,15,16,21,17,17,18,13,25,24,20,14,17,10,15,20,26,33,25,31,17,27,26,24,23,25,24,28,33,0};
	     System.out.println(mList.length);
	     System.out.println(pList.length);
		/*double f=7.0;
		double k=2.0;
		double mList[]= {0.0,5.0,7.0,9.0,11.0,13.0,15.0};
		double pList[]= {0.0,6.0,8.0,7.0,3.0,4.0,0.0};*/
		System.out.println("f:"+f);
		System.out.println("k:"+k);
		System.out.println("input size:"+mList.length);
		System.out.println("istasyonlarýn baslangic noktasina uzakliklari");
		for(int a=0;a<mList.length;++a)
			System.out.print(mList[a]+"\t");
		System.out.println("");
		System.out.println("Ýstasyon Fiyatlarý(baslangic noktasi ve bitis noktasinin fiyatlarý 0'dir.)");
		for(int b=0;b<pList.length;++b)
			System.out.print(pList[b]+"\t");
		System.out.println("");
		Greedy yeni=new Greedy(pList,mList,k,f);
		System.out.println("Aracin durma noktalari(bitis noktasindan baslangic noktasina)");
		int i=mList.length-1;
		while(i!=0)
		{
			if(yeni.parent[i]!=-1)
			{
				System.out.print(yeni.parent[i]+"	");
				i=yeni.parent[i];
			}
			//0 1 3 4 5 9 
		}
	}
}

