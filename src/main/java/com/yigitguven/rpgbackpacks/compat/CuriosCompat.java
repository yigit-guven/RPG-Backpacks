package com.yigitguven.rpgbackpacks.compat;

import com.yigitguven.rpgbackpacks.registry.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

public class CuriosCompat {
    public static ItemStack findBackpack(Player player) {
        if (!ModList.get().isLoaded("curios"))
            return ItemStack.EMPTY;

        Optional<SlotResult> result = CuriosApi.getCuriosHelper().findFirstCurio(player,
                stack -> stack.getItem() instanceof com.yigitguven.rpgbackpacks.item.BackpackItem);
        return result.map(SlotResult::stack).orElse(ItemStack.EMPTY);
    }
}
