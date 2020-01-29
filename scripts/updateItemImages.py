# grabs item images from runelite cache and copies them to frontend resources folder.
# outputs a list of item ids that were not found and thus could not be copied
#
# assumes both static.runelite.net and rune-achievements-app repositories are
# checked out and located in this repo's parent folder

import argparse
import time
from os import path
from shutil import copy

parser = argparse.ArgumentParser(description='Copy item images to frontend resources')
parser.add_argument('-f', '--file',
                    type=str,
                    default='./input/itemIds.txt',
                    help='item ids to get images for (one per line)')
args = parser.parse_args()

copy_from_dir = '../../static.runelite.net/cache/item/icon'
copy_to_dir = '../../rune-achievements-app/src/resources/items'

output_path = './output/unmatchedItemIds_' + str(int(time.time())) + '.txt'
output_file = open(output_path, 'w')

with open(args.file, 'r') as f:
    for item_id in f:
        item_path = copy_from_dir + '/' + item_id.strip() + '.png'
        if path.exists(item_path):
            copy(item_path, copy_to_dir)
        else:
            output_file.write(item_id)
