package com.github.kntmr;

import java.util.List;

public class InventorySearch {

    Inventory execute(List<Inventory> targets) {
        return execute1(targets);
    }

    Inventory execute1(List<Inventory> targets) {
        // Stream API を使ってみる

        if (targets.stream().filter(s -> s == Inventory.STOCKED).count() == targets.size()) {
            return Inventory.STOCKED;
        }

        if (targets.stream().filter(s -> s == Inventory.NO_STOCKED).count() == targets.size()) {
            return Inventory.NO_STOCKED;
        }

        if (targets.stream().filter(s -> s == Inventory.NO_HANDLING).count() > 0) {
            return Inventory.NO_HANDLING;
        }

        if (targets.stream().filter(s -> s == Inventory.FEWNESS).count() == targets.size()) {
            return Inventory.FEWNESS;
        }

        return Inventory.OTHER;
    }

}
