package aeyama.content;

import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.production.*;
import mindustry.world.meta.*;

import static mindustry.type.ItemStack.*;

public class AeyamaBlocks {
    public static Block
    basicCutter, basicMiner,

    floorStoneSlate, floorStoneGranite, floorStoneLimestone, floorStoneSandstone,
    floorStoneMarble,

    wallWoodTree;

    public static void load() {
        //#region Production
        basicCutter = new WallCrafter("basic-cutter") {{
            size = 2;
            health = 100;

            attribute = Attribute.get("basic-cutter-resources");

            requirements(Category.production, with(AeyamaItems.wood, 15, AeyamaItems.stone, 5));
        }};
        basicMiner = new AttributeCrafter("basic-miner") {{
            size = 2;
            health = 150;

            attribute = Attribute.get("basic-miner-resources");

            requirements(Category.production, with(AeyamaItems.wood, 30, AeyamaItems.stone, 15));
        }};
        //#endregion

        //#region Environment
        floorStoneSlate = new Floor("floor-stone-slate") {{
            itemDrop = AeyamaItems.stone;
            attributes.set(Attribute.get("basic-miner-resources"), 1f);
        }};
        floorStoneGranite = new Floor("floor-stone-granite") {{
            itemDrop = AeyamaItems.stone;
            attributes.set(Attribute.get("basic-miner-resources"), 1f);
        }};
        floorStoneLimestone = new Floor("floor-stone-limestone") {{
            itemDrop = AeyamaItems.stone;
            attributes.set(Attribute.get("basic-miner-resources"), 1f);
        }};
        floorStoneSandstone = new Floor("floor-stone-sandstone") {{
            itemDrop = AeyamaItems.stone;
            attributes.set(Attribute.get("basic-miner-resources"), 1f);
        }};
        floorStoneMarble = new Floor("floor-stone-marble") {{
            itemDrop = AeyamaItems.stone;
            attributes.set(Attribute.get("basic-miner-resources"), 1f);
        }};

        wallWoodTree = new StaticWall("wall-wood-tree") {{
            itemDrop = AeyamaItems.wood;
            attributes.set(Attribute.get("basic-cutter-resources"), 1f);
        }};
        //#endregion
    }
}
