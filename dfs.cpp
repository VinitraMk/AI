#include<iostream>
#include<cstdio>
#include<cstring>
#include<cmath>
#include<cstdlib>
#include<algorithm>
#include<vector>
#include<set>
#include<map>
#include<utility>
#include<queue>
#include<numeric>
#include<ctime>
#include<climits>
#define inp(a) scanf("%lld",&a)
#define fi(i,a,b) for(i=a;i<b;i++)
#define fin(i,a,b) for(i=a;i>=b;i--)
#define ll long long
#define N 100000
#define m(a,b) (a>b? b : a)
#define M(a,b) (a>b? a : b)
#define mod 1000000007
using namespace std;
vector<pair<ll,ll>> adj[N+1];
map<string,ll> nodes;
map<ll,string> nodes1;
bool vis[N+1]={false};
pair<ll,ll> path[N+1];
ll pi=0;
ll cost=0;

void dfs(ll s,ll d,ll c) {
    vis[s]=true;
    path[pi]=make_pair(s,c);
    pi++;
    if(s==d) {
        cout<<"DFS path is: ";
        for(ll i=0;i<pi;i++) {
            cost+=path[i].second;
            cout<<nodes1[path[i].first]<<" ";
        }
        cout<<endl<<"The cost of the path is: "<<cost<<endl;
        return;
    }
    else {
        for(auto it=adj[s].begin();it!=adj[s].end();it++) {
            pair<ll,ll> p = *it;
            if(!vis[p.first]) 
                dfs(p.first,d,p.second);
        }
    }
    vis[s]=false;
    pi--;
}
int main() {
    ll i,j,k,t,e,m,n,c;
    printf("Enter no of nodes: ");
    scanf("%lld",&n);
    printf("Enter no of edges: ");
    scanf("%lld",&m);
    string l,r;
    ll c1,c2;
    k=1;

    for(i=0;i<m;i++) {
        cin>>l>>r>>e;
        if(nodes[l]==0) {
            nodes[l]=k;
            nodes1[k]=l;
            k++;
        }
        if(nodes[r]==0) {
            nodes[r]=k;
            nodes1[k]=r;
            k++;
        }
        adj[nodes[l]].push_back(make_pair(nodes[r],e));
        adj[nodes[r]].push_back(make_pair(nodes[l],e));
    }
    set<pair<ll,ll>> se;
    cout<<endl<<endl;

    printf("Enter source and dest: ");
    cin>>l>>r;
    c=0;
    dfs(nodes[l],nodes[r],c);
    return 0;
}

