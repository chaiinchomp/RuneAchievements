# imports from a csv of category data and generates a SQL query to insert it
#
# expected format of csv:
# id,name,parent_category
#
# field values:
# id (int): expect to be unique across categories (not necessarily universally)
# name (str): self explanatory
# parent_category (int): OPTIONAL id of parent category

import argparse
import time
import re

parser = argparse.ArgumentParser(description='Import category data from csv')
parser.add_argument('-f', '--file',
                    type=str,
                    default='./input/categories.csv',
                    help='csv file to be imported')
parser.add_argument('-hh', '--has-headers',
                    action='store_true',
                    help='enable if the file has a header row')
args = parser.parse_args()

output_path = './output/category_' + str(int(time.time())) + '.sql'
output_file = open(output_path, 'w')

with open(args.file, 'r') as f:
    firstline = args.has_headers
    for line in f:
        if firstline:
            firstline = False
            continue
        segments = line.split(',')
        cat_id = segments[0]
        name = segments[1].strip()
        parent_cat = segments[2] if segments[2] else 'DEFAULT'
        insert_category = 'INSERT INTO categories (id, name, parent_category) VALUES ' \
                          f'({cat_id},"{name}",{parent_cat});\n'
        output_file.write(insert_category)
