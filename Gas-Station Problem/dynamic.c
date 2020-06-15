
/*Fatih Dursun Üzer 1812020501
Bilgisayar Mühendisliði 2.sýnýf*/

#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <time.h>
void dynamicProgramming(int mList[], int pList[], int size, int k, int f) {
    clock_t begin = clock();
    const int N = size;
    const int M = size;
    int tablo[M][N];
    for (int i = 0; i < M; ++i)
    {
        for (int j = 0; j < N; ++j)
            tablo[i][j] = -1;
    }
    for (int i = 0; i < M; ++i)
    {
        for (int j = i + 1; j < N; ++j)
        {
            int fark = abs(mList[j] - mList[i]);
            if ((fark >= k && fark <= f)) {
                tablo[i][j] = pList[j];
            }
        }
    }
    for (int i = 1; i < M; ++i)
    {
        int sayac = 0;
        for (int a = 0; a < i; ++a)
        {
            if (tablo[a][i] != -1)
                sayac++;
        }
        for (int j = i + 1; j < N; ++j)
        {
            if (sayac == 0)
                tablo[i][j] = -1;
        }
    }
    printf("\n");
    for (int i = 0; i < M; ++i)
    {
        for (int j = i + 1; j < N; ++j)
        {
            if (tablo[i][j] > -1)
            {
                int minimum = 0;
                for (int c = 0; c < i; ++c)
                {
                    if (minimum == 0 && tablo[c][i] != -1)
                    {
                        minimum = tablo[c][i];
                        continue;
                    }
                    if (tablo[c][i] <= minimum && tablo[c][i] != -1)
                    {
                        minimum = tablo[c][i];
                    }
                }
                tablo[i][j] += minimum;
            }
        }
    }
    for (int i = 0; i < M; ++i)
    {
        for (int j = 0; j < N; ++j)
        {
            printf("%d ", tablo[i][j]);
        }
        printf("\n");
    }
    int min = 9999;
    for (int i = 0; i < M; ++i)
    {
        printf("%d  ", tablo[i][size - 1]);
        if (tablo[i][size - 1] < min && tablo[i][size - 1] != -1)
            min = tablo[i][size - 1];
    }
    printf("\n");
    printf("minimum maliyet :%d ", min);
    clock_t end = clock();
    double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
    printf("toplam harcanan zaman %lf \t", time_spent);
}

int main()
{
    /*int mList[] = { 0,  38,    74 , 87  , 106 ,155 ,  197, 238,268 };
    int pList[] = { 0,  14 , 15 , 15,   7,  20,    10,  8,0 };*/
    /*int mList[] = { 0, 26, 64, 88, 133, 169, 225, 255, 301, 336, 385, 426, 456, 488, 515, 562, 577, 594, 633, 667,692 };
    int pList[] = { 0, 8, 11, 11, 11, 14, 19, 6, 15, 5, 9, 5, 9, 16, 19, 5, 18, 17, 8, 18,0 };*/
    /*int mList[] = { 0,8,16,24,30,35,39,41,43,49,50,51,58,65,71,75,80,84,89,95,100,105,109,110,115,119,143,130,135,136,138,140,147,155,160,165,171,178,184,190,192,198,200 };
    int pList[] = { 0,6,8,7,3,4,10,12,11,14,10,13,14,17,15,16,21,17,17,18,13,25,24,20,14,17,10,15,20,26,33,25,31,17,27,26,24,23,25,24,28,33,0 };*/
    int mList[]= {0,5,7,9,11,13,15};
    int pList[]= {0,6,8,7,3,4,0};
    /*int mList[] = { 0.0,10,18,29,39,55,57,65,69,71 };
    int pList[] = { 0.0,4,4,4,5,3,5,6,4,0.0 };*/

    int f = 7;
    int k = 2;
    dynamicProgramming(mList, pList, 7, k, f);
    // 0 1 3 5 6 7
}