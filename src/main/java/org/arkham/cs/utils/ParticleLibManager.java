package org.arkham.cs.utils;

import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;

public class ParticleLibManager {
	
	@SuppressWarnings("unused")
	private EffectManager effectManager;
	
	public ParticleLibManager(){
		EffectLib lib = EffectLib.instance();
		effectManager = new EffectManager(lib);
	}
	
	public enum FancyEffects {
		/**
		 *  - Create architectual correct arc of particles
		 */
		ArcLocationEffect,
		/**
		 *  - Create the orbital-model of the atom
		 */
		AtomLocationEffect,
		/**
		 * - Let the target entity bleed.
		 */
		BleedEntityEffect,
		/**
		 * - Cast a cone in all possible directions
		 */
		ConeLocationEffect,
		/**
		 * Create DNA molecule
		 */
		DnaLocationEffect,
		/**
		 *  - Create a explosion at location.
		 */
		ExplodeLocationEffect,
		/**
		 * - Let the target entity burn.
		 */
		FlameEntityEffect,
		/**
		 *  - Create a foundtain for you well
		 */
		FountainLocationEffect,
		/**
		 * - Customizable grid for you signwall
		 */
		GridLocationEffect,
		/**
		 *  - Create a customizable static helix.
		 */
		HelixLocationEffect,
		/**
		 * - Forces an entity to jump naturally.
		 */
		JumpEntityEffect,
		/**
		 *  - Draw line from A to B
		 */
		LineLocationEffect,
		/**
		 * - The target entity is in love.
		 */
		LoveEntityEffect,
		/**
		 * - Circle of notes appeares above the entity.
		 */
		MusicEntityEffect,
		/**
		 *  - Circle of notes appeares at a location.
		 */
		MusicLocationEffect,
		/**
		 *  - Spherical Shield around an entity.
		 */
		ShieldEntityEffect,
		/**
		 * - Foces an entity to fly into the sky.
		 */
		SkyRocketEntityEffect,
		/**
		 *  - Let the target entity smoke.
		 */
		SmokeEntityEffect,
		/**
		 * - Create fully customizable 3D star
		 */
		StarLocationEffect,
		/**
		 *  - Create particle-text with custom font, size and text
		 */
		TextLocationEffect,
		/**
		 *  - Create a trace along an entitys path.
		 */
		TraceEntityEffect,
		/**
		 *  - Forces the player to turn in a circle.
		 */
		TurnPlayerEffect,
		/**
		 * - Create a vortex of particles at location
		 */
		VortexLocationEffect,
		/**
		 * - Create a warp-effect around an entity
		 */
		WarpEntityEffect;
	}

}
