# Ruby Mod

A Fabric mod for Minecraft 26.2 that adds rubies: a new ore, a ruby block, and a full set of ruby tools, weapons and armor that sit between iron and diamond.

## Features

- **Ruby Ore** and **Deepslate Ruby Ore** generate deep underground, from y=-8 down to the bottom of the world, most common around y=-40. Requires an iron pickaxe or better. Drops a **ruby** (affected by Fortune). The ore only appears in newly generated chunks.
- **Ruby** can also be obtained by smelting the ore in a furnace or blast furnace.
- **Block of Ruby** is crafted from 9 rubies and can be crafted back into 9 rubies.
- **Ruby tools and weapons:** sword, spear, pickaxe, axe, shovel and hoe. They use the same recipes as diamond tools, with rubies instead of diamonds.
- **Ruby armor:** helmet, chestplate, leggings and boots, also with the diamond recipes.
- **Ruby Spear:** 1 ruby in the top right and 2 sticks diagonally down to the left. Click to stab, hold right-click to charge.
- All ruby items can be repaired with rubies in an anvil, can be enchanted, and ruby armor can be trimmed.
- All recipes show up in the recipe book once you pick up a ruby (ore recipes once you pick up the ore).

## Stats

| | Iron | Ruby | Diamond |
|---|---|---|---|
| Tool durability | 250 | 1000 | 1561 |
| Mining speed | 6.0 | 7.0 | 8.0 |
| Bonus attack damage | 2.0 | 2.5 | 3.0 |
| Can mine obsidian | No | Yes | Yes |
| Armor (helmet / chest / legs / boots) | 2 / 6 / 5 / 2 | 3 / 7 / 6 / 3 | 3 / 8 / 6 / 3 |
| Armor toughness | 0 | 1 | 2 |
| Armor durability multiplier | 15 | 25 | 33 |

## Creative mode tabs

| Tab | Items |
|---|---|
| Ingredients | Ruby |
| Natural Blocks | Ruby Ore, Deepslate Ruby Ore |
| Building Blocks | Block of Ruby |
| Tools & Utilities | Pickaxe, axe, shovel, hoe |
| Combat | Sword, spear, axe, armor |

## Requirements

- Minecraft 26.2
- Fabric Loader 0.19.5 or newer
- Fabric API
- Java 25

## Build and run

Test in a development client:

```
.\gradlew runClient
```

Build the mod jar:

```
.\gradlew build
```

Then copy `build/libs/rubymod-1.3.1.jar` into your `.minecraft/mods` folder and start the Fabric 26.2 profile.

## Where things are

| Path | Contents |
|---|---|
| `src/main/java/dk/jens/rubymod/ModItems.java` | Ruby, tools and armor: durability, damage, protection |
| `src/main/java/dk/jens/rubymod/ModBlocks.java` | Ore and ruby block |
| `src/main/resources/data/rubymod/recipe/` | Crafting, smelting and blasting recipes |
| `src/main/resources/data/rubymod/worldgen/` | Ore generation. Change `count` in `placed_feature/ruby_ore.json` for more or less ore |
| `src/main/resources/data/rubymod/loot_table/blocks/` | Block drops |
| `src/main/resources/assets/rubymod/textures/` | Item, block and armor textures |
| `src/main/resources/assets/rubymod/lang/` | English and Danish names |
