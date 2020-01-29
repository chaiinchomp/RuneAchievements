# imports from a csv of series data and generates a SQL query to insert it
#
# expected format of csv:
# id,name,length
#
# field values:
# id (int): expect to be unique across series (not necessarily universally)
# name (str): self explanatory
# length (int): number of achievements in the series

import argparse
import time
import re

parser = argparse.ArgumentParser(description='Import series data from csv')
parser.add_argument('-f', '--file',
                    type=str,
                    default='./input/series.csv',
                    help='csv file to be imported')
parser.add_argument('-hh', '--has-headers',
                    action='store_true',
                    help='enable if the file has a header row')
args = parser.parse_args()

output_path = './output/series_' + str(int(time.time())) + '.sql'
output_file = open(output_path, 'w')

with open(args.file, 'r') as f:
    firstline = args.has_headers
    for line in f:
        if firstline:
            firstline = False
            continue
        segments = line.split(',')
        uuid = 'S_' + segments[0]
        name = segments[1].strip()
        length = segments[2]
        insert_series = f'INSERT INTO series (uuid, name, length) VALUES ("{uuid}","{name}",{length});\n'
        output_file.write(insert_series)
