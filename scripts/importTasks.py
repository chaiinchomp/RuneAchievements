# imports from a csv of task data and generates a SQL query to insert it
#
# expected format of csv:
# id,name,itemRepresentation
#
# field values:
# id (int): expect to be unique across tasks (not necessarily universally)
# name (str): self explanatory
# itemRepresentation (str): name of icon to represent this achievement (usually an item id)

import argparse
import time
import re

parser = argparse.ArgumentParser(description='Import task data from csv')
parser.add_argument('-f', '--file',
                    type=str,
                    default='./input/tasks.csv',
                    help='csv file to be imported')
parser.add_argument('-hh', '--has-headers',
                    action='store_true',
                    help='enable if the file has a header row')
args = parser.parse_args()

output_path = './output/tasks_' + str(int(time.time())) + '.sql'
output_file = open(output_path, 'w')

with open(args.file, 'r') as f:
    firstline = args.has_headers
    for line in f:
        if firstline:
            firstline = False
            continue
        segments = line.split(',')
        uuid = 'T_' + segments[0]
        name = segments[1].strip()
        icon = segments[2].strip()
        insert_tasks = f'INSERT INTO tasks (uuid, name, icon) VALUES ("{uuid}","{name}","{icon}");\n'
        output_file.write(insert_tasks)
