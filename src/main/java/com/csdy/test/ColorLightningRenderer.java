package com.csdy.test;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LightningBolt;
import org.joml.Matrix4f;

import java.util.Random;

public class ColorLightningRenderer extends LightningBoltRenderer {

    public ColorLightningRenderer(EntityRendererProvider.Context context) {
        super(context);
    }
    public float[] colors = new float[]{0.45F, 0.45F, 0.5F};
    public void render(LightningBolt p_115266_, float p_115267_, float p_115268_, PoseStack p_115269_, MultiBufferSource p_115270_, int p_115271_) {
        float[] afloat = new float[8];
        float[] afloat1 = new float[8];
        float f = 0.0F;
        float f1 = 0.0F;
        RandomSource randomsource = RandomSource.create(p_115266_.seed);

        for (int i = 7; i >= 0; --i) {
            afloat[i] = f;
            afloat1[i] = f1;
            f += (float) (randomsource.nextInt(11) - 5);
            f1 += (float) (randomsource.nextInt(11) - 5);
        }

        VertexConsumer vertexconsumer = p_115270_.getBuffer(RenderType.lightning());
        Matrix4f matrix4f = p_115269_.last().pose();

        for (int j = 0; j < 4; ++j) {
            RandomSource randomsource1 = RandomSource.create(p_115266_.seed);

            for (int k = 0; k < 3; ++k) {
                int l = 7;
                int i1 = 0;
                if (k > 0) {
                    l = 7 - k;
                }

                if (k > 0) {
                    i1 = l - 2;
                }

                float f2 = afloat[l] - f;
                float f3 = afloat1[l] - f1;

                for (int j1 = l; j1 >= i1; --j1) {
                    float f4 = f2;
                    float f5 = f3;
                    if (k == 0) {
                        f2 += (float) (randomsource1.nextInt(11) - 5);
                        f3 += (float) (randomsource1.nextInt(11) - 5);
                    } else {
                        f2 += (float) (randomsource1.nextInt(31) - 15);
                        f3 += (float) (randomsource1.nextInt(31) - 15);
                    }

                    float f10 = 0.1F + (float) j * 0.2F;
                    if (k == 0) {
                        float var10000 = f10 * ((float) j1 * 0.1F + 1.0F);
                    }

                    float f11 = 0.1F + (float) j * 0.2F;
                    if (k == 0) {
                        f11 *= ((float) j1 - 1.0F) * 0.1F + 1.0F;
                    }

                    Random randCol = new Random(milliTime() * p_115266_.seed);
                    this.colors = new float[]{randCol.nextFloat(), randCol.nextFloat(), randCol.nextFloat()};
                    float r = this.colors[0];
                    float g = this.colors[1];
                    float b = this.colors[2];
                    float alpha = 1.0F;
                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, r, g, b, alpha, f11, false, false, true, false);
                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, r, g, b, alpha, f11, true, false, true, true);
                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, r, g, b, alpha, f11, true, true, false, true);
                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, r, g, b, alpha, f11, false, true, false, false);
                }
            }
        }

    }

    private static void quad(Matrix4f p_253966_, VertexConsumer p_115274_, float p_115275_, float p_115276_, int p_115277_, float p_115278_, float p_115279_, float p_115280_, float p_115281_, float p_115282_, float p_115283_, float p_115284_, boolean p_115285_, boolean p_115286_, boolean p_115287_, boolean p_115288_) {
        p_115274_.vertex(p_253966_, p_115275_ + (p_115285_ ? p_115284_ : -p_115284_), (float) (p_115277_ * 16), p_115276_ + (p_115286_ ? p_115284_ : -p_115284_)).color(p_115280_, p_115281_, p_115282_, 0.3F).endVertex();
        p_115274_.vertex(p_253966_, p_115278_ + (p_115285_ ? p_115283_ : -p_115283_), (float) ((p_115277_ + 1) * 16), p_115279_ + (p_115286_ ? p_115283_ : -p_115283_)).color(p_115280_, p_115281_, p_115282_, 0.3F).endVertex();
        p_115274_.vertex(p_253966_, p_115278_ + (p_115287_ ? p_115283_ : -p_115283_), (float) ((p_115277_ + 1) * 16), p_115279_ + (p_115288_ ? p_115283_ : -p_115283_)).color(p_115280_, p_115281_, p_115282_, 0.3F).endVertex();
        p_115274_.vertex(p_253966_, p_115275_ + (p_115287_ ? p_115284_ : -p_115284_), (float) (p_115277_ * 16), p_115276_ + (p_115288_ ? p_115284_ : -p_115284_)).color(p_115280_, p_115281_, p_115282_, 0.3F).endVertex();
    }

    private static long milliTime() {
        return System.nanoTime() / 100000000L;
    }
}
