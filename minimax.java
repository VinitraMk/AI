import java.util.*;
import java.io.*;
import java.lang.*;
class node {
    char[][] state = new char[3][3];
    ArrayList<node> children;
    int x,o,status;

    public node() {
    }

    public node(char[][] state) {
        this.state=state;
        this.children = new ArrayList();
        this.status=0;
        x=0;o=0;
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(state[i][j]=='x')
                    this.x=this.x+1;
                else if(state[i][j]=='o')
                    this.o=this.o+1;
            }
        }
    }

    public char toPlay() {
        if(x==o)
            return 'x';
        else
            return 'o';
    }

    public void addChild(node n) {
        children.add(n);
    }

    public ArrayList<node> getChildren() {
        return this.children;
    }
    
    public void setStatus(int p) {
        this.status=p;
    }

    public int getStatus() {
        return this.status;
    }

    public int getCount() {
        return (this.x+this.o);
    }

    public void setPointWrtChildren() {
        ArrayList<Integer> arr = new ArrayList();
        for(node nn:children)
            arr.add(nn.getStatus());
        if(toPlay()=='x')
            status=Collections.max(arr);
        else
            status=Collections.min(arr);
    }

    public char[][] getMat() {
        return this.state;
    }

}
class minimax {
	public static void main(String args[]) {
        char[][] mat = { {'x','o','x'},
                         {'o','o','x'},
                         {'-','-','-'}
        };

        node start = new node(mat);
        printMat(mat);
        solve(start);
        System.out.println("Solution: "+start.getStatus());
    }

    public static void solve(node start) {

        int i,j,k,l;
        int ss = isSol(start.getMat());
        char[][] board = start.getMat();
        char[][] temp = new char[3][3];
        if(ss!=0) {
            start.setStatus(ss);
            return;
        }

        else if(start.getCount()==9) {
            start.setStatus(0);
            return;
        }

        for(i=0;i<3;i++) {
            for(j=0;j<3;j++) {
                if(board[i][j]=='-') {
                    for(k=0;k<3;k++) {
                        for(l=0;l<3;l++)
                            temp[k][l]=board[k][l];
                    }
                    temp[i][j]=start.toPlay();
                    printMat(temp);
                    node nn = new node(temp);
                    start.addChild(nn);
                    solve(nn);
                }
            }
        }
        start.setPointWrtChildren();
    }

	public static void printMat(char[][] mat) {
        int i,j;
        for(i=0;i<3;i++) {
            for(j=0;j<3;j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------");
    
    }

    public static int isSol(char[][] mat) {
        int i,j;
        for(i=0;i<3;i++) {
            if(mat[i][0] == mat[i][1] && mat[i][1] == mat[i][2]) {
                if(mat[i][0]=='x') return 1;
                else if(mat[i][0]=='o') return -1;
            }
        }
        
        for(i=0;i<3;i++) {
            if(mat[0][i] == mat[1][i] && mat[1][i] == mat[2][i]) {
                if(mat[0][i]=='x') return 1;
                else if(mat[0][i]=='o') return -1;
            }
        }
        
        if(mat[0][0] == mat[1][1] && mat[1][1] == mat[2][2]) {
                if(mat[0][0]=='x') return 1;
                else if(mat[0][0]=='o') return -1;
            }
        if(mat[0][2] == mat[1][1] && mat[1][1] == mat[2][0]) {
                if(mat[2][0]=='x') return 1;
                else if(mat[2][0]=='o') return -1;
            }
        
        return 0;  

    }
}

	

