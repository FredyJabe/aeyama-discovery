package aeyama.world.block;

import arc.*;
import arc.graphics.g2d.*;
import arc.util.*;

import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.world.*;

import static mindustry.Vars.tilesize;

public class ModuleableBlock extends Block {
    /** The amount of available module slots to the player. */
    public int moduleSlots;

    //TODO fix rotation not changing rotation value
    public ModuleableBlock(String name, int moduleSlots) {        
        super(name);

        solid = true;
        update = true;
        sync = true;
        rotate = rotateDraw = true;
        drawArrow = false;

        this.moduleSlots = moduleSlots;
    }

    public ModuleableBlock(String name) {
        this(name, 0);
    }
    
    @Override
    public void init() {
        super.init();
        
        // module slots are on the side and so cannot be higher than the block size itself.
        if (moduleSlots > size) {
            Log.info("[Aeyama - WARN] The 'moduleSlots' ("+ moduleSlots +") of the "+ this.name +" block cannot be higher than the block size. Defaulted to the block size ("+ size +")");
            moduleSlots = size;
        }
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        super.drawPlanRegion(plan, list);

        for (int i=0; i < moduleSlots; i++) {
            Draw.rect(new TextureRegion(Core.atlas.find("aeyama-module-slot")), 
            plan.x * tilesize - (plan.block.size / 2f * tilesize - tilesize / 2f) + (i * tilesize),
            plan.y *tilesize + (plan.block.size / 2f * tilesize + tilesize / 2f),
            plan.rotation * 90f);
        }
    }

    public class ModuleableBuild extends Building {
        
        @Override
        public void rotation(int rotation) {
            this.rotation = rotation;
            Log.info(rotation);
        }

        @Override
        public void draw() {
            super.draw();

            for (int i=0; i < moduleSlots; i++) {
                Draw.rect(new TextureRegion(Core.atlas.find("aeyama-module-slot")), 
                          this.x - (this.block.size / 2f * tilesize - tilesize / 2f) + (i * tilesize),
                          this.y + (this.block.size / 2f * tilesize + tilesize / 2f),
                          this.rotation * 90f);
            }
        }
    }
}
