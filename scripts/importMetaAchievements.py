# imports from a tsv of meta achievement data and generates a SQL query to insert/update it
# (tsv instead of csv because data has commas in it)
#
# expected format of tsv:
# id \t name \t description \t itemRepresentation \t category \t achievements \t meta_acheivements
#
# field values:
# id (int): expect to be unique across achievements (not necessarily universally)
# name (str) / description (str): self explanatory
# itemRepresentation (str): name of icon to represent this achievement (usually an item id)
# category (int): id of the category to sort this achievement into
# achievements (list:int): OPTIONAL list of achievements ids required to complete this achievement, comma separated
# meta_acheivements (list:int): OPTIONAL list of meta achievements ids to complete this achievement, comma separated

import argparse
import time

parser = argparse.ArgumentParser(description='Import meta achievement data from csv')
parser.add_argument('-f', '--file',
                    type=str,
                    default='./input/metaachievements.tsv',
                    help='tsv file to be imported')
parser.add_argument('-hh', '--has-headers',
                    action='store_true',
                    help='enable if the file has a header row')
args = parser.parse_args()

output_path = './output/metaachievements_' + str(int(time.time())) + '.sql'
output_file = open(output_path, 'w')

with open(args.file, 'r') as f:
    firstline = args.has_headers
    for line in f:
        if firstline:
            firstline = False
            continue
        segments = line.split('\t')
        uuid = 'A_' + segments[0]

        all_achievs = segments[5].split(',') if segments[5] else []
        all_achievs.extend(segments[6].split(',') if segments[6] else [])
        sub_achievements = []
        for achiev in all_achievs:
            sub_achievements.append('A_' + achiev.strip())

        # achievement table
        name = segments[1].strip()
        description = segments[2].strip()
        icon = segments[3].strip()
        req_count = len(sub_achievements)
        category = segments[4]
        insert_achievements = 'INSERT INTO achievements ' \
                              '(uuid, name, description, icon, required_count, category_id) ' \
                              'VALUES ' \
                              f'("{uuid}","{name}","{icon}","{description}",{req_count},{category}) ' \
                              f'ON CONFLICT ({uuid}) DO UPDATE ' \
                              f'SET name = "{name}", description = "{description}", icon = "{icon}", ' \
                              f'required_count = {req_count}, category_id = {category};\n'
        set_achievement = ''
        output_file.write(insert_achievements)

        # achievement relationship table
        for subachievement_id in sub_achievements:
            # TODO this only inserts, need to fix to update on conflict (difficult since
            #  we don't know the primary key here, prob need to do a lookup first)
            insert_achieve_relationship = 'INSERT INTO achievementTasks ' \
                                       '(achievement_id, subachievement_id) ' \
                                       'VALUES ' \
                                       f'("{uuid}","{subachievement_id}");\n'
            output_file.write(insert_achieve_relationship)

        output_file.write('\n')
