
RV = 1; 
R1 = 1;
C1 = 100e-6; 
R2 = 1000;
C2 = 100e-6;
RL = 50; 


%Simulink Parameter
S1 = RV/RL + (RV*R2)/(RL*R1) + RV/R1 + R2/RL + 1; 
S2 = C2*RV + (R2*C2*RV)/R1 + (R2*C1*RV)/RL + C1*RV;
S3 = -1/(RV*R2*C2*C1);


K11 = 1/(C1*C2);
K12 = 1/(RL*R2) + 1/(RL*R1) + 1/(R2*R1) + 1/(RV*R2) + 1/(RL*RV);

K1 = K11*K12;

K2 = 1/(C1*R2)+1/(R1*C1)+1/(RL*C2)+1/(R2*C2)+1/(RV*C1);

f1 = K1/(K2*2*pi)

f2 = K2/(2*pi)

DC_Gain = (R1*RL)/(RV*(R1+R2+RL)+R1*(R2+RL))


