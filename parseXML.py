__author__ = 'Hrishikesh'
import xml.etree.ElementTree as ET
import re
import os

unicode_error = 0
none_type = 0
file = open('to_index.xml', 'w')
file.write('\n<add>')
path = 'E:/1'
data = {}
for dir_entry in os.listdir(path):
    dir_entry_path = os.path.join(path, dir_entry)
    if os.path.isfile(dir_entry_path):
        with open(dir_entry_path, 'r') as my_file:
            try:
                tree = ET.parse(my_file)
                print(my_file)
                root = tree.getroot()
                file.write('\n<doc>')
                for child in root:
                    for x in child:
                        if x.tag == 'title':
                            file.write('\n<field name="title">' + x.text + '</field>')
                        if x.tag == 'meta':
                            if re.search("publication", x.attrib.get('name')) is not None:
                                file.write('\n<field name="' + x.attrib.get('name') + '">' + x.attrib.get('content') + '</field>')
                        if x.tag == 'pubdata':
                            file.write('\n<field name="url">' + x.attrib.get('ex-ref') + '</field>')
                            file.write('\n<field name="date">' + x.attrib.get('date.publication') + '</field>')
                        if x.tag == 'body.content':
                            for block in x:
                                temp = ''
                                for p in block:
                                    temp += p.text
                                file.write('\n<field name="' + block.attrib.get('class') + '">' + temp + '</field>')
                file.write('\n</doc>')
            except UnicodeDecodeError:
                unicode_error += 1
            except TypeError:
                file.write('\n</doc>')
                none_type += 1
file.write('</add>')
file.close()