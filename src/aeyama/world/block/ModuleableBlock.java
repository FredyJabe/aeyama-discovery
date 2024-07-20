package aeyama.world.block;

import arc.*;
import arc.graphics.g2d.*;
import arc.struct.*;
import arc.util.*;

import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.draw.*;

import aeyama.world.block.ModuleBlock.*;

import static mindustry.Vars.tilesize;

public class ModuleableBlock extends Block {
    /** The amount of available module slots to the player. */
    public int moduleSlots;
    /** Filter the modules this block accept. */
    public Seq<String> tags = new Seq<String>();
    private TextureRegion moduleSlotTexture;

    public DrawBlock drawer = new DrawDefault();

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

        drawer.load(this);
        moduleSlotTexture = Core.atlas.find("aeyama-module-slot");
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        drawer.drawPlan(this, plan, list);;

        drawModuleSlots(plan.getX(), plan.getY(), plan.block.size, plan.rotation);
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

    @Override
    protected TextureRegion[] icons() {
        return drawer.finalIcons(this);
    }

    @Override
    public void getRegionsToOutline(Seq<TextureRegion> out) {
        drawer.getRegionsToOutline(this, out);
    }

    public class ModuleableBuild extends Building {
        public Seq<ModuleBuild> modules = new Seq<ModuleBuild>();

        @Override
        public void onProximityUpdate() {
            proximity.each(this::isModule, module -> {
                ((ModuleBuild)module).linkedBuild = this;
                modules.add((ModuleBuild)module);
            });

            super.onProximityUpdate();
        }

        public boolean isModule(Building tile) {
            return isModule(this, tile);
        }
        
        public boolean isModule(Building build, Building tile) {
            return tile instanceof ModuleBuild module && (module.linkedBuild == this || module.linkedBuild == null)
                   && modules.size < moduleSlots && !modules.contains(module);
        }

        @Override
        public void draw() {
            drawer.draw(this);

            drawModuleSlots(this.x, this.y, this.block.size, rotation);
        }

        @Override
        public void drawLight() {
            super.drawLight();
            drawer.drawLight(this);
        }
        
        @Override
        public void drawSelect() {
            Drawf.selected(this, Pal.accent);
            for (Building module : modules) {
                Drawf.selected(module, Pal.accent);
            }
        }
    }
}
