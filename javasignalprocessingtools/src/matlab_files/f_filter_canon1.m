function y = f_filter_canon1(coefs,x)
% Initializations and Reset
nCoefs    = length(coefs); 	% Tap-Anzahl der Impulsantwort	
state     = zeros(1,nCoefs); 	% State-Vektor erstellen + initialisieren.
NextState = state; 	% NextState-Vektor erstellen	
for n=1:length(x)
  % NextState-Logic:		
  NextState(nCoefs)=coefs(nCoefs)*x(n);
  for i=1:nCoefs-1;
    NextState(i) = state(i+1) + coefs(i)*x(n);
  end;
  % apply active clock edge	
  state = NextState;
  % Output-Logic: 
  y(n) = state(1);
end; % end of loop over time index n
