package aeyama.world.blocks.module;

import mindustry.gen.*;
import mindustry.world.*;

import aeyama.world.blocks.*;
import aeyama.world.blocks.storage.ModuleableStorageBlock.*;

public class ModuleStorage extends ModuleBlock {

    public ModuleStorage(String name) {
        super(name);
    }

    public class ModuleStorageBuild extends ModuleBuild {

        @Override
        public void effect(Building build, Block block) {
            if (build instanceof ModuleableStorageBuild storageBuild) {
                storageBuild.storageItemCapacity *= 1.1;
            }
        }
    }
}
