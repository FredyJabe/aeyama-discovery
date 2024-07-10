package aeyama.world.block.module;

import arc.struct.*;

import mindustry.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.world.*;

import aeyama.world.block.ModuleableBlock.*;

public class ModuleBlock extends Block {
    public ModuleType type;

    public ModuleBlock(String name, ModuleType type) {
        super(name);

        solid = true;
        update = true;

        if (type != null)
            this.type = type;
    }

    public ModuleBlock(String name) {
        this(name, null);
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

        return tileAround.contains(o -> o.build instanceof ModuleableBuild);
    }

    public class ModuleBuild extends Building {
        
    }
    
}
