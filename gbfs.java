import java.util.*;
import java.io.*;
import java.lang.*;
class node {
    ArrayList<node> children;
    String name;
    boolean isVis;
    int ecost;
    String path;
    int heucost;
    int pathcost;
    int cumhue;

    public node() {
    }

    public node(String name,int heucost) {
        this.name=name;
        this.children=new ArrayList();
        this.isVis=false;
        this.heucost=heucost;
        this.path="";
        this.ecost=0;
        this.pathcost=0;
        this.cumhue=0;
    }

    public String getName() {
        return this.name;
    }

    public void addChild(node n) {
        children.add(n);
    }

    public void addPath(String path,int c,int hc) {
        this.path=path;
        this.pathcost=c;
        this.cumhue=hc;
    }
    public String getPath() {
        return this.path;
    }

    public ArrayList<node> getChildren() {
        return this.children;
    }
    public int getHeu() {
        return this.heucost;
    }
    public int getEcost() {
        return this.ecost;
    }
    public int getPathCost() {
        return this.pathcost;
    }
    public void setEdgeCost(int c) {
        this.ecost=c;
    }
    public void setCumHue(int c) {
        this.cumhue=c;
    }
    public int getCumHue() {
        return this.cumhue;
    }
    public void removePath() {
        this.path="";
        this.pathcost=0;
    }
}

class gbfs{
    static int i,j,k,n,c;
    static String name,dest,src;
    static HashMap<String,node> hp = new HashMap();
    static ArrayList<String> arrn = new ArrayList();
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter State Space Tree: ");
        n=sc.nextInt();
        for(i=0;i<n;i++) {
            System.out.println("Enter node name and heuristic: ");
            name=sc.next();
            k=sc.nextInt();
            arrn.add(name);
            hp.put(name,new node(name,k));
        }
        for(String s1:arrn) {
            System.out.println("Enter no of children: ");
            c=sc.nextInt();
            for(j=0;j<c;j++) {
                name=sc.next();
                k=sc.nextInt();
                hp.get(name).setEdgeCost(k);
                hp.get(s1).addChild(hp.get(name));
            }
        }

        System.out.print("Enter source and destination: ");
        src=sc.next();
        dest=sc.next();
        hp.get(src).setCumHue(hp.get(src).getHeu());
        int thc=hp.get(src).getCumHue();
        hp.get(src).addPath(src+"->",0,thc);
        Gbfs(hp.get(src));
        System.out.println("\nPath: "+hp.get(dest).getPath()+", Cost: "+hp.get(dest).getPathCost());
	}

    public static void Gbfs(node start) {
        ArrayList<node> ch = start.getChildren();
        String prevPath = start.getPath();
        int prevPathCost = start.getPathCost();
        int precumHue = start.getCumHue();
        //System.out.println(prevPath+" "+precumHue);
        if(ch.size()==0)
            return;

        for(node chi:ch) {
            String newPath = prevPath + chi.getName() + "->";
            int cecost = chi.getEcost();
            int newPathCost = prevPathCost + cecost;
            int newCumHeu = precumHue + chi.getHeu();
            if(chi.getPath().equals("")) {
                chi.addPath(newPath,newPathCost,newCumHeu);
            }
            else {
                int chiPathHeu = chi.getCumHue();
                if(chiPathHeu > newCumHeu) {
                    chi.removePath();
                    chi.addPath(newPath,newPathCost,newCumHeu);
                }
            }
            Gbfs(chi);
        }
    }
}

