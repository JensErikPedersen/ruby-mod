package dk.jens.rubymod;

import java.util.Map;
import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwingAnimationType;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

public class ModItems {
	// Items i dette tag kan bruges til at reparere rubin-ting i en ambolt (se data/rubymod/tags/item/)
	public static final TagKey<Item> RUBY_REPAIR_MATERIALS = TagKey.create(Registries.ITEM, id("ruby_repair_materials"));

	// Rubin-værktøj: mellem jern og diamant, men kan bryde de samme blokke som diamant (fx obsidian)
	//                     jern   rubin  diamant
	// holdbarhed          250    1000   1561
	// hastighed           6.0    7.0    8.0
	// ekstra skade        2.0    2.5    3.0
	public static final ToolMaterial RUBY_TOOL_MATERIAL = new ToolMaterial(
			BlockTags.INCORRECT_FOR_DIAMOND_TOOL, // blokke værktøjet IKKE kan høste
			1000,  // holdbarhed (antal brug)
			7.0f,  // hvor hurtigt det hakker
			2.5f,  // ekstra skade
			12,    // hvor godt det kan fortrylles (enchantability)
			RUBY_REPAIR_MATERIALS
	);

	// Rubin-rustning: også mellem jern og diamant
	public static final ResourceKey<EquipmentAsset> RUBY_EQUIPMENT_ASSET = ResourceKey.create(EquipmentAssets.ROOT_ID, id("ruby"));
	public static final int RUBY_ARMOR_DURABILITY = 25; // jern 15, diamant 33
	public static final ArmorMaterial RUBY_ARMOR_MATERIAL = new ArmorMaterial(
			RUBY_ARMOR_DURABILITY,
			Map.of(
					ArmorType.HELMET, 3,
					ArmorType.CHESTPLATE, 7,
					ArmorType.LEGGINGS, 6,
					ArmorType.BOOTS, 3
			),
			12,                                // enchantability
			SoundEvents.ARMOR_EQUIP_DIAMOND,   // lyden når man tager den på
			1.0f,                              // toughness (jern 0, diamant 2)
			0.0f,                              // modstand mod knockback
			RUBY_REPAIR_MATERIALS,
			RUBY_EQUIPMENT_ASSET               // peger på assets/rubymod/equipment/ruby.json
	);

	// Selve ædelstenen, som rubinmalmen taber
	public static final Item RUBY = register("ruby", Item::new, new Item.Properties());

	// Værktøj og våben (skade og angrebshastighed er de samme som for diamant-udgaven)
	public static final Item RUBY_SWORD = register("ruby_sword", Item::new,
			new Item.Properties().sword(RUBY_TOOL_MATERIAL, 3.0f, -2.4f));
	// Spyd: kan stikke hurtigt (klik) eller holdes inde for et stormløb, der giver mere skade jo hurtigere man løber eller rider.
	// Tallene ligger mellem jern- og diamantspyddet.
	public static final Item RUBY_SPEAR = register("ruby_spear", Item::new,
			new Item.Properties().spear(RUBY_TOOL_MATERIAL,
					1.0f,   // sekunder pr. stik (jern 0.95, diamant 1.05)
					1.0f,   // skade-gange under stormløb (jern 0.95, diamant 1.075)
					0.55f,  // sekunder før stormløbet starter (jern 0.6, diamant 0.5)
					2.75f,  // sekunder stormløbet kan kaste ryttere af hesten
					10.0f,  // mindste fart (blokke/sek) for at kaste en rytter af
					6.5f,   // sekunder stormløbet kan skubbe fjender tilbage
					5.1f,   // mindste fart for at skubbe tilbage
					10.5f,  // sekunder stormløbet kan give skade
					4.6f)  // mindste fart for at give skade
					// Stik lige frem i stedet for at slå som med et sværd (20 ticks = 1 sekund pr. stik)
					.component(DataComponents.SWING_ANIMATION, new SwingAnimation(SwingAnimationType.STAB, 20)));
	public static final Item RUBY_PICKAXE = register("ruby_pickaxe", Item::new,
			new Item.Properties().pickaxe(RUBY_TOOL_MATERIAL, 1.0f, -2.8f));
	public static final Item RUBY_AXE = register("ruby_axe",
			properties -> new AxeItem(RUBY_TOOL_MATERIAL, 5.0f, -3.0f, properties), new Item.Properties());
	public static final Item RUBY_SHOVEL = register("ruby_shovel",
			properties -> new ShovelItem(RUBY_TOOL_MATERIAL, 1.5f, -3.0f, properties), new Item.Properties());
	public static final Item RUBY_HOE = register("ruby_hoe",
			properties -> new HoeItem(RUBY_TOOL_MATERIAL, -3.0f, 0.0f, properties), new Item.Properties());

	// Rustning
	public static final Item RUBY_HELMET = registerArmor("ruby_helmet", ArmorType.HELMET);
	public static final Item RUBY_CHESTPLATE = registerArmor("ruby_chestplate", ArmorType.CHESTPLATE);
	public static final Item RUBY_LEGGINGS = registerArmor("ruby_leggings", ArmorType.LEGGINGS);
	public static final Item RUBY_BOOTS = registerArmor("ruby_boots", ArmorType.BOOTS);

	private static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(RubyMod.MOD_ID, name);
	}

	private static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id(name));
		Item item = itemFactory.apply(properties.setId(itemKey));
		return Registry.register(BuiltInRegistries.ITEM, itemKey, item);
	}

	private static Item registerArmor(String name, ArmorType type) {
		return register(name, Item::new, new Item.Properties()
				.humanoidArmor(RUBY_ARMOR_MATERIAL, type)
				.durability(type.getDurability(RUBY_ARMOR_DURABILITY)));
	}

	public static void initialize() {
		// Læg rubinen i fanen "Ingredienser" i kreativ tilstand
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(creativeTab -> {
			creativeTab.accept(RUBY);
		});
		// Værktøj i fanen "Værktøj og hjælpemidler"
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(creativeTab -> {
			creativeTab.accept(RUBY_SHOVEL);
			creativeTab.accept(RUBY_PICKAXE);
			creativeTab.accept(RUBY_AXE);
			creativeTab.accept(RUBY_HOE);
		});
		// Sværd, økse og rustning i fanen "Kamp"
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(creativeTab -> {
			creativeTab.accept(RUBY_SWORD);
			creativeTab.accept(RUBY_SPEAR);
			creativeTab.accept(RUBY_AXE);
			creativeTab.accept(RUBY_HELMET);
			creativeTab.accept(RUBY_CHESTPLATE);
			creativeTab.accept(RUBY_LEGGINGS);
			creativeTab.accept(RUBY_BOOTS);
		});
	}
}
