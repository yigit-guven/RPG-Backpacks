package com.yigitguven.rpgbackpacks.compat;

import com.yigitguven.rpgbackpacks.registry.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

public class CuriosCompat {
    public static Optional<SlotResult> findBackpackResult(Player player) {
        if (!ModList.get().isLoaded("curios")) {
            return Optional.empty();
        }

        return CuriosApi.getCuriosHelper().findFirstCurio(player,
                stack -> stack.getItem() instanceof com.yigitguven.rpgbackpacks.item.BackpackItem);
    }

    public static ItemStack findBackpack(Player player) {
        return findBackpackResult(player).map(SlotResult::stack).orElse(ItemStack.EMPTY);
    }

    public static void updateBackpack(Player player, ItemStack updatedStack) {
        if (!ModList.get().isLoaded("curios") || updatedStack.isEmpty()) {
            return;
        }

        findBackpackResult(player).ifPresent(result -> CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
            var stacksHandler = handler.getCurios().get(result.slotContext().identifier());
            if (stacksHandler == null) {
                return;
            }
            stacksHandler.getStacks().setStackInSlot(result.slotContext().index(), updatedStack.copy());
        }));
    }
}
