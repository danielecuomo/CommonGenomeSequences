/*********************************************
 * OPL 12.8.0.0 Model
 * Author: Daniele Cuomo
 * Creation Date: 11/mar/2019 at 16:44:44
 *********************************************/

ordered {int} alphabet = ...;

int gSize = ...;
int hSize = ...;
range gRange = 1..gSize;
range hRange = 1..hSize;

int G[gRange] = ...;
int H[hRange] = ...;

tuple pair{ int i; int j; }

tuple dpair{ pair a; pair b; }

{pair} Ea[a in alphabet] = {<i,j> | i in gRange : G[i] == a, j in hRange : H[j] == a};
sorted {pair} E = union(a in alphabet) Ea[a];
{dpair} conflicts = {<<i,j>,<k,l>> | <i,j> in E, <k,l> in E : i<k && j>l};

dvar boolean z[<i,j> in E];

maximize sum(<i,j> in E) z[<i,j>];
subject to
{
		forall(a in alphabet)
		  	a: sum(<i,j> in Ea[a]) z[<i,j>] <= 1;		

		forall(<<i,j>,<k,l>> in conflicts)
			b:  z[<i,j>] + z[<k,l>] <= 1;
}

execute POST_PROCESS
{
	writeln("\nf.o. = ", cplex.getObjValue());
	
	var result = "[";
	for(var p in E)
		if(z[p] == 1) result += p + " ";
	writeln(result + "]");
}
