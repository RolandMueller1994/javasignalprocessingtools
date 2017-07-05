t=0:1e-3:1;
w1 = 100;
w2 = 2000;
w3 = 100;
yt = sin(w1.*t).*exp(-t.*5);
%bm = blackman(length(t));
%yt = yt.*bm';
figure(1)
subplot(3,1,1)
plot(t,yt)
ylabel('y(t)')
xlabel('t')

Yt = fft(yt);
HYt = hilbert(yt);

subplot(3,1,2)
plot(t,real(HYt))

subplot(3,1,3)
plot(t,imag(HYt))


figure(2) 
plot(t,real(HYt), t,imag(HYt))
legend('real','imaginary')

figure(3)
hyt = ifft(HYt);
subplot(3,1,1);
plot(t, hyt);
ylabel('hy(t)')

hk = sqrt((yt.^2)+(hyt.^2));
subplot(3,1,2)
plot(t,hk)
ylabel('Hüllkurve')

% hk mandl
for(i=1:length(t));
    {
    if  i<=(length(t)/2)
    {
    f(i)=-1;
        }
    else
        {
          f(i) = 1
          }
        }
HYf = Yt.*(-i*sign(f));
hyf = ifft(HYf);
subplot(3,1,3)
plot(t, hyf)


