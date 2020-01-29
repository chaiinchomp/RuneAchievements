# takes a list of item names (one per line) and returns the equivalent list of their ids
# if an item name isn't found, it will be returned as-is instead of replaced with an id

import argparse
import time
import json

parser = argparse.ArgumentParser(description='Map osrs item names to ids')
parser.add_argument("mapping_file", help='file containing map of item names -> ids')
parser.add_argument('item_names', help='file containing list of item names to be mapped')
args = parser.parse_args()

with open(args.mapping_file, 'r') as f:
    names_to_ids = json.load(f)
    names_to_ids = {k.lower().strip(): v for k, v in names_to_ids.items()}

output_path = './output/itemIdMapping_' + str(int(time.time())) + '.txt'
output_file = open(output_path, 'w')
with open(args.item_names, 'r') as f:
    for item_name in f:
        output_file.write(names_to_ids.get(item_name.lower().strip(), item_name.lower().strip()) + '\n')
