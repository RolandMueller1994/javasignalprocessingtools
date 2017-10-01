function blackmanharris = f_blackmanharris(cTaps) 
  a0=0.35875; a1=0.48829; a2=0.14128; a3=0.01168; 
  % sum_ai = a0 + a1 + a2 + a3 % should deliver 1 
  % dif_ai = a0 - a1 + a2 - a3 % should deliver 6e-5 
  t=0:cTaps-1; 
  phi=2*pi*t/(cTaps-1); 
  blackmanharris = a0 - a1*cos(phi) + a2*cos(2*phi) - a3*cos(3*phi); 