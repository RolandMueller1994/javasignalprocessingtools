t=0:1e-3:1;
w1 = 100;
w2 = 2000;
w3 = 100;
yt = sin(w1.*t).*exp(-t.*5);
figure(1)
subplot(3,1,1)
plot(t,yt)
ylabel('y(t)')
xlabel('t')

Yt = fft(yt);
HYt = hilbert(yt);

subplot(3,1,2)
plot(t,real(HYt))
ylabel('Re H-Trafo')
xlabel('t')

subplot(3,1,3)
plot(t,imag(HYt))
ylabel('Im H-Trafo')
xlabel('t')

figure(2)
hyt = ifft(HYt);
subplot(3,1,1);
plot(t, hyt);
ylabel('hy(t)')

hk = sqrt((yt.^2)+(hyt.^2));
subplot(3,1,2)
plot(t,hk)
ylabel('Hüllkurve')

% hk mandl
for(i=1:length(Yt))
    realYt = real(Yt);
    imagYt = imag(Yt);
end
          
        
for(i=1:length(t)/2)
    realHt(i) = imagYt(i);
    imagHt(i) = -realYt(i);
end

for(i=round(length(t)/2):length(t))
    realHt(i) = -imagYt(i);
    imagHt(i) = realYt(i);
end
i = sqrt(-1);
Ht = realHt + i*imagHt;
ht = ifft(Ht);

hk = sqrt(imag(ht).^2 + yt.^2);

subplot(3,1,3)
plot(t, hk)


