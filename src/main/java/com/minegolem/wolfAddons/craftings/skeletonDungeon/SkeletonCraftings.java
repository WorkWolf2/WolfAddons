package com.minegolem.wolfAddons.craftings.skeletonDungeon;

import com.minegolem.wolfAddons.WolfAddons;
import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class SkeletonCraftings {

    private static final WolfAddons INSTANCE = WolfAddons.INSTANCE;

    private static final NamespacedKey rottameOsseoNSK = new NamespacedKey(INSTANCE, "rottame_osseoCRF");
    private static final NamespacedKey lingottoOsseoNSK = new NamespacedKey(INSTANCE, "lingotto_osseoCRF");
    private static final NamespacedKey cristalloOsseoNSK = new NamespacedKey(INSTANCE, "cristallo_osseoCRF");

    private static final ItemStack cristalloOsseoIS = OraxenItems.getItemById("cristallo_osseo").build();

    private static final NamespacedKey boneSwordNSK = new NamespacedKey(INSTANCE, "bone_sword");
    private static final ItemStack boneSwordIS = OraxenItems.getItemById("bone_sword").build();
    private static final ItemStack netheriteSwordIS = new ItemStack(Material.NETHERITE_SWORD);

    private static final NamespacedKey boneShovelNSK = new NamespacedKey(INSTANCE, "bone_shovel");
    private static final ItemStack boneShovelIS = OraxenItems.getItemById("bone_shovel").build();
    private static final ItemStack netheriteShovelIS = new ItemStack(Material.NETHERITE_SHOVEL);

    private static final NamespacedKey bonePickaxeNSK = new NamespacedKey(INSTANCE, "bone_pickaxe");
    private static final ItemStack bonePickaxeIS = OraxenItems.getItemById("bone_pickaxe").build();
    private static final ItemStack netheritePickaxeIS = new ItemStack(Material.NETHERITE_PICKAXE);

    private static final NamespacedKey boneAxeNSK = new NamespacedKey(INSTANCE, "bone_axe");
    private static final ItemStack boneAxeIS = OraxenItems.getItemById("bone_axe").build();
    private static final ItemStack netheriteAxeIS = new ItemStack(Material.NETHERITE_AXE);

    private static final NamespacedKey boneHoeNSK = new NamespacedKey(INSTANCE, "bone_hoe");
    private static final ItemStack boneHoeIS = OraxenItems.getItemById("bone_hoe").build();
    private static final ItemStack netheriteHoeIS = new ItemStack(Material.NETHERITE_HOE);

    private static final NamespacedKey boneHelmetNSK = new NamespacedKey(INSTANCE, "bone_helmet");
    private static final ItemStack boneHelmetIS = OraxenItems.getItemById("bone_helmet").build();
    private static final ItemStack netheriteHelmetIS = new ItemStack(Material.NETHERITE_HELMET);

    private static final NamespacedKey boneChestplateNSK = new NamespacedKey(INSTANCE, "bone_chestplate");
    private static final ItemStack boneChestplateIS = OraxenItems.getItemById("bone_chestplate").build();
    private static final ItemStack netheriteChestplateIS = new ItemStack(Material.NETHERITE_CHESTPLATE);

    private static final NamespacedKey boneLeggingsNSK = new NamespacedKey(INSTANCE, "bone_leggings");
    private static final ItemStack boneLeggingsIS = OraxenItems.getItemById("bone_leggings").build();
    private static final ItemStack netheriteLeggingsIS = new ItemStack(Material.NETHERITE_LEGGINGS);

    private static final NamespacedKey boneBootsNSK = new NamespacedKey(INSTANCE, "bone_boots");
    private static final ItemStack boneBootsIS = OraxenItems.getItemById("bone_boots").build();
    private static final ItemStack netheriteBootsIS = new ItemStack(Material.NETHERITE_BOOTS);

    private static final ItemStack upgradeTemplate = new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE);

    private static final RecipeChoice templateChoice = new RecipeChoice.ExactChoice(upgradeTemplate);

    public static List<ShapedRecipe> getItemsShapedRecipes() {
        List<ShapedRecipe> recipes = new ArrayList<>();
        if (!OraxenItems.exists("frammento_osseo") && !OraxenItems.exists("rottame_osseo") && !OraxenItems.exists("lingotto_osseo") && !OraxenItems.exists("cristallo_osseo")) return null;

        ItemStack frammentoOsseoIS = OraxenItems.getItemById("frammento_osseo").build();
        ItemStack rottameOsseoIS = OraxenItems.getItemById("rottame_osseo").build();
        ItemStack lingottoOsseoIS = OraxenItems.getItemById("lingotto_osseo").build();

        // ROTTAME OSSEO
        ShapedRecipe rottameOsseoRecipe = new ShapedRecipe(rottameOsseoNSK, rottameOsseoIS);

        rottameOsseoRecipe.shape(
                "FBB",
                "FFF",
                "FFF"
        );

        rottameOsseoRecipe.setIngredient('F', frammentoOsseoIS);
        rottameOsseoRecipe.setIngredient('B', Material.IRON_BLOCK);

        recipes.add(rottameOsseoRecipe);

        // LINGOTTO OSSEO
        ShapedRecipe lingottoOsseoRecipe = new ShapedRecipe(lingottoOsseoNSK, lingottoOsseoIS);

        lingottoOsseoRecipe.shape(
                "RRR",
                "RBR",
                "RRR"
        );

        lingottoOsseoRecipe.setIngredient('R', rottameOsseoIS);
        lingottoOsseoRecipe.setIngredient('B', Material.IRON_BLOCK);

        recipes.add(lingottoOsseoRecipe);

        // CRISTALLO OSSEO
        ShapedRecipe cristalloOsseoRecipe = new ShapedRecipe(cristalloOsseoNSK, cristalloOsseoIS);

        cristalloOsseoRecipe.shape(
                "RRR",
                "RBR",
                "RRR"
        );

        cristalloOsseoRecipe.setIngredient('R', lingottoOsseoIS);
        cristalloOsseoRecipe.setIngredient('B', Material.AMETHYST_SHARD);

        recipes.add(cristalloOsseoRecipe);

        return recipes;
    }

    @SuppressWarnings("Duplicates")
    public static List<SmithingTransformRecipe> getWeaponsSmithingRecipes() {
        List<SmithingTransformRecipe> recipes = new ArrayList<>();

        if (!OraxenItems.exists("bone_sword") && !OraxenItems.exists("bone_shovel") && !OraxenItems.exists("bone_pickaxe") && !OraxenItems.exists("bone_axe") && !OraxenItems.exists("bone_hoe")) return null;

        // BONE SWORD
        SmithingTransformRecipe boneSwordRecipe = new SmithingTransformRecipe(
                boneSwordNSK,
                boneSwordIS,
                templateChoice,
                new RecipeChoice.ExactChoice(netheriteSwordIS),
                new RecipeChoice.ExactChoice(cristalloOsseoIS)
        );

        // BONE SHOVEL
        SmithingTransformRecipe boneShovelRecipe = new SmithingTransformRecipe(
                boneShovelNSK,
                boneShovelIS,
                templateChoice,
                new RecipeChoice.ExactChoice(netheriteShovelIS),
                new RecipeChoice.ExactChoice(cristalloOsseoIS)
        );

        // BONE PICKAXE
        SmithingTransformRecipe bonePickaxeRecipe = new SmithingTransformRecipe(
                bonePickaxeNSK,
                bonePickaxeIS,
                templateChoice,
                new RecipeChoice.ExactChoice(netheritePickaxeIS),
                new RecipeChoice.ExactChoice(cristalloOsseoIS)
        );

        // BONE AXE
        SmithingTransformRecipe boneAxeRecipe = new SmithingTransformRecipe(
                boneAxeNSK,
                boneAxeIS,
                templateChoice,
                new RecipeChoice.ExactChoice(netheriteAxeIS),
                new RecipeChoice.ExactChoice(cristalloOsseoIS)
        );

        // BONE HOE
        SmithingTransformRecipe boneHoeRecipe = new SmithingTransformRecipe(
                boneHoeNSK,
                boneHoeIS,
                templateChoice,
                new RecipeChoice.ExactChoice(netheriteHoeIS),
                new RecipeChoice.ExactChoice(cristalloOsseoIS)
        );

        recipes.add(boneSwordRecipe);
        recipes.add(boneShovelRecipe);
        recipes.add(bonePickaxeRecipe);
        recipes.add(boneAxeRecipe);
        recipes.add(boneHoeRecipe);

        return recipes;
    }

    public static List<SmithingTransformRecipe> getArmorSmithingRecipes() {
        List<SmithingTransformRecipe> recipes = new ArrayList<>();

        if (!OraxenItems.exists("bone_helmet") && !OraxenItems.exists("bone_chestplate") && !OraxenItems.exists("bone_leggings") && !OraxenItems.exists("bone_boots")) return null;

        SmithingTransformRecipe boneHelmetRecipe = new SmithingTransformRecipe(
                boneHelmetNSK,
                boneHelmetIS,
                templateChoice,
                new RecipeChoice.ExactChoice(netheriteHelmetIS),
                new RecipeChoice.ExactChoice(cristalloOsseoIS)
        );

        SmithingTransformRecipe boneChestPlateRecipe = new SmithingTransformRecipe(
                boneChestplateNSK,
                boneChestplateIS,
                templateChoice,
                new RecipeChoice.ExactChoice(netheriteChestplateIS),
                new RecipeChoice.ExactChoice(cristalloOsseoIS)
        );


        SmithingTransformRecipe boneLeggingsRecipe = new SmithingTransformRecipe(
                boneLeggingsNSK,
                boneLeggingsIS,
                templateChoice,
                new RecipeChoice.ExactChoice(netheriteLeggingsIS),
                new RecipeChoice.ExactChoice(cristalloOsseoIS)
        );

        SmithingTransformRecipe boneBootsRecipe = new SmithingTransformRecipe(
                boneBootsNSK,
                boneBootsIS,
                templateChoice,
                new RecipeChoice.ExactChoice(netheriteBootsIS),
                new RecipeChoice.ExactChoice(cristalloOsseoIS)
        );


        recipes.add(boneHelmetRecipe);
        recipes.add(boneChestPlateRecipe);
        recipes.add(boneLeggingsRecipe);
        recipes.add(boneBootsRecipe);

        return recipes;
    }
}
