function Q = generation(N)

Q=rand(N)*2-1;
for i=1:N
   Q(i,i)=NaN; 
end
