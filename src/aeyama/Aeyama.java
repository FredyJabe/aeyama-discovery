package aeyama;

import arc.util.*;
import mindustry.mod.*;

import aeyama.content.*;

public class Aeyama extends Mod {

    @Override
    public void init() {
        AeyamaVars.load();
        
        Log.info("Aeyama Reborn!");
    }

    @Override
    public void loadContent() { // The load order is VERY IMPORTANT, don't change it.
        AeyamaAttributes.load();
        
        AeyamaItems.load();
        AeyamaBlocks.load();
    }
}
