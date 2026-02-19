package com.yigitguven.rpgbackpacks.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.yigitguven.rpgbackpacks.item.BackpackItem;
import com.yigitguven.rpgbackpacks.registry.ModDataComponents;
import com.yigitguven.rpgbackpacks.registry.ModRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;

public class BackpackUpgradeRecipe extends ShapedRecipe {
    private final String group;
    private final CraftingBookCategory category;
    private final ShapedRecipePattern pattern;
    private final ItemStack result;

    public BackpackUpgradeRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern,
            ItemStack result) {
        super(group, category, pattern, result);
        this.group = group;
        this.category = category;
        this.pattern = pattern;
        this.result = result;
    }

    public BackpackUpgradeRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern,
            ItemStack result, boolean showNotification) {
        super(group, category, pattern, result, showNotification);
        this.group = group;
        this.category = category;
        this.pattern = pattern;
        this.result = result;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack resultStack = this.result.copy(); // Use our local result

        // Find the input backpack
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof BackpackItem) {
                // Found the old backpack. Copy its contents.
                // We use our custom DataComponent: BACKPACK_CONTENTS
                var contents = stack.get(ModDataComponents.BACKPACK_CONTENTS.get());
                if (contents != null) {
                    resultStack.set(ModDataComponents.BACKPACK_CONTENTS.get(), contents);
                }
                break; // Only one backpack per recipe expected
            }
        }

        return resultStack;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.BACKPACK_UPGRADE_SERIALIZER.get();
    }

    // Accessors for our local fields if needed, or direct access in Serializer
    public String getGroup() {
        return group;
    }

    public CraftingBookCategory category() {
        return category;
    }

    public ShapedRecipePattern pattern() {
        return pattern;
    }

    public ItemStack result() {
        return result;
    }

    public static class Serializer implements RecipeSerializer<BackpackUpgradeRecipe> {
        public static final MapCodec<BackpackUpgradeRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance
                .group(
                        MapCodec.unit("").forGetter(BackpackUpgradeRecipe::getGroup),
                        CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC)
                                .forGetter(BackpackUpgradeRecipe::category),
                        ShapedRecipePattern.MAP_CODEC.forGetter(BackpackUpgradeRecipe::pattern),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(BackpackUpgradeRecipe::result))
                .apply(instance, (group, category, pattern, result) -> new BackpackUpgradeRecipe(group, category,
                        pattern, result)));

        public static final StreamCodec<RegistryFriendlyByteBuf, BackpackUpgradeRecipe> STREAM_CODEC = StreamCodec
                .composite(
                        ByteBufCodecs.STRING_UTF8,
                        BackpackUpgradeRecipe::getGroup,
                        CraftingBookCategory.STREAM_CODEC,
                        BackpackUpgradeRecipe::category,
                        ShapedRecipePattern.STREAM_CODEC,
                        BackpackUpgradeRecipe::pattern,
                        ItemStack.STREAM_CODEC,
                        BackpackUpgradeRecipe::result,
                        BackpackUpgradeRecipe::new);

        @Override
        public MapCodec<BackpackUpgradeRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, BackpackUpgradeRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
