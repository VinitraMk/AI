man(tywin).
man(robert).
man(jaime).
man(tyrion).
man(unnamed).
man(joffrey).
man(tommen).

woman(joanna).
woman(cersei).
woman(sansa).
woman(margaery).
woman(myrcella).

parent(tywin,jaime).
parent(joanna,jaime).
parent(tywin,cersei).
parent(joanna,cersei).
parent(tywin,tyrion).
parent(joanna,tyrion).
parent(jaime,joffrey).
parent(cersei,joffrey).
parent(jaime,myrcella).
parent(cersei,myrcella).
parent(jaime,tommen).
parent(cersei,tommen).
parent(robert,unnamed).
parent(cersei,unnamed).

father(F,C):-man(F),parent(F,C).
mother(M,C):-woman(M),parent(M,C).

grandfather(GF,C):-man(GF),parent(GF,X),parent(X,C).
grandmother(GM,C):-woman(GM),parent(GM,X),parent(X,C).


full_sibling(X,Y):-father(F,X),father(F,Y),mother(M,X),mother(M,Y),X\=Y.

child(X,P):-parent(P,X).

children(C,X,Y):-parent(X,C),parent(Y,C),child(C,X).

sister(S,Y):-woman(S),full_sibling(S,Y),S\=Y.
brother(S,Y):-man(S),full_sibling(S,Y),S\=Y.

half_sibling(X,Y) :-
    parent(A,X),
    parent(A,Y),
    parent(B,X),
    parent(C,Y),
    not(A = B),
    not(A = C),
    not(B = C).

