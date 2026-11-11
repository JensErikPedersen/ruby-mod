package dk.jens.rubymod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;

public class RubyMod implements ModInitializer {
	public static final String MOD_ID = "rubymod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Henviser til data/rubymod/worldgen/placed_feature/ruby_ore.json
	public static final ResourceKey<PlacedFeature> RUBY_ORE_PLACED = ResourceKey.create(
			Registries.PLACED_FEATURE,
			Identifier.fromNamespaceAndPath(MOD_ID, "ruby_ore")
	);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModBlocks.initialize();

		// Læg rubinmalm ind under jorden i alle Overworld-biomer
		BiomeModifications.addFeature(
				BiomeSelectors.foundInOverworld(),
				GenerationStep.Decoration.UNDERGROUND_ORES,
				RUBY_ORE_PLACED
		);
		LOGGER.info("Ruby Mod er indlæst!");
	}
}
