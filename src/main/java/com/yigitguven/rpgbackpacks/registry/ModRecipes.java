package com.yigitguven.rpgbackpacks.registry;

import com.yigitguven.rpgbackpacks.RpgBackpacks;
import com.yigitguven.rpgbackpacks.recipe.BackpackUpgradeRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
            .create(Registries.RECIPE_SERIALIZER, RpgBackpacks.MODID);

    public static final Supplier<RecipeSerializer<BackpackUpgradeRecipe>> BACKPACK_UPGRADE_SERIALIZER = SERIALIZERS
            .register("backpack_upgrade", BackpackUpgradeRecipe.Serializer::new);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
