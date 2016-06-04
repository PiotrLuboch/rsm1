N=15;
Q=generation(N);
Q_copy=Q;
filename=['d:/in_' int2str(N) '.csv'];

csvwrite(filename,Q);
M=csvread(filename);
