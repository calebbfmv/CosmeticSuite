package org.arkham.cs.handler;

import org.arkham.cs.effects.ParticleEffect;
import org.bukkit.entity.Player;

import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.ArcLocationEffect;
import de.slikey.effectlib.effect.AtomLocationEffect;
import de.slikey.effectlib.effect.BleedEntityEffect;
import de.slikey.effectlib.effect.ConeLocationEffect;
import de.slikey.effectlib.effect.DnaLocationEffect;
import de.slikey.effectlib.effect.ExplodeLocationEffect;
import de.slikey.effectlib.effect.FlameEntityEffect;
import de.slikey.effectlib.effect.FountainLocationEffect;

public class ParticleLibManager {

	private static EffectManager effectManager;

	public ParticleLibManager() {
		EffectLib lib = EffectLib.instance();
		effectManager = new EffectManager(lib);
	}

	public enum FancyEffects {
		/**
		 * - Create architectual correct arc of particles
		 */
		ARCLOCATIONEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Create the orbital-model of the atom
		 */
		ATOMLOCATIONEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Let the target entity bleed.
		 */
		BLEEDENTITYEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Cast a cone in all possible directions
		 */
		CONELOCATIONEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * Create DNA molecule
		 */
		DNALOCATIONEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Create a explosion at location.
		 */
		EXPLODELOCATIONEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Let the target entity burn.
		 */
		FLAMEENTITYEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Create a foundtain for you well
		 */
		FOUNTAINLOCATIONEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Customizable grid for you signwall
		 */
		GRIDLOCATIONEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Create a customizable static helix.
		 */
		HELIXLOCATIONEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Forces an entity to jump naturally.
		 */
		JUMPENTITYEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Draw line from A to B
		 */
		LINELOCATIONEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - The target entity is in love.
		 */
		LOVEENTITYEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Circle of notes appeares above the entity.
		 */
		MUSICENTITYEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Circle of notes appeares at a location.
		 */
		MUSICLOCATIONEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Spherical Shield around an entity.
		 */
		SHIELDENTITYEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Foces an entity to fly into the sky.
		 */
		SKYROCKETENTITYEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Let the target entity smoke.
		 */
		SMOKEENTITYEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Create fully customizable 3D star
		 */
		STARLOCATIONEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Create particle-text with custom font, size and text
		 */
		TEXTLOCATIONEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Create a trace along an entitys path.
		 */
		TRACEENTITYEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Forces the player to turn in a circle.
		 */
		TURNPLAYEREFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Create a vortex of particles at location
		 */
		VORTEXLOCATIONEFFECT(ParticleClassType.LIB_EFFECT),
		/**
		 * - Create a warp-effect around an entity
		 */
		WARPENTITYEFFECT(ParticleClassType.LIB_EFFECT),

		/**
		 * @appearance Huge explosions
		 * @displayed by TNT and creepers
		 */
		HUGE_EXPLOSION(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Smaller explosions
		 * @displayed by TNT and creepers
		 */
		LARGE_EXPLODE(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Little white sparkling stars
		 * @displayed by Fireworks
		 */
		FIREWORKS_SPARK(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Bubbles
		 * @displayed in water
		 */
		BUBBLE(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Unknown
		 */
		SUSPEND(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Little gray dots
		 * @displayed in the Void and water
		 */
		DEPTH_SUSPEND(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Little gray dots
		 * @displayed by Mycelium
		 */
		TOWN_AURA(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Light brown crosses
		 * @displayed by critical hits
		 */
		CRIT(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Cyan stars
		 * @displayed by hits with an enchanted weapon
		 */
		MAGIC_CRIT(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Little black/gray clouds
		 * @displayed by torches, primed TNT and end portals
		 */
		SMOKE(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Colored swirls
		 * @displayed by potion effects
		 */
		MOB_SPELL(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Transparent colored swirls
		 * @displayed by beacon effect
		 */
		MOB_SPELL_AMBIENT(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Colored swirls
		 * @displayed by splash potions
		 */
		SPELL(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Colored crosses
		 * @displayed by instant splash potions (instant health/instant damage)
		 */
		INSTANT_SPELL(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Colored crosses
		 * @displayed by witches
		 */
		WITCH_MAGIC(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Colored notes
		 * @displayed by note blocks
		 */
		NOTE(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Little purple clouds
		 * @displayed by nether portals, endermen, ender pearls, eyes of ender
		 *            and ender chests
		 */
		PORTAL(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance: White letters
		 * @displayed by enchantment tables that are near bookshelves
		 */
		ENCHANTMENT_TABLE(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance White clouds
		 */
		EXPLODE(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Little flames
		 * @displayed by torches, furnaces, magma cubes and monster spawners
		 */
		FLAME(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Little orange blobs
		 * @displayed by lava
		 */
		LAVA(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Gray transparent squares
		 */
		FOOTSTEP(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Blue drops
		 * @displayed by water, rain and shaking wolves
		 */
		SPLASH(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Blue droplets
		 * @displayed on water when fishing
		 */
		WAKE(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Black/Gray clouds
		 * @displayed by fire, minecarts with furance and blazes
		 */
		LARGE_SMOKE(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Large white clouds
		 * @displayed on mob death
		 */
		CLOUD(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Little colored clouds
		 * @displayed by active redstone wires and redstone torches
		 */
		RED_DUST(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Little white parts
		 * @displayed by cracking snowballs and eggs
		 */
		SNOWBALL_POOF(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Blue drips
		 * @displayed by blocks below a water source
		 */
		DRIP_WATER(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Orange drips
		 * @displayed by blocks below a lava source
		 */
		DRIP_LAVA(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance White clouds
		 */
		SNOW_SHOVEL(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Little green parts
		 * @displayed by slimes
		 */
		SLIME(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Red hearts
		 * @displayed when breeding
		 */
		HEART(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Dark gray cracked hearts
		 * @displayed when attacking a villager in a village
		 */
		ANGRY_VILLAGER(ParticleClassType.PARTICLE_EFFECT),
		/**
		 * @appearance Green stars
		 * @displayed by bone meal and when trading with a villager
		 */
		HAPPY_VILLAGER(ParticleClassType.PARTICLE_EFFECT);

		private ParticleClassType type;

		private FancyEffects(ParticleClassType type) {
			this.type = type;
		}

		public ParticleClassType getType() {
			return type;
		}

		public void display(Player player) {
			switch (this.type) {
			case LIB_EFFECT:
				switch (this) {
				case ARCLOCATIONEFFECT:
					ArcLocationEffect arc = new de.slikey.effectlib.effect.ArcLocationEffect(effectManager, player.getLocation(), player.getLocation().getDirection().multiply(-3)
							.toLocation(player.getWorld()));
					arc.start();
					break;
				case ATOMLOCATIONEFFECT:
					AtomLocationEffect atom = new AtomLocationEffect(effectManager, player.getEyeLocation());
					atom.start();
					break;
				case BLEEDENTITYEFFECT:
					BleedEntityEffect bl = new BleedEntityEffect(effectManager, player);
					bl.start();
					break;
				case CONELOCATIONEFFECT:
					ConeLocationEffect cl = new ConeLocationEffect(effectManager, player.getLocation());
					cl.start();
					break;
				case DNALOCATIONEFFECT:
					DnaLocationEffect dn = new DnaLocationEffect(effectManager, player.getLocation());
					dn.start();
					break;
				case EXPLODELOCATIONEFFECT:
					ExplodeLocationEffect ex = new ExplodeLocationEffect(effectManager, player.getLocation());
					ex.start();
					break;
				case FLAMEENTITYEFFECT:
					FlameEntityEffect fl = new FlameEntityEffect(effectManager, player);
					fl.start();
					break;
				case FOUNTAINLOCATIONEFFECT:
					FountainLocationEffect fle = new FountainLocationEffect(effectManager, player.getLocation());
					fle.start();
					break;
				default:
					break;
				}
			case PARTICLE_EFFECT:
				ParticleEffect effect = ParticleEffect.ANGRY_VILLAGER;
				switch (this) {
				case ANGRY_VILLAGER:
					effect = ParticleEffect.ANGRY_VILLAGER;
					break;
				case BUBBLE:
					effect = ParticleEffect.BUBBLE;
					break;
				case CLOUD:
					effect = ParticleEffect.CLOUD;
					break;
				case CRIT:
					effect = ParticleEffect.CRIT;
					break;
				case DEPTH_SUSPEND:
					effect = ParticleEffect.DEPTH_SUSPEND;
					break;
				case DRIP_LAVA:
					effect = ParticleEffect.DRIP_LAVA;
					break;
				case DRIP_WATER:
					effect = ParticleEffect.DRIP_WATER;
					break;
				case EXPLODE:
					effect = ParticleEffect.EXPLODE;
					break;
				case FLAME:
					effect = ParticleEffect.EXPLODE;
					break;
				case FOOTSTEP:
					effect = ParticleEffect.EXPLODE;
					break;
				case HAPPY_VILLAGER:
					effect = ParticleEffect.EXPLODE;
					break;
				case HEART:
					effect = ParticleEffect.EXPLODE;
					break;
				case HUGE_EXPLOSION:
					effect = ParticleEffect.EXPLODE;
					break;
				case FIREWORKS_SPARK:
					effect = ParticleEffect.EXPLODE;
					break;
				case ENCHANTMENT_TABLE:
					effect = ParticleEffect.EXPLODE;
					break;
				case INSTANT_SPELL:
					effect = ParticleEffect.EXPLODE;
					break;
				case LARGE_EXPLODE:
					effect = ParticleEffect.EXPLODE;
					break;
				case LARGE_SMOKE:
					effect = ParticleEffect.EXPLODE;
					break;
				case LAVA:
					effect = ParticleEffect.EXPLODE;
					break;
				case MAGIC_CRIT:
					effect = ParticleEffect.EXPLODE;
					break;
				case MOB_SPELL:
					effect = ParticleEffect.EXPLODE;
					break;
				case MOB_SPELL_AMBIENT:
					effect = ParticleEffect.EXPLODE;
					break;
				case NOTE:
					effect = ParticleEffect.EXPLODE;
					break;
				case PORTAL:
					effect = ParticleEffect.EXPLODE;
					break;
				case RED_DUST:
					effect = ParticleEffect.EXPLODE;
					break;
				case SLIME:
					effect = ParticleEffect.EXPLODE;
					break;
				case SMOKE:
					effect = ParticleEffect.EXPLODE;
					break;
				case SNOW_SHOVEL:
					effect = ParticleEffect.EXPLODE;
					break;
				case SNOWBALL_POOF:
					effect = ParticleEffect.EXPLODE;
					break;
				case SPELL:
					effect = ParticleEffect.EXPLODE;
					break;
				case SPLASH:
					effect = ParticleEffect.EXPLODE;
					break;
				case SUSPEND:
					effect = ParticleEffect.EXPLODE;
					break;
				case TOWN_AURA:
					effect = ParticleEffect.EXPLODE;
					break;
				case WAKE:
					effect = ParticleEffect.EXPLODE;
					break;
				case WITCH_MAGIC:
					effect = ParticleEffect.EXPLODE;
					break;
				default:
					break;
				}
				effect.display(player.getLocation(), 10F, 10);
			}
		}

		public enum ParticleClassType {
			PARTICLE_EFFECT, LIB_EFFECT;
		}
	}

}
