x = 1:1000; 
nCoefs = 80; 
Fg = 0.3;
y = sin(10*x) + sin(100*x) + sin(500*x) + sin(3000*x); 
y_conv = f_filter(y, Fg, nCoefs);


tau = -(nCoefs-1)/2 : (nCoefs-1)/2;
hlp = sinc(2*Fg*tau); %calculation of coefs
y_canon = f_filter_canon2( hlp, y); 

% import file 
filename = 'C:\Users\Andre\Documents\GitHub\javasignalprocessingtools\javasignalprocessingtools\src\matlab_files\FIRFilterTest.txt';
delimiter = ' ';
formatSpec = '%f%f%[^\n\r]';
fileID = fopen(filename,'r');
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter, 'MultipleDelimsAsOne', true, 'TextType', 'string',  'ReturnOnError', false);
fclose(fileID);
FIRFilterTest = table(dataArray{1:end-1}, 'VariableNames', {'x_java','y_java'});
x_java = FIRFilterTest.x_java';
y_java = FIRFilterTest.y_java'; 
clearvars filename delimiter formatSpec fileID dataArray ans;

plot(x, y)
plot(x, y_canon, x_java, y_java)