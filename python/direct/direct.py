#!/usr/bin/python
#Filename:file.pi
vector = ["file1", "file2", "file3"]

direct = {}
for i in range(0, len(vector)):
	direct[i] = open(vector[i], 'w')
for j in direct:
	direct[j].write("hello")
	print j
for j in direct:
	direct[i].close()
print "all is ok"
