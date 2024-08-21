package com.grenades.weapons.entity;

import com.grenades.weapons.init.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ThrowableSmokeGrenadeEntity extends ThrowableGrenadeEntity {
    private static final int MAX_TICK_COUNT = 20 * 20;
    private static final double R = 3.0D;

    public ThrowableSmokeGrenadeEntity(EntityType<? extends ThrowableGrenadeEntity> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    public ThrowableSmokeGrenadeEntity(EntityType<? extends ThrowableGrenadeEntity> entityType, Level world, LivingEntity entity) {
        super(entityType, world, entity);
    }

    public ThrowableSmokeGrenadeEntity(Level world, LivingEntity player, int maxCookTime) {
        super(ModEntities.THROWABLE_SMOKE_GRENADE.get(), world, player);
        this.setMaxLife(maxCookTime);
    }

    private void smoke() {
        double xCenter = this.getX();
        double yCenter = this.getY();
        double zCenter = this.getZ();

        // 构建四叉树
        Quadtree quadtree = new Quadtree(xCenter - R, yCenter - 0.5D, zCenter - R, xCenter + R, yCenter + R, zCenter + R);

        // 插入粒子生成位置
        for (double x = xCenter - R; x < xCenter + R; x += 0.5D) {
            for (double y = yCenter - 0.5D; y < yCenter + R; y += 0.5D) {
                for (double z = zCenter - R; z < zCenter + R; z += 0.5D) {
                    if (Math.pow(x - xCenter, 2) + Math.pow(y + 0.5D - yCenter, 2) + Math.pow(z - zCenter, 2) <= R * R) {
                        quadtree.insert(x, y + 0.5D, z);
                    }
                }
            }
        }
        // 查询四叉树并生成粒子
        List<Particle> particles = quadtree.queryRange(xCenter - R, yCenter - 0.5D, zCenter - R, xCenter + R, yCenter + R, zCenter + R);
        for (Particle particle : particles) {
            this.level().addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, particle.x, particle.y, particle.z, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void tick() {
        super.tick();
        boolean isStationary = this.xOld == this.getX() && this.yOld == this.getY() && this.zOld == this.getZ();

        if (isStationary) {
            this.tickCount++;
        } else {
            this.tickCount = 0;
        }

        if (this.tickCount == 2) {
            this.onDeath();
        } else if (this.tickCount >= 2 && this.tickCount <= MAX_TICK_COUNT) {
            if (this.level().isClientSide && this.tickCount % 6 == 0) {
                this.smoke();
            }
        } else if (this.tickCount > MAX_TICK_COUNT) {
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    public void onDeath() {
        super.onDeath();
    }

    // 四叉树节点类
    private static class Quadtree {
        private final double x1, y1, z1, x2, y2, z2;
        private final List<Particle> particles;
        private Quadtree[] children;

        public Quadtree(double x1, double y1, double z1, double x2, double y2, double z2) {
            this.x1 = x1;
            this.y1 = y1;
            this.z1 = z1;
            this.x2 = x2;
            this.y2 = y2;
            this.z2 = z2;
            this.particles = new ArrayList<>();
            this.children = null;
        }

        public void insert(double x, double y, double z) {
            if (!inRange(x, y, z)) return;

            if (particles.size() < 4) {
                particles.add(new Particle(x, y, z));
            } else {
                if (children == null) {
                    split();
                }
                for (Quadtree child : children) {
                    child.insert(x, y, z);
                }
            }
        }

        public List<Particle> queryRange(double qx1, double qy1, double qz1, double qx2, double qy2, double qz2) {
            List<Particle> result = new ArrayList<>();
            if (!intersects(qx1, qy1, qz1, qx2, qy2, qz2)) return result;

            for (Particle particle : particles) {
                if (particle.inRange(qx1, qy1, qz1, qx2, qy2, qz2)) {
                    result.add(particle);
                }
            }

            if (children != null) {
                for (Quadtree child : children) {
                    result.addAll(child.queryRange(qx1, qy1, qz1, qx2, qy2, qz2));
                }
            }

            return result;
        }

        private boolean inRange(double x, double y, double z) {
            return x >= x1 && x <= x2 && y >= y1 && y <= y2 && z >= z1 && z <= z2;
        }

        private boolean intersects(double qx1, double qy1, double qz1, double qx2, double qy2, double qz2) {
            return !(qx2 < x1 || qx1 > x2 || qy2 < y1 || qy1 > y2 || qz2 < z1 || qz1 > z2);
        }

        private void split() {
            double xMid = (x1 + x2) / 2;
            double yMid = (y1 + y2) / 2;
            double zMid = (z1 + z2) / 2;
            children = new Quadtree[] {
                    new Quadtree(x1, y1, z1, xMid, yMid, zMid),
                    new Quadtree(xMid, y1, z1, x2, yMid, zMid),
                    new Quadtree(x1, yMid, z1, xMid, y2, zMid),
                    new Quadtree(xMid, yMid, z1, x2, y2, zMid),
                    new Quadtree(x1, y1, zMid, xMid, yMid, z2),
                    new Quadtree(xMid, y1, zMid, x2, yMid, z2),
                    new Quadtree(x1, yMid, zMid, xMid, y2, z2),
                    new Quadtree(xMid, yMid, zMid, x2, y2, z2)
            };
        }
    }

    // 粒子类
    private static class Particle {
        double x, y, z;

        public Particle(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public boolean inRange(double qx1, double qy1, double qz1, double qx2, double qy2, double qz2) {
            return x >= qx1 && x <= qx2 && y >= qy1 && y <= qy2 && z >= qz1 && z <= qz2;
        }
    }
}
