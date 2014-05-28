#!/usr/bin/python
#Fileneme:catchUrl.py

import urllib
import urllib2
import string
import re


def catchUrl(inputUrl,record):

	flag = 0
	for i,j in record.items():
		if(i == inputUrl):
			flag = 1

	if(flag == 0):
		record[inputUrl] = '1'
		req = urllib2.Request(inputUrl)
		try:
			response = urllib2.urlopen(req)
		except:
			print 1
			return 0
		sName = ''
		page = response.read()
		n = len(inputUrl)
		for i in range(0,n):
			if(inputUrl[i:i + 1] == '/'):
				sName += '%'
			else:
				sName += inputUrl[i:i + 1]
		print 'loading:\n' + inputUrl + '.......'
		f = open('temp/' + sName,'w+')
		f.write(page)
		f.close()
		results=re.findall("(?isu)(http\://[a-zA-Z0-9\.\?/&\=\:]+)",page)
		for i in results:
			catchUrl(i,record)

catchUrl('http://weibo.com/',{"baidu.com":"dss","sina.com":"djfj"})

