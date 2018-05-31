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
#include<bitset>
#include<numeric>
#define ll long long
#define inf 0x3f3f3f3f
#define pb push_back
#define mp make_pair
#define ipair pair<ll,ll>
#define inp(a) scanf("%lld",&a)
#define out(a) printf("%lld\n",a)
#define allv(a) a.begin(),a.end()
#define alla(a,n) a,a+n
#define fi(i,a,b) for(ll i=a;i<b;i++)
#define N 100000
#define set(n,x) x|=(1<<n)
#define unset(n,x) x&=~(1<<n)
#define chkbit(n,x) (x&(1<<n))!=0
#define m(a,b) (a>b? b : a)
#define M(a,b) (a>b? a : b)
#define mod 1000000007
using namespace std;
vector<pair<ll,ll>> adj[N+1];
//ll heur[1001][1001];
ll heur[1001];
map<string,ll> nodes;
map<ll,string> nodes1;
bool vis[N+1]={false};
pair<ll,ll> path[N+1];
ll pi=0;
ll cost=0;
set<pair<pair<ll,ll>,ll>> se;

bool notVisited(ll x,vector<pair<ll,ll>> path) {
    ll sz=path.size();
    for(ll i=0;i<sz;i++) {
        if(path[i].first==x)
            return false;
    }
    return true;
}

/*void gbfs(ll s,ll d,ll n) {
    queue<vector<pair<ll,ll>>> q;
    vector<pair<ll,ll>> path;
    path.push_back(make_pair(s,0));
    q.push(path);
    while(!q.empty()) {
        path=q.front();
        q.pop();
        //ll last=path[path.size()-1];
        pair<ll,ll> last = path[path.size()-1];
        //cout<<last.first<<" "<<last.second<<endl;
        if(last.first==d) {
            cout<<"The best first search path is: ";
            for(ll i=0;i<path.size();i++)  {
                cout<<nodes1[path[i].first]<<" ";
                cost+=path[i].second;
            }
            cout<<endl<<"Cost is: "<<cost<<endl;
            return;
        }
        //set<pair<pair<ll,ll>,ll>> se;
        for(auto it=adj[last.first].begin();it!=adj[last.first].end();it++) {
            pair<ll,ll> p =*it;
            //se.insert(make_pair(heur[p.first][d],p.first));
            if(notVisited(p.first,path)) {
                se.insert(make_pair(make_pair(heur[p.first],p.first),p.second));
            }
        }
        auto it=se.begin();
        pair<pair<ll,ll>,ll> p = *it;
        se.erase(it);
        vector<pair<ll,ll>> newpath(path);
        newpath.push_back(make_pair(p.first.second,p.second));
        q.push(newpath);
    }
}*/

void gbfs(ll s,ll d,ll c) {
    vis[s]=true;
    path[pi]=make_pair(s,c);
    pi++;
    if(s==d) {
        cout<<"The path with gbfs is: ";
        for(ll i=0;i<pi;i++) {
            cost+=path[i].second;
            cout<<nodes1[path[i].first]<<" ";
        }
        cout<<"\nCost is: "<<cost<<endl;
        return;
    }
    else {
        if(adj[s].size()==0)
            pi=1;
        for(auto it=adj[s].begin();it!=adj[s].end();it++) {
            pair<ll,ll> p=*it;
            se.insert(make_pair(make_pair(heur[p.first],p.first),p.second));
        }
    }
    auto it=se.begin();
    pair<pair<ll,ll>,ll> p=*it;
    se.erase(it);
    gbfs(p.first.second,d,p.second);
    vis[s]=false;
    pi--;
}

int main() {
    ll i,j,k,m,n,t,e,c;
    printf("Enter no of nodes: ");
    scanf("%lld",&n);
    cout<<n<<endl;

    printf("Enter no of edges: ");
    scanf("%lld",&m);
    cout<<m<<endl;
    string l,r;
    ll c1,c2;
    k=1;

    printf("Enter the graph: \n");
    for(i=0;i<m;i++) {
        cin>>l>>r>>e;
        cout<<l<<" "<<r<<" "<<e<<endl;
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
    }
    set<pair<ll,ll>> se;
    cout<<endl<<endl;

    printf("Enter source and dest: ");
    cin>>l>>r;
    cout<<l<<" "<<r<<endl;

    printf("Enter heuristics matrix: \n");
    for(i=1;i<=n;i++) 
        cin>>heur[i];
    cout<<endl;
    gbfs(nodes[l],nodes[r],0);
    return 0;
}

