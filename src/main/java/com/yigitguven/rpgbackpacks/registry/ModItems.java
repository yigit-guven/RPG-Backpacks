package com.yigitguven.rpgbackpacks.registry;

import com.yigitguven.rpgbackpacks.RpgBackpacks;
import com.yigitguven.rpgbackpacks.item.BackpackItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RpgBackpacks.MODID);

        public static final DeferredItem<BackpackItem> LEATHER_BACKPACK = ITEMS.register("leather_backpack",
                        () -> new BackpackItem(new Item.Properties().stacksTo(1), 2));
        public static final DeferredItem<BackpackItem> IRON_BACKPACK = ITEMS.register("iron_backpack",
                        () -> new BackpackItem(new Item.Properties().stacksTo(1), 3));
        public static final DeferredItem<BackpackItem> GOLDEN_BACKPACK = ITEMS.register("golden_backpack",
                        () -> new BackpackItem(new Item.Properties().stacksTo(1), 4));
        public static final DeferredItem<BackpackItem> DIAMOND_BACKPACK = ITEMS.register("diamond_backpack",
                        () -> new BackpackItem(new Item.Properties().stacksTo(1), 5));
        public static final DeferredItem<BackpackItem> NETHERITE_BACKPACK = ITEMS.register("netherite_backpack",
                        () -> new BackpackItem(new Item.Properties().stacksTo(1).fireResistant(), 6));

        public static void register(IEventBus eventBus) {
                ITEMS.register(eventBus);
        }
}
