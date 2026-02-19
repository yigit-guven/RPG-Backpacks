package com.yigitguven.rpgbackpacks.compat;

import com.yigitguven.rpgbackpacks.client.renderer.BackpackCurioRenderer;
import com.yigitguven.rpgbackpacks.registry.ModItems;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

public class CuriosClientCompat {
    public static void registerRenderers() {
        CuriosRendererRegistry.register(ModItems.LEATHER_BACKPACK.get(), BackpackCurioRenderer::new);
        CuriosRendererRegistry.register(ModItems.IRON_BACKPACK.get(), BackpackCurioRenderer::new);
        CuriosRendererRegistry.register(ModItems.GOLDEN_BACKPACK.get(), BackpackCurioRenderer::new);
        CuriosRendererRegistry.register(ModItems.DIAMOND_BACKPACK.get(), BackpackCurioRenderer::new);
        CuriosRendererRegistry.register(ModItems.NETHERITE_BACKPACK.get(), BackpackCurioRenderer::new);
    }
}
