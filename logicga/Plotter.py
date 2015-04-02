import matplotlib.pyplot as plt

#you must cd to the correct directory in the python command line
metafile = open("Metadata.txt",'r')

fitnessLevels = metafile.readlines()
fitnessLevels = [line.replace('\n','') for line in fitnessLevels]


x = []
y = []
for i in range (0,len(fitnessLevels)-1):
    x.append(i)
    y.append(int(fitnessLevels[i]))

plt.plot(x,y, "b")
plt.show()
