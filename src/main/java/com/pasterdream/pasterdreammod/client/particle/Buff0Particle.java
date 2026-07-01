package com.pasterdream.pasterdreammod.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class Buff0Particle extends TextureSheetParticle {

    private final SpriteSet spriteSet;

    protected Buff0Particle(ClientLevel level, double x, double y, double z,
                            double xSpeed, double ySpeed, double zSpeed, SpriteSet spriteSet) {
        super(level, x, y, z);
        this.spriteSet = spriteSet;
        this.setSize(0.2f, 0.2f);
        this.quadSize *= 1.6f;
        this.lifetime = Math.max(1, 10 + (this.random.nextInt(2) - 1));
        this.gravity = -1f;
        this.hasPhysics = true;
        this.xd = xSpeed * 0;
        this.yd = ySpeed * 0;
        this.zd = zSpeed * 0;
        this.pickSprite(spriteSet);
    }

    @Override
    public int getLightColor(float partialTick) {
        return 15728880;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new Buff0Particle(level, x, y, z, xSpeed, ySpeed, zSpeed, spriteSet);
        }
    }
}
