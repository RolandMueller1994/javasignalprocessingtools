function y = f_filter_canon2(coefs,x)
% Initializations incl. Reset
nCoefs    = length(coefs); % get # of Taps of impulse response
state     = zeros(1,nCoefs); % generate and initialize state
NextState = zeros(1,nCoefs); % generate nextstate
for n=1:length(x)
  % NextState-Logic
  NextState(1)=x(n);
  NextState(2:nCoefs) = state(1:nCoefs-1);
  % apply active clock edge
  state=NextState;
  % Output-Logic: compute y(n)
  y(n) = state*coefs';
end;