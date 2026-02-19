package com.yigitguven.rpgbackpacks.registry;

import com.yigitguven.rpgbackpacks.RpgBackpacks;
import com.yigitguven.rpgbackpacks.menu.BackpackMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU,
            RpgBackpacks.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<BackpackMenu>> BACKPACK_MENU = MENUS.register(
            "backpack_menu",
            () -> IMenuTypeExtension.create(BackpackMenu::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
