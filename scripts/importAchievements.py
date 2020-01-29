# imports from a tsv of achievement data and generates a SQL query to insert/update it
# (tsv instead of csv because data has commas in it)
#
# expected format of tsv:
# id \t name \t description \t itemRepresentation \t requiredCount \t seriesOrdinal \t tasks \t series \t category
#
# field values:
# id (int): expect to be unique across achievements (not necessarily universally)
# name (str) / description (str): self explanatory
# itemRepresentation (str): doesn't matter isn't used in this script
# requiredCount (int): OPTIONAL how many of the tasks need to be checked off for the achievement to be complete
# seriesOrdinal (int): OPTIONAL ordinal number of this achievement's place in its parent series
# tasks (list:int): OPTIONAL list of task ids required to complete this achievement, comma separated
# series (int): OPTIONAL id of the parent series
# category (int): id of the category to sort this achievement into

import argparse
import time

parser = argparse.ArgumentParser(description='Import achievement data from csv')
parser.add_argument('-f', '--file',
                    type=str,
                    default='./input/achievements.tsv',
                    help='tsv file to be imported')
# TODO this might be nice to have but idk if it's needed
# parser.add_argument('-m', '--mode',
#                     type=str,
#                     choices=['INSERT', 'UPDATE', 'INSERT_OR_UPDATE'],
#                     default='INSERT_OR_UPDATE',
#                     help='type of statement to generate')
parser.add_argument('-hh', '--has-headers',
                    action='store_true',
                    help='enable if the file has a header row')
args = parser.parse_args()

output_path = './output/achievements_' + str(int(time.time())) + '.sql'
output_file = open(output_path, 'w')

with open(args.file, 'r') as f:
    firstline = args.has_headers
    for line in f:
        if firstline:
            firstline = False
            continue
        segments = line.split('\t')
        uuid = 'A_' + segments[0]

        # achievement table
        name = segments[1].strip()
        description = segments[2].strip()
        req_count = segments[4] if segments[4] else 'DEFAULT'
        series_ord = segments[5] if segments[5] else 'DEFAULT'
        series = 'S_' + segments[7] if segments[7] else 'DEFAULT'
        category = segments[8]
        insert_achievements = 'INSERT INTO achievements ' \
                              '(uuid, name, description, required_count, series_ordinal, series_id, category_id) ' \
                              'VALUES ' \
                              f'("{uuid}","{name}","{description}",{req_count},{series_ord},"{series}",{category}) ' \
                              f'ON CONFLICT ({uuid}) DO UPDATE ' \
                              f'SET name = "{name}", description = "{description}", required_count = {req_count}, ' \
                              f'series_ordinal = {series_ord}, series_id = "{series}", category_id = {category};\n'
        set_achievement = ''
        output_file.write(insert_achievements)

        # task relationship table
        for task_id in segments[6].split(','):
            task_id = task_id.strip()
            # TODO this only inserts, need to fix to update on conflict (difficult since
            #  we don't know the primary key here, prob need to do a lookup first)
            insert_task_relationship = 'INSERT INTO achievementTasks ' \
                                       '(achievement_id, task_id) ' \
                                       'VALUES ' \
                                       f'("{uuid}","{task_id}");\n'
            output_file.write(insert_task_relationship)

        output_file.write('\n')
