disease(huntingtons,personality_change).
disease(huntingtons,mood_swings).
disease(huntingtons,depression).
disease(huntingtons,memory_loss).
disease(huntingtons,impaired_judgement).
disease(huntingtons,unsteady_gait).
disease(huntingtons,involuntary_movements).
disease(huntingtons,slurred_speech).
disease(huntingtons,swallowing_problem).
disease(huntingtons,weight_loss).

disease(als,slurred_speech).
disease(als,swallowing_porblem).
disease(als,breathing_problem).
disease(als,muscle_weakness).
disease(als,muscle_stiffness).
disease(als,muscle_paralysis).
disease(als,facciculations).
disease(als,pseudobulbar_effect).
disease(als,unsteady_gait).

disease(parkinsons,unsteady_gait).
disease(parkinsons,anosmia).
disease(parkinsons,nerve_pain).
disease(parkinsons,constipation).
disease(parkinsons,erectile_dysfuntion).
disease(parkinsons,swallowing_problem).
disease(parkinsons,insomnia).
disease(parkinsons,depression).
disease(parkinsons,dementia).
disease(parkinsons,tremor).

disease(alzheimers,depression).
disease(alzheimers,mood_swings).
disease(alzheimers,memory_loss).
disease(alzheimers,hallucinations).
disease(alzheimers,swallowing_problem).
disease(alzheimers,delusional_memory).
disease(alzheimers,impaired_judgement).
disease(alzheimers,weight_loss).
disease(alzheimers,disturbed_sleep).


start:-
         writef("Enter 5 symptoms: "),nl,
         read(S1),
         read(S2),
         read(S3),
         read(S4),
         read(S5),nl,
         findall(X,(disease(X,S1),disease(X,S2),disease(X,S3),disease(X,S4),disease(X,S5)),Z),
         writef("Disease diagnosed is:"),
         write(Z).
