import nltk
import sys
from nltk.parse.generate import generate
from nltk import CFG

sys.stdout = open('xssStrings', 'w')
grammar =  nltk.data.load('file:inter.cfg')

#print(grammar)

for sentence in generate(grammar):
    print(''.join(sentence))