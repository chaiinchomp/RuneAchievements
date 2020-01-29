# reverses id -> item name mapping from static.runelite.net item cache to allow lookup of id by item name
# outputs to a csv right now but this should probably go into a DB or something more queryable later ¯\_(ツ)_/¯
# rerun this to regenerate the mapping whenever new items are added

import argparse
import time
import json

parser = argparse.ArgumentParser(description='Reverse mapping of item ids to names')
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
        # usually items are duplicated, first occurrence is the standard item, second occurrence is the
        # noted variant. need to prevent overwriting the standard one with the noted variant
        if not itemName in output_dict:
            output_dict[itemName] = itemId

output_path = './output/reverseItemIdMapping_' + str(int(time.time())) + '.txt'
with open(output_path, 'w') as f:
    json.dump(output_dict, f, sort_keys=True, indent=4)
