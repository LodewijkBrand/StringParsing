counter = 0
f = open("EColi.txt", "r")
mapfile = open("ConvertedEColi.txt", 'w')
for line in f:
	sequence = []
	for x in line.split():
            if "a" in x or "t" in x or "g" in x or "c" in x:
                mapfile.write(x)
mapfile.close()
