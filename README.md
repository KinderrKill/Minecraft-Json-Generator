# Minecraft JSON Generator

Minecraft JSON Generator is a simple program that allow you to generate json with template JSON file for your blocks.
NOTE: It's my first public program in JAVA. Also my first README and GitHub project so be cool with me and please leave a star if you find the program usefull!

## Installation

Simply dowload the latest release version and unzip in the folder you want.

```diff
- **NOTE: NEVER CHANGE OR RENAME ASSETS FOLDER OR FOLDERS INSIDE!** -
```


## How to add a new template

First, open in the assets folder `templates-config.json`

Here some explaination of the template

```
  "basic_block": { //Name of the template 
    "blockstate": "basic_block", //Name of the parent file in blockstate > parents
    "model_blocks": "basic_block", //Name of the model/Block in model_blocks > parents
    "model_items": "basic_block" //Name of the model/Item in model_items > parents
  }
```

For a complexe blocks who use multiple model/Bocks
```
  "slab": { //Name of the template AND base name of the files
    "blockstate": "slab", //Name of the parent file in blockstate > parents
    "model_blocks": [
      "half_slab", //Name of the model/Block in model_blocks > parents 
      "upper_slab" //Name of the model/Block in model_blocks > parents
    ],
    "model_items": "slab" //Name of the model/Item in model_items > parents
  }
```

For this template, you gonne have base name system :

For **blockstate** it's gonna be 
> `slab_YOURBLOCK` (Name of the template + the name you define in the program)

For **model_blocks** it's gonna be 
> `half_slab_YOURBLOCK` (Name of the 'model_blocks" in template + the name you define in the program)
> `upper_slab_YOURBLOCK`(Name of the 'model_blocks" in template + the name you define in the program)

For **model/item** it's gonna be
> `slab_YOURBLOCK` (Name of the template + the name you define in the program)

With thoses explaination and the model inside the templates-config.json, you can simply understand how to add new template configuration.

## How to create the parent files

You already have exemple for basic block, slab and stairs inside the differents parent folder.

For **blockstate** parent file of slab for exemple
> `"half=bottom": { "model": "half_slab_$BLOCK" }` The key $BLOCK gonna be the value you define inside the program, always put the key at the end of the name

For **model/Block**
> `"bottom": "blocks/$TEXTURE",` Simply put the key $TEXTURE where you want, the key gonna the value you define inside the program

For **model/Item**
> `"parent": "block/$ITEM",`, Define the parent with the key $ITEM, the key gonna be the same value as $BLOCK

What the program gonna do ? (For exemple with the slab profile)

For example with value $BLOCK = oak and $TEXTURE = planks_oak

1) Find the parent file in blockstate, copy content, paste it with the template_name "slab" and add "_oak"
and rename $BLOCK inside with "oak"

2) Find model/Block, copy content, paste it with the name of the model_block, so "half_slab" and add "_oak"
and rename $TEXTURE inside with "planks_oak"

3) Find model/Item, copy content, paste it with the template_name "slab" and add "_oak"
and rename $ITEM with "oak".



That why it's important to follow the model already present if you have any error about copy or rename, it's because of the files or the template you create.

NOTE : With simple template using only one model_blocks, the file gonna be the value you define in the program and don't gonna use the template_name.
Exemple basic_block, if you select that and name it "CustomObsidian" the blockstate file gonna be "CustomObsidian"


## The program

Yu can launch "MinecraftJsonGenerator" by double click on it.

1) Simply select the template
2) Click on "Verified template"
3) Define the block name
4) Define the block texture
5) Click on generate JSON files

Generated files gonna be in blockstate, model_blocks and model_items

## Youtube videos

If you still don't understand how this program work, you can see my videos about it

French: (Not recorded yet)

English: (Not recorded yet)
