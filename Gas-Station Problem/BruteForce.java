
/*
 * Fatih Dursun Üzer
 * 18120205041
 * Bilgisayar Mühendisliði 2.sýnýf
 * shift kýsmýný https://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/ dan aldým.
 */

//A Java program to print all subsets of a set 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays; 
import java.util.Random;

class BruteForce
{  
	 ArrayList<ArrayList<Integer>> bruteForce(double [] mList,double []pList,double k,double f) 
	{ 
		long start = System.currentTimeMillis();
		int n = mList.length;
		ArrayList<ArrayList<Integer>>list=new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < Math.pow(2, n) ; i++) 
		{  
			ArrayList<Integer>subset=new ArrayList<Integer>();
			for (int j = 0; j < n; j++) 
			{
				if ((i & (1 << j)) > 0) 
				{
					subset.add(j);
				}
			}
			if(subset.size()>1)
			list.add(subset);
		} 
		ArrayList<ArrayList<Integer>> possiblePaths=new ArrayList<ArrayList<Integer>>();
		for(ArrayList<Integer> a:list)
		{
			int counter=0;
			for(int i=0;i<a.size();++i)
			{
				if((a.get(0)!=0 || !(a.contains(n-1))))
				{
					counter++;
					continue;
				}
				if(i+1<a.size())
				if(mList[a.get(i+1)]-mList[a.get(i)]<k || mList[a.get(i+1)]-mList[a.get(i)]>f)
				{
					counter++;
					continue;
				}
			}
			if(counter==0)
			{
				possiblePaths.add(a);
			}

		}
		double [] costs=new double[possiblePaths.size()];
		int i=0;
		for (ArrayList<Integer> indexs:possiblePaths)
		{
			costs[i]=0.0;
			for(int a=0;a<indexs.size();++a)
				costs[i]+=pList[indexs.get(a)];
			i++;
		}
		double minimum=costs[0];
		int shortPathIndex=0;
		for(int in=0;in<costs.length;++in)
		{
			if(costs[in]<=minimum)
			{
				minimum=costs[in];
				shortPathIndex=in;
			}
		}
		System.out.println("Gidilecek Yol");
		for(int kýsa=0;kýsa<possiblePaths.get(shortPathIndex).size();++kýsa)
		{
			System.out.print(possiblePaths.get(shortPathIndex).get(kýsa)+" ");
		}
		System.out.println(" \nGidilecek yolun toplam ücreti ");
		System.out.println(minimum);
	
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("Toplam süre:"+timeElapsed*0.001+" saniye");
		return possiblePaths;
	}

	// Driver code 
	public static void main(String[] args) 
	{ 
		ArrayList<ArrayList<Integer>> yeni=new ArrayList<ArrayList<Integer>>();
		/*double mList[]={0.0, 61.5,  120.0,147.5,  161.0,179.5,  219.0,278.5,  297.0,321.5,  336.0,393.5,  426.0,442.5,  490.0,548.5,  607.0,661.5,  718.0,764.5,  799.0 ,801};
		double pList[]={ 0.0, 15.4, 19.4,  5.4, 10.8,  6.6, 11.4,  7.4, 18.5,  9.2, 17.5, 14.2,  6.6, 13.2, 15.2, 17.8, 15.9, 15.3,  5.7, 19.6, 14.7,0.0};*/
		/*double mList[]= {0.0,4.0,6.0,10.0,12.0,14.0,18.0,21.0,26.0,32.0,36.0,42.0,47.0,51.0,55.0,59.0,62.0,66.0,70.0,72.0,78.0};
		double pList[]= {0.0,3.0,5.0,5.0,1.0,3.0,1.0,1.0,4.0,4.0,2.0,4.0,5.0,5.0,2.0,1.0,1.0,2.0,1.0,1.0,0.0};*/
		/*double mList[]={0.0,  38.0,    74.0 , 87.0  , 106.0 ,155.0 ,  197.0, 238.0,268.0};
	    double pList[]={0.0,  14.0 , 11.0 , 15.0,   7.0,  20.0,    10.0,   8.0,0.0};*/
		/*double f= 15.0;
		double k= 5.0;	*/
		/*double mList[] = { 0.0,  38.0,   70.0 , 87.0 ,120.0,150.0};
	    double pList[] = { 0.0,  14.0 , 15.0 , 18.0, 10.0,0.0 };
		double f= 70.0;
		double k= 35.0;	*/
		/*double mList[] = { 0.0,  38.0,   70.0 , 87.0 ,120.0,150.0,169.5,200.5,210.1};
	    double pList[] = { 0.0,  14.0 , 15.0 , 9.0, 10.0,9.0,4.0,7.0,0.0};	*/
		/*double mList[] = {0.0, 26.5,   48.0, 78.5,  124.0,160.5,  219.0,263.5,  296.0,312.5,  366.0,390.5,  447.0,479.5,  503.0,564.5,  611.0,645.5,  699.0,759.5,  794.0,849.5,  898.0,922.5,  940.0,963.5,  990.0,1006.5, 1059.0,1098.5, 1127.0,1180.5, 1205.0,1262.5, 1320.0,1337.5, 1371.0,1425.5, 1469.0,1501.5, 1536.0,1548.5, 1600.0,1634.5, 1670.0,1715.5, 1757.0,1803.5, 1846.0,1875.5,1900.5};
		double pList[] = {0.0, 13.5,   16.0, 17.3,  9.5,   12.0, 13.2, 16.4,  5.3, 16.4, 18.5, 19.9, 17.8, 10.1,  7.7, 10.5, 17.5, 12.1,  8.4,   16.0,  5.1,    7.0,  5.7,   15.0,  5.1, 11.8, 18.3, 10.5,  7.5, 12.9, 15.7, 19.8, 13.8, 19.7, 19.3, 16.2, 13.2,  7.4, 13.3,  6.3, 10.9,  6.9, 17.5, 15.9,  6.3,  5.8, 10.2, 16.2, 13.5,  9.5,0.0};*/
		/*double mList[]= {0.0,10.4,18.3,28.9,39.41,55.10,57.25,64.50,68.8,70.5};
		double pList[]= {0.0,3.7,4.1,3.6,4.8,3.17,3.20,3.21,3.33,0.0};*/
		/*double mList[]= {0,8,16,24.14,30,35,39,41,43,49,50,51,58,65,71,75,80,84,89,95,100};
		double pList[]= {0.0,1.2,1.3,2.15,1.9,2.8,1.54,2.0,1.13,2.0,1.88,1.74,1.56,1.44,1.1,1.25,1.37,1.36,1.39,1.50,0.0};*/
	    double f= 25.0;
		double k= 15.0;
		/*double mList[]= {0.0,5.0,7.0,9.0,11.0,13.0,15.0};
		double pList[]= {0.0,6.0,8.0,7.0,3.0,4.0,0.0};*/
		//double []mList=new double[10];
		double mList[]= {0.0,10.4,18.3,28.9,39.41,55.10,57.25,64.50,68.8,70.5};
		double pList[]= {0.0,3.7,4.1,3.6,4.8,3.17,3.20,3.21,3.33,0.0};
		BruteForce a=new BruteForce();
		System.out.println("mList");
		for(int i=0;i<mList.length;++i)
			System.out.print(mList[i]+"\t");
		System.out.println("");
		System.out.println("pList");
		for(int j=0;j<pList.length;++j)
			System.out.print(pList[j]+"\t");
		System.out.println("");
		System.out.println("k:"+k);
		System.out.println("f:"+f);
		System.out.println("input size : "+mList.length);
		yeni=a.bruteForce(mList,pList,k,f);
	} 
} 

