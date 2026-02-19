package com.yigitguven.rpgbackpacks.registry;

import com.yigitguven.rpgbackpacks.RpgBackpacks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, RpgBackpacks.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BACKPACKS_TAB = CREATIVE_MODE_TABS.register(
            "backpacks_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.rpgbackpacks.backpacks_tab"))
                    .icon(() -> new ItemStack(ModItems.LEATHER_BACKPACK.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.LEATHER_BACKPACK.get());
                        output.accept(ModItems.IRON_BACKPACK.get());
                        output.accept(ModItems.GOLDEN_BACKPACK.get());
                        output.accept(ModItems.DIAMOND_BACKPACK.get());
                        output.accept(ModItems.NETHERITE_BACKPACK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
