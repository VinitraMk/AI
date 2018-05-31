import java.util.*;
import java.io.*;
import java.lang.*;
class Sol implements Comparable<Sol>{
    int n,p,w;
    float ful;
    int[] a;
    public Sol() {
    }
    
    public Sol(float ful,int p,int w,int n,int a[]) {
        this.ful=ful;
        this.p=p;
        this.w=w;
        this.n=n;
        this.a=a;
    }

    public int compareTo(Sol s) {
        if(ful>s.ful)
            return -1;
        else if(ful<s.ful)
            return 1;
        else
            return 0;
    }
}

class knapsackgenetic {
    static int n,m;
    static TreeSet<Sol> sol = new TreeSet<Sol>();
    static int[] w = new int[101];
    static int[] p = new int[101];
    static int itr=1;
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Random rn = new Random();
        int i,j;
        System.out.print("Enter n: ");
        n=sc.nextInt();
        System.out.print("\nEnter capacity of knapsack: ");
        m=sc.nextInt();
        System.out.print("\nEnter weight and profits:\n");
        for(i=0;i<n;i++) {
            w[i]=sc.nextInt();
            p[i]=sc.nextInt();
        }

        constructSol();
        String res;
        do {
            iterations();
            printBest();
            System.out.print("Would you like to perform more iterations [yes/no]: ");
            res=sc.next();
            System.out.println();
            itr++;
        }while(res.equalsIgnoreCase("yes"));
	}

    public static void constructSol() {
        Random rn =new Random();
        int i,j,k;
        while(sol.size()<n) {
            int s[] = new int[n];
            int pf=0,wt=0;
            for(j=0;j<n;j++) {
                s[j]=rn.nextInt(2);
                pf+=s[j]*p[j];
                wt+=s[j]*w[j];
            }
            float ful=(((float)wt)/((float)m))*100;
            if(wt<=m && wt>0)
                sol.add(new Sol(ful,pf,wt,n,s));
        }

        System.out.println("\nInitial population");
        printBest();
    }

    public static void printBest() {
        System.out.println("Fitness  Profit  Weight\tSolution");
        for(Sol sols:sol) {
            System.out.printf("%.2f",sols.ful);
            System.out.print("\t"+sols.p+"\t"+sols.w+"\t");
            for(int i1:sols.a)
                System.out.print(i1+" ");
            System.out.println();
        }

        Sol st=sol.first();
        System.out.println("\nBest Solution:");
        System.out.println("Fitness Profit  Weight\tSolution");
        System.out.printf("%.2f",st.ful);
        System.out.print("\t"+st.p+"\t"+st.w+"\t");
        for(int i1:st.a)
            System.out.print(i1+" ");
        System.out.println("\n================================================================\n");
    }

    public static void iterations() {
        Random rn = new Random();
        int sz,i,j,k,l,pf,wt;
        float ful;
         
        System.out.println("Iteration "+itr);
        sz=sol.size();
        k=sz/2+((sz%2==0)?0:1);
        Sol s1,s2;
        int r1,r2;
        do {
            r1=rn.nextInt(k);
            r2=rn.nextInt(k);
        }while(r1==r2);

        s1=getSol(r1);
        s2=getSol(r2);

        //Crossover
        k=rn.nextInt(n);
        int a1[] = new int[n];
        int a2[] = new int[n];


        for(i=0;i<n;i++) {
            if(i<k) {
                a1[i]=s1.a[i];
                a2[i]=s2.a[i];
            }
            else {
                a1[i]=s2.a[i];
                a2[i]=s1.a[i];
            }
        }

                
        //Mutation
        k=rn.nextInt(n);
        a1[k]=(a1[k]==1?0:1);
        a2[k]=(a2[k]==1?0:1);

        pf=0;
        wt=0;
        for(i=0;i<n;i++) {
            pf+=(a1[i]*p[i]);
            wt+=(a1[i]*w[i]);
        }
        ful=(((float)wt)/((float)m))*100;
        if(wt<=m && wt>0)
            sol.add(new Sol(ful,pf,wt,n,a1));
        pf=0;
        wt=0;
        for(i=0;i<n;i++) {
            pf+=(a2[i]*p[i]);
            wt+=(a2[i]*w[i]);
        }
        ful=(((float)wt)/((float)m))*100;
        if(wt<=m && wt>0)
            sol.add(new Sol(ful,pf,wt,n,a2));
    }

    public static Sol getSol(int k) {
        int i=0;
        for(Sol s3:sol) {
            if(i==k)
                return s3;
            i++;
        }
        return new Sol();
    }
}

