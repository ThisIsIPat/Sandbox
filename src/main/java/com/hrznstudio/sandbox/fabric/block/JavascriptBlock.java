package com.hrznstudio.sandbox.fabric.block;

import com.eclipsesource.v8.V8Object;
import com.hrznstudio.sandbox.api.ScriptEngine;
import com.hrznstudio.sandbox.util.V8Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class JavascriptBlock extends Block {
    private V8Object object;

    public JavascriptBlock(V8Object object) {
        super(Block.Settings.of(Material.METAL));
        this.object = object;
    }

    @Override
    public void onBreak(World world_1, BlockPos pos, BlockState blockState_1, PlayerEntity playerEntity_1) {
        super.onBreak(world_1, pos, blockState_1, playerEntity_1);

        if (!world_1.isClient) {
            if (object.contains("onBreak")) {
                ScriptEngine.ENGINE.getLocker();
                V8Util.push();
                object.executeVoidFunction("onBreak", V8Util.createV8Array(
                        V8Util.createV8Pos(pos),
                        V8Util.createV8Player(playerEntity_1)
                ));
                V8Util.pop();
            }
        }
    }

    @Override
    public void onPlaced(World world_1, BlockPos pos, BlockState blockState_1, @Nullable LivingEntity livingEntity_1, ItemStack itemStack_1) {
        super.onPlaced(world_1, pos, blockState_1, livingEntity_1, itemStack_1);

        if (!world_1.isClient) {
            if (object.contains("onPlace")) {
                ScriptEngine.ENGINE.getLocker();
                V8Util.push();
                object.executeVoidFunction("onPlace", V8Util.createV8Array(
                        V8Util.createV8Pos(pos),
                        livingEntity_1 instanceof PlayerEntity ? V8Util.createV8Player((PlayerEntity) livingEntity_1) : null
                ));
                V8Util.pop();
            }
        }
    }
}