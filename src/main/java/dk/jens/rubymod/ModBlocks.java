package dk.jens.rubymod;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

public class ModBlocks {
	// Rubinblokken: lige så hård som en diamantblok og kræver en jernhakke
	public static final Block RUBY_BLOCK = register(
			"ruby_block",
			Block::new,
			BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_RED)
					.strength(5.0f, 6.0f)
					.requiresCorrectToolForDrops()
					.sound(SoundType.METAL)
	);

	// Rubinmalm i almindelig sten: giver 3-7 erfaringspoint, når den hakkes
	public static final Block RUBY_ORE = register(
			"ruby_ore",
			properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties),
			BlockBehaviour.Properties.of()
					.mapColor(MapColor.STONE)
					.strength(3.0f, 3.0f)
					.requiresCorrectToolForDrops()
	);

	// Rubinmalm i dybskifer (deepslate) længere nede: lidt hårdere, ligesom i vanilla
	public static final Block DEEPSLATE_RUBY_ORE = register(
			"deepslate_ruby_ore",
			properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties),
			BlockBehaviour.Properties.of()
					.mapColor(MapColor.DEEPSLATE)
					.strength(4.5f, 3.0f)
					.requiresCorrectToolForDrops()
					.sound(SoundType.DEEPSLATE)
	);

	// Registrerer en blok og det tilhørende item (så man kan holde blokken i hånden)
	private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
		Identifier id = Identifier.fromNamespaceAndPath(RubyMod.MOD_ID, name);
		ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);

		Block block = blockFactory.apply(properties.setId(blockKey));
		Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

		BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(itemKey));
		Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

		return block;
	}

	public static void initialize() {
		// Læg rubinblokken i fanen "Byggeblokke" i kreativ tilstand
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(creativeTab -> {
			creativeTab.accept(RUBY_BLOCK.asItem());
		});
		// Malmene i fanen "Naturlige blokke"
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(creativeTab -> {
			creativeTab.accept(RUBY_ORE.asItem());
			creativeTab.accept(DEEPSLATE_RUBY_ORE.asItem());
		});
	}
}
