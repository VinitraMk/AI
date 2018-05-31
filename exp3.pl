actor(allison).
actor(kate).
actor(richard).
actor(kendra).
actor(marvin).
actor(kyle).
actor(jonathan).
actor(harrison).

victim(allison,shot_in_chest).
victim(kendra,strangled_to_death).
victim(marvin,stabbed_to_death).

motive(harrison,money).
motive(harrison,revenge).

motive(kyle,deranged_serial_killer).

planted_evidence_against(kyle).

evidence(harrison,fake_passport_for_fake_allibi).
evidence(harrison,blab).

suspect(X):-motive(X,_).

innocent(X):-actor(X),planted_evidence_against(X).

culprit(X):-suspect(X),motive(X,_),evidence(X,_).

crime_list(X):-victim(_,X).

victim_list(X):-victim(X,_).

evidence_list(X,Y):-evidence(X,Y).

motive_list(X,Y):-motive(X,Y).


