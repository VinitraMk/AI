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
ll cost=0;
string path1="";
vector<ll> path;
void bfs(ll s,ll d) {
    queue<vector<pair<ll,ll>>> q;
    vector<pair<ll,ll>> path;
    path.push_back(make_pair(s,0));
    q.push(path);
    while(!q.empty()) {
        path=q.front();
        q.pop();
        ll last=path[path.size()-1].first;
        if(last==d) {
            cout<<"The BFS path is: ";
            for(ll i=0;i<(ll)path.size();i++) {
                cout<<nodes1[path[i].first]<<" ";
                cost+=path[i].second;
            }
            cout<<endl<<"The cost of the path is: "<<cost<<endl;
            cost=0;
            return;
        }

        for(auto it=adj[last].begin();it!=adj[last].end();it++) {
            pair<ll,ll> p=*it;
            if(find(path.begin(),path.end(),p)==path.end()) {
                vector<pair<ll,ll>> np(path);
                np.push_back(p);
                q.push(np);
            }
        }
    }
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
    bfs(nodes[l],nodes[r]);
    return 0;
}

