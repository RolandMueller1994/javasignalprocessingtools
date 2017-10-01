x = 1:1000; 
nCoefs = 80; 
Fg = 0.3;
y = sin(10*x) + sin(100*x) + sin(500*x) + sin(3000*x); 
y_conv = f_filter(y, Fg, nCoefs);


tau = -(nCoefs-1)/2 : (nCoefs-1)/2;
hlp = sinc(2*Fg*tau); %calculation of coefs
y_canon = f_filter_canon2( hlp, y); 

% import file 

