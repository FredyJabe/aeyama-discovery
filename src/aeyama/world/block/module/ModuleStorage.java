package aeyama.world.block.module;

import mindustry.gen.*;
import mindustry.world.*;

import aeyama.world.block.*;
import aeyama.world.block.storage.*;
import aeyama.world.block.storage.ModuleableStorageBlock.*;

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
