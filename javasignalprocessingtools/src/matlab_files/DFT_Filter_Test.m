t = 0:1e-3:1;
N = length(t);
w1 = 100;
w2 = 500;
w3 = 1000;
yt = sin(w1.*t)+sin(w2.*t)+sin(w3.*t);
figure(1); 
subplot(3,1,1)
plot(t, yt)

YT = fft(yt);
subplot(3,1,2)
plot(t(1:length(t)/2)*N*2*pi,abs(YT(1:length(t)/2)))

subplot(3,1,3)
plot(t(1:length(t)/2),(180/pi).*angle(YT(1:length(t)/2)))

fileID = fopen('TestFFT_Matlab_Out.txt','w');
fprintf(fileID,'%6.6f\n',yt);
fclose(fileID);

figure(2);
subplot(2,1,1)
plot(t(1:length(t)/2)*N*2*pi,abs(YT(1:length(t)/2)))
ylabel('Matlab FFT')
xlabel('f in Hz')

[real, imag] = importfile('TestFFT_Matlab_in.txt', 1, N);
YT_Mandl = real+i.*imag;
subplot(2,1,2) 
plot(t(1:length(t)/2)*N*2*pi,abs(YT_Mandl(1:length(t)/2)))
ylabel('Mandl FFT')
xlabel('f in Hz')


figure(3)
subplot(2,1,1)
plot(t(1:length(t)/2),(180/pi).*angle(YT(1:length(t)/2)))
subplot(2,1,2)
plot(t(1:length(t)/2),(180/pi).*angle(YT_Mandl(1:length(t)/2)))

er = abs(YT)-abs(YT_Mandl)';
m_er = max(er)

