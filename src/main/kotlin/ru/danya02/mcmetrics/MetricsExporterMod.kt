package ru.danya02.mcmetrics

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.player.AttackBlockCallback
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import org.slf4j.LoggerFactory

object MetricsExporterMod : ModInitializer {
    private val logger = LoggerFactory.getLogger("server-metrics-exporter")

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hello Fabric world!")

		val listener: (PlayerEntity, World, Hand, BlockPos, Direction) -> ActionResult = {
			pe:PlayerEntity, w:World, h:Hand, bp:BlockPos, d:Direction ->
			logger.info("AttackBlockCallback: {} {} {} {} {}", pe, w, h, bp, d)
			ActionResult.PASS
		}
		AttackBlockCallback.EVENT.register(listener)
	}
}