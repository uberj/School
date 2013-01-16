from math import floor
import itertools
import sys

fname = sys.argv[1]

list1, list2 = [], []
with open(fname, 'r') as fd:
    for line in fd.readlines():
        line = line.split()
        if not line:
            continue
        if len(line) == 1:
            list1.append(line[0])
        else:
            list1.append(line[0])
            list2.append(line[1])

list1.sort()
list2.sort()

hash1, hash2 = {}, {}

def print_s_l(l):
    minn = l[0]
    maxn = l[-1]
    hashx = {}
    for i in xrange(int(floor(float(minn))), int(floor(float(maxn))) + 1, 1):
        for a in l:
            if a.find('.') < 0:
                stem = a
                leaf = ""
            else:
                stem, leaf = a.split('.')
            stem = int(stem)
            if stem == i:
                hashx.setdefault(i, []).append(leaf)
    return hashx

hash1 = print_s_l(list1)
hash2 = print_s_l(list2)

print "Stem leafe plot for {0}".format(fname)
print "=============================="
for i in xrange(min(hash1.keys()), max(hash2.keys()) + 1, 1):
    tmp_line = ""
    for k in hash1.get(i, []):
        tmp_line += " " + str(k)
    tmp_line = tmp_line.rjust(30)
    tmp_line +=  ' | {0} | '.format(i)
    for j in hash2.get(i, []):
        tmp_line += " " + str(j)
    print tmp_line.center(30)


