package aeyama.world.block;

import arc.struct.*;
import arc.util.*;

import mindustry.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.world.*;

import aeyama.world.block.ModuleableBlock.*;

public class ModuleBlock extends Block {
    /** Filter for ModuleableBlock, 'universal' for all.  */
    public String tag = "universal";

    public ModuleBlock(String name) {
        super(name);

        solid = true;
        update = true;
    }

    //TODO can be placed around no matter of the "slot" placement
    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        Seq<Tile> tileAround = new Seq<>();

        /* Thanks to @_agzam_ */
        final int to = (this.size + 2) / 2;
        final int from = to + 1 - (this.size + 2);

        for (int ty = from; ty <= to; ty++) {
            for (int tx = from; tx <= to; tx++) {
                //Skip corners
                if ((ty == from || ty == to) && (tx == from ||tx == to))
                    continue;
                
                //Only check the side of the block
                if ((ty == from || ty == to || tx == from || tx == to)) {
                    int xx = tile.x + tx;
                    int yy = tile.y + ty;
                    tileAround.add(Vars.world.tile(xx, yy));
                }
            }
        }

        return tileAround.contains(o -> o.build instanceof ModuleableBuild moduleableBuild
                                        && (((ModuleableBlock)moduleableBuild.block).tags.contains(tag) || tag == "universal")
                                        && moduleableBuild.modules.size < ((ModuleableBlock)moduleableBuild.block).moduleSlots);
    }

    public class ModuleBuild extends Building {
        public @Nullable Building linkedBuild;

        public void effect(Building build, Block block) {}

        @Override
        public void onRemoved() {
            if (linkedBuild != null)
                ((ModuleableBuild)linkedBuild).modules.remove(this);

            super.onRemoved();
        }

        @Override
        public void drawSelect() {
            if (linkedBuild != null)
                linkedBuild.drawSelect();
        }
    }
    
}
