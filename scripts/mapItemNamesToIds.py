# reverses id -> item name mapping from static.runelite.net item cache to allow lookup of id by item name
# outputs to a csv right now but this should probably go into a DB or something more queryable later ¯\_(ツ)_/¯
# rerun this to regenerate the mapping whenever new items are added

import argparse
import time
import json

parser = argparse.ArgumentParser(description='Map osrs item names to ids')
parser.add_argument('-p', '--path',
                    type=str,
                    default='../../',
                    help='path to static.runelite.net repo')
args = parser.parse_args()

input_path = args.path + 'static.runelite.net/cache/item/names.json'
output_dict = {}
with open(input_path, 'r') as f:
    runelite_data = json.load(f)
    for (itemId, itemName) in runelite_data.items():
        output_dict[itemName] = itemId

output_path = './output/mapItemNamesToIds_' + str(int(time.time())) + '.txt'
with open(output_path, 'w') as f:
    json.dump(output_dict, f, sort_keys=True, indent=4)
