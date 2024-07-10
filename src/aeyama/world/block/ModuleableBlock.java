package aeyama.world.block;

import arc.*;
import arc.graphics.g2d.*;

import mindustry.gen.*;
import mindustry.world.*;

public class ModuleableBlock extends Block {
    /** The amount of available module slots to the player. */
    public int moduleSlots;

    public ModuleableBlock(String name, int moduleSlots) {
        super(name);
        
        solid = true;
        update = true;
        sync = true;
        rotate = true;
        rotateDraw = true;

        this.moduleSlots = moduleSlots;
    }
    
    public ModuleableBlock(String name) {
        this(name, 0);
    }

    public class ModuleSlot {

    }

    public class ModuleableBuild extends Building {
        
        @Override
        public void draw() {
            super.draw();

            drawModuleSlots();
        }

        //TODO make this works
        public void drawModuleSlots() {
            for (int i=0; i < moduleSlots; i++) {
                Draw.rect(new TextureRegion(Core.atlas.find("module-slot")), this.x + this.block.size, this.y + this.block.size, this.drawrot());
            }
        }
    }
}
