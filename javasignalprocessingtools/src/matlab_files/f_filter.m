function y = f_filter(x, Fg, nCoefs)

tau = -(nCoefs-1)/2 : (nCoefs-1)/2;
hlp = sinc(2*Fg*tau); % Impulse response of ideal lowpass
hlp = hlp .* f_blackmanharris(nCoefs); % Use Blackman-Harris Window
hlp = hlp/sum(hlp); % scale to DC-Amplification 1 or 0dB

y = conv(hlp, x);
