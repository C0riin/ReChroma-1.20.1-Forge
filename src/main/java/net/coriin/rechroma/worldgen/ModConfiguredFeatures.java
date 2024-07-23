package net.coriin.rechroma.worldgen;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_CRYSTAL_KEY = registerKey("crystal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_CRYSTAL_KEY = registerKey("nether_crystal");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest airReplaceables = new BlockMatchTest(Blocks.CAVE_AIR);

        RuleTest netherrackReplacables = new BlockMatchTest(Blocks.NETHERRACK);


        List<OreConfiguration.TargetBlockState> overworldCrystals = List.of(
                OreConfiguration.target(stoneReplaceable, ModBlocks.PURPLE_CRYSTAL_BLOCK.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.PURPLE_CRYSTAL_BLOCK.get().defaultBlockState()),
                OreConfiguration.target(airReplaceables, ModBlocks.PURPLE_CRYSTAL_BLOCK.get().defaultBlockState()));

        register(context, OVERWORLD_CRYSTAL_KEY, Feature.ORE, new OreConfiguration(overworldCrystals, 1));
        register(context, NETHER_CRYSTAL_KEY, Feature.ORE, new OreConfiguration(netherrackReplacables,
                ModBlocks.PURPLE_CRYSTAL_BLOCK.get().defaultBlockState(), 1));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ReChroma.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
