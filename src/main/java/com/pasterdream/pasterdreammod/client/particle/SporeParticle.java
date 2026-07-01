package com.pasterdream.pasterdreammod.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class SporeParticle extends TextureSheetParticle {

    private final SpriteSet spriteSet;

    protected SporeParticle(ClientLevel level, double x, double y, double z,
                            double xSpeed, double ySpeed, double zSpeed, SpriteSet spriteSet) {
        super(level, x, y, z);
        this.spriteSet = spriteSet;
        this.setSize(0f, 0f);
        this.quadSize *= 1.5f;
        this.lifetime = Math.max(1, 10 + (this.random.nextInt(6) - 3));
        this.gravity = 0f;
        this.hasPhysics = false;
        this.xd = xSpeed * 0.1;
        this.yd = ySpeed * 0.1;
        this.zd = zSpeed * 0.1;
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public int getLightColor(float partialTick) {
        return 15728880;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.removed) {
            this.setSprite(this.spriteSet.get((this.age / 5) % 4 + 1, 4));
        }
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
            return new SporeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, spriteSet);
        }
    }
}
