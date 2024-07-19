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
    private TextureRegion moduleSlotTexture;

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
    public void load() {
        super.load();

        moduleSlotTexture = Core.atlas.find("aeyama-module-slot");
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        super.drawPlanRegion(plan, list);

        // '* tilesize' to convert the coordinates to World Unit
        // '+ tilesize / 2f' is to fix a weird offset with plan with a peer size
        float posX = (size % 2 == 0) ? plan.x * tilesize + tilesize / 2f : plan.x * tilesize;
        float posY = (size % 2 == 0) ? plan.y * tilesize + tilesize / 2f : plan.y * tilesize;
        drawModuleSlots(posX, posY, plan.block.size, plan.rotation);
    }

    public void drawModuleSlots(float x, float y, int size, int rotation) {
        float slotPosX = 0f;
        float slotPosY = 0f;

        switch (rotation) {
            case 0: // Right
                slotPosX = x + (size / 2f * tilesize + tilesize / 2f);
                slotPosY = y + (size / 2f * tilesize - tilesize / 2f);
                break;

            case 1: // Up
                slotPosX = x - (size / 2f * tilesize - tilesize / 2f);
                slotPosY = y + (size / 2f * tilesize + tilesize / 2f);
                break;

            case 2: // Left
                slotPosX = x - (size / 2f * tilesize + tilesize / 2f);
                slotPosY = y + (size / 2f * tilesize - tilesize / 2f);
                break;

            case 3: // Down
                slotPosX = x - (size / 2f * tilesize - tilesize / 2f);
                slotPosY = y - (size / 2f * tilesize + tilesize / 2f);
                break;
        
            default: break;
        }

        for (int i=0; i < moduleSlots; i++) {
            float posX = (rotation == 0 || rotation == 2) ? slotPosX : slotPosX + (i * tilesize);
            float posY = (rotation == 1 || rotation == 3) ? slotPosY : slotPosY - (i * tilesize);

            Draw.rect(moduleSlotTexture, posX, posY);
        }
    }

    public class ModuleableBuild extends Building {

        @Override
        public void draw() {
            super.draw();

            drawModuleSlots(this.x, this.y, this.block.size, rotation);
        }
    }
}
